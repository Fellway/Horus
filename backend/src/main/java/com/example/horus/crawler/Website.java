package com.example.horus.crawler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Website {
    private final UUID id;
    private final String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Website website = (Website) o;
        return Objects.equals(url, website.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
