package com.espacex.decouverte.enginsspatiaux;

/**
 * This class is abstract so it cannot be directly instantiated:
 * @see <b>VaisseauCivil</b> : child class
 * @see <b>VaisseauDeGuerre</b> : child class
 */
public abstract class Vaisseau {
    public static TypeVaisseau type;
    public int blindage;
    public int nbPassagers;
    public int resistanceDuBouclier;
    public int tonnageMax;
    protected int tonnageActuel; // can be modified only by emporterCargaison
    private int nbVaisseaux ;

    /**
     * constructor
     */
    Vaisseau(){
        nbVaisseaux++;
    }

    /**
     * @see VaisseauCivil and VaisseauDeGuerre classes wich override this method
     * @param tonnage
     * @return
     */
    public abstract int emporterCargaison(int tonnage);

    /**
     * @see VaisseauCivil and VaisseauDeGuerre classes wich override this method
     */
    public void activerBouclier(){
        System.out.println(" Activation du bouclier d'un vaisseau de type "+ this.type);
    }

    /**
     * @see VaisseauCivil and VaisseauDeGuerre classes wich override this method
     */
    public void desactiverBouclier(){
        System.out.println(" Désactivation du bouclier d'un vaisseau de type " + this.type);
    }

    /**
     *
     * @return the nb of ships created
     */
    public int getNbVaisseaux(){
        return nbVaisseaux;
    }

    /**
     *
     * @return the type of ship from enum
     * @see TypeVaisseau
     */
    public String getType(){
        String type = this.type.name();
        return type;
    }

}
