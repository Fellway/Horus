package com.example.horus.quickscan.validator;

import com.example.horus.vulnerabilities.VulnerabilitiesLib;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileAbsentValidator {

    public List<VulnerabilitiesLib> validate(String scheme, String url) throws IOException, InterruptedException {
        List<VulnerabilitiesLib> vulnerabilitiesLibs = new ArrayList<>();
        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpRequest robotRequest = HttpRequest.newBuilder(URI.create(scheme + url + "/robots.txt"))
                .build();
        final HttpRequest securityRequest = HttpRequest.newBuilder(URI.create(scheme + url + "/security.txt"))
                .build();

        final HttpResponse<String> robotResponse = httpClient.send(robotRequest, HttpResponse.BodyHandlers.ofString());

        if(robotResponse.statusCode() == 200) {
            vulnerabilitiesLibs.add(VulnerabilitiesLib.ROBOTS_FILE_FOUND);
        }

        final HttpResponse<String> securityResponse = httpClient.send(securityRequest, HttpResponse.BodyHandlers.ofString());
        if(securityResponse.statusCode() != 200) {
            vulnerabilitiesLibs.add(VulnerabilitiesLib.SECURITY_FILE_NOT_FOUND);
        }
        return vulnerabilitiesLibs;
    }

}
