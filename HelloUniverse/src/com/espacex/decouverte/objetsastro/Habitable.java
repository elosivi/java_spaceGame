/*
implemented by PlanetTellurique
*/
package com.espacex.decouverte.objetsastro;
import com.espacex.decouverte.enginsspatiaux.Vaisseau;

/**
 * the interface Habitable implements the class "PlaneteTellurique"
 * Allow the planet to accept a ship on her dock
 * @see com.espacex.decouverte.objetsastro.PlaneteTellurique#accueillirVaisseaux(Vaisseau...) 
 */
public interface Habitable {

    // welcome a new spatialship
    public abstract void accueillirVaisseaux(Vaisseau... nouveauxVaisseaux);
}
