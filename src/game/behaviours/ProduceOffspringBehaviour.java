package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.capabilities.Reproducible;

import java.util.ArrayList;
import java.util.Random;

public class ProduceOffspringBehaviour extends Action implements Behaviour {

    private final Random random = new Random();
    private Reproducible reproducible;
    private Location reproduceAt;

    /**
     * Constructor to create a ProduceOffspringBehaviour.
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ProduceOffspringBehaviour(Reproducible reproducible) {
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
        return this;
    }

    /**
     * Allow the Actor to reproduce.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of the ReproduceAction suitable for the menu.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean nearBlessedEntity = false;
        ArrayList<Location> validLocations = new ArrayList<>();

        // Check all exits from the current location
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            // Check if the destination location is near a blessed entity
            if (destination.getGround().hasCapability(Status.BLESSED)) {
                nearBlessedEntity = true;
            }

            // Check if the destination is valid for reproduction
            if (destination.canActorEnter(actor) && !destination.containsAnActor()) {
                validLocations.add(destination);
            }
        }

        // If not near a blessed entity, reproduction is not possible
        if (!nearBlessedEntity) {
            return null;
        }

        // If there are valid locations, randomly select one
        if (!validLocations.isEmpty()) {
            reproduceAt = validLocations.get(random.nextInt(validLocations.size()));
            reproducible.reproduce(actor, reproduceAt);
            return menuDescription(actor); // Return this behaviour as the action
        }

        return actor + " is unable to reproduce."; // No valid reproduction action found
    }

    /**
     * Returns a description of this reproduction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "SpiritGoat gives birth to an offspring at North"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " successfully reproduces >o<";
    }
}