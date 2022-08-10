package com.example.horus.api;

import com.example.horus.crawler.CrawlerStatistics;
import com.example.horus.crawler.ScanWebsiteRequest;
import com.example.horus.crawler.ScanWebsiteResponse;
import com.example.horus.crawler.dynamic.DynamicWebsiteScraper;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

    private final DynamicWebsiteScraper dynamicWebsiteScraper;

    @GetMapping()
    public ScanWebsiteResponse getWebsiteSubLinks(ScanWebsiteRequest scanWebsiteRequest) throws IOException {
        final CrawlerStatistics crawlerStatistics = dynamicWebsiteScraper.scrapData(scanWebsiteRequest.getScanDepth(), scanWebsiteRequest.getWebsiteUrl());
        return ScanWebsiteResponse.of(crawlerStatistics);
    }

}
