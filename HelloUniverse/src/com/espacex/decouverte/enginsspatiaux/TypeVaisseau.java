package com.espacex.decouverte.enginsspatiaux;

public enum TypeVaisseau {
    CHASSEUR("Chasseur"),
    CARGO("Cargo"),
    CROISEUR("Croiseur"),
    FREGATE("Frégate"),
    VAISSEAUMONDE("Vaisseau-Monde");

    String nom;

    private TypeVaisseau(String nomTypeVaisseau) {
        this.nom = nomTypeVaisseau;
    }

}
