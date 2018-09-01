package com.sky.assessment.parentalcontrol.parentalcontrolservice.api;

import com.sky.assessment.parentalcontrol.movieservice.api.MovieService;
import com.sky.assessment.parentalcontrol.movieservice.bean.ParentalControlLevel;
import com.sky.assessment.parentalcontrol.movieservice.exception.ParentalControlLevelNotFoundException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TechnicalFailureException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IParentalControlLevelService implements ParentalControlLevelService {

    private Logger logger = LoggerFactory.getLogger(IParentalControlLevelService.class);

    private MovieService movieService;

    public IParentalControlLevelService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public boolean canWatchMovie(String movieId, String customerParentalControlLevel) throws TitleNotFoundException, ParentalControlLevelNotFoundException, TechnicalFailureException {
        final String movieServiceParentalControlLevel = movieService.getParentalControlLevel(movieId);
        final ParentalControlLevel cPCLResult = getParentalControlLevel(customerParentalControlLevel);
        final ParentalControlLevel msPCLResult = getParentalControlLevel(movieServiceParentalControlLevel);
        return validate(msPCLResult, cPCLResult);
    }

    // Validate according to criteria, if parent control level of movie is equal to or less than customer's reference
    private boolean validate(ParentalControlLevel movieServicePCL, ParentalControlLevel customerPreference) throws TechnicalFailureException {
        boolean canWatch;
        try {
            if (movieServicePCL.getMovieId() <= customerPreference.getMovieId()) {
                canWatch = true;
            } else {
                throw new TechnicalFailureException("System Error!!");
            }
        } finally {
            if (movieServicePCL.getMovieId() > customerPreference.getMovieId()) {
                logger.error("Customer cannot watch this movie");
            }
        }
        return canWatch;
    }

    // Get parental control level based on give parental control level, opposite to movie service's method based on 'movieId'
    private ParentalControlLevel getParentalControlLevel(String parentalControlLevel) throws ParentalControlLevelNotFoundException {
        try {
            return ParentalControlLevel.stream()
                    .filter(e -> e.getParentalControlLevel().equals(parentalControlLevel))
                    .findFirst().get();
        } catch (Exception e) {
            throw new ParentalControlLevelNotFoundException("Could not find Parental Control Level");
        }
    }
}
