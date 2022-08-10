package com.example.horus.model;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Label {

    private String id;
    private String name;
    private String value;
    private String forDescription;

}
