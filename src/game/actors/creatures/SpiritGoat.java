package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.conditions.NearbyCondition;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.enums.Status;
import game.actions.CureAction;
import game.actors.statuseffects.CrimsonRot;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Reproducible;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a Spirit Goat
 *
 * @author Chong Jun Hao and Ng Jun Xian
 * @version 22.0.1
 */
public class SpiritGoat extends Creature implements Curable, Reproducible {

    private final Random random = new Random();

    /**
     * Constructor
     *
     * @param selection the Spirit Goat's strategy for selecting behaviours
     */
    public SpiritGoat(BehaviourSelection selection) {
        super("Spirit Goat", 'y', 50, selection);
    }

    /**
     * Constructor with default behaviour selection as default
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
    }

    @Override
    protected void setup() {
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1,
                new ReproduceBehaviour(this, new NearbyCondition(Status.BLESSED)));
        this.addStatusEffect(new CrimsonRot(10));
        this.addCapability(Ability.PRODUCE_OFFSPRING);
        this.addCapability(Status.CURSED);
    }

    /**
     * Creates an offspring at a random valid location around the actor.
     * This contains all the logic for finding a valid location and placing the offspring.
     *
     * @param actor    the parent actor
     * @param location the actor's current location
     */
    @Override
    public String reproduce(Actor actor, Location location) {
        List<Location> destinations = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(actor)) {
                destinations.add(destination);
            }
        }

        if (!destinations.isEmpty()) {
            Creature child = new SpiritGoat(getSelection());
            destinations.get(random.nextInt(destinations.size())).addActor(child);

            return this + " produces a new " + child;
        }

        return this + " tries to reproduce but finds no space for offspring";
    }

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
