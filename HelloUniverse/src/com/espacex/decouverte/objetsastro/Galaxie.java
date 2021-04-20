package com.espacex.decouverte.objetsastro;

import java.util.*;

/**
 * the Galaxie class create new galaxie,
 * String nom is the name of the Galaxie
 * Set planetes the list of planets inside this Galaxie
 */
public class Galaxie {
    public String nom;
    public Set<Planete> planetes = new TreeSet();

    /**
     * constructor #1
     */

    public Galaxie(){

    };

    /**
     * constructor #2
     * @param nouvellePlanete which is a Planet object, the new planet added to the galaxies
     */
    public Galaxie(Planete nouvellePlanete){
        planetes.add(nouvellePlanete);
        sortPlanetes();
    };

    /**
     * constructor #3
     * @param nom : a String which is the name of the galaxie
     * @param nouvellesPlanetes : a list of planet object to add to the galaxie
     */

    public Galaxie(String nom, Planete... nouvellesPlanetes) {

        this.nom= nom;
        System.out.println("set size "+planetes.size());
        System.out.println("add "+nouvellesPlanetes.length +" planetes");

        // add each planet in the arrayList in param, in the TreeSet of the galaxie
        for (Planete p :  nouvellesPlanetes) {
            planetes.add(p);
        }
        // sort the arrayList in chronological order of distance to the sun
        sortPlanetes();

    }

    /**
     * the sortPlanetes() method, sort the planets according to her distance to the sun
     */
    public void sortPlanetes(){
        Iterator<Planete> it = planetes.iterator();
        System.out.println("Le "+ this.nom +" comprends les planetes: ");
        while(it.hasNext()){
            Planete p = it.next();
            System.out.println(p.nom + " | à "+ p.distanceEtoile+ " million de km du soleil");
        }
    }
}