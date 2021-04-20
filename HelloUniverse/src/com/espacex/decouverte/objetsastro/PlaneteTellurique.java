package com.espacex.decouverte.objetsastro;

import com.espacex.decouverte.enginsspatiaux.TypeVaisseau;
import com.espacex.decouverte.enginsspatiaux.VaisseauCivil;
import com.espacex.decouverte.enginsspatiaux.VaisseauDeGuerre;
import com.espacex.decouverte.enginsspatiaux.Vaisseau;

import java.util.Arrays;

/**
 * the PlaneteTellurique class implements telluric planets,
 * habitable and accostable planet
 */
public class PlaneteTellurique extends Planete implements Habitable {

    public int totalVisiteurs;
    public int tailleBaie;
    public int civilPlacesLibres;
    public int guerrePlacesLibres;
    // the bay is separated in 2 zones, one for civilians spaceship, one for war spaceship. The 2 bays have the same number of places
    //Vaisseau[civil/guerre][places for this cat]
    public Vaisseau[][] vaisseauxAccostes;

    /**
     * constructor
     * @param nom
     * @param tailleBaie
     */
    public PlaneteTellurique(String nom, int tailleBaie) {
        super(nom);
        this.tailleBaie= tailleBaie;
        this.vaisseauxAccostes = new Vaisseau[2][tailleBaie];
        this.civilPlacesLibres = this.guerrePlacesLibres = tailleBaie;
    }

    /**
     * The HowManyCivilFreePlaces() method verify if there is available place for a civil ship
     * in the arrayList vaisseauxAccostes[0]
     * modify civilPlacesLibres attribute
     * @see com.espacex.decouverte.objetsastro.PlaneteTellurique#restePlaceDisponible
     */
    public void HowManyCivilFreePlaces() {
        for (int place = 0; place < vaisseauxAccostes[0].length; place++) {
            if (vaisseauxAccostes[0][place] == null) {
                civilPlacesLibres++;
            }
        }
    }
    /**
     * The HowManyWarFreePlaces() method verify if there is available place for a war ship
     * in the arrayList vaisseauxAccostes[1]
     * modify guerrePlacesLibres attribute
     * @see com.espacex.decouverte.objetsastro.PlaneteTellurique#restePlaceDisponible
     */
    public void HowManyWarFreePlaces() {
        for (int place = 0; place < vaisseauxAccostes[1].length; place++) {
            if (vaisseauxAccostes[1][place] == null) {
                guerrePlacesLibres++;
            }
        }
    }

    /**
     * The restePlaceDisponible() method
     * Before starships dock on the planet : the bay is it full ?
     * @param typeVaisseau
     * @return true (bay is free) or false (bay is full)
     */
    public boolean restePlaceDisponible(TypeVaisseau typeVaisseau) {
        boolean civil = false;
        switch (typeVaisseau) {
            case CARGO:
            case VAISSEAUMONDE:
                civil = true;
        }
        if (civil && civilPlacesLibres > 0) {
            //System.out.println("... tu peux accoster ton vaisseau civil !");
            return true;
        }

        if (!civil && guerrePlacesLibres > 0) {
            //System.out.println("... tu peux accoster ton vaisseau de guerre!");
            return true;
        }
        return false;
    }

    /**
     * the accueillirVaisseaux() method override the instance Habitable
     * this method verify if there is anought place for each starship pass in param
     * @param nouveauxVaisseaux (varargs)
     */
//
    @Override
    public void accueillirVaisseaux(Vaisseau... nouveauxVaisseaux) {
        // loop on each new starship
        for (int i = 0; i < nouveauxVaisseaux.length; i++) {

            // *** Is there a place for this starship? if not, return to the next one
            if (!restePlaceDisponible(nouveauxVaisseaux[i].type)) {
                return;
            }

            // *** If there is a place...
            // update nbPassagers
            this.totalVisiteurs += nouveauxVaisseaux[i].nbPassagers;

            // If it's a war starship...
            if (nouveauxVaisseaux[i] instanceof VaisseauDeGuerre) {
                // 1- disables weapons
                ((VaisseauDeGuerre) nouveauxVaisseaux[i]).desactiverArmes();
                // 2- Add the starship in the bay: in vaisseauxAccostes[1][null];
                if (guerrePlacesLibres > 0) {
                    for (int warPlace = 0; warPlace < vaisseauxAccostes[1].length; warPlace++) {
                        if (vaisseauxAccostes[1][warPlace] == null) {
                            vaisseauxAccostes[1][warPlace] = nouveauxVaisseaux[i];
                            this.guerrePlacesLibres--;
                            break;
                        }
                    }
                }
            }

            // If civil starship...
            if (nouveauxVaisseaux[i] instanceof VaisseauCivil) {
                //Add the starship in the bay: in vaisseauxAccostes[0][null];
                if (civilPlacesLibres > 0) {
                    for (int civilPlace = 0; civilPlace < vaisseauxAccostes[0].length; civilPlace++) {
                        if (vaisseauxAccostes[0][civilPlace] == null) {
                            vaisseauxAccostes[0][civilPlace] = nouveauxVaisseaux[i];
                            this.civilPlacesLibres--;
                            break;
                        }
                    }
                }
            }

        }
        System.out.println("INFO PLANETE :\n      Desormais il reste sur cette planete : " + civilPlacesLibres +
                " place/s pour des vaisseaux civils, et " + guerrePlacesLibres + " pour des vaisseaux de Guerre.)\n");
    }


}
