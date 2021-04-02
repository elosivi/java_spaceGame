package com.espacex.decouverte.enginsspatiaux;

/**
 * type of ship that can be used
 */
public enum TypeVaisseau {
    CHASSEUR("Chasseur"),
    CARGO("Cargo"),
    CROISEUR("Croiseur"),
    FREGATE("Frégate"),
    VAISSEAUMONDE("Vaisseau-Monde");

    String nom;

    /**
     * constructor which define the type of ship
     * @param nomTypeVaisseau
     */
    private TypeVaisseau(String nomTypeVaisseau) {
        this.nom = nomTypeVaisseau;
    }

}
