package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.capabilities.Reproducible;

import java.util.ArrayList;
import java.util.Random;

public class ReproduceBehaviour extends Action implements Behaviour {
    
    private final Random random = new Random();
    private Reproducible reproducible;
    private Location reproduceAt;
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
     * Returns this action to handle reproduction, either by producing offspring or laying eggs.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action for reproduction, or null if not possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.PRODUCE_OFFSPRING)) {
            // Check if offspring production is possible
            if (canProduceOffspring(actor, map)) {
                return this;
            }
        } else if (actor.hasCapability(Ability.PRODUCE_EGG)) {
            // Increment counter for egg-laying logic
            counter++;
            // Check if it's time to lay an egg (every 7 turns)
            if (counter % 7 == 0) {
                return this;
            }
        }
        return null;
    }
    
    /**
     * Allow the Actor to reproduce, either by producing offspring or laying eggs.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of the reproduction action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.PRODUCE_OFFSPRING)) {
            return produceOffspring(actor, map);
        } else if (actor.hasCapability(Ability.PRODUCE_EGG)) {
            return produceEgg(actor, map);
        }
        return actor + " cannot reproduce.";
    }
    
    /**
     * Check if offspring production is possible by finding valid locations near blessed entities.
     *
     * @param actor the Actor attempting to reproduce
     * @param map the GameMap the actor is on
     * @return true if reproduction is possible, false otherwise
     */
    private boolean canProduceOffspring(Actor actor, GameMap map) {
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
        
        // If not near a blessed entity or no valid locations, reproduction is not possible
        if (!nearBlessedEntity || validLocations.isEmpty()) {
            return false;
        }
        
        // Select a random valid location for reproduction
        reproduceAt = validLocations.get(random.nextInt(validLocations.size()));
        return true;
    }
    
    /**
     * Produce offspring at the previously determined valid location.
     *
     * @param actor the Actor reproducing
     * @param map the GameMap the actor is on
     * @return a description of the reproduction
     */
    public String produceOffspring(Actor actor, GameMap map) {
        reproducible.reproduce(actor, reproduceAt);
        return menuDescription(actor);
    }
    
    /**
     * Produce an egg at the actor's current location.
     * Egg production only depends on turn count and doesn't need blessed entities.
     *
     * @param actor the Actor laying the egg
     * @param map the GameMap the actor is on
     * @return a description of the egg laying
     */
    public String produceEgg(Actor actor, GameMap map) {
        // Simply reproduce at the current location
        reproducible.reproduce(actor, map.locationOf(actor));
        return actor + " lays an egg.";
    }
    
    /**
     * Returns a description of this reproduction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String describing the reproduction
     */
    @Override
    public String menuDescription(Actor actor) {
        if (actor.hasCapability(Ability.PRODUCE_OFFSPRING)) {
            return actor + " successfully reproduces >o<";
        } else {
            return actor + " lays an egg >o<";
        }
    }
}