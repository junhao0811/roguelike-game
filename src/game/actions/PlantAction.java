package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import edu.monash.fit2099.engine.positions.Ground;
import game.items.seeds.Seed;

/**
 * Class representing an action to plant crops that the Player can perform.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class PlantAction extends Action {
    
    /**
     * The Seed to be planted
     */
    private Seed seed;
    
    /**
     * The Ground to plant the Seed on
     */
    private Ground ground;
    
    /**
     * Constructor.
     *
     * @param seed the Seed to be planted
     */
    public PlantAction(Seed seed, Ground ground) {
        this.seed = seed;
        this.ground = ground;
    }
    
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be
     * displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!seed.canPlant(ground)) {
            return seed + " cannot be planted here";
        }
        seed.plant(actor);
        return seed + " has been planted";
    }
    
    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants " + seed;
    }
}
