package com.example.horus.model;

import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Form {

    private List<Input> inputs;
    private List<Label> labels;
    private Button submitButton;
    private Input submitInput;
    private String action;
    private String method;
    private List<Button> buttons;

}
