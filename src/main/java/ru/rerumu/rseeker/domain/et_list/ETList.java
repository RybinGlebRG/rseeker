package ru.rerumu.rseeker.domain.et_list;

import ru.rerumu.rseeker.domain.fuzzy_result.FuzzyResultLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ETList {
    private final List<String> lines;
    private final FuzzySearchService fuzzySearchService;

    ETList(List<String> lines, FuzzySearchService fuzzySearchService) {
        this.lines = lines;
        this.fuzzySearchService = fuzzySearchService;
    }

    public List<String> findFuzzy(String searchString){
        return fuzzySearchService.findMatchingLines(searchString, lines)
                .map(FuzzyResultLine::toResultString)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
