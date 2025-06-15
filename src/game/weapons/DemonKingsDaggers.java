package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.EffectList;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;

/**
 * Secret weapon given by Merchant Kale at high affection
 * Legendary Demon King's Daggers
 *
 * @author Chong Jun Hao
 */
public class DemonKingsDaggers extends WeaponItem implements Sellable {
    
    /**
     * Constructor for the Demon King's Daggers
     */
    public DemonKingsDaggers() {
        super("Demon King's Daggers", 'V', 999, "slashes", 90);
    }
    
    @Override
    public EffectList getEffects() {
        EffectList effects = new EffectList();
        effects.add(new IncreaseMaxAttributeEffect(BaseActorAttributes.HEALTH, 100));
        effects.add(new IncreaseMaxAttributeEffect(BaseActorAttributes.STAMINA, 100));
        return effects;
    }
    
    @Override
    public String toString() {
        return "Demon King's Daggers";
    }
}