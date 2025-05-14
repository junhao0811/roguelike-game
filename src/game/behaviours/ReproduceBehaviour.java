package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.ReproduceAction;
import game.capabilities.Reproducible;

/**
 * A behavior that determines when an actor should reproduce
 */
public class ReproduceBehaviour implements Behaviour {

    private Reproducible reproducible;
    private int counter;

    /**
     * Constructor to create a ReproduceBehaviour.
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ReproduceBehaviour(Reproducible reproducible) {
        this.reproducible = reproducible;
        this.counter = 0;
    }

    /**
     * Returns a ReproduceAction when an actor should reproduce.
     * For actors with PRODUCE_OFFSPRING: checks if near a blessed entity
     * For actors with PRODUCE_EGG: checks if counter is at a multiple of 7
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return a ReproduceAction if conditions are met, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.PRODUCE_OFFSPRING)) {
            // Check if actor is near any blessed entity
            Location actorLocation = map.locationOf(actor);
            boolean nearBlessedEntity = false;

            for (Exit exit : actorLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround().hasCapability(Status.BLESSED)) {
                    nearBlessedEntity = true;
                    break;
                }
            }

            if (nearBlessedEntity) {
                return new ReproduceAction(reproducible);
            }
        }
        else if (actor.hasCapability(Ability.PRODUCE_EGG)) {
            // Increment counter and check if it's time to lay egg
            counter++;
            if (counter % 7 == 0) {
                return new ReproduceAction(reproducible);
            }
        }

        return null;
    }
}