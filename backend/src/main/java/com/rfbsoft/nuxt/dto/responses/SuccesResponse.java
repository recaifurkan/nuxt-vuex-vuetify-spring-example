package com.rfbsoft.nuxt.dto.responses;

public class SuccesResponse extends ApiResponse {
    public SuccesResponse(Object data) {
        super(true, data, 200, null);
    }
}
