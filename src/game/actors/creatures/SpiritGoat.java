package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.CureAction;
import game.actors.CrimsonRot;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Curable;
import game.capabilities.Reproducible;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class SpiritGoat extends Npc implements Curable, Reproducible {

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.behaviours.put(Integer.MAX_VALUE, new WanderBehaviour());
        this.behaviours.put(888, new ReproduceBehaviour(this));
    }

    /**
     * Resets the countdown timer back to 10 when it is being cured
     *
     * @param actor    unused here
     * @param location unused here
     */
    public boolean cure(Actor actor, Location location) {
        // Retrieves all status effects the spirit goat currently has right now
        List<StatusEffect> statusEffects = this.getStatusEffects();
        StatusEffect crimsonRot = null;

        // linear searching to find if spirit goat has the crimson rot status effect
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect.toString().contains("Crimson Rot")) {
                crimsonRot = statusEffect;
            }
        }

        // Resets countdown timer back to 10 turns
        this.removeStatusEffect(crimsonRot);
        this.addStatusEffect(new CrimsonRot(10));

        System.out.println("The Spirit Goat's energy has been restored!");
        return true;
    }

    public void reproduce(Actor actor, GameMap map) {
        SpiritGoat offSpring = new SpiritGoat();
        map.addActor(offSpring, map.locationOf(this));
        new Display().println("An offspring of Spirit Goat has been born near the Inheritree!");
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
        ActionList actions = new ActionList();
        // Check if the actor if he has the item which has the ability to cure
        if (otherActor.hasCapability(Ability.CURE)) {
            actions.add(new CureAction(this, direction, map.locationOf(this)));
        }
        return actions;
    }
}
