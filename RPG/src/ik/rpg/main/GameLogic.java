package ik.rpg.main;


import ik.rpg.main.characters.*;
import ik.rpg.main.encounters.*;
import ik.rpg.main.stories.*;
import ik.rpg.main.visuals.*;


public class GameLogic {


    public static Player player;

    public static boolean isRunning;

    //random encounters
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};

    //enemy names
    public static String[] enemies = {"Ogre", "Goblin","Goblin", "Goblin", "Stone Elemental"};

    //story elements
    public static int place = 0, act = 1;
    public static String[] places = {"Everlasting Mountains", "Haunted Landlines", "Castle of the Evil Emperor", "Throne Room"};



    //method to start the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        //print title screen
        GraphicMotor.clearConsole();
        GraphicMotor.printSeparator(40);
        GraphicMotor.printSeparator(30);
        System.out.println("DUNGEON CRAWLER ||no-copyright||");
        System.out.println("RPG BY DANIEL");
        GraphicMotor.printSeparator(30);
        GraphicMotor.printSeparator(40);
        GraphicMotor.anythingToContinue();

        //getting the player name
        do {
            GraphicMotor.clearConsole();
            GraphicMotor.printHeading("What's your name?");
            name = GraphicMotor.scanner.next();

            //ask the player to correcting choice
            GraphicMotor.clearConsole();
            GraphicMotor.printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = GraphicMotor.readInt("-> ", 2);
            if (input == 1) {
                nameSet = true;
            }
        } while (!nameSet);

        //print story intro
        Story.printIntro();

        //create new player object with the name
        player = new Player(name);

        //print first story act intro
        Story.printFirstActIntro();


        //setting is running to true to continue the game loop
        isRunning = true;

        //start main game loop
        gameLoop();
    }

    //changing the game's values depending the player's xp
    public static void checkAct() {
        //change acts based on xp
        if (player.xp >= 10 && act == 1) {
            //increment act and place
            act = 2;
            place = 1;
            //story
            Story.printFirstActOutro();
            //player level up
            player.chooseTrait();
            //story
            Story.printSecondActIntro();
            //set new enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Goblin";
            enemies[2] = "Wolf Pack";
            enemies[3] = "Henchman of the Evil Emperor";
            enemies[4] = "Scary Stranger";
            //set new encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
        } else if (player.xp >= 50 && act == 2) {
            //increment act and place
            act = 3;
            place = 2;
            //story
            Story.printSecondActOutro();
            //player level up
            player.chooseTrait();
            //story
            Story.printThirdActIntro();
            //set new enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Evil Mercenary";
            enemies[2] = "Henchman of the Evil Emperor";
            enemies[3] = "Henchman of the Evil Emperor";
            enemies[4] = "Henchman of the Evil Emperor";
            //set new encounters
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Battle";
            encounters[4] = "Shop";
            //fully heal the player
            player.hp = player.maxHp;
        } else if (player.xp >= 100 && act == 3) {
            //increment act and place
            act = 4;
            place = 3;
            //story
            Story.printThirdActOutro();
            //player level up
            player.chooseTrait();
            //story
            Story.printFourthActIntro();
            //fully heal the player
            player.hp = player.maxHp;
            //finalBattle();
        }
    }

    //calculate random encounter
    public static void randomEncounter() {
        //random number between 0 and the length of the array
        int encounter = (int) (Math.random() * encounters.length);
        //calling the respective methods
        if (encounters[encounter].equals("Battle")) {
            Battle.randomBattle();
        } else if (encounters[encounter].equals("Rest")) {
            Rest.takeRest();
        } else {
            Shop.shop();
        }

    }

    //method to continue journey
    public static void continueJourney() {
        //check if act must be increased
        checkAct();
        //check if game isn't in last act
        if (act != 4) {
            randomEncounter();
        }
    }

    //printing the most important about the player character
    public static void characterInfo() {
        GraphicMotor.clearConsole();
        GraphicMotor.printHeading("CHARACTER INFO");
        System.out.println(player.name + "\tHP" + player.hp + "/" + player.maxHp);
        GraphicMotor.printSeparator(20);
        //player xp and gold
        System.out.println("XP: " + player.xp + "\tGold:" + player.gold);
        GraphicMotor.printSeparator(20);
        //number of pots
        System.out.println("Potions: " + player.pots);
        GraphicMotor.printSeparator(20);

        //printing the chosen traits
        if (player.numAtkUpgrades > 0) {
            System.out.println("Offensive trait: " + player.atkUpgrades[player.numAtkUpgrades]);
            GraphicMotor.printSeparator(20);
        }
        if (player.numDefUpgrades > 0) {
            System.out.println("Defensive trait: " + player.atkUpgrades[player.numDefUpgrades]);
            GraphicMotor.printSeparator(20);
        }
        GraphicMotor.anythingToContinue();
    }


        //printing the main menu
        public static void printMenu () {
            GraphicMotor.clearConsole();
            GraphicMotor.printHeading(places[place]);
            System.out.println("Choose an action:");
            GraphicMotor.printSeparator(20);
            System.out.println("(1) Continue on your journey");
            System.out.println("(2) Character info");
            System.out.println("(3) Exit game");
        }

        //the final battle
        public static void finalBattle(){
            Battle.battle(new Enemy("EVIL EMPEROR", 100));
        Story.printEnd(player);
        isRunning = false;
        }

        //in case of player death
        public static void playerDied(){
            GraphicMotor.clearConsole();
            GraphicMotor.printHeading("You died...");
            isRunning = false;
        }

        //main game loop
        public static void gameLoop () {
            while (isRunning) {
                printMenu();
                int input = GraphicMotor.readInt("-> ", 3);
                if (input == 1) {
                    continueJourney();
                } else if (input == 2) {
                    characterInfo();
                } else {
                    isRunning = false;
                }
            }
        }
    }

