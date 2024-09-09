package com.ilkayburak.bitask.exception;

import static com.ilkayburak.bitask.enumarations.core.ResponseEnum.*;

import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import jakarta.mail.MessagingException;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(LockedException exp) {

        return new ResponsePayload<>(UNAUTHORIZED,
                ExceptionResponseDTO.builder()
                        .businessErrorCode(MessageEnum.ACCOUNT_LOCKED.getKod())
                        .businessExceptionDescription(MessageEnum.ACCOUNT_LOCKED.getMessage())
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(DisabledException exp) {

        return new ResponsePayload<>(UNAUTHORIZED,
                ExceptionResponseDTO.builder()
                        .businessErrorCode(MessageEnum.ACCOUNT_DISABLED.getKod())
                        .businessExceptionDescription(MessageEnum.ACCOUNT_DISABLED.getMessage())
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(BadCredentialsException exp) {

        return new ResponsePayload<>(UNAUTHORIZED,
                ExceptionResponseDTO.builder()
                        .businessErrorCode(MessageEnum.BAD_CREDENTIALS.getKod())
                        .businessExceptionDescription(MessageEnum.BAD_CREDENTIALS.getMessage())
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(MessagingException exp) {

        return new ResponsePayload<>(INTERNAL_SERVER_ERROR,
                ExceptionResponseDTO.builder()
                        .error(exp.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(MethodArgumentNotValidException exp) {
        Set<String> errors = exp.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        return new ResponsePayload<>(BADREQUEST,
                ExceptionResponseDTO.builder()
                        .validationErrors(errors)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponsePayload<ExceptionResponseDTO> handleException(Exception exp) {

       exp.printStackTrace();

       return new ResponsePayload<>(INTERNAL_SERVER_ERROR,
               ExceptionResponseDTO.builder()
                       .businessExceptionDescription("Internal error, please contact the admin!")
                       .error(exp.getMessage())
                       .build());
    }


}
