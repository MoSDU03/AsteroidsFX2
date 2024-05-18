package dk.sdu.mmmi.cbse.enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemySpaceship;

    @Override
    public void start(GameData gameData, World world) {
        enemySpaceship = createEnemyShip(gameData);
        world.addEntity(enemySpaceship);
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemySpaceship = new Enemy();
        enemySpaceship.setX(gameData.getDisplayWidth() / 3);
        enemySpaceship.setY(gameData.getDisplayHeight() / 3);
        enemySpaceship.setRadius(10);
        enemySpaceship.setRotation(0);
        enemySpaceship.setPolygonCoordinates(10, -10, 10, 10, -10, 10, -10, -10);
        return enemySpaceship;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove enemy spaceship entity from the world
        world.removeEntity(enemySpaceship);
    }
}
