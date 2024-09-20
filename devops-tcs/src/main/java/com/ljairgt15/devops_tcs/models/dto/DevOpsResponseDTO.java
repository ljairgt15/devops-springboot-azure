package com.ljairgt15.devops_tcs.models.dto;


public class DevOpsResponseDTO {
    private String message;

    // Constructor
    public DevOpsResponseDTO(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
