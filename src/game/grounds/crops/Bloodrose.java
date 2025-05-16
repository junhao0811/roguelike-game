package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing a Bloodrose
 */
public class Bloodrose extends Crop {

    /**
     * Constructor
     */
    public Bloodrose() {
        super('w', "Bloodrose", 75);
    }

    public void plantingEffect(Actor actor, GameMap map) {
        actor.hurt(5);
    }

    public void cropEffect(Location location) {
        Display display = new Display();

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor actor = destination.getActor();
                actor.hurt(10);

                if (!actor.isConscious()) {
                    actor.unconscious(location.map());
                    display.println(actor + " met their demise at the hands of a " + this);
                }
            }
        }
    }
}
