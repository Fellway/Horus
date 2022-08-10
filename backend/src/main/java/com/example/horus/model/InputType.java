package com.example.horus.model;

public enum InputType {

    BUTTON("button"),
    CHECKBOX("checkbox"),
    COLOR("color"),
    DATE("date"),
    DATETIME_LOCAL("datetime-local"),
    EMAIL("email"),
    FILE("file"),
    HIDDEN("hidden"),
    IMAGE("image"),
    MONTH("month"),
    NUMBER("number"),
    PASSWORD("password"),
    RADIO("radio"),
    RANGE("range"),
    RESET("reset"),
    SEARCH("search"),
    SUBMIT("submit"),
    TEL("tel"),
    TEXT("text"),
    TIME("time"),
    URL("url"),
    WEEk("week");

    private final String htmlDescription;

    InputType(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public static InputType of(String htmlDescription) {
        final InputType[] values = InputType.values();
        for (InputType value : values) {
            if(value.getHtmlDescription().equals(htmlDescription)) {
                return value;
            }
        }
        return null;
    }

}
