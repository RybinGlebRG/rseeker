package ru.rerumu.rseeker.domain.fuzzy_result;

public class FuzzyResultLine {

    private final String line;
    private final Float score;

    public FuzzyResultLine(String line, Float score) {
        this.line = line;
        this.score = score;
    }

    public String toResultString(){
        return String.format("[%.2f] %s", score, line);
    }
}
