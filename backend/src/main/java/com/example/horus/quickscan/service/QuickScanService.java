package com.example.horus.quickscan.service;

import com.example.horus.quickscan.request.QuickScanRequest;
import com.example.horus.quickscan.validator.FileAbsentValidator;
import com.example.horus.quickscan.validator.HeaderValidator;
import com.example.horus.quickscan.validator.WebsiteAccessibilityValidator;
import com.example.horus.vulnerabilities.VulnerabilitiesLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickScanService {

    private final HeaderValidator headerValidator;
    private final FileAbsentValidator fileAbsentValidator;
    private final WebsiteAccessibilityValidator websiteAccessibilityValidator;

    @Autowired
    public QuickScanService(HeaderValidator headerValidator, FileAbsentValidator fileAbsentValidator, WebsiteAccessibilityValidator websiteAccessibilityValidator) {
        this.headerValidator = headerValidator;
        this.fileAbsentValidator = fileAbsentValidator;
        this.websiteAccessibilityValidator = websiteAccessibilityValidator;
    }

    public List<VulnerabilitiesLib> doScan(QuickScanRequest quickScanRequest) throws IOException, InterruptedException {
        List<VulnerabilitiesLib> finalTestResults = new ArrayList<>();
        final HttpClient httpClient = HttpClient.newHttpClient();
        final URI uri = URI.create(quickScanRequest.getWebsiteUrl());

        final HttpRequest request = HttpRequest.newBuilder(uri)
                .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        finalTestResults.addAll(headerValidator.validate(response.headers()));
        finalTestResults.addAll(websiteAccessibilityValidator.validate(response.statusCode()));
        finalTestResults.addAll(fileAbsentValidator.validate(uri.getScheme() + "://", uri.getHost()));


        return finalTestResults;
    }

}
