package com.blundell.quicksand.viscosity;

import java.util.Random;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LinearChangeViscosityTest {

    private static final long ANY_LONG = new Random().nextLong();

    @Test
    public void testWhenMaxViewsReachedDurationIsZero() throws Exception {
        int maxViews = 2;
        Viscosity viscosity = new LinearChangeViscosity(maxViews);

        long duration = viscosity.calculateDuration(ANY_LONG, maxViews);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIsOverMaxViewsDurationIsZero() throws Exception {
        int maxViews = 2;
        Viscosity viscosity = new LinearChangeViscosity(maxViews);

        long duration = viscosity.calculateDuration(ANY_LONG, ++maxViews);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIs1DurationEqualsCurrentDuration() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 1;
        long currentDuration = ANY_LONG;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(currentDuration);
    }

    @Test
    public void testWhenViewCountIs2of10AndCurrentDuration100DurationEquals80() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 2;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(80);
    }

    @Test
    public void testWhenViewCountIs3of10AndCurrentDuration100DurationEquals70() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 3;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(70);
    }

    @Test
    public void testWhenViewCountIs5of10AndCurrentDuration100DurationEquals50() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 5;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(50);
    }

    @Test
    public void testWhenViewCountIs9of10AndCurrentDuration100DurationEquals10() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 9;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(10);
    }

    @Test
    public void testWhenViewCountIs10of10AndCurrentDuration100DurationEquals0() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 10;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIs11of10AndCurrentDuration100DurationEquals0() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(10);
        int viewCount = 11;
        long currentDuration = 100;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(0);
    }

    @Test
    public void testWhenViewCountIs10of20AndCurrentDuration500DurationEquals250() throws Exception {
        Viscosity viscosity = new LinearChangeViscosity(20);
        int viewCount = 10;
        long currentDuration = 500;

        long duration = viscosity.calculateDuration(currentDuration, viewCount);

        assertThat(duration).isEqualTo(250);
    }
}
