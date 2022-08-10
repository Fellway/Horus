package com.example.horus.api;

import com.example.horus.datacollector.DataCollectorService;
import com.example.horus.model.request.FormFromWebsiteRequest;
import com.example.horus.model.response.FormFromWebsiteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/data-collector")
public class DataCollectorController {

    private final DataCollectorService dataCollectorService;

    @Autowired
    public DataCollectorController(DataCollectorService dataCollectorService) {
        this.dataCollectorService = dataCollectorService;
    }

    @GetMapping("forms")
    public FormFromWebsiteResponse getFormsFromWebsite(FormFromWebsiteRequest formFromWebsiteRequest) throws MalformedURLException {
        return dataCollectorService.getFormsFromWebsite(formFromWebsiteRequest);
    }

}
