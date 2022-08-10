package com.example.horus.model.response;

import com.example.horus.model.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FormFromWebsiteResponse {

    private final List<Form> forms;

}
