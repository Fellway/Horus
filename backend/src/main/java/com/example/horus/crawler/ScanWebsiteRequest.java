package com.example.horus.crawler;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScanWebsiteRequest {

    private String websiteUrl;
    private Integer scanDepth;

}
