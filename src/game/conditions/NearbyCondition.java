package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class NearbyCondition extends Condition {

    private final Enum<?> capability;

    public NearbyCondition(Enum<?> capability) {
        this.capability = capability;
    }

    @Override
    public boolean check(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
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
