package az.elvinali.ps.api.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodeEnum {
    VALIDATION(410, "Validation error"),
    UNKNOWN(403, "Unknown error");

    private final int code;
    private final String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
