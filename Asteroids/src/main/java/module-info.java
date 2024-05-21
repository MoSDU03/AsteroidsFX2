import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroids;
    requires junit;

    exports dk.sdu.mmmi.cbse.asteroid;
    opens dk.sdu.mmmi.cbse.asteroid to junit;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
}
