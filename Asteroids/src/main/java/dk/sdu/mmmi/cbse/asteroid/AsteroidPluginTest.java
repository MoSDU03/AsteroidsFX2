package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsteroidPluginTest {

    private AsteroidPlugin asteroidPlugin;
    private GameData gameData;
    private World world;

    @Before
    public void setUp() {
        asteroidPlugin = new AsteroidPlugin();
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testStart() {
        asteroidPlugin.start(gameData, world);
        assertFalse(world.getEntities(Asteroid.class).isEmpty());
    }

    @Test
    public void testStop() {
        asteroidPlugin.start(gameData, world);
        asteroidPlugin.stop(gameData, world);
        assertTrue(world.getEntities(Asteroid.class).isEmpty());
    }
}
