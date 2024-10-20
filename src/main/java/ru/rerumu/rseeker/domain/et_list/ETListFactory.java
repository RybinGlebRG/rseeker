package ru.rerumu.rseeker.domain.et_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rerumu.rseeker.dao.ETListService;

import java.util.ArrayList;

@Component
public class ETListFactory {

    private final ETListService etListService;
    private final FuzzySearchService fuzzySearchService;

    @Autowired
    public ETListFactory(ETListService etListService, FuzzySearchService fuzzySearchService) {
        this.etListService = etListService;
        this.fuzzySearchService = fuzzySearchService;
    }

    public ETList loadETList(){
        return new ETList(etListService.getList(), fuzzySearchService);
    }
}
