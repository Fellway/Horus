package com.example.horus.quickscan.validator;

import com.example.horus.vulnerabilities.VulnerabilitiesLib;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.net.HttpHeaders.*;

@Service
public class HeaderValidator {

    public List<VulnerabilitiesLib> validate(HttpHeaders headers) {
        final String headersCollection = getHeadersAsString(headers);
        List<VulnerabilitiesLib> vulnerabilities = new ArrayList<>();
        if (!containsCSP(headersCollection)){
            vulnerabilities.add(VulnerabilitiesLib.MISSING_CONTENT_SECURITY_POLICY);
        }
        if (!containsXContentTypeOptions(headersCollection)) {
            vulnerabilities.add(VulnerabilitiesLib.MISSING_X_CONTENT_TYPE_OPTIONS);
        }
        if (!containsXFrameOptions(headersCollection)) {
            vulnerabilities.add(VulnerabilitiesLib.MISSING_X_FRAME_OPTIONS);
        }
        if (!containsStrictTransportSecurity(headersCollection)) {
            vulnerabilities.add(VulnerabilitiesLib.MISSING_STRICT_TRANSPORT_SECURITY);
        }
        if(!containsReferrerPolicy(headersCollection)) {
            vulnerabilities.add(VulnerabilitiesLib.MISSING_REFERRER_POLICY);
        }
        if(!containsXSSProtection(headersCollection)) {
            vulnerabilities.add(VulnerabilitiesLib.MISSING_X_XSS_PROTECTION);
        }
        vulnerabilities.addAll(validateSetCookie(headersCollection));
        return vulnerabilities;
    }

    private List<VulnerabilitiesLib> validateSetCookie(String header) {
        List<VulnerabilitiesLib> vulnerabilitiesLibs = new ArrayList<>();
        if(header.contains(SET_COOKIE.toLowerCase()) && header.contains("domain")) {
            final String domain = header.substring(header.indexOf("domain"));
            final String substring = domain.substring(domain.indexOf("=") + 1, ';');
            if(substring.startsWith(".")) {
                vulnerabilitiesLibs.add(VulnerabilitiesLib.INSECURE_SET_COOKIE);
            }
        }
        return vulnerabilitiesLibs;
    }

    private boolean containsXSSProtection(String header) {
        return header.contains(X_XSS_PROTECTION.toLowerCase());
    }

    private boolean containsReferrerPolicy(String header) {
        return header.contains(REFERRER_POLICY.toLowerCase());
    }

    private boolean containsStrictTransportSecurity(String header) {
        return header.contains(STRICT_TRANSPORT_SECURITY.toLowerCase());
    }

    private boolean containsXFrameOptions(String header) {
        return header.contains(X_FRAME_OPTIONS.toLowerCase());
    }

    private boolean containsXContentTypeOptions(String header) {
        return header.contains(X_CONTENT_TYPE_OPTIONS.toLowerCase());
    }

    private boolean containsCSP(String header) {
        return header.contains(CONTENT_SECURITY_POLICY.toLowerCase());
    }

    private String getHeadersAsString(HttpHeaders headers) {
        StringBuilder headerBuilder = new StringBuilder();
        headers.map().entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .forEach(entry -> {
                    headerBuilder.append(entry.getKey()).append(": ");
                    List headerValues = entry.getValue();
                    Iterator it = headerValues.iterator();
                    if (it.hasNext()) {
                        headerBuilder.append(it.next());
                        while (it.hasNext()) {
                            headerBuilder.append(", ").append(it.next());
                        }
                    }
                    headerBuilder.append("\n");
                });
        return headerBuilder.toString();
    }

}
