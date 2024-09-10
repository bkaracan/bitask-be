package com.ilkayburak.bitask;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilkayburak.bitask.auth.AuthenticationService;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationResponseDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.email.EmailService;
import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.Role;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.UserDTOMapper;
import com.ilkayburak.bitask.repository.JobTitleRepository;
import com.ilkayburak.bitask.repository.RoleRepository;
import com.ilkayburak.bitask.repository.TokenRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerRegisterTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private AuthenticationService authenticationService;

  @MockBean private RoleRepository roleRepository;

  @MockBean private JobTitleRepository jobTitleRepository;

  @MockBean private UserDTOMapper userDTOMapper;

  @MockBean private PasswordEncoder passwordEncoder;

  @MockBean private UserRepository userRepository;

  @MockBean private EmailService emailService;

  @MockBean private TokenRepository tokenRepository;

  @MockBean private JavaMailSender javaMailSender;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testRegisterEndpoint() throws Exception {
    // Test için bir RegistrationRequestDTO nesnesi oluşturalım
    RegistrationRequestDTO request =
        RegistrationRequestDTO.builder()
            .firstName("John")
            .lastName("Doe")
            .email("johndoe@example.com")
            .password("password123")
            .jobTitleId(1L)
            .build();

    // Mock rolleri
    Mockito.when(roleRepository.findByName("USER"))
        .thenReturn(java.util.Optional.of(Role.builder().id(1L).name("USER").build()));

    // Mock job title
    Mockito.when(jobTitleRepository.findById(1L))
        .thenReturn(java.util.Optional.of(JobTitle.builder().id(1L).name("Backend Developer").build()));

    // Mock user mapping
    User mockUser = new User();
    Mockito.when(userDTOMapper.mapForRegistration(Mockito.any(), Mockito.any()))
        .thenReturn(mockUser);

    // Mock password encoding
    Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

    // Mock user save
    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

    // Mock email service - JavaMailSender bağımlılığıyla birlikte
    Mockito.doNothing().when(javaMailSender).send((MimeMessage) Mockito.any());  // JavaMailSender send methodunu mockla
    Mockito.doNothing().when(emailService)
        .sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    // Mock token repository save
    Mockito.when(tokenRepository.save(Mockito.any())).thenReturn(null);

    // Mock authentication service response
    Mockito.when(authenticationService.register(Mockito.any(RegistrationRequestDTO.class)))
        .thenReturn(
            new ResponsePayload<>(
                ResponseEnum.OK,
                "The registration has been completed! Please check your email to activate your account.",
                RegistrationResponseDTO.builder().build()));

    // POST isteğini gönderelim ve yanıtı kontrol edelim
    mockMvc
        .perform(
            post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The registration has been completed! Please check your email to activate your account."))
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseEnum").value("OK"));
  }
}
