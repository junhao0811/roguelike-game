package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for entities that can be cured.
 * Classes that implement this interface can be healed or restored
 * to a normal state through a curing action performed by another actor.
 *
 * @author Ng Jun Xian
 */
public interface Curable {

    /**
     * Applies a curing effect to the implementing entity.
     * This method is called when an actor attempts to cure this entity.
     *
     * @param actor the actor performing the curing action
     * @param location the location where the curing is taking place
     * @return true if curing was successful, false otherwise
     */
    boolean cure(Actor actor, Location location);
}