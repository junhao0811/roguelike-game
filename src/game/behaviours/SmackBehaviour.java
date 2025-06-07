package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Status;

/**
 * A behaviour that makes an actor attack nearby hostile targets
 *
 * @author Ng Jun Xian
 */
public class SmackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction if there's a hostile target nearby, null otherwise
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);

        // Check all adjacent locations for hostile targets
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                // Check if target is hostile to this actor
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null; // No nearby targets to attack
    }
}