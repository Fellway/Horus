package com.example.horus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Button {
    private String value;
    private String name;
    private InputType type;
    private String content;
    private String id;
}
