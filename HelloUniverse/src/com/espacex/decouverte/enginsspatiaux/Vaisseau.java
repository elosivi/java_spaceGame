package com.espacex.decouverte.enginsspatiaux;

/**
 * This class is abstract so it cannot be directly instantiated:
 * @see <b>VaisseauCivil</b> : child class
 * @see <b>VaisseauDeGuerre</b> : child class
 */
public abstract class Vaisseau {
    public final TypeVaisseau type;
    public int blindage;
    public int nbPassagers;
    public int resistanceDuBouclier;
    public int tonnageMax;
    public int tonnageActuel; // can be modified only by emporterCargaison
    private int nbVaisseaux ;

    /**
     * constructor
     */
    protected Vaisseau(TypeVaisseau type) {
        this.type = type;
        nbVaisseaux++;
    }

    /**
     * @see VaisseauCivil and VaisseauDeGuerre classes wich override this method
     * @param tonnage
     */
    public abstract void emporterCargaison(int tonnage) throws DepassementTonnageException, NbPassagersInsuffisantException, CargoSupNbPassagersException;

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
