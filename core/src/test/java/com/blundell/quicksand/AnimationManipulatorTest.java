package com.blundell.quicksand;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AnimationManipulatorTest {

    @Mock
    private AnimationTracker mockAnimationTracker;

    @Test
    public void whenResetTransitionIsCalledThenWeDelegateToThe() throws Exception {
        MockitoAnnotations.initMocks(this);
        AnimationManipulator manipulator = new AnimationManipulator(mockAnimationTracker, null, null);
        String expectedKey = "ExpectedKey";

        manipulator.resetTransition(expectedKey);

        verify(mockAnimationTracker).resetCount(expectedKey);
    }

}
