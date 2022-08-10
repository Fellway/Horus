package com.example.horus.api;

import com.example.horus.quickscan.request.QuickScanRequest;
import com.example.horus.quickscan.response.QuickScanResponse;
import com.example.horus.quickscan.service.QuickScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/quick-scan")
public class QuickScanController {

    private final QuickScanService quickScanService;

    @Autowired
    public QuickScanController(QuickScanService quickScanService) {
        this.quickScanService = quickScanService;
    }

    @GetMapping
    public QuickScanResponse getQuickScan(QuickScanRequest quickScanRequest) throws IOException, InterruptedException {
        return QuickScanResponse.of(quickScanService.doScan(quickScanRequest));
    }

}
