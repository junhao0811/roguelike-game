package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Egg;

/**
 * Class representing an action for the egg to hatch.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class HatchAction extends Action {
    
    private Egg egg;
    
    /**
     * Constructor.
     *
     * @param egg the Egg to be hatched
     */
    public HatchAction(Egg egg) {
        this.egg = egg;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        egg.hatch(actor, map);
        return menuDescription(actor);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " hatches from " + egg;
    }
}
