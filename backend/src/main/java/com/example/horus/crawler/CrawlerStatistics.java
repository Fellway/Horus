package com.example.horus.crawler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CrawlerStatistics {

    private HashSet<String> collectedHrefs;
    private HashSet<String> collectedScripts;
    private HashSet<String> collectedLinks;
    private int processedPageCount;
    private int totalLinksCount;

    public CrawlerStatistics() {
        collectedHrefs = new HashSet<>();
        collectedScripts = new HashSet<>();
        collectedLinks = new HashSet<>();
        processedPageCount = 0;
        totalLinksCount = 0;
    }

    public void incrementProcessedPageCount() {
        processedPageCount++;
    }

    public void incrementTotalLinksCount(int linksCount) {
        totalLinksCount += linksCount;
    }

    public boolean addHrefToCollection(String website) {
        return this.collectedHrefs.add(website);
    }

    public boolean addScriptToCollection(String script) {
        return this.collectedScripts.add(script);
    }

    public boolean addLinkToCollection(String link) {
        return this.collectedLinks.add(link);
    }

}
