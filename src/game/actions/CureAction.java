package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Curable;

/**
 * This class inherits the Action class and represents an Action that allows actors to cure entities that implement the Curable interface.
 * This action is typically performed using a Talisman on a curable target in a specific direction.
 *
 * @author Ng Jun Xian
 */
public class CureAction extends Action {

    private Curable curable;
    private String direction;

    /**
     * Constructor for CureAction.
     *
     * @param curable the target that can be cured
     * @param direction the direction from the actor to the curable target
     */
    public CureAction(Curable curable, String direction) {
        this.curable = curable;
        this.direction = direction;
    }

    /**
     * Executes the curing action on the target.
     * Attempts to cure the curable target and returns a message indicating the result.
     *
     * @param actor the actor performing the cure action
     * @param map the game map where the action is performed
     * @return a string describing the result of the curing attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean cured = curable.cure(actor, map);
        if (cured) {
            return actor + " used the Talisman to cure" + curable + " at " + direction;
        }
        else{
            return actor + " failed to use the Talisman to cure " + curable + " at " + direction;
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
        return actor + " uses Talisman on the " + curable + " at " + direction ;
    }
}