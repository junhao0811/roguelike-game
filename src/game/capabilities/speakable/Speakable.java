package game.capabilities.speakable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing a Speakable
 */
public interface Speakable {

    /**
     * Method to house the logic to set up the MonologueList
     */
    MonologueList getMonologues(Actor actor, GameMap map);

    /**
     * Method to house the speaking logic
     */
    default String speak(Actor actor, GameMap map) {
        MonologueList monologues = getMonologues(actor, map);
        return this + ": “" + monologues.getMonologue() + "”";
    }
}
