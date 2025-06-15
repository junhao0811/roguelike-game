package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the nearby condition
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public class NearbyCondition extends Condition {

    private final Enum<?> capability;

    /**
     * Constructor
     *
     * @param capability the capability to check for in the surroundings
     */
    public NearbyCondition(Enum<?> capability) {
        this.capability = capability;
    }

    @Override
    public boolean check(Actor actor, Location location) {
        if (location.getGround().hasCapability(capability)) {
            return true;
        }

        if (location.containsAnActor() &&
                location.getActor().hasCapability(capability)) {
            return true;
        }

        for (Item item : location.getItems()) {
            if (item.hasCapability(capability)) {
                return true;
            }
        }

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(capability)) {
                return true;
            }

            if (destination.containsAnActor() &&
                    destination.getActor().hasCapability(capability)) {
                return true;
            }

            for (Item item : destination.getItems()) {
                if (item.hasCapability(capability)) {
                    return true;
                }
            }
        }

        return false;
    }
}
