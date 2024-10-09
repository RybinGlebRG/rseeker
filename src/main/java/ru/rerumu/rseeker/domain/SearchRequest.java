package ru.rerumu.rseeker.domain;

import lombok.Getter;

public class SearchRequest {

    @Getter
    private final String text;

    public SearchRequest(String text) {
        this.text = text;
    }
}
