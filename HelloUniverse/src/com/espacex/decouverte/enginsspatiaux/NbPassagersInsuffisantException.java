package com.espacex.decouverte.enginsspatiaux;

public class NbPassagersInsuffisantException extends Exception {

    public NbPassagersInsuffisantException() {
        super("Votre nombre de passagers est inferieur à 12 donc insuffisant pour charger une cargaison");
    }
}
