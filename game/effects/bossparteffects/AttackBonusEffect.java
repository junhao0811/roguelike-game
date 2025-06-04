package game.effects.bossparteffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;

/**
 * Effect that increases the boss's attack power
 * Applied when boss parts grow
 *
 * @author GitHub Copilot
 */
public class AttackBonusEffect extends Effect {
    
    private final int bonusAmount;
    
    /**
     * Constructor for AttackBonusEffect
     *
     * @param target the target actor (boss)
     * @param bonusAmount the amount of attack bonus to add
     */
    public AttackBonusEffect(Actor target, int bonusAmount) {
        super(target);
        this.bonusAmount = bonusAmount;
    }
    
    /**
     * Constructor with no specific target
     *
     * @param bonusAmount the amount of attack bonus to add
     */
    public AttackBonusEffect(int bonusAmount) {
        super();
        this.bonusAmount = bonusAmount;
    }
    
    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = getTarget(actor);
        // The attack bonus is handled by the boss's getIntrinsicWeapon() method
        // This effect serves as a marker/record of the bonus
        return target + " gains " + bonusAmount + " attack power from grown part";
    }
    
    /**
     * Gets the bonus amount for external access
     */
    public int getBonusAmount() {
        return bonusAmount;
    }
}