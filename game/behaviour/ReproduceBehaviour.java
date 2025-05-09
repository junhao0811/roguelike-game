package game.behaviour; //req 1

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ability.Ability;
import game.actions.ReproduceAction;
import game.interfaces.Reproducable;
import game.status.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * A behaviour that allows an entity to reproduce when near entities blessed by grace (Inheritrees).
 * Returns a ReproduceAction when the entity is near a graced entity.
 *
 */
public class ReproduceBehaviour implements Behaviour {

    private final Random random = new Random();
    private final Reproducable reproducable;

    /**
     * Constructor for ReproduceBehaviour.
     * 
     * @param reproducable the entity capable of reproduction
     */
    public ReproduceBehaviour(Reproducable reproducable) {
        this.reproducable = reproducable;
    }

    /**
     * Checks if the actor can reproduce and if so, returns a ReproduceAction.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return ReproduceAction if reproduction is possible, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Ensure the actor is the same as our reproducible entity
        if (!actor.hasCapability(Ability.PRODUCE_OFF_SPRING)) {
            return null;
        }
        
        Location actorLocation = map.locationOf(actor);
        
        // Check if actor is adjacent to any graced entity (Inheritree)
        boolean nearGracedEntity = false;
        for (Exit exit : actorLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.getGround().hasCapability(Status.GRACED)) {
                nearGracedEntity = true;
                break;
            }
        }
        
        // If not near a graced entity, cannot reproduce
        if (!nearGracedEntity) {
            return null;
        }
        
        // Find valid locations for offspring
        ArrayList<Location> validLocations = new ArrayList<>();
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor) && !destination.containsAnActor()) {
                validLocations.add(destination);
            }
        }
        
        // If there's at least one valid location, return a ReproduceAction
        if (!validLocations.isEmpty()) {
            Location targetLocation = validLocations.get(random.nextInt(validLocations.size()));
            return new ReproduceAction(reproducable, targetLocation);
        } else{
            System.out.println("Cannot reproduce, no valid locations available.");
        }
        return null;
    }
}