package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
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

/**
 * Class representing an Omen Sheep. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class OmenSheep extends NPC implements Curable, Reproducible, Hatchable {

    private int reproductionCounter = 0;
    
    /**
     * Constructor.
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new ReproduceBehaviour(this));
        this.addCapability(Ability.PRODUCE_EGG);
    }

    /**
     * Cures the environment by replacing nearby ground with Inheritree instances.
     * This action applies to the locations surrounding the current actor's position
     * in the provided game map.
     *
     * @param actor the actor initiating the cure action
     * @param location the location of the actor
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
     * Checks if the OmenSheep can lay an egg.
     * OmenSheep lays eggs every 7 turns.
     *
     * @param actor the actor considering reproduction
     * @param map the map the actor is on
     * @return true if the actor can lay an egg, false otherwise
     */
    @Override
    public boolean canReproduce(Actor actor, GameMap map) {
        reproductionCounter++;
        return reproductionCounter % 7 == 0;
    }

    /**
     * Hatches the egg into a new Omen Sheep instance.
     *
     * @return a new Omen Sheep instance
     */
    @Override
    public NPC hatch() {
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
        return counter == 3;
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

        // Check if the actor has the item which has the ability to cure
        if (otherActor.hasCapability(Ability.CURE)) {
            actions.add(new CureAction(this, direction, map.locationOf(this)));
        }

        return actions;
    }
}

