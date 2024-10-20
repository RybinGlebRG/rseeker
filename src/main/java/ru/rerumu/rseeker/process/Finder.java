package ru.rerumu.rseeker.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rerumu.rseeker.domain.et_list.ETList;
import ru.rerumu.rseeker.domain.et_list.ETListFactory;
import ru.rerumu.rseeker.domain.SearchRequest;
import ru.rerumu.rseeker.domain.SearchResponse;

import java.util.List;

@Service
public class Finder {

    private final ETListFactory etListFactory;

    @Autowired
    public Finder(ETListFactory etListFactory) {
        this.etListFactory = etListFactory;
    }

    public SearchResponse find(SearchRequest searchRequest){
        ETList etList = etListFactory.loadETList();
        List<String> result = etList.findFuzzy(searchRequest.getText());

//        String.join("\n", result);

        SearchResponse searchResponse = new SearchResponse(String.join("\n", result));
        return searchResponse;
    }
}
