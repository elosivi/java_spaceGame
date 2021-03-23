package com.espacex.decouverte.objetsastro;

import java.util.*;

public class Galaxie {
    public String nom;
    public Set<Planete> planetes = new TreeSet();

    public Galaxie(){

    };
    public Galaxie(Planete nouvellePlanete){
        planetes.add(nouvellePlanete);
        sortPlanetes();
    };

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

    public void sortPlanetes(){
        Iterator<Planete> it = planetes.iterator();
        System.out.println("Le "+ this.nom +" comprends les planetes: ");
        while(it.hasNext()){
            Planete p = it.next();
            System.out.println(p.nom + " | à "+ p.distanceEtoile+ " million de km du soleil");
        }
    }
}