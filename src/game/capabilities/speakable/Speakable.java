package game.capabilities.speakable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Speakable
 */
public interface Speakable {

    /**
     * Method to house the speaking logic
     */
    String speak(Actor actor, GameMap map);
}
