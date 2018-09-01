package com.sky.assessment.parentalcontrol.parentalcontrolservice.api;

import com.sky.assessment.parentalcontrol.movieservice.api.MovieService;
import com.sky.assessment.parentalcontrol.movieservice.exception.ParentalControlLevelNotFoundException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TechnicalFailureException;
import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParentalControlServiceTests {

    private MovieService movieService;
    private ParentalControlLevelService parentalControlLevelService;

    @Before
    public void setup() {
        movieService = mock(MovieService.class);
        parentalControlLevelService = new IParentalControlLevelService(movieService);
    }

    @Test
    public void shouldReturnTrue_whenMoviePCL_PG_andCustomerPCL_12() throws TitleNotFoundException, ParentalControlLevelNotFoundException, TechnicalFailureException {
        when(movieService.getParentalControlLevel("1")).thenReturn("PG");
        assertThat(parentalControlLevelService.canWatchMovie("1", "12"), equalTo(true));
    }

    @Test
    public void shouldReturnTrue_whenMoviePCL_12_andCustomerPCL_15() throws TitleNotFoundException, ParentalControlLevelNotFoundException, TechnicalFailureException {
        when(movieService.getParentalControlLevel("2")).thenReturn("12");
        assertThat(parentalControlLevelService.canWatchMovie("2", "15"), equalTo(true));
    }

    @Test(expected = TechnicalFailureException.class)
    public void shouldThrowTechnicalFailureException_whenMoviePCL_isGreaterThan_customersPreference() throws TitleNotFoundException, ParentalControlLevelNotFoundException, TechnicalFailureException {
        when(movieService.getParentalControlLevel("2")).thenReturn("12");
        assertThat(parentalControlLevelService.canWatchMovie("2", "PG"), equalTo(false));
    }

}
