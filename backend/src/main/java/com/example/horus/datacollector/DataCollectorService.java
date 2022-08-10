package com.example.horus.datacollector;

import com.example.horus.configuration.SeleniumConfig;
import com.example.horus.model.*;
import com.example.horus.model.request.FormFromWebsiteRequest;
import com.example.horus.model.response.FormFromWebsiteResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollectorService {

    private final SeleniumConfig seleniumConfig;

    @Autowired
    public DataCollectorService(SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
    }

    public FormFromWebsiteResponse getFormsFromWebsite(FormFromWebsiteRequest formFromWebsiteRequest) throws MalformedURLException {
        seleniumConfig.setupChromeDriver();
        final WebDriver firefoxDriver = seleniumConfig.getChromeDriver();

        firefoxDriver.get(formFromWebsiteRequest.getWebsiteUrl());
        final Document document = Jsoup.parse(firefoxDriver.getPageSource());
        final Elements forms = document.select("form");
        List<Form> parsedForms = new ArrayList<>();
        for (Element form : forms) {
            if (form instanceof FormElement) {
                parsedForms.add(buildFormFromDocument((FormElement) form));
            }
        }
        return new FormFromWebsiteResponse(parsedForms);
    }

    private Form buildFormFromDocument(FormElement form) {
        List<Input> inputs = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        final Elements inputElements = form.select("input");
        final Elements labelElements = form.select("label");
        final Elements buttonElements = form.select("button");
        Input submitInput = null;
        Button submitButton = null;
        for (Element inputElement : inputElements) {
            final Input input = Input.builder()
                    .name(inputElement.attr("name"))
                    .value(inputElement.attr("value"))
                    .inputType(InputType.of(inputElement.attr("type")))
                    .id(inputElement.attr("id"))
                    .build();
            if(!inputElement.attr("type").equals("submit")) {
               inputs.add(input);
            } else {
                submitInput = input;
            }
        }

        for (Element buttonElement : buttonElements) {
            final Button button = Button.builder()
                    .name(buttonElement.attr("name"))
                    .value(buttonElement.attr("value"))
                    .type(InputType.of(buttonElement.attr("type")))
                    .id(buttonElement.attr("id"))
                    .content(buttonElement.text())
                    .build();
            if(button.getType().equals(InputType.SUBMIT)) {
                submitButton = button;
            } else {
                buttons.add(button);
            }
        }

        for (Element labelElement : labelElements) {
            labels.add(Label.builder()
                    .name(labelElements.attr("name"))
                    .value(labelElements.attr("value"))
                    .id(labelElements.attr("id"))
                    .forDescription(labelElement.attr("for"))
                    .build());
        }

        return Form.builder()
                .action(form.attr("action"))
                .inputs(inputs)
                .labels(labels)
                .buttons(buttons)
                .method(form.attr("method"))
                .submitButton(submitButton)
                .submitInput(submitInput)
                .build();
    }

}
