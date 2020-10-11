package com.rfbsoft.nuxt.dto.responses;

import java.util.Date;

public class ApiResponse {
    public Boolean succes;
    public Object data;
    public int statusCode;
    public Object error;
    public Date date = new Date();

    public ApiResponse(Boolean succes, Object data, int statusCode, Object error) {
        this.succes = succes;
        this.data = data;
        this.statusCode = statusCode;
        this.error = error;
    }
}
