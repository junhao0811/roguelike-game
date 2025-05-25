package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing an Edible
 *
 * @author Chong Jun Hao
 */
public interface Edible {

    /**
     * Method to house the eating logic
     */
    void eat(Actor actor, GameMap map);
}
