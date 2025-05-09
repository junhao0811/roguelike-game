package game.creatures; //req 1

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.statuseffect.CrimsonRot;
import game.behaviour.WanderBehaviour;
import game.behaviour.ReproduceBehaviour;
import game.ability.Ability;
import game.actions.AttackAction;
import game.actions.CureAction;
import game.interfaces.Curable;
import game.interfaces.Reproducable;
import game.status.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing a Spirit Goat creature in the game.
 * The Spirit Goat wanders around the map and disappears after the countdown timer expires.
 * It can be cured by actors with the CURING ability, which resets its countdown timer.
 * It can also be attacked by actors with the HOSTILE_TO_ENEMY capability.
 *
 * @author Ng Jun Xian
 */
public class SpiritGoat extends Actor implements Curable, Reproducable{
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();


    /**
     * Constructor for SpiritGoat.
     * Creates a Spirit Goat with the character 'y', 50 hit points, and a wander behavior.
     * Adds a CrimsonEffect as a StatusEffect and initialise the countdown from 15
     * Includes ReproduceBehaviour with higher priority than WanderBehaviour
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.behaviours.put(800, new ReproduceBehaviour(this));  // Higher priority for reproduction
        //this.behaviours.put(999, new WanderBehaviour());
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
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Cures the Spirit Goat by resetting its countdown timer.
     * This method is called when an actor with CURING ability uses a Talisman on the Spirit Goat.
     *
     * @param actor the actor performing the cure action
     * @param location the location of the Spirit Goat
     * @return true, indicating the cure was successful
     */
    @Override
    public boolean cure(Actor actor, Location location) {
        StatusEffect crimsonRot = null;

        for (StatusEffect effect: this.getStatusEffects()){
            if (effect.toString().contains("CrimsonRot")){
                crimsonRot = effect;
            }
        }
        this.removeStatusEffect(crimsonRot);
        this.addStatusEffect(new CrimsonRot(100));
        System.out.println("The Spirit Goat's energy is restored!");
        return true;
    }

    /**
     * Allows a Spirit Goat to reproduce and create a new Spirit Goat in a surrounding location.
     * The ReproduceBehaviour class has already checked that conditions are suitable for reproduction.
     *
     * @param actor the actor reproducing (the parent Spirit Goat)
     * @param location the location where the offspring will be placed
     * @param map the game map containing the Spirit Goat
     */
    @Override
    public void reproduce(Actor actor, Location location, GameMap map) {
        // Create and add new Spirit Goat at the specified location
        // The location has already been validated by ReproduceBehaviour
        SpiritGoat offspring = new SpiritGoat();
        location.addActor(offspring);
        System.out.println("A new Spirit Goat is born near the Inheritree!");
    }

    /**
     * Returns a collection of actions that can be performed on the Spirit Goat by another actor.
     * Actors with CURING ability can cure the Spirit Goat.
     * Actors with HOSTILE_TO_ENEMY capability can attack the Spirit Goat.
     *
     * @param otherActor the Actor that might perform an action on this Spirit Goat
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
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}