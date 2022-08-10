package com.example.horus.crawler;

import lombok.Getter;

import java.util.Set;

@Getter
public class ScanWebsiteResponse {

    private Set<String> urls;
    private Set<String> scripts;
    private Set<String> links;

    public static ScanWebsiteResponse of(CrawlerStatistics crawlerStatistics) {
        final ScanWebsiteResponse scanWebsiteResponse = new ScanWebsiteResponse();
        scanWebsiteResponse.urls = crawlerStatistics.getCollectedHrefs();
        scanWebsiteResponse.scripts = crawlerStatistics.getCollectedScripts();
        scanWebsiteResponse.links = crawlerStatistics.getCollectedLinks();
        return scanWebsiteResponse;
    }

}
