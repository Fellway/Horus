package com.example.horus.quickscan.validator;

import com.example.horus.vulnerabilities.VulnerabilitiesLib;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteAccessibilityValidator {

    public List<VulnerabilitiesLib> validate(int statusCode) {
        List<VulnerabilitiesLib> vulnerabilitiesLibs = new ArrayList<>();
        if(statusCode >= 400) {
            vulnerabilitiesLibs.add(VulnerabilitiesLib.WEBSITE_IS_NOT_ACCESSIBLE);
        }
        return vulnerabilitiesLibs;
    }

}
