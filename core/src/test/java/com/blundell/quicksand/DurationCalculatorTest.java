package com.blundell.quicksand;

import com.blundell.quicksand.act.Act;
import com.blundell.quicksand.viscosity.Viscosity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DurationCalculatorTest {

    @Mock
    Act mockAct;
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
        when(mockAct.getDuration()).thenReturn(0L);

        long duration = calc.calculateNewDuration(mockAct, 0, mockViscosity);

        assertThat(duration).isEqualTo(0L);
    }

    @Test
    public void givenActViewedForFirstTimeWhenDurationCalculatedThenCalculatedDurationIsCurrentDuration() throws Exception {
        long currentDuration = 50L;
        when(mockAct.getDuration()).thenReturn(currentDuration);

        long duration = calc.calculateNewDuration(mockAct, 0, mockViscosity);

        assertThat(duration).isEqualTo(currentDuration);
    }

    @Test
    public void givenAnActWhenDurationCalculatedThenCalculatedDurationIsDelegatedToTheViscosity() throws Exception {
        int timesTransitionViewed = 1;
        long currentDuration = 50L;
        when(mockAct.getDuration()).thenReturn(currentDuration);

        calc.calculateNewDuration(mockAct, timesTransitionViewed, mockViscosity);

        verify(mockViscosity).calculateDuration(currentDuration, timesTransitionViewed);
    }
}
