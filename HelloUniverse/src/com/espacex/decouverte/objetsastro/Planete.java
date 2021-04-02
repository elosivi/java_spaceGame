/*
2 childs : PlaneteTellurique + PlaneteGazeuse
*/

package com.espacex.decouverte.objetsastro;
import com.espacex.decouverte.objetsastro.*;


/**
 * This class is abstract so it cannot be directly instantiated:
 * @see com.espacex.decouverte.objetsastro.PlaneteGazeuse : child class
 * @see com.espacex.decouverte.objetsastro.PlaneteTellurique : child class
 */
public abstract class Planete implements Comparable{
    public Atmosphere atmosphere;
    public String nom;
    public long diametre;
    public int nbPlanetesDecouvertes ;
    public Float distanceEtoile;// in million of km

    /**
     * constructor
     * @param nom
     */
    public Planete(String nom){
        this.nom = nom;
        nbPlanetesDecouvertes ++;
        //System.out.println(nbPlanetesDecouvertes);
    }

    /**
     * GETTER: get the name of planet
     * @return the name of the planet
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * The compareTo() method compare the distance between
     * planets and sun.
     * @param o
     * @return planets sorted by distance to the sun
     */
    @Override
    public int compareTo(Object o) {
        Planete autrePlanete = (Planete)o;
        return this.distanceEtoile.compareTo(autrePlanete.distanceEtoile);
    }

    //  ------- planet activity -------  //

    /**
     * revolution of the planet
     * @param degres
     * @return the nbr of the planet's lap completed
     */
    int revolution(int degres) {
    //  System.out.println("Je suis la planète "+ nom + " et je tourne autour de mon étoile");
        int round = degres / 360;
        return round;
    }

    /**
     * rotation of the planet
     * @param degres
     * @return the nbr of the planet's lap completed
     */
    int rotation(int degres) {
    //  System.out.println("Je suis la planète "+ nom + " et je tourne sur moi même");
        int round = degres / 360;
        return round;
    }

}


