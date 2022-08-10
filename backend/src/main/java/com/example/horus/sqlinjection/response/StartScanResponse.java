package com.example.horus.sqlinjection.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StartScanResponse {

    private Boolean success;
    private Integer engineid;
    private String message;
    private String taskId;

}
