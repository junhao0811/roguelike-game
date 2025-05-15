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
import game.Status;
import game.actions.EatAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Edible;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.items.Egg;

import java.util.Map;
import java.util.TreeMap;

public class GoldenBeetle extends Npc implements Reproducible, Hatchable, Edible {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private int reproductionCounter = 0;
    
    /**
     * Constructor.
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        this.behaviours.put(0, new ReproduceBehaviour(this));
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.PRODUCE_EGG);
    }
    
    /**
     * Reproduces an egg at the given location.
     *
     * @param actor    the actor that is reproducing
     * @param location the location where the egg will be placed
     */
    public void reproduce(Actor actor, Location location) {
        location.addItem(new Egg(this, "Golden Egg"));
    }

    /**
     * Checks if the GoldenBeetle can lay an egg.
     * GoldenBeetle lays eggs every 5 turns.
     *
     * @param actor the actor considering reproduction
     * @param map the map the actor is on
     * @return true if the actor can lay an egg, false otherwise
     */
    @Override
    public boolean canReproduce(Actor actor, GameMap map) {
        reproductionCounter++;
        return reproductionCounter % 5 == 0;
    }

    public Npc hatch() {
        return new GoldenBeetle();
    }
    
    public boolean isReadyToHatch(int counter, Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.getGround().hasCapability(Status.CURSED)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Allows the provided actor to eat the Egg, increasing their maximum health if they the egg is
     * in their inventory. The Egg is removed from the actor's inventory upon consumption.
     *
     * @param actor the actor attempting to eat the Egg
     * @param map
     */
    public void eat(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.EAT)) {
            actor.heal(15);
            actor.addBalance(1000);
            this.unconscious(actor, map);
        }
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
            // The Golden Beetle will begin to follow the actor if they detect a surrounding actor is followable
            boolean foundActorToFollow = false;
            for (Exit exit : map.locationOf(this).getExits()) {
                Location nearby = exit.getDestination();
                // It will only follow the actor until either the followed actor or the beetle itself is gone from the map
                if (nearby.containsAnActor() && !this.behaviours.containsKey(1)) {
                    Actor target = nearby.getActor();
                    this.behaviours.put(1, new FollowBehaviour(target));
                    foundActorToFollow = true;
                    break;
                }
            }
            
            // Only remove FollowBehavior if checked all exits and found no actors
            if (!foundActorToFollow && this.behaviours.containsKey(1)) {
                this.behaviours.remove(1);
            }
            
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
     * The Golden Beetle can be consumed by any actor that has the EAT capability
     *
     * @param otherActor the Actor that might consume the Golden Beetle
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of actions that can be performed
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Ability.EAT)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }
}