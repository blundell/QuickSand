package com.blundell.quicksand;

import android.os.CountDownTimer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class AnimationTrackerTest {

    private static final long ANY_DURATION = 1L;

    @Mock
    private AnimationCounter mockCounter;
    @Mock
    private CountDownTimerFactory mockTimerFactory;
    @Mock
    private CountDownTimer mockTimer;
    private AnimationTracker tracker;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tracker = new AnimationTracker(mockCounter, mockTimerFactory);
        when(mockTimerFactory.getTimer(anyLong(), any(Runnable.class))).thenReturn(mockTimer);
    }

    @Test
    public void givenAnAnimationIsNotPartOfASetWhenIncrementViewCountIsAttemptedThenThereIsAnIncrement() throws Exception {
        tracker.attemptToIncrementAnimationSetViewCount("TestKey", ANY_DURATION);

        verify(mockCounter).incrementCount("TestKey");
    }

    @Test
    public void givenAnAnimationIsPartOfASetWhenIncrementIsAttemptedThenThereIsOnlyOneIncrement() throws Exception {
        tracker.attemptToIncrementAnimationSetViewCount("TestKey", ANY_DURATION);
        tracker.attemptToIncrementAnimationSetViewCount("TestKey", ANY_DURATION);
        tracker.attemptToIncrementAnimationSetViewCount("TestKey", ANY_DURATION);

        verify(mockCounter, times(1)).incrementCount("TestKey");
    }

    @Test
    public void givenTwoAnimationsAreNotPartOfASetWhenIncrementViewCountIsAttemptedThenThereIsTwoIncrements() throws Exception {
        tracker.attemptToIncrementAnimationSetViewCount("TestKey1", ANY_DURATION);
        tracker.attemptToIncrementAnimationSetViewCount("TestKey2", ANY_DURATION);

        verify(mockCounter, times(2)).incrementCount(anyString());
    }

    @Test
    public void whenGetCountThenDelegatesToCounter() throws Exception {
        String expectedKey = "TestKey";
        tracker.getCount(expectedKey);

        verify(mockCounter).getCount(expectedKey);
    }

    @Test
    public void whenResetCountThenDelegatesToCounter() throws Exception {
        String expectedKey = "TestKey";
        tracker.resetCount(expectedKey);

        verify(mockCounter).resetCount(expectedKey);
    }
}
