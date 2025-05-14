package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.CureAction;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.grounds.crops.Inheritree;
import game.items.Egg;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing an Omen Sheep. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class OmenSheep extends Npc implements Curable, Reproducible, Hatchable {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.behaviours.put(0, new ReproduceBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.PRODUCE_EGG);
    }

    /**
     * Cures the environment by replacing nearby ground with Inheritree instances.
     * This action applies to the locations surrounding the current actor's position
     * in the provided game map.
     *
     * @param actor the actor initiating the cure action
     * @param location the location of the actor1
     *
     */
    public boolean cure(Actor actor, Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.canActorEnter(this)) {
                nearby.setGround(new Inheritree());
            }
        }
        System.out.println("Inheritree has been planted around the Omen Sheep!");
        return true;
    }

    /**
     * Reproduces an egg at the given location.
     *
     * @param actor    the actor performing the reproduction
     * @param location the location where the egg will be placed
     */
    @Override
    public void reproduce(Actor actor, Location location) {
        location.addItem(new Egg(this, "Egg"));
    }

    /**
     * Hatches the egg into a new Omen Sheep instance.
     *
     * @return a new Omen Sheep instance
     */
    @Override
    public Npc hatch() {
        return new OmenSheep();
    }

    /**
     * Checks if the egg is ready to hatch. The egg is ready to hatch if the counter is 3.
     *
     * @param counter   the current counter value
     * @param location  the location of the egg
     * @return true if the egg is ready to hatch, false otherwise
     */
    @Override
    public boolean isReadyToHatch(int counter, Location location) {
        return counter == 3; // OmenSheep eggs hatch after 3 turns
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

