package com.espacex.decouverte.objetsastro;

import java.util.HashMap;
import java.util.Map;

/**
 * The class Atmosphere allows to create the composition of the atmosphere of a planet
 * String is the chemical formula of the component , float the quantity in %
 * ex:
 * Atmosphere atmosphereMars = new Atmosphere();
 * atmosphereMars.constituants.put("CO2", 95f );
 */
public class Atmosphere {

    // key = chemical symbol, value = float
    public Map<String, Float> constituants = new HashMap();


}
