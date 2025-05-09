package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ability.Ability;
import game.actions.PlantAction;
import game.crops.Crop;
import game.interfaces.Plantable;
import game.status.Status;

/**
 * Abstract class representing seeds that can be planted to grow crops.
 * Seeds require stamina to plant and can only be planted on suitable ground.
 * Each specific seed type should implement methods to define its stamina cost
 * and the type of crop it produces.
 *
 * @author Ng Jun Xian
 */
public abstract class Seed extends Item implements Plantable {

    /**
     * Constructor for Seed.
     * Creates a seed with display character '*' and the PLANTING ability.
     *
     * @param name the name of the seed
     */
    public Seed(String name) {
        super(name, '*', true);
        this.addCapability(Ability.PLANTING);
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

        if (actor.hasCapability(Ability.PLANTING)) {
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
     * Returns a list of actions that can be performed with this seed.
     * If the actor is standing on plantable ground, adds the PlantAction.
     *
     * @param actor the actor carrying the seed
     * @param map the game map where the actor is located
     * @return an action list containing possible actions for this seed
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = super.allowableActions(actor, map);

        Location currentLocation = map.locationOf(actor);
        if (currentLocation != null &&
                currentLocation.getGround().hasCapability(Status.PLANTABLE_GROUND)) {
            actions.add(new PlantAction(this));
        }
        return actions;
    }
}
