package com.espacex.decouverte.enginsspatiaux;

/**
 * This exception is ot used by Udemy tests
 * This exception is about a cargo not allowed because of the number of passengers
 * @see VaisseauDeGuerre
 */
public class CargoSupNbPassagersException extends Exception{

    public CargoSupNbPassagersException() {
        super(" La charge est superieur à 2x le nombre de passagers, vous ne pouvez eporter cette cargaison ");
    }
}
