package com.example.horus.sqlinjection.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StartScanRequest {

    private String url;
    private String cookie;
    private boolean getBanner;
    private boolean getTables;
    private boolean getColumns;
    private boolean getPasswordHashes;
    private boolean getUsers;
    private boolean getRoles;

}
