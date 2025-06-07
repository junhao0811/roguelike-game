package game.capabilities;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Growable capability
 * This interface is used to define the grow behavior for objects that can grow
 * in the game, such as plants or boss parts.
 *
 * @author Ng Jun Xian
 */
public interface Growable {

    /**
     * Method to house the growing logic
     * This method should be implemented to define how the object grows
     * and what it grows into.
     *
     * @param map the GameMap where the growth occurs
     * @return a String describing the growth action
     */
    String grow(GameMap map);
}
