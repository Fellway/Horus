package com.example.horus.crawler.dynamic;


import com.example.horus.configuration.SeleniumConfig;
import com.example.horus.crawler.CrawlerStatistics;
import com.example.horus.crawler.Website;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Component
public class DynamicWebsiteScraper {

    private final SeleniumConfig seleniumConfig;
    private final CrawlerStatistics crawlerStatistics;

    private String baseUrl = "";
    private Integer currentDepth = 0;

    public DynamicWebsiteScraper(SeleniumConfig seleniumConfig, CrawlerStatistics crawlerStatistics) {
        this.seleniumConfig = seleniumConfig;
        this.crawlerStatistics = crawlerStatistics;
    }

    public CrawlerStatistics scrapData(Integer scanDepth, String url) throws IOException {
        baseUrl = getBaseUrl(url);
        currentDepth = 0;
        seleniumConfig.setupChromeDriver();
        final WebDriver driver = seleniumConfig.getChromeDriver();
        collectPageData(driver, scanDepth, url);
        seleniumConfig.closeChromeDriver();
        return crawlerStatistics;
    }

    private void collectPageData(WebDriver driver, Integer scanDepth, String url) throws IOException {
        if (StringUtils.isNotBlank(url)) {
            if (!crawlerStatistics.getCollectedHrefs().contains(url)) {
                crawlerStatistics.addHrefToCollection(url);
                driver.get(url);

                if (scanDepth == 0 ||scanDepth > currentDepth) {
                    currentDepth++;
                    Document document = Jsoup.parse(driver.getPageSource());
                    collectScripts(document);
                    collectLinks(document);
                    collectHrefs(document, scanDepth);
                }
            }
        }
    }


    private void collectHrefs(Document document, Integer scanDepth) throws IOException {
        Elements hrefsOnPage = document.select("a[href]");
        for (Element href : hrefsOnPage) {
            String subPageUrl = modifyResultUrl(href.attr("href"));
            if (subPageUrl.startsWith(baseUrl)) {
                collectPageData(seleniumConfig.getChromeDriver(), scanDepth, subPageUrl);
            }
        }
    }

    private void collectScripts(Document document) {
        final Elements scriptsOnPage = document.select("script[src]");
        for (Element script : scriptsOnPage) {
            String scriptLocation = modifyResultUrl(script.attr("src"));
            if (!crawlerStatistics.getCollectedScripts().contains(scriptLocation)) {
                crawlerStatistics.addScriptToCollection(scriptLocation);
            }
        }
    }

    private void collectLinks(Document document) {
        final Elements linksOnPage = document.select("link[href]");
        for (Element link : linksOnPage) {
            String linkLocation = modifyResultUrl(link.attr("href"));
            if (!crawlerStatistics.getCollectedLinks().contains(linkLocation)) {
                crawlerStatistics.addLinkToCollection(linkLocation);
            }
        }
    }

    private String modifyResultUrl(String url) {
        if (url.startsWith("/")) {
            return baseUrl + url;
        }

        if (!url.startsWith("http") && !url.startsWith("https") && !url.startsWith("www")) {
            return baseUrl + "/" + url;
        }
        return url;
    }

    private String getBaseUrl(String url) throws MalformedURLException {
        URL baseUrl = new URL(url);
        String protocol = baseUrl.getProtocol();
        String host = baseUrl.getHost();
        int port = baseUrl.getPort();
        return protocol + "://" + host + (port == -1 ? "" : ":" + port);
    }

}
