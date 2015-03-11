package com.blundell.quicksand.viscosity;

import java.util.Random;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoChangeViscosityTest {

    private static final long ANY_LONG = new Random().nextLong();

    @Test
    public void testNoMatterTheViewCountDurationIsNeverChanged() throws Exception {
        Viscosity viscosity = new NoChangeViscosity();

        for (int i = 0; i < 5000; i++) {
            long duration = viscosity.calculateDuration(ANY_LONG, i);

            assertThat(duration).isEqualTo(ANY_LONG);
        }
    }
}
