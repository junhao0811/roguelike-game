package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.CureAction;
import game.actors.CrimsonRot;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class SpiritGoat extends Npc implements Curable, Reproducible {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private final Random random = new Random();

    /**
     * Constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.behaviours.put(0, new ReproduceBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.PRODUCE_OFFSPRING);
    }

    /**
     * Resets the countdown timer back to 10 when it is being cured
     *
     * @param actor    unused here
     * @param location unused here
     */
    public boolean cure(Actor actor, Location location) {
        // Retrieves all status effects the spirit goat currently has right now
        List<StatusEffect> statusEffects = this.getStatusEffects();
        StatusEffect crimsonRot = null;

        // linear searching to find if spirit goat has the crimson rot status effect
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect.toString().contains("Crimson Rot")) {
                crimsonRot = statusEffect;
            }
        }

        // Resets countdown timer back to 10 turns
        this.removeStatusEffect(crimsonRot);
        this.addStatusEffect(new CrimsonRot(10));

        System.out.println("The Spirit Goat's energy has been restored!");
        return true;
    }

    /**
     * Creates an offspring at a random valid location around the actor.
     * This contains all the logic for finding a valid location and placing the offspring.
     *
     * @param actor    the parent actor
     * @param location the actor's current location
     */
    public void reproduce(Actor actor, Location location) {
        // Find all valid locations around the actor
        ArrayList<Location> validLocations = new ArrayList<>();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor) && !destination.containsAnActor()) {
                validLocations.add(destination);
            }
        }

        // If no valid locations found, don't reproduce
        if (validLocations.isEmpty()) {
            new Display().println(actor + " tries to reproduce but finds no space for offspring");
            return;
        }

        // Select a random valid location
        Location birthLocation = validLocations.get(random.nextInt(validLocations.size()));

        // Create and place the offspring
        SpiritGoat offspring = new SpiritGoat();
        birthLocation.addActor(offspring);

        new Display().println("An offspring of " + actor + " has spawned");
    }

    /**
     * Checks if the SpiritGoat can reproduce.
     * SpiritGoat requires being near a blessed entity to reproduce.
     *
     * @param actor the actor considering reproduction
     * @param map the map the actor is on
     * @return true if the actor can reproduce, false otherwise
     */
    @Override
    public boolean canReproduce(Actor actor, GameMap map) {
        // Check if actor is near any blessed entity
        Location actorLocation = map.locationOf(actor);

        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Status.BLESSED)) {
                return true;
            }
        }

        return false;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in
     *                   conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action
     * is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Check if the actor is unconscious due to natural causes or accident
        if (!this.isConscious()) {
            display.println(this.unconscious(map));
        }

        // Check if this actor is still on the map
        if (map.locationOf(this) != null) {
            for (Behaviour behaviour : behaviours.values()) {
                Action action = behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
        }
        return new DoNothingAction();
    }

    /**
     * The creature can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions that can be performed by the other actor
     * @see edu.monash.fit2099.demo.huntsman.HuntsmanSpider#allowableActions(Actor, String, GameMap)
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        // Check if the actor if he has the item which has the ability to cure
        if (otherActor.hasCapability(Ability.CURE)) {
            actions.add(new CureAction(this, direction, map.locationOf(this)));
        }
        return actions;
    }
}
