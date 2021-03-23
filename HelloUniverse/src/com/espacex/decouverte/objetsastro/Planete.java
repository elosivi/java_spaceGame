/*
2 childs : PlaneteTellurique + PlaneteGazeuse
*/

package com.espacex.decouverte.objetsastro;
import com.espacex.decouverte.objetsastro.*;

public abstract class Planete implements Comparable{
    public Atmosphere atmosphere;
    public String nom;
    public long diametre;
    public int nbPlanetesDecouvertes ;
    public Float distanceEtoile;// in million of km

    public Planete(String nom){
        this.nom = nom;
        nbPlanetesDecouvertes ++;
        //System.out.println(nbPlanetesDecouvertes);
    }

    public String getNom(){
        return this.nom;
    }
    @Override
    public int compareTo(Object o) {
        Planete autrePlanete = (Planete)o;
        return this.distanceEtoile.compareTo(autrePlanete.distanceEtoile);
    }

    //  ------- planet activity -------  //
    int revolution(int degres) {
    //  System.out.println("Je suis la planète "+ nom + " et je tourne autour de mon étoile");
        int round = degres / 360;
        return round;
    }

    int rotation(int degres) {
    //  System.out.println("Je suis la planète "+ nom + " et je tourne sur moi même");
        int round = degres / 360;
        return round;
    }

}


