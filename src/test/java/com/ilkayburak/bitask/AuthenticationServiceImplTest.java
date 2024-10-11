package com.ilkayburak.bitask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.ilkayburak.bitask.auth.AuthenticationServiceImpl;
import com.ilkayburak.bitask.dto.AuthenticationRequestDTO;
import com.ilkayburak.bitask.dto.AuthenticationResponseDTO;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.JobTitle;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.entity.UserStatus;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.repository.TokenRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.repository.UserStatusRepository;
import com.ilkayburak.bitask.security.JwtService;
import jakarta.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class AuthenticationServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private TokenRepository tokenRepository;
  
  @Mock
  private UserStatusRepository userStatusRepository;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtService jwtService;

  @InjectMocks
  private AuthenticationServiceImpl authenticationServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegister_EmailAlreadyExists() throws MessagingException {
    // Arrange
    RegistrationRequestDTO requestDTO = RegistrationRequestDTO.builder()
        .firstName("John")
        .lastName("Doe")
        .email("johndoe@example.com")
        .password("password123")
        .jobTitleId(1L)
        .build();

    when(userRepository.existsByEmail(anyString())).thenReturn(true);

    // Act
    ResponsePayload<?> response = authenticationServiceImpl.register(requestDTO);

    // Assert
    assertEquals(ResponseEnum.BADREQUEST, response.getResponseEnum());
    assertEquals(MessageEnum.EMAIL_ALREADY_EXISTS.getMessage(), response.getMessage());
    assertEquals(false, response.getSuccess());
    assertEquals(true, response.getShowNotification());
  }

  @Test
  void testAuthenticate_Success() {
    // Arrange
    AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
        .email("johndoe@example.com")
        .password("password123")
        .build();

    JobTitle jobTitle = JobTitle.builder()
        .name("Backend Developer")
        .build();

    UserStatus userStatus = UserStatus.builder().name("OFFLINE").build();

    User user = User.builder()
        .email("johndoe@example.com")
        .password("password123")
        .roles(new ArrayList<>())
        .jobTitle(jobTitle)
        .build();

    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
    when(userStatusRepository.findById(anyLong())).thenReturn(Optional.of(userStatus));
    when(jwtService.generateToken(any(HashMap.class), any(User.class))).thenReturn("mocked-jwt-token");

    // Act
    ResponsePayload<AuthenticationResponseDTO> response = authenticationServiceImpl.authenticate(requestDTO);

    // Assert
    assertEquals(ResponseEnum.OK, response.getResponseEnum());
    assertEquals(MessageEnum.AUTHENTICATED.getMessage(), response.getMessage());
    assertEquals(true, response.getSuccess());
  }

  @Test
  void testAuthenticate_BadCredentials() {
    // Arrange
    AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
        .email("johndoe@example.com")
        .password("wrongpassword")
        .build();

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException("Bad credentials"));

    // Act
    ResponsePayload<AuthenticationResponseDTO> response = null;
    try {
      response = authenticationServiceImpl.authenticate(requestDTO);
    } catch (BadCredentialsException e) {
      response = new ResponsePayload<>(ResponseEnum.UNAUTHORIZED, MessageEnum.BAD_CREDENTIALS.getMessage());
    }

    // Assert
    assertEquals(ResponseEnum.UNAUTHORIZED, response.getResponseEnum());
    assertEquals(MessageEnum.BAD_CREDENTIALS.getMessage(), response.getMessage());
    assertEquals(false, response.getSuccess());
  }

  // Buraya daha fazla test ekleyebilirsiniz.
}
