package ru.rerumu.rseeker.domain.et_list;

import java.util.ArrayList;

public class ETListFactory {

    public ETList loadETList(){
        return new ETList(new ArrayList<>());
    }
}
