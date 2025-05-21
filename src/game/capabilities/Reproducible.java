package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


/**
 * Interface representing a Reproducible
 */
public interface Reproducible {

    /**
     * Method to house the reproducing logic
     */
    void reproduce(Actor actor, Location location);

    /**
     * Method to check if the Reproducible can reproduce at the current tick of game
     */
    boolean canReproduce(Actor actor, GameMap map);
}
