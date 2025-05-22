package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.enums.Status;
import game.actions.CureAction;
import game.actors.statuseffects.CrimsonRot;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Reproducible;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao and Ng Jun Xian
 * @version 22.0.1
 */
public class SpiritGoat extends NPC implements Curable, Reproducible {

    private final Random random = new Random();

    /**
     * Constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new ReproduceBehaviour());
        this.addStatusEffect(new CrimsonRot(10));
        this.addCapability(Ability.PRODUCE_OFFSPRING);
        this.addCapability(Status.CURSED);
    }

    /**
     * Resets the countdown timer back to 10 when it is being cured
     *
     * @param actor    unused here
     */
    @Override
    public String cure(Actor actor, GameMap map) {
        StatusEffect crimsonRot = null;

        for (StatusEffect statusEffect : this.getStatusEffects()) {
            if (statusEffect.toString().contains("Crimson Rot"))
                crimsonRot = statusEffect;
        }

        this.removeStatusEffect(crimsonRot);
        this.addStatusEffect(new CrimsonRot(10));

        return actor + " cures the " + this + ", resetting its decay of Crimson Rot";
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
     * The creature can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions that can be performed by the other actor
     * @see edu.monash.fit2099.demo.huntsman.HuntsmanSpider#allowableActions(Actor, String, GameMap)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        for (Item item : otherActor.getItemInventory()) {
            if (item.hasCapability(Ability.CURE)) {
                actions.add(new CureAction(this, direction, item));
            }
        }

        return actions;
    }
}
