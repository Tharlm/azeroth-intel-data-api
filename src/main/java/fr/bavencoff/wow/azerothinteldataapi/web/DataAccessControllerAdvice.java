package fr.bavencoff.wow.azerothinteldataapi.web;

import fr.bavencoff.wow.apis.exceptions.realms.RealmAlreadyExistException;
import fr.bavencoff.wow.apis.exceptions.realms.RealmNotFoundException;
import fr.bavencoff.wow.apis.exceptions.regions.RegionNotFoundException;
import fr.bavencoff.wow.web.exceptions.realms.RealmAlreadyExistResponseException;
import fr.bavencoff.wow.web.exceptions.realms.RealmNotFoundResponseException;
import fr.bavencoff.wow.web.exceptions.regions.RegionNotFoundResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class DataAccessControllerAdvice extends ResponseEntityExceptionHandler {

    private final Clock clock;

    @Autowired
    public DataAccessControllerAdvice(final Clock clock) {
        this.clock = clock;
    }


    @ExceptionHandler({
            RegionNotFoundException.class,
            RealmNotFoundException.class,
            RealmAlreadyExistException.class
    })
    public ResponseEntity<Object> handleCustomException(
            Exception e,
            WebRequest request
    ) throws Exception {
        if (e instanceof RegionNotFoundException subEx) {
            return handleException(new RegionNotFoundResponseException(subEx), request);
        } else if (e instanceof RealmNotFoundException subEx) {
            return handleException(new RealmNotFoundResponseException(subEx), request);
        } else if (e instanceof RealmAlreadyExistException subEx) {
            return handleException(new RealmAlreadyExistResponseException(subEx), request);
        } else {
            throw e;
        }
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<Object> catchUnexpectedRuntimeException(
            Exception exception,
            WebRequest request
    ) {
        log.warn("catchUnexpectedRuntimeException is throwed to client for exception", exception);
        ProblemDetail body = createProblemDetail(
                exception,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error occurs",
                null,
                null,
                request
        );
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (!(body instanceof ProblemDetail p)) {
            return super.createResponseEntity(body, headers, statusCode, request);
        }

        // Add custom details
        p.setProperty("timestamp", Instant.now(clock));

        return super.createResponseEntity(body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.info("Call handleExceptionInternal with parameters: Exception ex = {}, Object body = {}, HttpHeaders headers = {}, HttpStatusCode statusCode = {}, WebRequest request = {}", ex, body, headers, statusCode, request);
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
