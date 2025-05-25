package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.grounds.Soil;

/**
 * A class representing an Inheritree
 *
 * @author Lee Hao Zhe
 */
public class Inheritree extends Crop {

    /**
     * Constructor
     */
    public Inheritree() {
        super('t', "Inheritree", 25);
        this.addCapability(Status.BLESSED);
    }

    @Override
    public String plantingEffect(Actor actor, GameMap map) {
        boolean convert = false;

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(Status.CURSED)) {
                destination.setGround(new Soil());
                convert = true;
            }
        }

        if (convert) {
            return "The surrounding cursed grounds are cured, " +
                    "revealing the Soil beneath";
        }

        return "There are no cursed grounds in the surroundings to be cured";
    }

    @Override
    public void cropEffect(Location location) {
        Display display = new Display();
        int healValue = 5;
        int staminaRecoverValue = 5;

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor actor = destination.getActor();
                actor.heal(healValue);
                display.println(this + " heals " + actor + " by " + healValue + " points");

                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA,
                            ActorAttributeOperations.INCREASE, staminaRecoverValue);
                    display.println(this + " restores the stamina of " + actor + " by " +
                            staminaRecoverValue + " points");
                }
            }
        }
    }
}
