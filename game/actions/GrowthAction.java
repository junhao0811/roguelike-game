package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.creatures.BedOfChaos;
import game.enums.Ability;

/**
 * Action for the Bed of Chaos to grow new parts
 * Follows Command pattern for encapsulating growth logic
 *
 * @author GitHub Copilot
 */
public class GrowthAction extends Action {
    
    @Override
    public String execute(Actor actor, GameMap map) {
        // Use capability system to check if actor can grow
        if (actor.hasCapability(Ability.CAN_GROW)) {
            // Delegate to the actor's growth method
            return ;
        }
        return actor + " cannot grow";
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows new parts";
    }
}