package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.ViscosityInterpolator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ViscosityInterpolatorMapTest {

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByTheCorrectKeyThenTheCorrectViscosityIsReturned() throws Exception {
        Map<String, ViscosityInterpolator> viscosities = new HashMap<>();
        String key = "TestKey";
        StubViscosityInterpolator expectedViscosity = new StubViscosityInterpolator();
        viscosities.put(key, expectedViscosity);
        ViscosityInterpolatorMap collection = new ViscosityInterpolatorMap(viscosities, new StubViscosityInterpolator());

        ViscosityInterpolator actualViscosity = collection.getFor(key);

        assertThat(actualViscosity).isEqualTo(expectedViscosity);
    }

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByAnIncorrectKeyThenTheCorrectViscosityIsNotReturned() throws Exception {
        Map<String, ViscosityInterpolator> viscosities = new HashMap<>();
        StubViscosityInterpolator expectedViscosity = new StubViscosityInterpolator();
        viscosities.put("TestKey", expectedViscosity);
        ViscosityInterpolatorMap collection = new ViscosityInterpolatorMap(viscosities, new StubViscosityInterpolator());

        ViscosityInterpolator actualViscosity = collection.getFor("WrongTestKey");

        assertThat(actualViscosity).isNotEqualTo(expectedViscosity);
    }

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByAnIncorrectKeyThenTheDefaultViscosityIsReturned() throws Exception {
        Map<String, ViscosityInterpolator> viscosities = new HashMap<>();
        StubViscosityInterpolator expectedViscosity = new StubViscosityInterpolator();
        viscosities.put("TestKey", expectedViscosity);
        StubViscosityInterpolator defaultViscosity = new StubViscosityInterpolator();
        ViscosityInterpolatorMap collection = new ViscosityInterpolatorMap(viscosities, defaultViscosity);

        ViscosityInterpolator actualViscosity = collection.getFor("WrongTestKey");

        assertThat(actualViscosity).isEqualTo(defaultViscosity);
    }

    @Test
    public void givenAnEmptyCollectionWhenWeRetrieveByAnyKeyThenTheDefaultViscosityIsReturned() throws Exception {
        Map<String, ViscosityInterpolator> viscosities = Collections.emptyMap();
        StubViscosityInterpolator defaultViscosity = new StubViscosityInterpolator();
        ViscosityInterpolatorMap collection = new ViscosityInterpolatorMap(viscosities, defaultViscosity);

        ViscosityInterpolator actualViscosity = collection.getFor("AnyTestKey");

        assertThat(actualViscosity).isEqualTo(defaultViscosity);
    }

    private static class StubViscosityInterpolator implements ViscosityInterpolator {

        @Override
        public long calculateDuration(long currentDuration, long viewCount) {
            return 0;
        }

    }
}
