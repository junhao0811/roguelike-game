package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for entities that can be eaten.
 * This interface is implemented by items that can be consumed by actors.
 * 
 * @author Ng Jun Xian
 */
public interface Edible {
    
    /**
     * Defines what happens when an entity is eaten by an actor.
     * 
     * @param actor the actor eating this entity
     * @return true if successfully eaten, false otherwise
     */
    boolean eaten(Actor actor);
}