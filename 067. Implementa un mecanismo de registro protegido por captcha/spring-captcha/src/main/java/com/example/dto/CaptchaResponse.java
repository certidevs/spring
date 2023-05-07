package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class CaptchaResponse {

    private Boolean success;
    private LocalDateTime timestamp;
    private String hostname;
    @JsonProperty("error-codes")
    private List<String> errorCodes;

    public CaptchaResponse() {
    }

    public CaptchaResponse(Boolean success, LocalDateTime timestamp, String hostname, List<String> errorCodes) {
        this.success = success;
        this.timestamp = timestamp;
        this.hostname = hostname;
        this.errorCodes = errorCodes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Override
    public String toString() {
        return "CaptchaResponse{" +
                "success=" + success +
                ", timestamp=" + timestamp +
                ", hostname='" + hostname + '\'' +
                ", errorCodes=" + errorCodes +
                '}';
    }
}
