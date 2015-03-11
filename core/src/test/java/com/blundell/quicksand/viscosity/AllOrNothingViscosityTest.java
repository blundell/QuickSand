package com.blundell.quicksand.viscosity;

import java.util.Random;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class AllOrNothingViscosityTest {

    private static final long ANY_LONG = new Random().nextLong();

    @Test
    public void testWhenMaxViewsReachedDurationIsZero() throws Exception {
        int maxViews = 2;
        Viscosity viscosity = new AllOrNothingViscosity(maxViews);

        long duration = viscosity.calculateDuration(ANY_LONG, ++maxViews);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIsOverMaxViewsDurationIsZero() throws Exception {
        int maxViews = 2;
        Viscosity viscosity = new AllOrNothingViscosity(maxViews);

        long duration = viscosity.calculateDuration(ANY_LONG, ++maxViews);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIsLessThanMaxDurationEqualsCurrentDuration() throws Exception {
        Viscosity viscosity = new AllOrNothingViscosity(2);
        int viewCount = 1;
        long currentDuration = ANY_LONG;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(currentDuration);
    }
}
