package com.espacex.decouverte.enginsspatiaux;

/**
 * child of " Vaisseau "
 * this class have no child
 */

public class VaisseauDeGuerre extends Vaisseau {
    private boolean armesDesactivees; // only modified by desactiverArmes or

    /**
     * constructor who determines the type of vessel and tonnageMax
     * @param type (object typeVaisseau)
     */
    public VaisseauDeGuerre(TypeVaisseau type){
        super(type);

        switch (this.type){
             case FREGATE: this.tonnageMax = 50; break;
             case CROISEUR: this.tonnageMax = 1000; break;
             case CHASSEUR: this.tonnageMax = 0; break;
        }

        /*System.out.println("\nNOUVEAU VAISSEAU DE GUERRE #"+this.setNbVaisseaux()+" de type : "
                + this.type + "| cargo ton max: "+ this.tonnageMax); // test*/
    }

    /**
     * this method allow a "vaisseauDeGuerre" to attack an other ship if its weapons are actives
     * @param cible
     * @param armeUtilisee
     * @param dureeAttaque
     */
    public void attaque(Vaisseau cible, String armeUtilisee, int dureeAttaque){

        if (armesDesactivees) {
            System.out.println("Attaque impossible, l'armement est désactivé.");
        } else {
            System.out.println(
                    "Un vaisseau de type " + this.type.name() + " attaque un vaisseau de type " + cible.type.name() + " en utilisant l'arme "
                            + armeUtilisee + " pendant " + dureeAttaque + " minutes.");
            //destroy the shield
            cible.resistanceDuBouclier = 0;

            //decrease shielding /2
            cible.blindage = cible.blindage/2;
        }
    }

    /**
     * this method deactivate weapons
     */
    public void desactiverArmes(){
        this.armesDesactivees = true;
        System.out.println("Désactivation des armes d'un vaisseau de type "+ this.type.name());
    }
    /**
     * this method activate weapons
     */
    public void activerArmes(){
        this.armesDesactivees = false;
        System.out.println("Activation des armes d'un vaisseau de type "+ this.type.name());
    }

    /**
     * this method activate a shield
     */
    public void activerBouclier(){
        System.out.println("Activation du bouclier d'un vaisseau de type "+ this.type.name());
        this.desactiverArmes();
    }

    /**
     * This method is a mother method and throws 3 exceptions.
     * this method verify and allow to take away cargo :
     * Cargo is authorized only if there is min 12 passengers on bord
     * @see NbPassagersInsuffisantException
     * Then, spaceship can refuse all or a part of the cargo if :
     *     the tonnageMax is reach
     * @see DepassementTonnageException
     *     or if tonnage > nbPassagers*2
     * @see CargoSupNbPassagersException
     *
     * This method modify changes the current tonnage of the vessel
     * @param tonnage, tonnage of the new cargo to take away
     */
    @Override
//  public void emporterCargaison(int tonnage) throws DepassementTonnageException, NbPassagersInsuffisantException, CargoSupNbPassagersException{
    public void emporterCargaison(int tonnage) throws DepassementTonnageException {
        if(nbPassagers<12) {
//            throw new NbPassagersInsuffisantException();
            //System.out.println("nb passagers insuffisants "); // pour udemy car ne prend pas en charge qu'1 seule exception
            throw new DepassementTonnageException(tonnage);
        }

        if((nbPassagers>=12)&&(tonnage < nbPassagers*2)) {
//            throw new CargoSupNbPassagersException();
            //System.out.println("tonnage superieur à la quantité autorisée ( " + nbPassagers * 2 + "t ) " +
             //       "pour ce nombre de passagers ( " + nbPassagers + " )"); // pour udemy car ne prend pas en charge qu'1 seule exception
            throw new DepassementTonnageException(tonnage);
        }

        if ((nbPassagers>=12)&&(tonnage > nbPassagers*2)) {
            System.out.println(" Check... (tonnage actuel "+ this.tonnageActuel + " ... ton tonnage max: " + tonnageMax +
                    " ... nb passagers à bord: "+nbPassagers+" ...)"); // test
            if ((tonnageActuel + tonnage > tonnageMax)) {
                int tonnageEnExces = -tonnageMax - (tonnageActuel + tonnage);
                throw new DepassementTonnageException(tonnageEnExces);
            }

        }
        this.tonnageActuel += tonnage;
        System.out.println("Nouveau tonnage: "+ tonnageActuel);
    }
}


