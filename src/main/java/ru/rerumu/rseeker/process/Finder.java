package ru.rerumu.rseeker.process;

import org.springframework.stereotype.Service;
import ru.rerumu.rseeker.domain.et_list.ETList;
import ru.rerumu.rseeker.domain.et_list.ETListFactory;
import ru.rerumu.rseeker.domain.SearchRequest;
import ru.rerumu.rseeker.domain.SearchResponse;

@Service
public class Finder {

    public SearchResponse find(SearchRequest searchRequest){
        ETListFactory etListFactory = new ETListFactory();
        ETList etList = etListFactory.loadETList();

        SearchResponse searchResponse = new SearchResponse(searchRequest.getText());
        return searchResponse;
    }
}
