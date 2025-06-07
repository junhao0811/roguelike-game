package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action that allows an actor to teleport to a destination
 *
 * @author Tan Choong Sheng
 *
 */
public class TeleportAction extends Action {

    private Location destination;
    private String destinationName;

    /**
     * Constructor
     *
     * @param destination the location to teleport to
     * @param destinationName the name of the destination
     */
    public TeleportAction(Location destination, String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * Execute the teleportation
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, destination);
        return actor + " teleports to " + destinationName;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + destinationName;
    }
}