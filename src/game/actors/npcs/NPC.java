package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.enums.Status;

import java.util.*;

/**
 * Class representing an NPC
 *
 * @author Lee Hao Zhe
 */
public abstract class NPC extends Actor {

    /**
     * The behaviours TreeMap to store all the behaviours of the NPC
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private final boolean randomBehaviour;

    /**
     * Constructor
     *
     * @param name        the name of the NPC
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the NPC's starting hit points
     * @param randomBehaviour boolean to determine if the NPC selects behaviour randomly
     */
    protected NPC(String name, char displayChar, int hitPoints, boolean randomBehaviour) {
        super(name, displayChar, hitPoints);
        this.randomBehaviour = randomBehaviour;
        setup();
    }

    /**
     * Constructor with random behaviour set to false as default
     *
     * @param name        the name of the NPC
     * @param displayChar the character that will represent the NPC in the display
     * @param hitPoints   the NPC's starting hit points
     */
    protected NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.randomBehaviour = false;
        setup();
    }

    /**
     * Method to setup all the unique properties of the NPC such as behaviours,
     * capabilities, status effects, and intrinsic weapon
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

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (randomBehaviour) {
            Random random = new Random();
            List<Behaviour> behaviourList = new ArrayList<>(getBehaviours());
            Behaviour behaviour = behaviourList.get(random.nextInt(behaviourList.size()));
            Action action = behaviour.getAction(this, map);

            if (action != null) {
                return action;
            }

        } else {
            for (Behaviour behaviour : getBehaviours()) {
                Action action = behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
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
