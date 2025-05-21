package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.EffectList;
import game.effects.attributeeffects.ReduceAttributeEffect;

/**
 * Class representing a weapon item called a katana.
 * This weapon item deals 50 damage points with a 60% chance to hit the target.
 */
public class Katana extends WeaponItem implements Sellable {

    /**
     * Constructor
     */
    public Katana() {
        super("Katana", 'j', 50, "hits", 60);
    }

    @Override
    public EffectList getEffects() {
        EffectList effects = new EffectList();

        effects.add(new ReduceAttributeEffect(BaseActorAttributes.HEALTH, 25));

        return effects;
    }
}