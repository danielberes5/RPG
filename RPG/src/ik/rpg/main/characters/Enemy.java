package ik.rpg.main.characters;

public class Enemy extends Character{

    //storing the players current xp
    int playerXp;

    //Enemy specific constructor
    public Enemy(String name, int playerXp){
        super(name, (int) (Math.random()*playerXp + playerXp/3 + 5), (int) (Math.random()*(playerXp/4 + 2) + 1));
        //assign variable
        this.playerXp = playerXp;

    }

    //Enemy specific attacks
    @Override
    public int attack() {
        return (int) (Math.random()*(playerXp/4 + 1) + xp/4 + 3);
    }

    //Enemy specific defense
    @Override
    public int defense() {
        return (int) (Math.random()*(playerXp/4 + 1) + xp/4 + 3);
    }
}
