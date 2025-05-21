package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.EffectList;
import game.effects.attributeeffects.ReduceAttributeEffect;

public class Katana extends WeaponItem implements Sellable {

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