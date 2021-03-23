package com.espacex.decouverte.enginsspatiaux;

public class VaisseauCivil extends Vaisseau {

    public VaisseauCivil(TypeVaisseau typeVaisseau){
        this.type = typeVaisseau;
        switch (this.type){
            case CARGO : this.tonnageMax = 500; break;
            case VAISSEAUMONDE : this.tonnageMax = 2000; break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.type);
        }

        /*System.out.println("\nNOUVEAU VAISSEAU CIVIL #"+ this.setNbVaisseaux()
                +" de type : "+ this.type + " | cargo ton max: "+ this.tonnageMax); // Test*/
    }

    @Override
    /*Cargo is authorized only if:
     *  total actualCargo + new tonnage <= tonnageMax
     * */
    public int emporterCargaison(int tonnage) {
        System.out.println(" CHECK :\nTon tonnage actuel "+ this.tonnageActuel + " VS ton tonnage max: " + tonnageMax); // test
        int quantiteRefusee;
        if ((tonnageActuel + tonnage <= tonnageMax)) {
            quantiteRefusee = 0;
            tonnageActuel += tonnage;
        }else{
            quantiteRefusee = (tonnageActuel+tonnage)-tonnageMax;
            tonnageActuel += (tonnage-quantiteRefusee); // tonnageActuel = tonnageMax
        }
        System.out.println("Nouveau tonnage: "+tonnageActuel );
        System.out.println("Quantité refusée : "+ quantiteRefusee);
        return quantiteRefusee;
    }
}
