package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Things that are Plantable can be planted in the soil.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface Plantable {
    
    /**
     * Plant the object in the soil.
     */
    void plant(Actor actor);
    
    /**
     * Check if the object can be planted based on the ground.
     *
     * @return true if the object can be planted, false otherwise
     */
    boolean canPlant(Ground ground);
}
