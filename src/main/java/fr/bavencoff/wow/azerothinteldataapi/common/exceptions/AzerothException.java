package fr.bavencoff.wow.azerothinteldataapi.common.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class AzerothException extends ErrorResponseException {

    public AzerothException(HttpStatusCode status) {
        super(status);
    }

    public AzerothException(HttpStatusCode status, Throwable cause) {
        super(status, cause);
    }

    public AzerothException(HttpStatusCode status, ProblemDetail body, Throwable cause) {
        super(status, body, cause);
    }

    public AzerothException(HttpStatusCode status, ProblemDetail body, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, body, cause, messageDetailCode, messageDetailArguments);
    }
}
