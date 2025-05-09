package game.actions; //req 2

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Plantable;

/**
 * This class inherits the Action class and represents an Action that allows actors to plant objects that implement the Plantable interface.
 * This action is used when a player wants to plant a seed or another plantable item at their current location.
 *
 * @author Ng Jun Xian
 */
public class PlantAction extends Action{
    private Plantable plantable;

    /**
     * Constructor for PlantAction.
     *
     * @param plantable the object to be planted
     */
    public PlantAction(Plantable plantable) {
        this.plantable = plantable;
    }

    /**
     * Executes the planting action at the actor's current location.
     * Attempts to plant the plantable object and returns a message indicating the result.
     *
     * @param actor the actor performing the planting action
     * @param map the game map where the action is performed
     * @return a string describing the result of the planting attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean planted = plantable.plant(actor, map.locationOf(actor));

        if (planted) {
            return actor + " plants " + plantable + ".";
        } else {
            return actor + " is unable to plant " + plantable + " here.";
        }
    }

    /**
     * Returns a description of this action for menu display
     *
     * @param actor the actor performing the action
     * @return a string describing the action for menu display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants " + plantable;
    }
}
