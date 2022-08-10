package com.example.horus.sqlinjection;

import com.example.horus.sqlinjection.request.SetOptionsRequest;
import com.example.horus.sqlinjection.request.StartScanRequest;
import com.example.horus.sqlinjection.request.StartScanSqlMapRequest;
import com.example.horus.sqlinjection.response.CreateTaskResponse;
import com.example.horus.sqlinjection.response.ScanLogResponse;
import com.example.horus.sqlinjection.response.ScanStatusResponse;
import com.example.horus.sqlinjection.response.StartScanResponse;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlInjectionService {

    private final SqlmapClientConfig sqlmapClientConfig;

    @Autowired
    public SqlInjectionService(SqlmapClientConfig sqlmapClientConfig) {
        this.sqlmapClientConfig = sqlmapClientConfig;
    }

    public CreateTaskResponse createNewTask() {
        return sqlmapClientConfig.getSqlMapClient().createNewTask();
    }

    public StartScanResponse startScan(StartScanRequest startScanRequest) {
        final CreateTaskResponse newTask = createNewTask();
        setOptions(newTask.getTaskid(), SetOptionsRequest.of(startScanRequest));
        final StartScanResponse startScanResponse = sqlmapClientConfig.getSqlMapClient().startScan(newTask.getTaskid(), new StartScanSqlMapRequest(startScanRequest.getUrl()));
        startScanResponse.setTaskId(newTask.getTaskid());
        return startScanResponse;
    }

    public ScanStatusResponse getStatus(String taskId) {
        return sqlmapClientConfig.getSqlMapClient().getStatus(taskId);
    }

    public void setOptions(String taskId, SetOptionsRequest setOptionsRequest) {
        sqlmapClientConfig.getSqlMapClient().setOptions(taskId, setOptionsRequest);
    }

    public ScanLogResponse getLogs(String taskId) {
       return sqlmapClientConfig.getSqlMapClient().getLogs(taskId);
    }

    public Object getData(String taskId) {
        return sqlmapClientConfig.getSqlMapClient().getData(taskId);
    }

}
