package com.sky.assessment.parentalcontrol.parentalcontrolservice.api;

import com.sky.assessment.parentalcontrol.movieservice.exception.ParentalControlLevelNotFoundException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TechnicalFailureException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;

public interface ParentalControlLevelService {

    boolean canWatchMovie(String movieId, String customerParentalControlLevelInput) throws TitleNotFoundException, ParentalControlLevelNotFoundException, TechnicalFailureException;

}
