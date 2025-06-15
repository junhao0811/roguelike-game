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
    private final BehaviourSelection selection;

    /**
     * Random instance for random behavior selection
     */
    private static final Random random = new Random();

    /**
     * Constructor
     *
     * @param name        the name of the Creature
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the Creature's starting hit points
     * @param selection   the Creature's strategy for selecting behaviours
     */
    protected Creature(String name, char displayChar, int hitPoints, BehaviourSelection selection ) {
        super(name, displayChar, hitPoints);
        this.selection = selection;
        this.setup();
    }

    /**
     * Constructor with default behaviour selection as default
     *
     * @param name        the name of the Creature
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the Creature's starting hit points
     */
    protected Creature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.selection = BehaviourSelection.DEFAULT;
        this.setup();
    }

    /**
     * Method to setup the basic behaviours, capabilities,
     * and/or status effects of the Creature
     */
    protected abstract void setup();

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

    /**
     * Gets the behaviour selection of the creature
     *
     * @return the behaviour selection
     */
    public BehaviourSelection getSelection() {
        return selection;
    }
    
    /**
     * Determines and executes the action to be performed by the creature during its turn.
     * The action is selected based on the set of behaviours the creature has and its behaviour selection strategy.
     * If no behaviours are applicable or no action is selected, a DoNothingAction is returned.
     *
     * @param actions A list of allowable actions the creature can perform.
     * @param lastAction The action the creature performed in the last turn.
     * @param map The current game map where the creature is located.
     * @param display The display used to show output to the user.
     * @return The selected action for the current turn, or a DoNothingAction if no actions are determined.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action selectedAction = null;

        if (selection == BehaviourSelection.DEFAULT) {
            for (Behaviour behaviour : behaviours.values()) {
                selectedAction = behaviour.getAction(this, map);
                if (selectedAction != null) {
                    break;
                }
            }

        } else if (selection == BehaviourSelection.RANDOM) {
            if (!behaviours.isEmpty()) {
                List<Behaviour> behaviorList = new ArrayList<>(behaviours.values());
                Behaviour randomBehaviour = behaviorList.get(random.nextInt(behaviorList.size()));
                selectedAction = randomBehaviour.getAction(this, map);
                // If the selected behavior returns null, the creature does nothing
            }
        }

        if (selectedAction != null) {
            return selectedAction;
        }

        return new DoNothingAction();
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