package com.blundell.quicksand;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ActManipulatorTest {

    @Mock
    private AnimationTracker mockAnimationTracker;

    @Test
    public void whenResetTransitionIsCalledThenWeDelegateToThe() throws Exception {
        MockitoAnnotations.initMocks(this);
        ActManipulator manipulator = new ActManipulator(mockAnimationTracker, null, null);
        String expectedKey = "ExpectedKey";

        manipulator.resetTransition(expectedKey);

        verify(mockAnimationTracker).reset(expectedKey);
    }

}
