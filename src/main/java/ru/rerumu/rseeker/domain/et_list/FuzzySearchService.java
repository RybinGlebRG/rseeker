package ru.rerumu.rseeker.domain.et_list;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import ru.rerumu.rseeker.domain.fuzzy_result.FuzzyResultLine;

@Service
public class FuzzySearchService {
    private final static int LIMIT = 10;
    private final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public Stream<FuzzyResultLine> findMatchingLines(String searchString, List<String> lines){
        Map<String, Float> linesDistances = new HashMap<>();

        for (String line: lines){
            float score = getScore(line, searchString);
            linesDistances.put(line, score);
        }

        Comparator<Map.Entry<String, Float>> mapComparator= (e1, e2)->Float.compare(e1.getValue(), e2.getValue());
        mapComparator = mapComparator.reversed();

        return linesDistances.entrySet().stream()
                .sorted(mapComparator)
                .limit(LIMIT)
                .map(entry -> new FuzzyResultLine(entry.getKey(), entry.getValue()));
    }

    private float getScore(String line, String searchString){
        if (line.equalsIgnoreCase(searchString)){
            return 1f;
        }

        List<String> lineSubstrings = new ArrayList<>();
        lineSubstrings.add(line);
        lineSubstrings.addAll(Arrays.asList(line.split(" ")));

        List<Float> scores = new ArrayList<>();
        for(String item: lineSubstrings){
            Integer distance = levenshteinDistance.apply(item.toUpperCase(), searchString.toUpperCase());
            Float score = (float) (item.length() - distance) / item.length();
            scores.add(score);
        }

        Float res = scores.stream().max(Float::compareTo).orElseThrow();
        return res;
    }
}
