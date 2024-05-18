package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {
    /**
     * PreCondition: gameData != null
     * PreCondition: world != null
     * PostCondition: Entities further processed
     * @param gameData
     * @param world
     */

    void process(GameData gameData, World world);
}
