package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.EffectList;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;

/**
 * Class representing a weapon item called a dragonslayer greatsword.
 * This weapon item deals 70 damage points with a 75% chance to hit the target.
 *
 * @author Tan Choong Sheng
 */
public class DragonslayerGreatsword extends WeaponItem implements Sellable {

    /**
     * Constructor
     */
    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D',
                70, "strikes", 75);
    }

    @Override
    public EffectList getEffects() {
        EffectList effects = new EffectList();

        effects.add(new IncreaseMaxAttributeEffect(BaseActorAttributes.HEALTH, 15));

        return effects;
    }
}