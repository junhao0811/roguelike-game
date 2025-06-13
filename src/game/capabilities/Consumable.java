package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Consumable item
 * Follows the same pattern as Edible interface
 *
 * @author Ng Jun Xian
 */
public interface Consumable {
    
    /**
     * Method to house the consumption logic
     *
     * @param actor the actor consuming the item
     * @param map the current game map
     */
    String consume(Actor actor, GameMap map);
}