package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.capabilities.Growable;


/**
 * Action for the Bed of Chaos to grow new parts
 * Follows Command pattern for encapsulating growth logic
 *
 * @author GitHub Copilot
 */
public class GrowthAction extends Action {

    private Growable boss;
    
    /**
     * Constructor
     *
     * @param reproducible The reproducible entity
     */
    public GrowthAction(Growable boss) {
        this.boss = boss;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Use capability system to check if actor can grow
        if (actor.hasCapability(Ability.CAN_GROW)) {
            return boss.grow(map);
        }
        return actor + " cannot grow any further";
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...";
    }
}