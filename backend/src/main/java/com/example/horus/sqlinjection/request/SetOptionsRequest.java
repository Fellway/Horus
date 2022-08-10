package com.example.horus.sqlinjection.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SetOptionsRequest {

    private final String cookie;
    private final boolean getBanner;
    private final boolean getTables;
    private final boolean getColumns;
    private final boolean getPasswordHashes;
    private final boolean getUsers;
    private final boolean getRoles;


    public static SetOptionsRequest of(StartScanRequest startScanRequest) {
        return SetOptionsRequest
                .builder()
                .cookie(startScanRequest.getCookie())
                .getBanner(startScanRequest.isGetBanner())
                .getTables(startScanRequest.isGetTables())
                .getColumns(startScanRequest.isGetColumns())
                .getPasswordHashes(startScanRequest.isGetColumns())
                .getUsers(startScanRequest.isGetUsers())
                .getRoles(startScanRequest.isGetRoles())
                .build();
    }
}
