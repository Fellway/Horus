package com.example.horus.api;

import com.example.horus.sqlinjection.SqlInjectionService;
import com.example.horus.sqlinjection.request.StartScanRequest;
import com.example.horus.sqlinjection.response.ScanLogResponse;
import com.example.horus.sqlinjection.response.ScanStatusResponse;
import com.example.horus.sqlinjection.response.StartScanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql-injection")
public class SqlInjectionController {

    private final SqlInjectionService sqlInjectionService;

    @Autowired
    public SqlInjectionController(SqlInjectionService sqlInjectionService) {
        this.sqlInjectionService = sqlInjectionService;
    }

    @PostMapping ("/start-scan")
    public StartScanResponse startScan(@RequestBody StartScanRequest startScanRequest) {
        return sqlInjectionService.startScan(startScanRequest);
    }

    @RequestMapping("/status/{id}")
    public ScanStatusResponse getStatus(@PathVariable String id) {
        return sqlInjectionService.getStatus(id);
    }

    @RequestMapping("/logs/{id}")
    public ScanLogResponse getLogs(@PathVariable String id) {
        return sqlInjectionService.getLogs(id);
    }

    @RequestMapping("/data/{id}")
    public Object getData(@PathVariable String id) {
        return sqlInjectionService.getData(id);
    }

}
