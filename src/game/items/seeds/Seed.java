package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import game.actions.PlantAction;
import game.capabilities.Plantable;

/**
 * Seed is an abstract class that represents a seed item in the game.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public abstract class Seed extends Item implements Plantable {
    
    /**
     * Constructor.
     */
    public Seed(String name) {
        super(name, '*', true);
    }
    
    /**
     * Plant the seed in the ground.
     */
    @Override
    public void plant(Actor actor) {
        actor.removeItemFromInventory(this); // OCP
    }
    
    @Override
    public boolean canPlant(Ground ground) {
        return ground.getDisplayChar() == '.';
    }
    
    /**
     * Create and return an action to plant this Seed.
     *
     * @return a new PlantAction
     */
    public PlantAction getPlantAction(Ground ground) {
        return new PlantAction(this, ground);
    }
    
    /**
     * List of allowable actions that can be performed on the seed
     *
     * @param location
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();
        Ground ground = location.getGround();
        if (canPlant(ground)) {
            actions.add(getPlantAction(ground));
        }
        return actions;
    }
}
