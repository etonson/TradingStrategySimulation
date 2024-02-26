package com.example.demo.exception;

import org.json.JSONObject;

import com.example.demo.enums.ETLError;


public class ReferenceException extends Exception {
    private static final long serialVersionUID = 1801990343177209389L;
    private JSONObject toJson = null;
    private String rc;

    public ReferenceException(String rc, String rm) {
        super(rm);
        this.rc = rc;
        toJson = new JSONObject();
        toJson.put("rc", this.rc);
        toJson.put("rm", rm);
    }

    public ReferenceException(ETLError err) {
        super(err.message());
        toJson = new JSONObject();
        toJson.put("rc", err.code());
        toJson.put("rm", err.message());
    }

    public String getRc() {
        return rc;
    }

    public String getRm() {
        return super.getMessage();
    }

    public JSONObject toJson() {
        return toJson;
    }
}
