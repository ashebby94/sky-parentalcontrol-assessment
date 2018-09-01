package com.sky.assessment.parentalcontrol.movieservice.api;

import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;

public interface MovieService {
    String getParentalControlLevel(String movieId) throws TitleNotFoundException;
}
