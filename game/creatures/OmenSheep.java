package game.creatures; //req 1

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.statuseffect.CrimsonRot;
import game.crops.Inheritree;
import game.behaviour.WanderBehaviour;
import game.behaviour.LayEggBehaviour;
import game.ability.Ability;
import game.actions.AttackAction;
import game.actions.CureAction;
import game.interfaces.Curable;
import game.interfaces.Reproducable;
import game.items.OmenSheepEgg;
import game.status.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing an Omen Sheep creature in the game which implements the Curable and Reproducable interfaces.
 * The Omen Sheep wanders around the map and disappears after the countdown timer ends.
 * It can be cured by actors with the CURING ability, which will plant Inheritrees in surrounding locations.
 * It can also be attacked by actors with the HOSTILE_TO_ENEMY capability.
 * Every seven turns, it lays an egg that can hatch into a new Omen Sheep if left on the ground for 3 turns.
 *
 */
public class OmenSheep extends Actor implements Curable, Reproducable { //req 1 only inherits actor
    private Map<Integer, Behaviour> behaviours = new TreeMap<>(); //req 1 assoc with behaviour
    private static final int EGG_LAY_INTERVAL = 7;

    /**
     * Constructor for OmenSheep.
     * Creates an Omen Sheep with the character 'm', 75 hit points, and a wander behavior.
     * Adds a CrimsonEffect as a StatusEffect and initialise the countdown from 15
     * Includes LayEggBehaviour to produce eggs every seven turns
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.behaviours.put(800, new LayEggBehaviour(this, EGG_LAY_INTERVAL)); // Higher priority for egg laying
        this.behaviours.put(999, new WanderBehaviour());//dependency with wander
        this.addStatusEffect(new CrimsonRot(100));
        this.addCapability(Ability.PRODUCE_OFF_SPRING);
    }

    /**
     * At each turn, select a valid action to perform.
     * It will follow its behaviors prioritized by their keys.
     * If no valid behavior action is found, it will do nothing.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map); //dependency on behaviour but alr got assoc
            if(action != null) //dependency on actions.Action
                return action;
        }
        return new DoNothingAction(); //dependency DoNothingAction inherits actions
    }

    /**
     * Cures the Omen Sheep by planting Inheritrees in all adjacent locations.
     * This method is called when an actor with CURING ability uses a Talisman on the Omen Sheep.
     *
     * @param actor the actor performing the cure action
     * @param location the location of the Omen Sheep
     * @return true, indicating the cure was successful
     */
    @Override
    public boolean cure(Actor actor, Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            destination.setGround(new Inheritree());

        }
        System.out.println("Inheritree has been planted around the Omen Sheep!");
        return true;
    }
    
    /**
     * Allows an Omen Sheep to reproduce by laying an egg at the specified location.
     * 
     * @param actor the actor reproducing (the parent Omen Sheep)
     * @param location the location to place the egg
     * @param map the map containing the actor
     */
    @Override
    public void reproduce(Actor actor, Location location, GameMap map) {
        // Create a new Omen Sheep egg and add it to the location
        OmenSheepEgg egg = new OmenSheepEgg();
        location.addItem(egg);
        System.out.println("The Omen Sheep has laid an egg!");
    }
    
    /**
     * Returns a collection of actions that can be performed on the Omen Sheep by another actor.
     * Actors with CURING ability can cure the Omen Sheep.
     * Actors with HOSTILE_TO_ENEMY capability can attack the Omen Sheep.
     *
     * @param otherActor the Actor that might perform an action on this Omen Sheep
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of actions that can be performed by otherActor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.CURING)){
            actions.add(new CureAction(this, map.locationOf(this), direction));
        }
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){ //req 1
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

}