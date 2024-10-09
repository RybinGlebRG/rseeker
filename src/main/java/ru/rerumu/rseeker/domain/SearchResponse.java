package ru.rerumu.rseeker.domain;

import lombok.Getter;

public class SearchResponse {
    @Getter
    private final String text;

    public SearchResponse(String text) {
        this.text = text;
    }
}
