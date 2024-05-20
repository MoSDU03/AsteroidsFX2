package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {
    private static final float MIN_ASTEROID_RADIUS = 1;
    private static final float SPLIT_OFFSET = 10;
    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        float newRadius = asteroid.getRadius() / 2;

        if (newRadius < MIN_ASTEROID_RADIUS) {
            System.out.println("Asteroid too small to split, destroying asteroid");
            // The asteroid is too small to split further, destroy it
            world.removeEntity(asteroid);
            return;
        }

        System.out.println("Splitting asteroid into two smaller ones");

        Entity asteroid1 = createAsteroid(asteroid, newRadius, SPLIT_OFFSET);
        Entity asteroid2 = createAsteroid(asteroid, newRadius, -SPLIT_OFFSET);

        world.addEntity(asteroid1);
        world.addEntity(asteroid2);

        System.out.println("Created two new asteroids with radius: " + newRadius);
        System.out.println("Asteroid1 position: (" + asteroid1.getX() + ", " + asteroid1.getY() + ")");
        System.out.println("Asteroid2 position: (" + asteroid2.getX() + ", " + asteroid2.getY() + ")");
    }

    private Entity createAsteroid(Entity original, float newRadius, float offset) {
        Entity newAsteroid = new Asteroid();

        newAsteroid.setRadius(newRadius);
        newAsteroid.setX(original.getX() + offset);
        newAsteroid.setY(original.getY() + offset);
        newAsteroid.setRotation(original.getRotation());

        // Copy or set additional properties as necessary
        newAsteroid.setPolygonCoordinates(original.getPolygonCoordinates());

        return newAsteroid;
    }
}

