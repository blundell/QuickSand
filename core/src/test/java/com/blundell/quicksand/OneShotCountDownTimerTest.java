package com.blundell.quicksand;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class OneShotCountDownTimerTest {

    @Mock
    private Runnable mockRunnable;
    private CountDownTimerFactory.OneShotCountDownTimer timer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        timer = new CountDownTimerFactory.OneShotCountDownTimer(1L);
    }

    @Test
    public void givenACorrectTimerSetupWhenTimerFinishesThenRunnableIsExecuted() throws Exception {
        timer.executeThisOnFinish(mockRunnable);

        timer.onFinish();

        verify(mockRunnable).run();
    }

    @Test(expected = IllegalStateException.class)
    public void givenAnIncorrectTimerSetupWhenTimerFinishesThenExceptionIsThrown() throws Exception {
        timer.executeThisOnFinish(null);

        timer.onFinish();
    }
}
