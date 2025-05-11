package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Things that are Plantable can be planted in the soil.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface Plantable {
    
    /**
     * Plant the object in the soil.
     *
     * @param actor the actor performing the action
     * @param map   the map the actor is on
     */
    boolean plant(Actor actor, GameMap map);
}
