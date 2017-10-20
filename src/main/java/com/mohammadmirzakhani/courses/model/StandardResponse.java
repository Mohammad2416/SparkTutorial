package com.mohammadmirzakhani.courses.model;

import com.google.gson.JsonElement;
import com.mohammadmirzakhani.courses.enams.StatusResponse;

/**
 * Created by Mohammad on 10/20/17.
 */
public class StandardResponse {

    private StatusResponse status;
    private String message;
    private JsonElement data;


    public StandardResponse(StatusResponse status) {
        this.status = status;
    }

    public StandardResponse(StatusResponse status, String message) {
        this.status = status;
        this.message = message;
    }


    public StandardResponse(StatusResponse status, JsonElement jsonElement) {
        this.status = status;
        this.data = jsonElement;
    }
}
