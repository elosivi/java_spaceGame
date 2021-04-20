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
        atmosphereMars.constituants.put("CO2", 95f );
        atmosphereMars.constituants.put("N2", 3f );
        atmosphereMars.constituants.put("AR", 1.5f );
        atmosphereMars.constituants.put("NO", 0.013f );
        Mars.atmosphere = atmosphereMars;

        System.out.println("L'atmosphère de "+ Mars.nom + " est constituée de :");
        for (Map.Entry taux : atmosphereMars.constituants.entrySet()){
            System.out.println( taux.getValue()+"% de "+ taux.getKey());
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


        String playAgain;
/**
 * While the variable playAgain is set to "oui", player can play and play again
 * The play:
 *  1- player choose a starship : {@link HelloUniverse#choisirVaisseau()}
 *  the type of the ship chosen is verified
 *  If it's not an authorized type, player is invited to choose a new vessel
 *
 *  2- player choose a planet : {@link HelloUniverse#choisirPlanete()}
 *  the planet chosen is verified (type, dock available,...),
 *  If it's not an authorized planet, player is invited to choose a new planet
 *
 * 3- if planet chosen is ok, player can choose is cargo (in ton) : {@link HelloUniverse.choisirChargement()}
 *
 */
        do {

            /*  1 / 3
             * C H O O S E       S P A C E S H I P
             * ask user to indicate witch spaceship he wants, indicating the type
             */

            String monVaisseau = choisirVaisseau();
            Vaisseau vaisseauChoisi = null;

            if (monVaisseau != null) {
                switch (monVaisseau) {
                    case "CARGO":
                        vaisseauChoisi=cargo;
                        break;
                    case "VAISSEAUMONDE":
                        vaisseauChoisi=vaisseauMonde;
                        break;
                    case "CHASSEUR":
                        vaisseauChoisi=chasseur;
                        break;
                    case "FREGATE":
                        vaisseauChoisi=fregate;
                        break;
                    case "CROISEUR":
                        vaisseauChoisi=croiseur;
                        break;
                    default:
                        vaisseauChoisi = null;
                        choisirVaisseau();
                }

            }
            System.out.println("Tu as choisi un vaisseau de type: " + vaisseauChoisi.type.name());// Test


            /*  2 / 3
             * C H O O S E       A     P L A N E T
             * 2.1 ask the user on which planet he would like to dock, indicating the name
             */
            // planets authorized
            String[] PlanetesTelluriques = {"Mercure", "Venus","Terre", "Mars"};
            System.out.println(Arrays.toString(PlanetesTelluriques));

            // planete choosen
            String maPlanete = choisirPlanete();
            System.out.println("tu as choisi d'accoster sur "+ maPlanete);

            // Check if planete choosen is valid and find the object Planete with the same name
            Planete planeteChoisie = null;
            Iterator<Planete> it = systemeSolaire.planetes.iterator();
            while(it.hasNext()){
                Planete p = it.next();
                if (maPlanete.equals(p.nom)){
                    planeteChoisie = p;
                    break;
                }
            }
            if (planeteChoisie == null){
                System.out.println("Désolée je ne connais pas cette planète du système solaire! ");
                choisirPlanete();
            }

            // witch kind of planete ? Is it validated ?
            boolean tellurique = Arrays.asList(PlanetesTelluriques).contains(planeteChoisie.nom);


            if (!tellurique) {
                System.out.println("Désolée cette planète ne peut pas accueillir de vaisseau, elle est ... gazeuse! ");
                choisirPlanete();
            }

            // ok name and type of planet are valid...
                // 2.1 Verify if the dock is full or not, if yes : forbid the ship to dock
                if (!((PlaneteTellurique)planeteChoisie).restePlaceDisponible(vaisseauChoisi.type)) {
                    System.out.println("" +
                            "Le vaisseau ne peut pas se poser sur la planete par manque de place dans la baie");
                // play again
                playAgain = DoYouWantPlayAgain();

                } else {
                    ((PlaneteTellurique)planeteChoisie).accueillirVaisseaux(vaisseauChoisi);
                    System.out.println( "Pour rappel: ");
                    System.out.println( "Le tonnage max de ton vaisseau est de: "+ vaisseauChoisi.tonnageMax + "t");
                    System.out.println( "Ton tonnage actuel est de: "+ vaisseauChoisi.tonnageActuel + "t");
                    System.out.println( "Le nb de passagers à ton bord est de: "+vaisseauChoisi.nbPassagers);

                    /*  3 / 3
                     * C H O O S E       A      C A R G O
                     * ask the user how much cargo he would like to load
                     */

//                    monChargement = choisirChargement();
//
//                    // Résultat
//                    System.out.println("Avec ton " + monVaisseau + " tu souhaite accoster sur " + maPlanete
//                            + " et embarquer " + monChargement + "tonne/s de chargement ");
//
//                    ((PlaneteTellurique)planeteChoisie).accueillirVaisseaux(vaisseauChoisi);
//                    System.out.println("...demande d' accord en cours ...");

                    //TODO en cours
//                    int monChargement = choisirChargement(vaisseauChoisi,planeteChoisie);
//
//                    try{
//                        vaisseauChoisi.emporterCargaison(monChargement);
//                    }catch(Exception ex){
//                        System.out.println(ex.getMessage());
//                        choisirChargement(vaisseauChoisi,planeteChoisie);
//                    }
                    choisirChargement(vaisseauChoisi,planeteChoisie);


                }
            //play again
            playAgain = DoYouWantPlayAgain();
        } while ( playAgain.equals("oui") );
    }


    /////////////////////////////////     F U N C T I O N S      C H O O S E     ///////////////////////////////////////////////////

    /**
     * This method listen the ship chosen by the player in the terminal, verify if this type of ship exist
     * if not: player is invited to choose an other ship
     * if yes: the ship chosen is returned
     * @return the ship chosen by player (String vaisseauChoisi)
     */
    public static String choisirVaisseau() {
        //spaceship existing
        String[] vaisseauxCivils = {"CARGO", "VAISSEAUMONDE"};
        String[] vaisseauxDeGuerre = {"FREGATE", "CROISEUR", "CHASSEUR"};

        System.out.println("\n" + Arrays.toString(values()) + " ??? "); //test
        //listen user
        System.out.println(" A TOI DE JOUER !!! Choisi un vaisseau :  ... ");
        Scanner scPlanete = new Scanner(System.in);
        String monVaisseau = scPlanete.nextLine();

        // switch in good format
        String vaisseauChoisi = monVaisseau.toUpperCase();
        //System.out.println(monVaisseau+ " --> "+ vaisseauChoisi); // Test

        // witch type of spaceship ? is it validated ?
        boolean civil = Arrays.asList(vaisseauxCivils).contains(vaisseauChoisi);
        boolean deGuerre = Arrays.asList(vaisseauxDeGuerre).contains(vaisseauChoisi);

        if (civil || deGuerre) {
            return vaisseauChoisi;
        } else {
            System.out.println("Désolée ce type de vaisseau n'existe pas ... ! ");
            choisirVaisseau();
        }

        return null;
    }

    /**
     * This method listen the planet chosen by the player in the terminal, verify if this type of planet is a valid planet
     * @return the planet chosen by player (String maPlanete)
     */
    public static String choisirPlanete() {

        //listen user
        System.out.println(" \n ... ok ! maintenant choisi une planete : ... ");
        Scanner scPlanete = new Scanner(System.in);
        String result = scPlanete.nextLine().toLowerCase(Locale.ROOT);
        String maPlanete = result.substring(0, 1).toUpperCase() + result.substring(1);

        return maPlanete;
    }

    /**
     * This method listen the ton of cargo chosen by the player in the terminal,
     * check if an exception is thrown
     * if yes, offers the player to enter a new quantity
     * @see
     * @return the ton of cargo chosen
     */
    public static void choisirChargement(Vaisseau monVaisseau, Planete maPlanete) {
        System.out.println(" \n Combien de tonne(s) de chargement souhaites tu embarquer ? :  ... ");
        Scanner scCargo = new Scanner(System.in);
        int monChargement = scCargo.nextInt();
//        System.out.println("Avec ton " + monVaisseau.type.name() + " tu souhaite accoster sur " + maPlanete.nom
//                + " et embarquer " + monChargement + "tonne/s de chargement ");

        System.out.println("...demande d' accord en cours ...");
        try{
            monVaisseau.emporterCargaison(monChargement);
        }catch(DepassementTonnageException dep){
            System.out.println(dep.getMessage());
            System.out.println("Souhaites-tu choisir une autre cargaison? (oui / non) ");
            Scanner sc = new Scanner(System.in);
            String result = sc.nextLine().toLowerCase(); // yes, no, other
            if(result.equals("oui")){
                choisirChargement(monVaisseau,maPlanete);
            }else{
                System.out.println(" // operation annulée //");
                DoYouWantPlayAgain();
            }
            ;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            DoYouWantPlayAgain();
        }
    }

    /**
     * Check chargement.
     * This method was created to allow player replay if the 1st cargo choosen is invalid (throw an exception)
     * @param monVaisseau the mon vaisseau, the spaceship choosen by the player
     * @param maPlanete   the ma planete, the planet choosen by the player
     */
//    public static void checkChargement(Vaisseau monVaisseau, Planete maPlanete){
//        int monChargement = choisirChargement();
//
//        // Résultat
//        System.out.println("Avec ton " + monVaisseau.type + " tu souhaite accoster sur " + maPlanete.nom
//                + " et embarquer " + monChargement + "tonne/s de chargement ");
//        ((PlaneteTellurique)maPlanete).accueillirVaisseaux(monVaisseau);
//        System.out.println("...demande d' accord en cours ...");
//    }

    /**
     * The DoYouWantPlayAgain() method invite player to play again
     * @return "oui" "non" or other answer
     */

    public static String DoYouWantPlayAgain() {
        System.out.println(" \n * * *    On continue ???  * * *\n (Voulez vous rejouer? oui/non)\n");
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine().toLowerCase();
        playerPlayAgainOrNot(result);
        return result; // oui, non, autre
    }

    /**
     * The playerPlayAgainOrNot() method analyse player answer
     * if answer is not yes or no, player is invited to answer again
     * @see HelloUniverse#DoYouWantPlayAgain()
     * @param reponsePlayer
     */
    public static void playerPlayAgainOrNot(String reponsePlayer){
        if(reponsePlayer.equals("non")){
            System.out.println(" Ok Bye!");
            return;
        }else if( !reponsePlayer.equals("oui")){
            System.out.println(" Désolée je ne comprends pas votre réponse: "+ reponsePlayer);
            DoYouWantPlayAgain();
        }
    }
}



