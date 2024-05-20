package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemy.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class CollisionDetector implements IPostEntityProcessingService {

    /*public CollisionDetector() {
    }*/
    private static final int PLAYER_HIT_THRESHOLD = 20;
    private static final int ENEMY_HIT_THRESHOLD = 20;

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    handleCollision(entity1, entity2, world);
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    private void handleCollision(Entity entity1, Entity entity2, World world) {
        if (entity1 instanceof Bullet && entity2 instanceof Asteroid) {
            System.out.println("Bullet hit an Asteroid");
            splitAsteroid(entity2, world);
            world.removeEntity(entity1);
            world.removeEntity(entity2);
        } else if (entity1 instanceof Asteroid && entity2 instanceof Bullet) {
            System.out.println("Asteroid hit by a Bullet");
            splitAsteroid(entity1, world);
            world.removeEntity(entity1);
            world.removeEntity(entity2);
        } else if (entity1 instanceof Bullet && entity2 instanceof Player) {
            handleEntityHit(entity2, PLAYER_HIT_THRESHOLD, world);
            world.removeEntity(entity1);
        } else if (entity1 instanceof Player && entity2 instanceof Bullet) {
            handleEntityHit(entity1, PLAYER_HIT_THRESHOLD, world);
            world.removeEntity(entity2);
        } else if (entity1 instanceof Bullet && entity2 instanceof Enemy) {
            handleEnemyHit((Enemy) entity2, world);
            world.removeEntity(entity1);
        } else if (entity1 instanceof Enemy && entity2 instanceof Bullet) {
            handleEnemyHit((Enemy) entity1, world);
            world.removeEntity(entity2);
        } else if (entity1 instanceof Player && entity2 instanceof Enemy) {
            world.removeEntity(entity1);
            world.removeEntity(entity2);
        }
    }

    private void splitAsteroid(Entity asteroid, World world) {
        System.out.println("Splitting asteroid with ID: " + asteroid.getID());
        IAsteroidSplitter splitter = new AsteroidSplitterImpl();
        splitter.createSplitAsteroid(asteroid, world);
    }

    private void handleEntityHit(Entity entity, int hitThreshold, World world) {
        entity.incrementHits();
        System.out.println("Entity ID: " + entity.getID() + " has taken " + entity.getHitsTaken() + " hits");
        if (entity.getHitsTaken() >= hitThreshold) {
            System.out.println("Entity ID: " + entity.getID() + " has been destroyed");
            world.removeEntity(entity);
        }

    }
    private void handleEnemyHit(Enemy enemy, World world) {
        enemy.decreaseHealth(1);
        if (enemy.getHealth() <= 0) {
            world.removeEntity(enemy);
        }

    }
}
