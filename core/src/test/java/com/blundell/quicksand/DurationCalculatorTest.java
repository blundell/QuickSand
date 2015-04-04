package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.Viscosity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class DurationCalculatorTest {

    @Mock
    Viscosity mockViscosity;

    private DurationCalculator calc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calc = new DurationCalculator();
    }

    @Test
    public void givenActHasDurationZeroWhenDurationCalculatedThenCalculatedDurationIsZero() throws Exception {
        long currentDuration = 0L;

        long duration = calc.calculateNewDuration(0, mockViscosity, currentDuration);

        assertThat(duration).isEqualTo(0L);
    }

    @Test
    public void givenActViewedForFirstTimeWhenDurationCalculatedThenCalculatedDurationIsCurrentDuration() throws Exception {
        long currentDuration = 50L;

        long duration = calc.calculateNewDuration(0, mockViscosity, currentDuration);

        assertThat(duration).isEqualTo(currentDuration);
    }

    @Test
    public void givenAnActWhenDurationCalculatedThenCalculatedDurationIsDelegatedToTheViscosity() throws Exception {
        int timesTransitionViewed = 1;
        long currentDuration = 50L;

        calc.calculateNewDuration(timesTransitionViewed, mockViscosity, currentDuration);

        verify(mockViscosity).calculateDuration(currentDuration, timesTransitionViewed);
    }
}
