package com.example.horus.vulnerabilities;

import com.example.horus.quickscan.response.RiskLevel;
import lombok.Getter;

@Getter
public enum VulnerabilitiesLib {

    MISSING_CONTENT_SECURITY_POLICY("Missing security header: Content-Security-Policy",
            "The Content-Security-Policy (CSP) header activates a protection mechanism implemented in web browsers which prevents exploitation of Cross-Site Scripting vulnerabilities (XSS). If the target application is vulnerable to XSS, lack of this header makes it easily exploitable by attackers.",
            "Configure the Content-Security-Header to be sent with each HTTP response in order to apply the specific policies needed by the application.",
            RiskLevel.LOW),
    MISSING_X_CONTENT_TYPE_OPTIONS("Missing security header: X-Content-Type-Options",
            "The HTTP header `X-Content-Type-Options` is addressed to the Internet Explorer browser and prevents it from reinterpreting the content of a web page (MIME-sniffing) and thus overriding the value of the Content-Type header). Lack of this header could lead to attacks such as Cross-Site Scripting or phishing.",
            "We recommend setting the X-Content-Type-Options header such as `X-Content-Type-Options: nosniff`.",
            RiskLevel.LOW),
    MISSING_X_FRAME_OPTIONS("Missing security header: X-Frame-Options",
            "Because the `X-Frame-Options` header is not sent by the server, an attacker could embed this website into an iframe of a third party website. By manipulating the display attributes of the iframe, the attacker could trick the user into performing mouse clicks in the application, thus performing activities without user consent (ex: delete user, subscribe to newsletter, etc). This is called a Clickjacking attack and it is described in detail here: https://owasp.org/www-community/attacks/Clickjacking",
            "We recommend you to add the `X-Frame-Options` HTTP header with the values `DENY` or `SAMEORIGIN` to every page that you want to be protected against Clickjacking attacks.",
            RiskLevel.LOW),
    MISSING_STRICT_TRANSPORT_SECURITY("Missing security header: Strict-Transport-Security",
            "The HTTP Strict-Transport-Security header instructs the browser to initiate only secure (HTTPS) connections to the web server and deny any unencrypted HTTP connection attempts. Lack of this header permits an attacker to force a victim user to initiate a clear-text HTTP connection to the server, thus opening the possibility to eavesdrop on the network traffic and extract sensitive information (e.g. session cookies).",
            "The Strict-Transport-Security HTTP header should be sent with each HTTPS response. The syntax is as follows: `Strict-Transport-Security: max-age=<seconds>[; includeSubDomains]` The parameter `max-age` gives the time frame for requirement of HTTPS in seconds and should be chosen quite high, e.g. several months. A value below 7776000 is considered as too low by this scanner check. The flag `includeSubDomains` defines that the policy applies also for sub domains of the sender of the response.",
            RiskLevel.LOW),
    MISSING_REFERRER_POLICY("Missing security header: Referrer-Policy",
            "The Referrer-Policy HTTP header controls how much referrer information the browser will send with each request originated from the current web application. For instance, if a user visits the web page \"http://example.com/pricing/\" and it clicks on a link from that page going to e.g. \"https://www.google.com\", the browser will send to Google the full originating URL in the `Referer` header, assuming the Referrer-Policy header is not set. The originating URL could be considered sensitive information and it could be used for user tracking.",
            "The Referrer-Policy header should be configured on the server side to avoid user tracking and inadvertent information leakage. The value `no-referrer` of this header instructs the browser to omit the Referer header entirely.",
            RiskLevel.LOW),
    MISSING_X_XSS_PROTECTION("Missing security header: X-XSS-Protection",
            "We detected a missing X-XSS-Protection header which means that this website could be at risk of a Cross-site Scripting (XSS) attacks. The HTTP X-XSS-Protection response header is a feature of Internet Explorer, Chrome and Safari that stops pages from loading when they detect reflected XSS attacks.",
            "It is recommended that the following be implemented: Add the X-XSS-Protection header with a value of 1; mode= block.",
            RiskLevel.LOW),
    WEBSITE_IS_NOT_ACCESSIBLE("Website is not accessible",
            "Request returned an error status. This can happen when the client or server side of application contains errors.",
            "Check your configuration of application and manually test the url.",
            RiskLevel.INFO),
    ROBOTS_FILE_FOUND("Robots.txt file found",
            "There is no particular security risk in having a robots.txt file. However, this file is often misused by website administrators to try to hide some web pages from the users. This should not be considered a security measure because these URLs can be easily read directly from the robots.txt file.",
            "We recommend you to manually review the entries from robots.txt and remove the ones which lead to sensitive locations in the website (ex. administration panels, configuration files, etc).",
            RiskLevel.LOW),
    SECURITY_FILE_NOT_FOUND("Security.txt file is missing",
            "We have detected that the server is missing the security.txt file. There is no particular risk in not creating a valid Security.txt file for your server. However, this file is important because it offers a designated channel for reporting vulnerabilities and security issues.",
            "We recommend you to implement the security.txt file according to the standard, in order to allow researchers or users report any security issues they find, improving the defensive mechanisms of your server.",
            RiskLevel.LOW),
    INSECURE_SET_COOKIE("Insecure cookie setting: domain too loose",
            "A cookie may be used in multiple subdomains belonging to the same domain. For instance, a cookie set for example.com, may be sent along with the requests sent to dev.example.com, calendar.example.com, hostedsite.example.com. Potentially risky websites under your main domain may access those cookies and use the victim session on the main site.",
            "The `Domain` attribute should be set to the origin host to limit the scope to that particular server. For example if the application resides on server app.mysite.com, then it should be set to `Domain=app.mysite.com`",
            RiskLevel.MEDIUM);

    private final String title;
    private final String description;
    private final String recommendation;
    private final RiskLevel riskLevel;

    VulnerabilitiesLib(String title, String description, String recommendation, RiskLevel riskLevel) {
        this.title = title;
        this.description = description;
        this.recommendation = recommendation;
        this.riskLevel = riskLevel;
    }

}
