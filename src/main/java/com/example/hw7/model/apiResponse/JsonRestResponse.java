package com.example.hw7.model.apiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonRestResponse {

    private boolean success;
    private String massage;
    private Object data;
}
