package game.interfaces; //req 2

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that can be planted in the game.
 * Classes that implement this interface can be planted at a location by an actor,
 * typically representing seeds or other plantable items.
 *
 * @author Ng Jun Xian
 */
public interface Plantable {

    /**
     * Plants the object at the specified location by the given actor.
     *
     * @param actor the actor performing the planting action
     * @param location the location where the object should be planted
     * @return true if planting was successful, false otherwise
     */
    boolean plant(Actor actor, Location location);
}