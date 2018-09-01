package com.sky.assessment.parentalcontrol.movieservice.api;

import com.sky.assessment.parentalcontrol.movieservice.bean.ParentalControlLevel;
import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;

public class IMovieService implements MovieService {

    /* TechnicalFailureException seems pointless here since we're simple
    only getting ParentalControlLevel value based on 'movieId'.
    According to the extract, TechnicalFailureException is a 'system error'
    to show that the customer cannot watch the movie, as this method does
    not compare user input to movie id.*/
    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException {
        return ParentalControlLevel.stream()
                .filter(pcl -> pcl.getMovieId() == Integer.parseInt(movieId))
                .findFirst()
                .orElseThrow(() -> new TitleNotFoundException("The movie service could not find the given movie"))
                .getParentalControlLevel();
    }

}
