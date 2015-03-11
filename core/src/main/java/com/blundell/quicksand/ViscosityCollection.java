package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.LinearChangeViscosity;
import com.blundell.quicksand.viscosity.Viscosity;

import java.util.HashMap;
import java.util.Map;

class ViscosityCollection { // TODO better name when we have more behaviour or realise Viscosity is a dumb name

    private final Map<String, Viscosity> viscosityMap;
    private final Viscosity defaultViscosity;

    public static ViscosityCollection newInstance(Map<String, Viscosity> viscosities) {
        Map<String, Viscosity> viscosityMap = new HashMap<>();
        viscosityMap.putAll(viscosities);
        Viscosity defaultViscosity = LinearChangeViscosity.defaultInstance();
        return new ViscosityCollection(viscosityMap, defaultViscosity);
    }

    ViscosityCollection(Map<String, Viscosity> viscosityMap, Viscosity defaultViscosity) {
        this.viscosityMap = viscosityMap;
        this.defaultViscosity = defaultViscosity;
    }

    public Viscosity getFor(String key) {
        if (viscosityMap.containsKey(key)) {
            return viscosityMap.get(key);
        } else {
            return defaultViscosity;
        }
    }
}
