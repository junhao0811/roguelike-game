package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A behaviour that makes an actor attack nearby hostile targets
 *
 * @author Ng Jun Xian
 */
public class SmackBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * Returns an AttackAction if there's a hostile target nearby, null otherwise
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                actions.add(new AttackAction(target, exit.getName()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }

        return null; // No nearby targets to attack
    }
}