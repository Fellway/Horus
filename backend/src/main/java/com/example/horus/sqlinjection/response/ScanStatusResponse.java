package com.example.horus.sqlinjection.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ScanStatusResponse {

    private final Boolean success;
    private final String message;
    private final String status;
    private final Integer returncode;

}
