package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Consumable item
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public interface Consumable {
    
    /**
     * the consumption logic
     *
     * @param actor the actor consuming the item
     * @param map the current game map
     */
    String consume(Actor actor, GameMap map);
}