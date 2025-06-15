package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Consumable;

/**
 * Class representing an action to consume an item
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
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
        return menuDescription(actor) + consumable.consume(actor, map);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable;
    }
}