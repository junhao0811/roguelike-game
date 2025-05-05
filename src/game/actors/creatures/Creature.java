package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import game.actions.AttackAction;
import game.Status;
import game.behaviours.WanderBehaviour;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public abstract class Creature extends Actor {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public Creature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(0, new WanderBehaviour()); // Highest priority
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
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}