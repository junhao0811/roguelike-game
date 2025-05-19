package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Soil;

/**
 * A class representing an Inheritree
 */
public class Inheritree extends Crop {

    /**
     * Constructor
     */
    public Inheritree() {
        super('t', "Inheritree", 25);
    }

    @Override
    public void plantingEffect(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(Status.CURSED)) {
                destination.setGround(new Soil());
            }
        }
    }

    @Override
    public void cropEffect(Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor actor = destination.getActor();
                actor.heal(5);

                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA,
                            ActorAttributeOperations.INCREASE, 5);
                }
            }
        }
    }
}
