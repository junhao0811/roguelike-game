package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Curable;

/**
 * Class representing an action to cure
 */
public class CureAction extends Action {
    
    private Curable curable;
    
    /**
     * Constructor
     *
     * @param curable a Curable which will be cured by the farmer
     */
    public CureAction(Curable curable) {
        this.curable = curable;
    }
    
    /**
     * This method is called when the action is executed.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    public String execute(Actor actor, GameMap map) {
        curable.cure(actor, map);
        return curable + " has been cured";
    }
    
    /**
     * This method returns a string describing the action.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    public String menuDescription(Actor actor) {
        return actor + " cures the " + curable;
    }
}
