package com.blundell.quicksand;

import android.os.CountDownTimer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void givenAnAnimationIsNotPartOfASetWhenWeAskThenItSaysItsANewAnimation() throws Exception {
        boolean startOfANewAnimation = tracker.isTheStartOfANewAnimation("TestKey", ANY_DURATION);

        assertThat(startOfANewAnimation).isTrue();
    }

    @Test
    public void givenAnAnimationIsPartOfASetWhenWeAskThenThereItSaysItIsNotANewAnimation() throws Exception {
        String setKey = "TestKey";
        tracker.isTheStartOfANewAnimation(setKey, ANY_DURATION);

        boolean startOfANewAnimation = tracker.isTheStartOfANewAnimation(setKey, ANY_DURATION);

        assertThat(startOfANewAnimation).isFalse();
    }

    @Test
    public void givenTwoAnimationsAreNotPartOfASetWhenWeAskThenThereIsTwoIncrements() throws Exception {
        tracker.isTheStartOfANewAnimation("TestKey1", ANY_DURATION);

        boolean startOfANewAnimation = tracker.isTheStartOfANewAnimation("TestKey2", ANY_DURATION);

        assertThat(startOfANewAnimation).isTrue();
    }

    @Test
    public void whenIncrementViewCountThenDelegatesToCounter() throws Exception {
        String expectedKey = "TestKey";
        tracker.incrementAnimationViewCount(expectedKey);

        verify(mockCounter).incrementCount(expectedKey);
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
        tracker.reset(expectedKey);

        verify(mockCounter).reset(expectedKey);
    }
}
