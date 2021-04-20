package com.espacex.decouverte.enginsspatiaux;
/**
 * This exception is ot used by Udemy tests
 * This exception is about a cargo not allowed because of the number of passengers
 * @see VaisseauDeGuerre
 */
public class NbPassagersInsuffisantException extends Exception {

    public NbPassagersInsuffisantException() {
        super("Votre nombre de passagers est inferieur à 12 donc insuffisant pour charger une cargaison");
    }
}
