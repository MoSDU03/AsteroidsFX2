package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private int hitsTaken = 0;

    public int getHitsTaken() {
        return hitsTaken;
    }
    public void incrementHits() {
        hitsTaken++;
    }
}
