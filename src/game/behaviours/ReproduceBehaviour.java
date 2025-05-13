package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ReproduceAction;
import game.capabilities.Reproducible;
import java.util.ArrayList;
import java.util.Random;

public class ReproduceBehaviour implements Behaviour {

    private final Random random = new Random();
    private final Reproducible reproducible;

    /**
     * Constructor to create a ReproduceBehaviour.
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ReproduceBehaviour(Reproducible reproducible) {
        this.reproducible = reproducible;
    }

    /**
     * Returns a ReproduceAction to reproduce at a random location, if possible.
     * If no reproduction is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no ReproduceAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        boolean nearBlessedEntity = false;
        // Check all exits from the current location
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // Check if the destination location is valid for reproduction
            if (destination.getGround().hasCapability(Status.BLESSED)) {
                nearBlessedEntity = true;
                break;
            }
        }

        // If the actor is not near a blessed entity, return null
        if (!nearBlessedEntity) {
            return null;
        }

        // Check all exits from the current location
        ArrayList<Location> validLocations = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor) && !destination.containsAnActor()) {
                validLocations.add(destination);
            }
        }

        // If there are valid locations, create a ReproduceAction for one random location
        if (!validLocations.isEmpty()) {
            // Randomly select a valid location
            Location reproduceAt = validLocations.get(random.nextInt(validLocations.size()));
            actions.add(new ReproduceAction(reproducible, reproduceAt));

        }

        // If there are valid actions, return one of them
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}