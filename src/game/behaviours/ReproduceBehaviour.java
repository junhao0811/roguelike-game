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
        ArrayList<Location> validLocations = new ArrayList<>();

        // Check if the actor can reproduce
        if (actor != reproducible) {
            return null;
        }

        // Check all exits from the current location
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // Check if the destination is valid for reproduction
            if (destination.canActorEnter(actor) && !destination.containsAnActor() && destination.getGround().hasCapability(Status.BLESSED)) {
                // Add the destination to the list of valid locations
                validLocations.add(destination);
            }
        }
        // If there are valid locations, create a ReproduceAction for one of them
        if (!validLocations.isEmpty()) {
            // Randomly select a valid location
            Location location = validLocations.get(random.nextInt(validLocations.size()));
            actions.add(new ReproduceAction(reproducible, location));
        }

        // If there are valid actions, return one of them
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}