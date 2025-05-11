package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;

import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.PlantAction;
import game.capabilities.Plantable;
import game.capabilities.Sproutable;
import game.grounds.crops.Crop;

/**
 * Seed is an abstract class that represents a seed item in the game.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public abstract class Seed extends Item implements Plantable, Sproutable {
    
    /**
     * Constructor.
     *
     * @param name the name of the seed
     */
    public Seed(String name) {
        super(name, '*', true);
    }

    /**
     * Returns the stamina cost required to plant this seed.
     *
     * @return the stamina cost as an integer
     */
    public abstract int getStaminaCost();

    /**
     * Creates and returns a new crop corresponding to this seed type.
     *
     * @return the crop that grows from this seed
     */
    public abstract Crop createCrop();

    /**
     * Plants this seed at the specified location if the actor has enough stamina.
     * Consumes stamina from the actor, removes the seed from inventory,
     * and places the corresponding crop at the location.
     *
     * @param actor the actor planting the seed
     * @param location the location to plant the seed
     * @return true if planting was successful, false otherwise
     */
    public boolean plant(Actor actor, Location location) {

        if (actor.hasCapability(Ability.PLANT)) {
            int staminaCost = getStaminaCost();

            if (actor.hasAttribute(BaseActorAttributes.STAMINA) &&
                    actor.getAttribute(BaseActorAttributes.STAMINA) >= staminaCost) {

                actor.removeItemFromInventory(this);

                location.setGround(createCrop());

                actor.modifyAttribute(BaseActorAttributes.STAMINA,
                        ActorAttributeOperations.DECREASE, staminaCost);

                System.out.println(staminaCost + " stamina is consumed for planting " + this);
                System.out.println("Attempt to plant is successful.");
                return true;
            }
        }
        System.out.println("Attempt to plant is unsuccessful. Insufficient stamina.");
        return false;
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
        if (ground.hasCapability(Status.PLANTING_TERRAIN)) {
            actions.add(getPlantAction());
        }
        return actions;
    }
}
