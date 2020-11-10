package ik.rpg.main.encounters;

import ik.rpg.main.GameLogic;
import ik.rpg.main.visuals.GraphicMotor;

public class Rest {
    //taking a rest
    public static void takeRest(){
        GraphicMotor.clearConsole();
        if(GameLogic.player.restsLeft >= 1){
            GraphicMotor.printHeading("Do you want to take a rest? (" + GameLogic.player.restsLeft + " hour left)");
            System.out.println("(1) Yes!\n(2) No, not now.");
            int input = GraphicMotor.readInt("-> ", 2);
            if(input == 1){
                //GameLogic.player taking rest
                GraphicMotor.clearConsole();
                if (GameLogic.player.hp < GameLogic.player.maxHp){
                    int hpRestored = (int) (Math.random()* (GameLogic.player.xp/4 + 1) + 10);
                    GameLogic.player.hp += hpRestored;
                    if (GameLogic.player.hp > GameLogic.player.maxHp){
                        GameLogic.player.hp = GameLogic.player.maxHp;
                    }
                    System.out.println("You took a rest and you feel refreshed.");
                    System.out.println("You are now at " + GameLogic.player.hp + "/" +GameLogic.player.maxHp + "health.");
                    GameLogic.player.restsLeft--;
                }
                else{
                    System.out.println("You don't feel tired.");
                }
                GraphicMotor.anythingToContinue();
            }

        }
    }

}
