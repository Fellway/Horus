package com.example.horus.api;

import com.example.horus.model.request.FormVulnerabilitiesRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checker")
public class FormCheckerController {

    @GetMapping
    public void getFormVulnerabilities(@RequestBody FormVulnerabilitiesRequest formVulnerabilitiesRequest) {

    }

}
