package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Things that are Curable can be cured by the actor.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface Curable {
    
    /**
     * Cure the object in the map.
     *
     * @param actor the actor performing the action
     * @param location the location of the object
     */
    boolean cure(Actor actor, Location location);
}
