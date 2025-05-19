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

    @Override
    public String plantingEffect(Actor actor, GameMap map) {
        int damage = 5;

        actor.hurt(damage);
        String result = "The health of " + actor + " is sapped by " + damage + " points";

        if (!actor.isConscious()) {
            result += "\n" + actor + " met their demise at the hands of a " + this;
            actor.unconscious(map);
        }

        return result;
    }

    @Override
    public void cropEffect(Location location) {
        Display display = new Display();
        int damage = 10;

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor actor = destination.getActor();
                actor.hurt(damage);
                display.println(this + " hurts " + actor + " by " + damage + " points");

                if (!actor.isConscious()) {
                    display.println(actor + " met their demise at the hands of a " + this);
                    actor.unconscious(location.map());
                }
            }
        }
    }
}
