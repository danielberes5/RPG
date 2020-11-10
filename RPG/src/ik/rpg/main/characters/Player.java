package ik.rpg.main.characters;

import ik.rpg.main.GameLogic;
import ik.rpg.main.visuals.GraphicMotor;

public class Player extends Character {

    //additional player stats
    public int gold, restsLeft, pots;

    //storing skills
    public int numAtkUpgrades, numDefUpgrades;

    //arrays to store skill names
    public String[] atkUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones", "Stoneskin", "Scale Armor", "Holy Aura"};

    //player specific constructor
    public Player(String name){

        //calling constructor of superclass
        super(name, 100,0);

        //setting upgrades to 0
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;

        this.gold = 5;
        this.restsLeft = 1;
        this.pots = 0;

        //player choose trait when creating the character
        chooseTrait();
    }

    //player specific attack
    @Override
    public int attack() {
        return (int) (Math.random()*(xp/4 + numAtkUpgrades* 3 + 3) + xp/10 + numAtkUpgrades*2 + numDefUpgrades + 1);
    }
    //player specific defense
    @Override
    public int defense() {
        return (int) (Math.random()*(xp/4 + numDefUpgrades* 3 + 3) + xp/10 + numDefUpgrades*2 + numAtkUpgrades + 1);
    }

    //let the player choose trait from the skill paths
    public void chooseTrait(){
        GraphicMotor.clearConsole();
        GraphicMotor.printHeading("Choose a trait:");
        System.out.println("(1) " + atkUpgrades[numAtkUpgrades]);
        System.out.println("(2) " + defUpgrades[numDefUpgrades]);

        //get the player choice
        int input = GraphicMotor.readInt("-> ", 2);
        GraphicMotor.clearConsole();

        //deal with both cases
        if (input == 1){
            GraphicMotor.printHeading("You chose " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else{
            GraphicMotor.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }
        GraphicMotor.anythingToContinue();
    }
}
