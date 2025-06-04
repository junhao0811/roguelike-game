package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Edible;

/**
 * Class representing an action to eat
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class EatAction extends Action {
    
    private final Edible edible;
    
    /**
     * Constructor
     *
     * @param edible the edible to be eaten
     */
    public EatAction(Edible edible) {
        this.edible = edible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        edible.eat(actor, map);
        return edible + " has been consumed by the " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + edible;
    }
}
