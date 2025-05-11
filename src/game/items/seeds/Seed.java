package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;

import game.Ability;
import game.Status;
import game.actions.PlantAction;
import game.capabilities.Plantable;
import game.capabilities.SproutEffect;

/**
 * Seed is an abstract class that represents a seed item in the game.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public abstract class Seed extends Item implements Plantable, SproutEffect {
    
    /**
     * Constructor.
     *
     * @param name the name of the seed
     */
    public Seed(String name) {
        super(name, '*', true);
    }
    
    /**
     * Plant the seed in the ground.
     *
     * @param actor the actor performing the action.
     * @param map  the map the actor is on.
     */
    public void plant(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this); // OCP
        this.sprout(actor, map);
        this.addCapability(Ability.PLANTING);
    }
    
    /**
     * Create and return an action to plant this Seed.
     *
     * @return a new PlantAction
     */
    public PlantAction getPlantAction() {
        return new PlantAction(this);
    }
    
    /**
     * List of allowable actions that can be performed on the seed
     *
     * @param actor the Actor acting
     * @param map   the GameMap the Actor is on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        Ground ground = map.locationOf(actor).getGround();
        if (ground.hasCapability(Status.PLANTABLE_GROUND)) {
            actions.add(getPlantAction());
        }
        return actions;
    }
}
