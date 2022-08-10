package com.example.horus.sqlinjection.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateTaskResponse {

    private Boolean success;
    private String taskid;

}
