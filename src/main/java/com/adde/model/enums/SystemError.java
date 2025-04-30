package com.adde.model.enums;


import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public enum SystemError {

    SERVER_ERROR(HttpServletResponse.SC_SERVICE_UNAVAILABLE),
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST);

    private final Integer value;

    SystemError(Integer value) {
        this.value = value;
    }

}
