package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface representing a Reproducible
 *
 * @author Ng Jun Xian
 */
public interface Reproducible {

    /**
     * Method to house the reproducing logic
     */
    String reproduce(Actor actor, Location location);
}
