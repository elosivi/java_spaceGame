package com.espacex.decouverte.objetsastro;

import com.espacex.decouverte.enginsspatiaux.TypeVaisseau;
import com.espacex.decouverte.enginsspatiaux.VaisseauCivil;
import com.espacex.decouverte.enginsspatiaux.VaisseauDeGuerre;
import com.espacex.decouverte.enginsspatiaux.Vaisseau;

import java.util.Arrays;

public class PlaneteTellurique extends Planete implements Habitable {

    public int totalVisiteurs;
    public int tailleBaie;
    public int civilPlacesLibres;
    public int guerrePlacesLibres;
    // the bay is separated in 2 zones, one for civilians spaceship, one for war spaceship. The 2 bays have the same number of places
    //Vaisseau[civil/guerre][places for this cat]
    public Vaisseau[][] vaisseauxAccostes;

    public PlaneteTellurique(String nom, int tailleBaie) {
        super(nom);
        this.tailleBaie= tailleBaie;
        this.vaisseauxAccostes = new Vaisseau[2][tailleBaie];
        this.civilPlacesLibres = this.guerrePlacesLibres = tailleBaie;
    }

    public void HowManyCivilFreePlaces() {
        for (int place = 0; place < vaisseauxAccostes[0].length; place++) {
            if (vaisseauxAccostes[0][place] == null) {
                civilPlacesLibres++;
            }
        }
    }

    public void HowManyWarFreePlaces() {
        for (int place = 0; place < vaisseauxAccostes[1].length; place++) {
            if (vaisseauxAccostes[1][place] == null) {
                guerrePlacesLibres++;
            }
        }
    }

    // Before starships dock on the planet : the bay is it full?
    public boolean restePlaceDisponible(TypeVaisseau typeVaisseau) {
        boolean civil = false;
        switch (typeVaisseau) {
            case CARGO:
            case VAISSEAUMONDE:
                civil = true;
        }
        if (civil && civilPlacesLibres > 0) {
            System.out.println("... tu peux accoster ton vaisseau civil !");
            return true;
        }

        if (!civil && guerrePlacesLibres > 0) {
            System.out.println("... tu peux accoster ton vaisseau de guerre!");
            return true;
        }
        return false;
    }

    // override the instance Habitable
    @Override
    public void accueillirVaisseaux(Vaisseau... nouveauxVaisseaux) {
        // Send to the player info about the free places;
        System.out.println("\n ----------------\nACCUEILLIR " + nouveauxVaisseaux.length + " VAISSEAUX sur " + this.nom + ": ");
        System.out.println("La baie de " + this.nom + " peut accueillir " + this.tailleBaie + " vaisseaux civils et "
                + this.tailleBaie + " vaisseaux de guerre.");
        System.out.println("Il reste :" + civilPlacesLibres + " place/s libre/s pour les vaisseaux civils \net "
                + guerrePlacesLibres + " place/s libre/s pour les vaisseaux de guerre.\n ");


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
        System.out.println("\n La planète " + this.nom + " accueille désormais: " + Arrays.deepToString(vaisseauxAccostes)); // Test
        System.out.println("Il lui reste: " + civilPlacesLibres + " place/s pour des vaisseaux civils, et " + guerrePlacesLibres + " pour des vaisseaux de Guerre.\n");
    }


}
