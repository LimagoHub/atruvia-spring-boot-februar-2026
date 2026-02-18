package de.fi.webapp.presentation.errorhandler;


import de.fi.webapp.service.AlreadyExistsException;
import de.fi.webapp.service.NotFoundException;
import de.fi.webapp.service.PersonenServiceException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.rmi.AlreadyBoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ":" + x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);

        // WICHTIG !!!!!!
        logger.error("Upps", ex);

        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(PersonenServiceException.class)
    public @Nullable ResponseEntity<Object> handlePersonenServiceException(PersonenServiceException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("message", ex.getMessage());
        body.put("type", ex.getClass().getSimpleName());
        logger.error("Upps", ex);
        if(ex.getMessage().equals("Antipath!")) {
            return ResponseEntity.badRequest().body(body);
        }


        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("type", ex.getClass().getSimpleName());
        body.put("xyz", "abc");
        // Loggen !!!!!!
        logger.error("Upps", ex);
        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {


        // Loggen !!!!!!
        logger.warn("Upps", ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExistsException ex, WebRequest request) {


        // Loggen !!!!!!
        logger.warn("Upps", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(IdMismatchException.class)
    public ResponseEntity<Object> handleIdMismatchException(IdMismatchException ex, WebRequest request) {
        // Loggen !!!!!!
        logger.warn("Upps", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
