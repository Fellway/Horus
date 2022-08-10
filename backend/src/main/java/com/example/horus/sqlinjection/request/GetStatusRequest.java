package com.example.horus.sqlinjection.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetStatusRequest {

    private String taskid;

}
