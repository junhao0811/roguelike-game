package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.enums.Ability;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * @author Riordan D. Alfredo
 * @see edu.monash.fit2099.demo.mars.Application
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the number of steps between a and b if you can only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Returns a MoveAction to wander to the nearest possible location to the target,
     * if a target is registered and not nearby.
     * If no target is found or target is already nearby, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is needed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor() &&
                    destination.getActor().hasCapability(Ability.CAN_BE_FOLLOWED)) {
                target = destination.getActor();
                break;
            }
        }

        if (target != null) {
            if(!map.contains(target) || !map.contains(actor))
                return null;

            Location here = map.locationOf(actor);
            Location there = map.locationOf(target);

            int currentDistance = distance(here, there);

            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();

                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);

                    if (newDistance < currentDistance) {
                        return destination.getMoveAction(actor, "to follow " + target,
                                exit.getHotKey());
                    }
                }
            }
        }

        return null;
    }
}
