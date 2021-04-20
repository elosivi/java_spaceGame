package com.espacex.decouverte.enginsspatiaux;

public class CargoSupNbPassagersException extends Exception{

    public CargoSupNbPassagersException() {
        super(" La charge est superieur à 2x le nombre de passagers, vous ne pouvez eporter cette cargaison ");
    }
}
