package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.enums.Status;
import game.actions.EatAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Edible;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.items.Egg;

public class GoldenBeetle extends NPC implements Reproducible, Hatchable, Edible {

    private int reproductionCounter = 0;
    
    /**
     * Constructor.
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new FollowBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 2, new ReproduceBehaviour(this));
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

    public NPC hatch() {
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
     * Allows the provided actor to eat the Egg, increasing their maximum health if they eat the Egg
     * in their inventory. The Egg is removed from the actor's inventory upon consumption.
     *
     * @param actor the actor attempting to eat the Egg
     * @param map the map
     */
    public void eat(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.EAT)) {
            actor.heal(15);
            actor.addBalance(1000);
            this.unconscious(actor, map);
        }
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