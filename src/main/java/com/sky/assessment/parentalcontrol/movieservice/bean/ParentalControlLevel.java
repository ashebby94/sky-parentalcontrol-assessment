package com.sky.assessment.parentalcontrol.movieservice.bean;

import java.util.stream.Stream;

public enum ParentalControlLevel {

    U("U", 0), PG("PG", 1), TWELVE("12", 2), FIFTEEN("15", 3), EIGHTEEN("18", 4);

    private String parentalControlLevel;
    private int movieId;

    ParentalControlLevel(String parentalControlLevel, int movieId) {
        this.parentalControlLevel = parentalControlLevel;
        this.movieId = movieId;
    }

    public String getParentalControlLevel() {
        return parentalControlLevel;
    }

    public int getMovieId() {
        return movieId;
    }

    public static Stream<ParentalControlLevel> stream() {
        return Stream.of(ParentalControlLevel.values());
    }

}
