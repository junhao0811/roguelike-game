package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Consumable;

/**
 * Class representing an action to consume an item
 * Follows the same pattern as EatAction
 *
 * @author Ng Jun Xian
 */
public class ConsumeAction extends Action {
    
    private final Consumable consumable;
    
    /**
     * Constructor
     *
     * @param consumable the consumable to be consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consume(actor, map);
        return consumable + " has been consumed by " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable;
    }
}