package com.espacex.decouverte;

import com.espacex.decouverte.enginsspatiaux.*;

import static com.espacex.decouverte.enginsspatiaux.TypeVaisseau.*;

import com.espacex.decouverte.objetsastro.*;

import java.util.*;

/**
 * the main class
 */
public class HelloUniverse {

    public static void main(String... args) throws DepassementTonnageException {

// ////////////////////////////   S O L A R    S Y S T E M    ////////////////////////////////// //
        /**
         * creation of the solar system:
         * 4 telluric planets : Mercure, Venus, Terre, Mars
         * @see PlaneteTellurique
         * 4 gas planets : Jupiter, Uranus, Saturne, Neptune
         * @see PlaneteGazeuse
         * 1 galaxie : systemeSolaire
         * @see Galaxie
         */

        PlaneteTellurique Mercure = new PlaneteTellurique("Mercure", 0);
        Mercure.diametre = 4880;
        Mercure.distanceEtoile = 57.91f;

        PlaneteTellurique Venus = new PlaneteTellurique("Venus", 1);
        Venus.diametre = 12100;
        Venus.distanceEtoile = 108.2f;

        PlaneteTellurique Terre = new PlaneteTellurique("Terre", 2);
        Terre.diametre = 12756;
        Terre.distanceEtoile = 149.6f;

        PlaneteTellurique Mars = new PlaneteTellurique("Mars", 3);
        Mars.diametre = 6792;
        Mars.distanceEtoile = 227.9f;
        Atmosphere atmosphereMars = new Atmosphere();
        atmosphereMars.constituants.put("CO2", 95f);
        atmosphereMars.constituants.put("N2", 3f);
        atmosphereMars.constituants.put("AR", 1.5f);
        atmosphereMars.constituants.put("NO", 0.013f);
        Mars.atmosphere = atmosphereMars;

        System.out.println("L'atmosphère de " + Mars.nom + " est constituée de :");
        for (Map.Entry taux : atmosphereMars.constituants.entrySet()) {
            System.out.println(taux.getValue() + "% de " + taux.getKey());
        }


        PlaneteGazeuse Jupiter = new PlaneteGazeuse("Jupiter");
        Jupiter.diametre = 142984;
        Jupiter.distanceEtoile = 778.5f;

        PlaneteGazeuse Saturne = new PlaneteGazeuse("Saturne");
        Saturne.diametre = 120536;
        Saturne.distanceEtoile = 1434f;

        PlaneteGazeuse Uranus = new PlaneteGazeuse("Uranus");
        Uranus.diametre = 120536;
        Uranus.distanceEtoile = 2871f;

        PlaneteGazeuse Neptune = new PlaneteGazeuse("Neptune");
        Neptune.diametre = 49532;
        Neptune.distanceEtoile = 4495f;

        Galaxie systemeSolaire = new Galaxie("Système solaire", Mercure, Venus, Terre, Mars, Jupiter, Saturne, Uranus, Neptune);


        // ////////////////////////////   S T A R S H I P S    ////////////////////////////////// //

        /**
         * Création of starships, can be civil or war type
         * Both can take away cargo with different conditions
         * @see VaisseauCivil
         * @see VaisseauDeGuerre
         *
         */
        VaisseauDeGuerre chasseur = new VaisseauDeGuerre(CHASSEUR);
        chasseur.blindage = 156;
        chasseur.resistanceDuBouclier = 2;
        chasseur.nbPassagers = 42;

        VaisseauDeGuerre chasseur2 = new VaisseauDeGuerre(CHASSEUR);

        VaisseauCivil vaisseauMonde = new VaisseauCivil(VAISSEAUMONDE);
        vaisseauMonde.blindage = 4784;
        vaisseauMonde.resistanceDuBouclier = 30;
        vaisseauMonde.nbPassagers = 9;

        VaisseauDeGuerre fregate = new VaisseauDeGuerre(FREGATE);
        fregate.blindage = 356;
        fregate.resistanceDuBouclier = 12;
        fregate.nbPassagers = 14;

        VaisseauDeGuerre croiseur = new VaisseauDeGuerre(CROISEUR);
        croiseur.blindage = 92;
        croiseur.resistanceDuBouclier = 28;
        croiseur.nbPassagers = 2;

        VaisseauCivil cargo = new VaisseauCivil(CARGO);
        cargo.blindage = 2496;
        cargo.resistanceDuBouclier = 27;
        cargo.nbPassagers = 20;

        ////////////////////////////////////  F I R S T    P L A Y   //////////////////////////////////

        /**
         * game initialization
         * On planet terre there is allready some ships
         */
        // 2 chasseurs + 1 cargo, on Terre
        Terre.accueillirVaisseaux(chasseur, chasseur2, cargo);


        //////////////////////////////////// U S E R    P L A Y ////////////////////////////////////



/**
 * While the variable playAgain is set to "oui", player can play and play again
 * {@link HelloUniverse#doYouWantPlayAgain()}
 * {@link HelloUniverse#playerPlayAgainOrNot(String)}
 *
 * The play:
 *  step 1- player choose a starship :
 *  {@link HelloUniverse#choisirVaisseau()}
 *  the type of the ship chosen is verified
 *  If it's not an authorized type, player is invited to choose a new vessel
 *
 *  step 2- player choose a planet : {@link HelloUniverse#choisirPlanete()}
 *  the planet chosen is verified (type, dock available,...),
 *  If it's not an authorized planet, player is invited to choose a new planet
 *
 * 3- if planet chosen is ok, player can choose is cargo (in ton) : {@link HelloUniverse.choisirChargement()}
 *
 */

        /*      step 1 / 3       *       C H O O S E       S P A C E S H I P     *
         * ask user to indicate witch spaceship he wants, indicating the type
         * Check the value
         * if validated, memorize it
         */
        String playAgain = null;
        do {
            String monVaisseau = choisirVaisseau();
            Vaisseau vaisseauChoisi = null;

            if (monVaisseau != null) {
                switch (monVaisseau) {
                    case "CARGO":
                        vaisseauChoisi = cargo;
                        break;
                    case "VAISSEAUMONDE":
                        vaisseauChoisi = vaisseauMonde;
                        break;
                    case "CHASSEUR":
                        vaisseauChoisi = chasseur;
                        break;
                    case "FREGATE":
                        vaisseauChoisi = fregate;
                        break;
                    case "CROISEUR":
                        vaisseauChoisi = croiseur;
                        break;
                    default:
                        vaisseauChoisi = null;
                        choisirVaisseau();
                }

            }

            // if vaisseauChoisi is allready null...
            try {
                System.out.println("... Embarquement immédiat à bord d'un vaisseau de type " + vaisseauChoisi.type.name() + "...");
            } catch (NullPointerException ue) {
                System.out.println("oups! Le vaisseau est null...");
                doYouWantPlayAgain();
            }


            /*    step 2 / 3     *      C H O O S E       A     P L A N E T     *
             * Ask the user on which planet he would like to dock, indicating the name
             * Check the value
             * if validated, memorize it
             */


            // planete choosen
            String maPlanete = choisirPlanete();
            System.out.println("...Accostage sur " + maPlanete + " en cours ...");

            // Check if planete choosen is valid and find the object Planete with the same name
            Planete planeteChoisie = null;
            Iterator<Planete> it = systemeSolaire.planetes.iterator();
            while (it.hasNext()) {
                Planete p = it.next();
                if (maPlanete.equals(p.nom)) {
                    planeteChoisie = p;
                    break;
                }
            }
            if (planeteChoisie == null) {
                System.out.println("Désolée je ne connais pas cette planète du système solaire! ");
                choisirPlanete();
            }


            // ok name and type of planet are valid...

            // Verify if the dock is full or not,
            // if yes : forbid the ship to dock and play again
            if (!((PlaneteTellurique) planeteChoisie).restePlaceDisponible(vaisseauChoisi.type)) {
                System.out.println("" +
                        "Le vaisseau ne peut pas se poser sur la planete par manque de place dans la baie");
                playAgain = doYouWantPlayAgain();

                // if not : ok planet choosen validated, continue and choose the cargo...
            } else {
                ((PlaneteTellurique) planeteChoisie).accueillirVaisseaux(vaisseauChoisi);
                System.out.println("INFO VAISSEAU (pour rappel) :");
                System.out.println("     Le tonnage max de ton vaisseau est de: " + vaisseauChoisi.tonnageMax + "t");
                System.out.println("     Ton tonnage actuel est de: " + vaisseauChoisi.tonnageActuel + "t");
                System.out.println("     Le nb de passagers à ton bord est de: " + vaisseauChoisi.nbPassagers);

                /*  3 / 3   *       C H O O S E       A      C A R G O      *
                 * ask the user how much cargo he would like to load
                 * Check the value
                 * if validated, memorize it
                 */

                choisirChargement(vaisseauChoisi);
            }

            // the session is successful does the player want to replay?

            if( playAgain == null ) {
                playAgain = doYouWantPlayAgain();
            }


        } while (playAgain.equals("oui"));
    }


    /////////////////////////////////     F U N C T I O N S      C H O O S E     ///////////////////////////////////////////////////

    /**
     * This method listen the ship chosen by the player in the terminal, verify if this type of ship exist
     * if not: player is invited to choose an other ship
     * if yes: the ship chosen is returned
     *
     * @return the ship chosen by player (String vaisseauChoisi)
     */
    public static String choisirVaisseau() {
        //spaceship existing
        String[] vaisseauxCivils = {"CARGO", "VAISSEAUMONDE"};
        String[] vaisseauxDeGuerre = {"FREGATE", "CROISEUR", "CHASSEUR"};

        //listen user choice
        System.out.println("--------------------------------------------");
        System.out.println("           A     T O I     D E     J O U E R   ! ! !");
        System.out.println("------------------   1   ------------------ \n " +
                "Choisi un vaisseau : ");
        System.out.println(Arrays.toString(values()));


        // Starship choose is it a null object ?

        ;
        Scanner scV = new Scanner(System.in);
        String monVaisseau = scV.nextLine();

        // switch in good format
        String vaisseauChoisi = monVaisseau.toUpperCase();

        // witch type of spaceship ? is it validated ?
        boolean civil = Arrays.asList(vaisseauxCivils).contains(vaisseauChoisi);
        boolean deGuerre = Arrays.asList(vaisseauxDeGuerre).contains(vaisseauChoisi);

        if (civil || deGuerre) {
            return vaisseauChoisi; // si input invalid avant ne retourne plus ici
        } else {
            System.out.println("Désolée ce type de vaisseau n'existe pas ... ! ");
            return choisirVaisseau();
        }

    }

    /**
     * This method listen the planet chosen by the player in the terminal,
     * verify if this type of planet is a valid planet
     *
     * @return the planet chosen by player (String maPlanete)
     */
    public static String choisirPlanete() {
        // planets authorized
        System.out.println("------------------   2   ------------------");
        System.out.println(" Maintenant choisi une planete :");
        String[] PlanetesTelluriques = {"Mercure", "Venus", "Terre", "Mars"};
        String[] PlanetesGazeuses = {"Jupiter","Saturne","Uranus","Neptune"};
        System.out.println(Arrays.toString(PlanetesTelluriques));


        //listen player choice
        Scanner scPlanete = new Scanner(System.in);
        String result = scPlanete.nextLine().toLowerCase(Locale.ROOT);
        String maPlanete = result.substring(0, 1).toUpperCase() + result.substring(1);

        //check kind of planet, if tellurique ok, if not replay
        boolean tellurique = Arrays.asList(PlanetesTelluriques).contains(maPlanete);
        boolean gazeuse = Arrays.asList(PlanetesGazeuses).contains(maPlanete);

        if (tellurique) {
            return maPlanete;
        } else if(gazeuse) {
            System.out.println("Oups cette planète est gazeuse, c'est un peu risqué tu ne crois pas ?! ");
            return choisirPlanete();
        } else {
            System.out.println("Désolée je ne connais pas cette planète du système solaire! ");
            return choisirPlanete();
        }
    }


    /**
     * This method listen the ton of cargo chosen by the player in the terminal,
     * check if an exception is thrown
     * if yes, offers the player to enter a new quantity
     * if cargo is validated, play is finished, player can play again
     *
     * @param monVaisseau, the starship choosen by the player in step 1
     */
    public static void choisirChargement(Vaisseau monVaisseau) {
        System.out.println("------------------   3   ------------------");
        System.out.println(" Dernière étape... Combien de tonne(s) de chargement souhaites tu embarquer ? :  ... ");

        // listen player
        int monChargement = 0;

        // scanner is it an int ?...
        try {
            Scanner scCargo = new Scanner(System.in);
            monChargement = scCargo.nextInt();
        } catch (InputMismatchException in) {
            System.out.println("Petite erreur on dirait ! Merci d'entrer un nombre entier, allez... rejoue !");
            choisirChargement(monVaisseau);
            return;
        }

        //ok, scanner is an int ...
        System.out.println("...demande d' accord en cours ...");

        // verify if this cargo is authorized
        try {
            monVaisseau.emporterCargaison(monChargement);
        } catch (DepassementTonnageException dep) {
            System.out.println(dep.getMessage());
            System.out.println("Souhaites-tu choisir une autre cargaison? (oui / non) ");
            Scanner sc = new Scanner(System.in);
            String result = sc.nextLine().toLowerCase(); // yes, no, other
            if (result.equals("oui")) {
                choisirChargement(monVaisseau);
                return;
            } else {
                System.out.println(" // operation annulée //");
                doYouWantPlayAgain();
            }
            ;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            doYouWantPlayAgain();
        } // the 2nd cacth will not be accepted by udemy because don't know the 2 other exceptions
    }


    /**
     * The doYouWantPlayAgain() method invite player to play again
     *
     * @return "oui" "non" or other answer
     */

    public static String doYouWantPlayAgain() {
        System.out.println(" \n * * *    On continue ???  * * *\n (Voulez vous rejouer? oui/non)\n");
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine().toLowerCase();
        playerPlayAgainOrNot(result);
        return result; // oui, non, autre
    }

    /**
     * The playerPlayAgainOrNot() method analyse player answer
     * if answer is not yes or no, player is invited to answer again
     *
     * @param reponsePlayer
     * @see HelloUniverse#doYouWantPlayAgain()
     */
    public static void playerPlayAgainOrNot(String reponsePlayer) {
        if (reponsePlayer.equals("non")) {
            System.out.println(" Ok Bye!");
            return;
        } else if (!reponsePlayer.equals("oui")) {
            System.out.println(" Désolée je ne comprends pas votre réponse: " + reponsePlayer);
            doYouWantPlayAgain();
        }
    }
}



