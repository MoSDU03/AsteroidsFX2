package dk.sdu.mmmi.cbse.enemy;


import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    private int health = 20;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(int amount) {
        this.health -= amount;
    }
}
