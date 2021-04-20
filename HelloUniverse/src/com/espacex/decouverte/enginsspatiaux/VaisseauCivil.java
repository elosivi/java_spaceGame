package com.espacex.decouverte.enginsspatiaux;

/**
 * child of " Vaisseau "
 * this class have no child
 */
public class VaisseauCivil extends Vaisseau {


    /**
     * constructor who determines the type of vessel and tonnageMax
     * @param type (object TypeVaisseau)
     */
    public VaisseauCivil(TypeVaisseau type){
        super(type);
        switch (this.type){
            case CARGO : this.tonnageMax = 500; break;
            case VAISSEAUMONDE : this.tonnageMax = 2000; break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }

        /*System.out.println("\nNOUVEAU VAISSEAU CIVIL #"+ this.setNbVaisseaux()
                +" de type : "+ this.type + " | cargo ton max: "+ this.tonnageMax); // Test*/
    }
    /**
     * This method is a mother method
     * this method verify and allow to take away cargo :
     * Cargo is authorized only if tonnageActuel + tonnage <= tonnageMax
     * This method modify changes the current tonnage of the vessel
     * @param tonnage, tonnage of the new cargo to take away
     */
    @Override
    public void emporterCargaison(int tonnage) throws DepassementTonnageException{
        System.out.println(" CHECK ... (Ton tonnage actuel "+ this.tonnageActuel + " ... ton tonnage max: " + tonnageMax+" )"); // test
//        int quantiteRefusee;
//        if ((tonnageActuel + tonnage <= tonnageMax)) {
//            quantiteRefusee = 0;
//            tonnageActuel += tonnage;
//        }else{
//            quantiteRefusee = (tonnageActuel+tonnage)-tonnageMax;
//            tonnageActuel += (tonnage-quantiteRefusee); // tonnageActuel = tonnageMax
//        }
//        System.out.println("Nouveau tonnage: "+tonnageActuel );
//        System.out.println("Quantité refusée : "+ quantiteRefusee);
//        return quantiteRefusee;

        if((tonnageActuel + tonnage > tonnageMax)){
            int tonnageEnExces = tonnageMax - (tonnageActuel + tonnage);
            throw new DepassementTonnageException(tonnageEnExces);
        }
        tonnageActuel += tonnage;
        System.out.println("Nouveau tonnage: "+ tonnageActuel);
    }
}
