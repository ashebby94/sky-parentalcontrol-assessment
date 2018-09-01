package com.sky.assessment.parentalcontrol.movieservice.api;

import com.sky.assessment.parentalcontrol.movieservice.exception.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieServiceTests {

    private MovieService movieService;

    @Before
    public void setup() {
        movieService = new IMovieService();
    }

    @Test
    public void shouldGetParentalControlLevel_PG_givenMovieId_1() throws TitleNotFoundException {
        assertThat(movieService.getParentalControlLevel("1"), is("PG"));
    }

    @Test
    public void shouldGetParentalControlLevel_18_givenMovieId_4() throws TitleNotFoundException {
        assertThat(movieService.getParentalControlLevel("4"), is("18"));
    }

    @Test(expected = TitleNotFoundException.class)
    public void shouldThrowTitleNotFoundException_whenMovieIsNotFound_givenMovieId_80() throws TitleNotFoundException {
        assertThat(movieService.getParentalControlLevel("80"), is("18"));
    }

}
