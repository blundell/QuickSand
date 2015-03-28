package com.blundell.quicksand;

import android.view.ViewPropertyAnimator;

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
    AccessibleTransition mockTransition;
    @Mock
    ViewPropertyAnimator mockViewPropertyAnimator;
    @Mock
    Viscosity mockViscosity;
    private DurationCalculator calc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calc = new DurationCalculator();
    }

    @Test
    public void givenTransitionHasDurationZeroWhenDurationCalculatedThenCalculatedDurationIsZero() throws Exception {
        when(mockTransition.getDuration()).thenReturn(0L);

        long duration = calc.calculateNewDuration(mockTransition, 0, mockViscosity);

        assertThat(duration).isEqualTo(0L);
    }

    @Test
    public void givenViewPropertyAnimatorHasDurationZeroWhenDurationCalculatedThenCalculatedDurationIsZero() throws Exception {
        when(mockViewPropertyAnimator.getDuration()).thenReturn(0L);

        long duration = calc.calculateNewDuration(mockViewPropertyAnimator, 0, mockViscosity);

        assertThat(duration).isEqualTo(0L);
    }

    @Test
    public void givenTransitionViewedForFirstTimeWhenDurationCalculatedThenCalculatedDurationIsCurrentDuration() throws Exception {
        long currentDuration = 50L;
        when(mockTransition.getDuration()).thenReturn(currentDuration);

        long duration = calc.calculateNewDuration(mockTransition, 0, mockViscosity);

        assertThat(duration).isEqualTo(currentDuration);
    }

    @Test
    public void givenViewPropertyAnimatorViewedForFirstTimeWhenDurationCalculatedThenCalculatedDurationIsCurrentDuration() throws Exception {
        long currentDuration = 50L;
        when(mockViewPropertyAnimator.getDuration()).thenReturn(currentDuration);

        long duration = calc.calculateNewDuration(mockViewPropertyAnimator, 0, mockViscosity);

        assertThat(duration).isEqualTo(currentDuration);
    }

    @Test
    public void givenATransitionWhenDurationCalculatedThenCalculatedDurationIsDelegatedToTheViscosity() throws Exception {
        int timesTransitionViewed = 1;
        long currentDuration = 50L;
        when(mockTransition.getDuration()).thenReturn(currentDuration);

        calc.calculateNewDuration(mockTransition, timesTransitionViewed, mockViscosity);

        verify(mockViscosity).calculateDuration(currentDuration, timesTransitionViewed);
    }

    @Test
    public void givenAViewPropertyAnimatorWhenDurationCalculatedThenCalculatedDurationIsDelegatedToTheViscosity() throws Exception {
        int timesTransitionViewed = 1;
        long currentDuration = 50L;
        when(mockViewPropertyAnimator.getDuration()).thenReturn(currentDuration);

        calc.calculateNewDuration(mockViewPropertyAnimator, timesTransitionViewed, mockViscosity);

        verify(mockViscosity).calculateDuration(currentDuration, timesTransitionViewed);
    }
}
