package game.capabilities;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * Interface defining a contract for how items can affect NPC affection levels
 * Follows Interface Segregation Principle - only affection-related methods
 *
 * @author Chong Jun Hao
 */
public interface Giveable {
    
    /**
     * Applies affection changes to the target NPC
     *
     * @param giver the actor giving the affection item
     * @param target the target actor receiving the affection item
     * @param map the game map
     * @return description of the affection change
     */
    String give(Actor giver, Actor target, GameMap map);
}