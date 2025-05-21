package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Edible;

/**
 * Class representing an action to eat that an actor can perform.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class EatAction extends Action {
    
    private final Edible edible;
    
    /**
     * Constructor.
     *
     * @param edible the edible to be eaten
     */
    public EatAction(Edible edible) {
        this.edible = edible;
    }
    
    /**
     * Perform the EatAction.
     *
     * @param actor The actor performing the eat action.
     * @return a description of what happened (the result of the action being performed) that can be
     * displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        edible.eat(actor, map);
        return edible + " has been consumed by the " + actor;
    }
    
    /**
     * Describe what action will be performed if EatAction is chosen in the menu.
     *
     * @param actor The actor performing the action
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + edible;
    }
}
