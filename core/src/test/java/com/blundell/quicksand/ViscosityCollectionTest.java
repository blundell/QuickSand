package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.Viscosity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ViscosityCollectionTest {

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByTheCorrectKeyThenTheCorrectViscosityIsReturned() throws Exception {
        Map<String, Viscosity> viscosities = new HashMap<>();
        String key = "TestKey";
        StubViscosity expectedViscosity = new StubViscosity();
        viscosities.put(key, expectedViscosity);
        ViscosityCollection collection = new ViscosityCollection(viscosities, new StubViscosity());

        Viscosity actualViscosity = collection.getFor(key);

        assertThat(actualViscosity).isEqualTo(expectedViscosity);
    }

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByAnIncorrectKeyThenTheCorrectViscosityIsNotReturned() throws Exception {
        Map<String, Viscosity> viscosities = new HashMap<>();
        StubViscosity expectedViscosity = new StubViscosity();
        viscosities.put("TestKey", expectedViscosity);
        ViscosityCollection collection = new ViscosityCollection(viscosities, new StubViscosity());

        Viscosity actualViscosity = collection.getFor("WrongTestKey");

        assertThat(actualViscosity).isNotEqualTo(expectedViscosity);
    }

    @Test
    public void givenAKnownViscosityWhenWeRetrieveItByAnIncorrectKeyThenTheDefaultViscosityIsReturned() throws Exception {
        Map<String, Viscosity> viscosities = new HashMap<>();
        StubViscosity expectedViscosity = new StubViscosity();
        viscosities.put("TestKey", expectedViscosity);
        StubViscosity defaultViscosity = new StubViscosity();
        ViscosityCollection collection = new ViscosityCollection(viscosities, defaultViscosity);

        Viscosity actualViscosity = collection.getFor("WrongTestKey");

        assertThat(actualViscosity).isEqualTo(defaultViscosity);
    }

    @Test
    public void givenAnEmptyCollectionWhenWeRetrieveByAnyKeyThenTheDefaultViscosityIsReturned() throws Exception {
        Map<String, Viscosity> viscosities = Collections.emptyMap();
        StubViscosity defaultViscosity = new StubViscosity();
        ViscosityCollection collection = new ViscosityCollection(viscosities, defaultViscosity);

        Viscosity actualViscosity = collection.getFor("AnyTestKey");

        assertThat(actualViscosity).isEqualTo(defaultViscosity);
    }

    private static class StubViscosity implements Viscosity {

        @Override
        public long calculateDuration(long currentDuration, long viewCount) {
            return 0;
        }

    }
}
