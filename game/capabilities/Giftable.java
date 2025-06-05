package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.NPC;
import edu.monash.fit2099.engine.items.Item;

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
     * @param giver the actor giving the affection item
     * @param receiver the NPC receiving the affection item
     * @param map the game map
     * @return description of the affection change
     */
    String gift(NPC receiver, Actor giver, GameMap map);

}