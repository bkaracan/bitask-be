package com.ilkayburak.bitask.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;

@Service
public class JwtKeyService {

  private String secretKey;
  private static final String KEY_FILE_PATH = "jwtSecret.key";  // Anahtarın saklanacağı dosya yolu

  @PostConstruct
  public void init() {
    File keyFile = new File(KEY_FILE_PATH);
    if (keyFile.exists()) {
      try {
        // Mevcut key'i dosyadan oku
        this.secretKey = new String(Files.readAllBytes(Paths.get(KEY_FILE_PATH)));
      } catch (IOException e) {
        throw new RuntimeException("Could not read JWT secret key from file", e);
      }
    } else {
      // Yeni bir key oluştur ve dosyaya yaz
      Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
      this.secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
      try (FileWriter writer = new FileWriter(KEY_FILE_PATH)) {
        writer.write(this.secretKey);
      } catch (IOException e) {
        throw new RuntimeException("Could not write JWT secret key to file", e);
      }
    }
  }

  public String getSecretKey() {
    return this.secretKey;
  }
}
