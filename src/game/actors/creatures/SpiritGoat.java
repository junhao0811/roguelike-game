package game.actors.creatures;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.CrimsonRot;
import game.behaviours.WanderBehaviour;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a Spirit Goat. Created by: Chong Jun Hao
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class SpiritGoat extends Creature {
    
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    
    /**
     * Constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.behaviours.put(0, new WanderBehaviour()); // Highest priority
    }
    
    /**
     * Resets the countdown timer back to 10 when it is being cured
     * @param actor unused here
     * @param map the gamemap the actor is currently on
     */
    public boolean cure(Actor actor, GameMap map) {
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

        System.out.println("The Spirit Goat's energy is restored!");
        return true;
    }
}
