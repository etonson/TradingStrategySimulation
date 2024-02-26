package com.example.demo.enums;

public enum ETLError {
    
    SUCCESS("0", "success"),
    NOT_FIND_FILE("1", "找不到檔案");

    String code;
    String message;

    ETLError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}