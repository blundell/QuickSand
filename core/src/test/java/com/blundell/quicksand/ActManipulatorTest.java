package com.blundell.quicksand;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ActManipulatorTest {

    @Mock
    private AnimationTracker mockAnimationTracker;
    @Mock
    private DurationCalculator mockDurationCalculator;
    private ActManipulator manipulator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenResetTransitionIsCalledThenWeDelegateToTheAnimationTracker() throws Exception {
        ActManipulator manipulator = new ActManipulator(mockAnimationTracker, null, null);
        String expectedKey = "ExpectedKey";

        manipulator.resetTransition(expectedKey);

        verify(mockAnimationTracker).reset(expectedKey);
    }
}
