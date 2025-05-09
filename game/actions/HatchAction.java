package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Hatchable;

/**
 * An action for hatching eggs or other hatchable entities.
 * 
 * @author Ng Jun Xian
 */
public class HatchAction extends Action {
    
    private final Hatchable hatchable;
    private final Location location;
    
    /**
     * Constructor for HatchAction.
     * 
     * @param hatchable the entity that can hatch
     * @param location the location where the hatching will occur
     */
    public HatchAction(Hatchable hatchable, Location location) {
        this.hatchable = hatchable;
        this.location = location;
    }
    
    /**
     * Executes the hatching action by calling the hatch method on the hatchable entity.
     * 
     * @param actor the actor associated with the hatching (null if none)
     * @param map the game map where the hatching occurs
     * @return a description of the hatching result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean success = hatchable.hatch(location);
        if (success) {
            return "The egg hatches successfully!";
        }
        return "The egg fails to hatch.";
    }
    
    /**
     * Returns null since this action should not appear in the menu.
     * This is an automatic action triggered by a condition.
     * 
     * @param actor the actor performing the action
     * @return null, so this action doesn't appear in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}