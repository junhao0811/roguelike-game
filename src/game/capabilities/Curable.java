package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Curable
 */
public interface Curable {

    /**
     * Method to house the curing logic
     */
    String cure(Actor actor, GameMap map);
}
