package com.adde.configs;

import com.adde.model.enums.SystemError;
import com.adde.model.exceptions.ErrorResult;
import com.adde.model.exceptions.SystemException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleUnHandledException(Exception exception, HttpServletResponse response) {
        log.error(exception.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new ErrorResult(SystemError.SERVER_ERROR, 5000, "server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResult> handleSystemException(SystemException exception, HttpServletResponse response) {
        response.setStatus(exception.getErrorCode());
        return new ResponseEntity<>(new ErrorResult(exception), HttpStatus.valueOf(exception.getError().getValue()));
    }

}
