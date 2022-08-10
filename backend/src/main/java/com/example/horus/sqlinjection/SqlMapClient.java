package com.example.horus.sqlinjection;

import com.example.horus.sqlinjection.request.SetOptionsRequest;
import com.example.horus.sqlinjection.request.StartScanSqlMapRequest;
import com.example.horus.sqlinjection.response.CreateTaskResponse;
import com.example.horus.sqlinjection.response.ScanLogResponse;
import com.example.horus.sqlinjection.response.ScanStatusResponse;
import com.example.horus.sqlinjection.response.StartScanResponse;
import com.google.gson.JsonObject;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface SqlMapClient {

    @RequestLine("GET /task/new")
    CreateTaskResponse createNewTask();

    @RequestLine("POST /scan/{id}/start")
    @Headers("Content-Type: application/json")
    StartScanResponse startScan(@Param("id") String id, StartScanSqlMapRequest startScanSqlMapRequest);

    @RequestLine("GET /scan/{id}/status")
    ScanStatusResponse getStatus(@Param("id") String id);

    @RequestLine("POST /option/{id}/set")
    @Headers("Content-Type: application/json")
    void setOptions(@Param("id") String id, SetOptionsRequest setOptionsRequest);

    @RequestLine("GET /scan/{id}/log")
    ScanLogResponse getLogs(@Param("id") String id);

    @RequestLine("GET /scan/{id}/data")
    Object getData(@Param("id") String id);

}
