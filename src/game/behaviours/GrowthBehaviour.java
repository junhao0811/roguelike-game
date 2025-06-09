package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GrowthAction;
import game.capabilities.Growable;

/**
 * A behaviour that makes the boss grow when no players are nearby
 *
 * @author Ng Jun Xian
 */
public class GrowthBehaviour implements Behaviour {

    private final Growable growable;

    /**
     * Constructor for GrowthBehaviour
     *
     * @param growable the Growable object that can grow
     */
    public GrowthBehaviour(Growable growable) {
        this.growable = growable;
    }

    /**
     * Returns a GrowthAction if no hostile targets are nearby, null otherwise
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a GrowthAction or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check all adjacent locations for possible targets (actors)
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                return null;
            }
        }

        // No targets (actors) nearby, can grow
        return new GrowthAction(growable);
    }
}