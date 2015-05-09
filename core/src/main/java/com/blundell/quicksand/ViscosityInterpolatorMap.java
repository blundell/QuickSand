package com.blundell.quicksand;

import com.blundell.quicksand.viscosity.LinearChangeViscosityInterpolator;
import com.blundell.quicksand.viscosity.ViscosityInterpolator;

import java.util.HashMap;
import java.util.Map;

class ViscosityInterpolatorMap {

    private final Map<String, ViscosityInterpolator> viscosityMap;
    private final ViscosityInterpolator defaultViscosity;

    public static ViscosityInterpolatorMap newInstance(Map<String, ViscosityInterpolator> viscosities) {
        Map<String, ViscosityInterpolator> viscosityMap = new HashMap<>();
        viscosityMap.putAll(viscosities);
        ViscosityInterpolator defaultViscosity = LinearChangeViscosityInterpolator.defaultInstance();
        return new ViscosityInterpolatorMap(viscosityMap, defaultViscosity);
    }

    ViscosityInterpolatorMap(Map<String, ViscosityInterpolator> viscosityMap, ViscosityInterpolator defaultViscosity) {
        this.viscosityMap = viscosityMap;
        this.defaultViscosity = defaultViscosity;
    }

    public ViscosityInterpolator getFor(String key) {
        if (viscosityMap.containsKey(key)) {
            return viscosityMap.get(key);
        } else {
            return defaultViscosity;
        }
    }
}
