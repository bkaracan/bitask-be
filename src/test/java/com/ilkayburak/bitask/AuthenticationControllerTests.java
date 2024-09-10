package com.ilkayburak.bitask;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilkayburak.bitask.auth.AuthenticationService;
import com.ilkayburak.bitask.dto.RegistrationRequestDTO;
import com.ilkayburak.bitask.dto.RegistrationResponseDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.email.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private AuthenticationService authenticationService;

  @MockBean private EmailService emailService;

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

    // Mock bağımlılıkları ayarla
    Mockito.doNothing().when(emailService).sendEmail(
        Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    // Mock sonucu
    Mockito.when(authenticationService.register(Mockito.any(RegistrationRequestDTO.class)))
        .thenReturn(
            new ResponsePayload<RegistrationResponseDTO>(
                ResponseEnum.OK, "Registration successful", null));

    // POST isteğini gönderelim ve yanıtı kontrol edelim
    mockMvc
        .perform(
            post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Registration successful"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.responseEnum")
                .value("OK")) // $.status yerine $.responseEnum
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200)) // İsteğe göre eklenebilir
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.success").value(true)); // İsteğe göre eklenebilir
  }
}
