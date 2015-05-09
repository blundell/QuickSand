package com.blundell.quicksand.viscosity;

import java.util.Random;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TwoStepViscosityTest {

    private static final long ANY_LONG = new Random().nextLong();

    @Test
    public void testWhenViewCountIsUnderStepThresholdDurationIsNotChanged() throws Exception {
        int stepThreshold = 2;
        ViscosityInterpolator viscosity = new TwoStepViscosityInterpolator(stepThreshold);

        long duration = viscosity.calculateDuration(ANY_LONG, --stepThreshold);

        assertThat(duration).isEqualTo(ANY_LONG);
    }

    @Test
    public void testWhenStepThresholdReachedDurationIsHalved() throws Exception {
        int stepThreshold = 2;
        ViscosityInterpolator viscosity = new TwoStepViscosityInterpolator(stepThreshold);

        long duration = viscosity.calculateDuration(ANY_LONG, stepThreshold);

        assertThat(duration).isEqualTo(ANY_LONG / 2);
    }

    @Test
    public void testWhenViewCountIsOverStepThresholdDurationIsHalved() throws Exception {
        int stepThreshold = 2;
        ViscosityInterpolator viscosity = new TwoStepViscosityInterpolator(stepThreshold);

        long duration = viscosity.calculateDuration(ANY_LONG, ++stepThreshold);

        assertThat(duration).isEqualTo(ANY_LONG / 2);
    }

}
