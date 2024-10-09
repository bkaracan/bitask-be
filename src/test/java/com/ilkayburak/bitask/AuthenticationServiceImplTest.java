package com.ilkayburak.bitask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.ilkayburak.bitask.auth.AuthenticationServiceImpl;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthenticationServiceImplTest {

  @Mock
  private UserRepository userRepository;

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

  // Buraya daha fazla test ekleyebilirsiniz.
}
