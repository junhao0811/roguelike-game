package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.enums.BehaviourSelection;
import game.enums.Status;


import java.util.*;

/**
 * Class representing a Creature
 *
 * @author Tan Choong Sheng
 */
public abstract class Creature extends Actor {

    /**
     * The behaviours TreeMap to store all the behaviours of the Creature
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The strategy for selecting behaviors
     */
    private final BehaviourSelection selectionStrategy;

    /**
     * Random instance for random behavior selection
     */
    private static final Random random = new Random();

    /**
     * Constructor
     *
     * @param name        the name of the Creature
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the NPC's starting hit points
     */
    protected Creature(String name, char displayChar, int hitPoints, BehaviourSelection selectionStrategy ) {
        super(name, displayChar, hitPoints);
        this.selectionStrategy = selectionStrategy;
    }

    /**
     * Add behaviour to the behaviours TreeMap
     *
     * @param key       the key
     * @param behaviour the behaviour
     */
    public void addBehaviour(int key, Behaviour behaviour) {
        this.behaviours.put(key, behaviour);
    }

    /**
     * Gets the collection of behaviours
     *
     * @return the behaviours
     */
    public Collection<Behaviour> getBehaviours() {
        return behaviours.values();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action selectedAction = null;

        if (selectionStrategy == BehaviourSelection.DEFAULT) {
            for (Behaviour behaviour : behaviours.values()) {
                selectedAction = behaviour.getAction(this, map);
                if (selectedAction != null) {
                    break;
                }
            }

        } else if (selectionStrategy == BehaviourSelection.RANDOM) {
            if (!behaviours.isEmpty()) {
                List<Behaviour> behaviorList = new ArrayList<>(behaviours.values());
                Behaviour randomBehaviour = behaviorList.get(random.nextInt(behaviorList.size()));
                selectedAction = randomBehaviour.getAction(this, map);
                // If the selected behavior returns null, the creature does nothing
            }
        }

        if (selectedAction != null) {
            return selectedAction;
        } else {
            return new DoNothingAction();
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

}