package ik.rpg.main.encounters;

import ik.rpg.main.GameLogic;
import ik.rpg.main.characters.Enemy;
import ik.rpg.main.visuals.GraphicMotor;



public class Battle {
    //creating random battle
    public static void randomBattle() {
        GraphicMotor.clearConsole();
        GraphicMotor.printHeading("You encountered an enemy and you must fight!");
        GraphicMotor.anythingToContinue();
        //creating new enemy
        battle(new Enemy(GameLogic.enemies[(int) (Math.random() * GameLogic.enemies.length)], GameLogic.player.xp));
    }

    //main battle method
    public static void battle(Enemy enemy){
        //main battle loop
        while (true){
            GraphicMotor.clearConsole();
            GraphicMotor.printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHp);
            GraphicMotor.printHeading(GameLogic.player.name + "\nHP" + GameLogic.player.hp + "/" + GameLogic.player.maxHp);
            System.out.println("Choose an action:");
            GraphicMotor.printSeparator(20);
            System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away");
            int input = GraphicMotor.readInt("-> ", 3);
            //react accordingly to GameLogic.player choice
            if(input == 1){
                //FIGHT
                //calculate damage
                int dmg = GameLogic.player.attack() - enemy.defense();
                int dmgTook = enemy.attack() - GameLogic.player.defense();
                //check if damage is negative
                if(dmgTook < 0){
                    //add some damage if GameLogic.player defends well
                    dmg -= dmgTook/2;
                    dmgTook = 0;
                }
                if (dmg < 0){
                    dmg =0;
                }
                GameLogic.player.hp -= dmgTook;
                enemy.hp -= dmg;
                //print info of the turn
                GraphicMotor.clearConsole();
                System.out.println("You dealt " + dmg + " damage to the " + enemy.name + ".");
                GraphicMotor.printSeparator(15);
                System.out.println(enemy.name + " dealt " + dmgTook + " damage to you.");
                GraphicMotor.anythingToContinue();
                //check if somebody died
                if(GameLogic.player.hp <= 0){
                    GameLogic.playerDied();
                    break;
                }
                else if(enemy.hp <= 0){
                    GraphicMotor.clearConsole();
                    GraphicMotor.printHeading("You defeated " + enemy.name + "!");
                    GameLogic.player.xp += enemy.xp;
                    System.out.println("you earned " + enemy.xp + " XP!");
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random()* enemy.xp);
                    if (addRest){
                        GameLogic.player.restsLeft++;
                        System.out.println("You earned a chance to get an additional rest!");
                    }
                    if(goldEarned > 0 ){
                        GameLogic.player.gold += goldEarned;
                        System.out.println("You collected " + goldEarned + " gold!");
                    }
                    GraphicMotor.anythingToContinue();
                    break;
                }
            }
            else if(input == 2){
                //POTION
                GraphicMotor.clearConsole();
                if(GameLogic.player.pots > 0 && GameLogic.player.hp < GameLogic.player.maxHp){
                    GraphicMotor.printHeading("Do you want to drink a potion? (" + GameLogic.player.pots + " left)");
                    System.out.println("(1) Yes!\n(2) No, maybe later.");
                    input = GraphicMotor.readInt("-> ", 2);
                    if(input == 1){
                        GameLogic.player.hp = GameLogic.player.maxHp;
                        GameLogic.player.pots--;
                        GraphicMotor.printHeading("You drank the potion and now feel restored.");
                        GraphicMotor.anythingToContinue();
                    }
                }
                else{
                    GraphicMotor.printHeading("You cannot use potion now!");
                    GraphicMotor.anythingToContinue();
                }
            }
            else{
                //RUN
                GraphicMotor.clearConsole();
                if(GameLogic.act != 4){
                    //35% chance to escape
                    if (Math.random()*10 <= 3.5){
                        GraphicMotor.printHeading("You ran away from " + enemy.name + ".");
                        GraphicMotor.anythingToContinue();
                        break;
                    }
                    else{
                        GraphicMotor.printHeading("You didn't manage to escape.");
                        //calculate damage
                        int dmgTook = enemy.attack();
                        System.out.println("You took " + dmgTook + "damage!");
                        GraphicMotor.anythingToContinue();
                        //check GameLogic.player death
                        if(GameLogic.player.hp <= 0){
                            GameLogic.playerDied();
                        }
                    }
                }
                else{
                    GraphicMotor.printHeading("YOU CANNOT ESCAPE FROM THE EVIL EMPEROR!!!");
                    GraphicMotor.anythingToContinue();
                }
            }
        }
    }
}
