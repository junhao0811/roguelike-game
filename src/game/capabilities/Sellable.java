package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Sellable
 */
public interface Sellable {

    /**
     * Method to house the selling logic
     */
    String sell(Actor actor, GameMap map);
}
