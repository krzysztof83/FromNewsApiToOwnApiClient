package com.czechowski.fromnewsapitoownapiclient.model.error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class BadRequestError {

    HttpStatus status;
    String timestamp;
    String message;
    String debugMessage;
    Map<String, Set<String>> additionalInfo = new HashMap();

    public BadRequestError() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public BadRequestError setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public BadRequestError setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BadRequestError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public BadRequestError setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }

    public Map<String, Set<String>> getAdditionalInfo() {
        return additionalInfo;
    }

    public BadRequestError setAdditionalInfo(Map<String, Set<String>> additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }
}
