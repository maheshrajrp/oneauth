package com.assessment.core.exception;

import com.assessment.core.dto.generic.GenericErrorRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = CoreException.class)
    public ResponseEntity<GenericErrorRes> handleCoreException(CoreException exception) {
        log.error("Core Exception Occurred", exception);
        return new ResponseEntity<>(new GenericErrorRes(exception), exception.getCoreError().getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<GenericErrorRes> handleGenericException(Exception exception) {
        log.error("Exception Occurred", exception);
        return new ResponseEntity<>(new GenericErrorRes(500, "Something went wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GenericErrorRes> handleMethodException(MethodArgumentNotValidException exception) {
        log.error("Exception Occurred", exception);
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new GenericErrorRes(400, errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<GenericErrorRes> handleRuntimeException(RuntimeException exception) {
        log.error("Exception Occurred", exception);
        return new ResponseEntity<>(new GenericErrorRes(500, "Something went wrong while performing the action."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
