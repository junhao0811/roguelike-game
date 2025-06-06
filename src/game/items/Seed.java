package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.enums.Status;
import game.actions.PlantAction;
import game.grounds.crops.Crop;

/**
 * A class representing a Seed that an actor can pick up, drop, and plant
 *
 * @author Lee Hao Zhe
 */
public class Seed extends Item {

    private final Crop crop;

    /**
     * Constructor
     */
    public Seed(Crop crop) {
        super(crop.getName()+" Seed", '*', true);
        this.crop = crop;
    }

    /**
     * Method that plants the Seed and removes it from the actor's inventory
     * (basic planting logic)
     */
    public String plant(Actor actor, GameMap map) {
        if (actor.hasAttribute(BaseActorAttributes.STAMINA) &&
                actor.getAttribute(BaseActorAttributes.STAMINA) >= crop.getStaminaCost()) {
            actor.removeItemFromInventory(this);
            map.locationOf(actor).setGround(crop);
            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE, crop.getStaminaCost());

            return actor + " plants the " + this + ", consuming " + crop.getStaminaCost() +
                    " stamina" + "\n" + crop.plantingEffect(actor, map);
        } else {
            return " does not have enough stamina to plant the " + this;
        }
    }

    /**
     * Method to get a new PlantAction instance associated with the Seed if applicable
     */
    public PlantAction getPlantAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.PLANT_CROPS) &&
                map.locationOf(actor).getGround().hasCapability(Status.FERTILE)) {
            return new PlantAction(this);
        }

        return null;
    }

    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(this.getPlantAction(actor, map));
        return actions;
    }
}
