package ik.rpg.main.encounters;

import ik.rpg.main.GameLogic;
import ik.rpg.main.visuals.GraphicMotor;

public class Shop {
    //in game shop
    public static void shop(){
        GraphicMotor.clearConsole();
        GraphicMotor.printHeading("You meet a mysterious stranger\nHe offers you something");
        int price = (int) (Math.random()*(10 + GameLogic.player.pots*3) + 10 + GameLogic.player.pots);
        System.out.println("Healing potion: " + price + " gold");
        GraphicMotor.printSeparator(20);
        System.out.println("Do you want to buy one?\n(1) Yes!\n(2) No, thanks!");
        int input = GraphicMotor.readInt("-> ", 2);
        if(input == 1){
            if(GameLogic.player.gold >= price) {
                GraphicMotor.clearConsole();
                GraphicMotor.printHeading("You bought a healing potion for " + price + " gold.");
                GameLogic.player.gold -= price;
                GameLogic.player.pots++;
            }
            else {
                GraphicMotor.printHeading("Sorry, but you are broke...");
            }
        }
        GraphicMotor.anythingToContinue();
    }
}
