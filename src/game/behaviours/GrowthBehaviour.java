package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GrowthAction;
import game.enums.Status;
import game.capabilities.Growable;

/**
 * A behaviour that makes the boss grow when no players are nearby
 *
 * @author Ng Jun Xian
 */
public class GrowthBehaviour implements Behaviour {

    private Growable growable;

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
        Location actorLocation = map.locationOf(actor);

        // Check all adjacent locations for hostile targets (players)
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                // If there's a player nearby, don't grow
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return null;
                }
            }
        }
        // No players nearby, can grow
        return new GrowthAction(growable);
    }
}