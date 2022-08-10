package com.example.horus.model;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Input {

    private String id;
    private String name;
    private String value;
    private InputType inputType;

}
