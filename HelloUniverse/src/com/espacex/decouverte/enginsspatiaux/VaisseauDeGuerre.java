package com.espacex.decouverte.enginsspatiaux;


public class VaisseauDeGuerre extends Vaisseau {
    private boolean armesDesactivees; // only modified by desactiverArmes or

    public VaisseauDeGuerre(TypeVaisseau typeVaisseau){
        this.type = typeVaisseau;

        switch (this.type){
             case FREGATE: this.tonnageMax = 50; break;
             case CROISEUR: this.tonnageMax = 1000; break;
             case CHASSEUR: this.tonnageMax = 0; break;
        }

        /*System.out.println("\nNOUVEAU VAISSEAU DE GUERRE #"+this.setNbVaisseaux()+" de type : "
                + this.type + "| cargo ton max: "+ this.tonnageMax); // test*/
    }

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

    public void desactiverArmes(){
        this.armesDesactivees = true;
        System.out.println("Désactivation des armes d'un vaisseau de type "+ this.type.name());
    }

    public void activerArmes(){
        this.armesDesactivees = false;
        System.out.println("Activation des armes d'un vaisseau de type "+ this.type.name());
    }

    public void activerBouclier(){
        System.out.println("Activation du bouclier d'un vaisseau de type "+ this.type.name());
        this.desactiverArmes();
    }

    @Override
    /* Cargo is authorized only if:
    *  there is min 12 passengers on bord
    *   Then, there is others conditions :
    *       spaceship can refuse all or a part of the cargo if :
    *       the tonnageMax is reach
    *       or if tonnage > nbPassagers*2
    * */
    public int emporterCargaison(int tonnage) {
            int quantiteRefusee;
        System.out.println(" Check: \n ton tonnage actuel "+ this.tonnageActuel + " VS ton tonnage max: " + tonnageMax); // test
        //System.out.println("De plus, en tant que Vaisseau de Guerre ayant " + nbPassagers+ " passagers à son bord, tu es limité à: "+ );
            if (nbPassagers>=12){
                if(tonnageActuel+tonnage > tonnageMax){
                    quantiteRefusee = tonnageActuel+tonnage-tonnageMax;
                    tonnageActuel = tonnageActuel + (tonnage-quantiteRefusee); // tonnageActuel = tonnageMax
                }else if(tonnage > nbPassagers*2){
                    quantiteRefusee = tonnage-(nbPassagers*2);
                    tonnageActuel += (tonnage-quantiteRefusee);
                }else{
                    quantiteRefusee = 0;
                    tonnageActuel += tonnage;
                }
            }else{
                quantiteRefusee = tonnage;
            }
            System.out.println("   Quantité refusée : "+ quantiteRefusee);
            return quantiteRefusee;
    }
}


