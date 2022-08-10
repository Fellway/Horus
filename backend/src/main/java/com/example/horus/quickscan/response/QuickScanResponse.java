package com.example.horus.quickscan.response;

import com.example.horus.vulnerabilities.VulnerabilitiesLib;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class QuickScanResponse {

    private final List<Vulnerability> lowRisk;
    private final List<Vulnerability> mediumRisk;
    private final List<Vulnerability> highRisk;
    private final List<Vulnerability> infoRisk;
    private final Integer total;

    public static QuickScanResponse of(List<VulnerabilitiesLib> vulnerabilitiesLibList) {
        List<Vulnerability> lowRisk = new ArrayList<>();
        List<Vulnerability> mediumRisk = new ArrayList<>();
        List<Vulnerability> highRisk = new ArrayList<>();
        List<Vulnerability> infoRisk = new ArrayList<>();

        vulnerabilitiesLibList.forEach(v -> {
            final Vulnerability vulnerability = new Vulnerability(v.getTitle(), v.getDescription(), v.getRecommendation(), v.getRiskLevel());
            switch(v.getRiskLevel()) {
                case LOW:
                    lowRisk.add(vulnerability);
                    break;
                case MEDIUM:
                    mediumRisk.add(vulnerability);
                    break;
                case HIGH:
                    highRisk.add(vulnerability);
                case INFO:
                    infoRisk.add(vulnerability);
            }
        });
        return new QuickScanResponse(lowRisk, mediumRisk, highRisk, infoRisk, lowRisk.size() + mediumRisk.size() + highRisk.size() + infoRisk.size());
    }
}
