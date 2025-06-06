package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface defining a contract for how items can affect NPC affection levels
 * Follows Interface Segregation Principle - only affection-related methods
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public interface Giftable {

    /**
     * Applies affection changes to the target NPC
     *
     * @param actor the actor giving the affection item
     * @param item the affection item being given
     * @param map the game map
     * @return description of the affection change
     */
    String gift(Actor actor, Item item, GameMap map);

}