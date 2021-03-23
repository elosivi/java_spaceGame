package com.espacex.decouverte.enginsspatiaux;

public abstract class Vaisseau {
    public static TypeVaisseau type;
    public int blindage;
    public int nbPassagers;
    public int resistanceDuBouclier;
    public int tonnageMax;
    protected int tonnageActuel; // can be modified only by emporterCargaison
    private static int nbVaisseaux ;

    Vaisseau(){
        nbVaisseaux++;
    }
    public abstract int emporterCargaison(int tonnage);

    public void activerBouclier(){
        System.out.println(" Activation du bouclier d'un vaisseau de type "+ this.type);
    }

    public void desactiverBouclier(){
        System.out.println(" Désactivation du bouclier d'un vaisseau de type " + this.type);
    }

    public int setNbVaisseaux(){
        return nbVaisseaux;
    }

    public String getType(){
        String type = this.type.name();
        return type;
    }

}
