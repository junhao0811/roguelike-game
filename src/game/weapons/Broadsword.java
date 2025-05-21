package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.EffectList;
import game.effects.attributeeffects.RestoreAttributeEffect;

public class Broadsword extends WeaponItem implements Sellable {

    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }

    @Override
    public EffectList getEffects() {
        EffectList effects = new EffectList();

        effects.add(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 10));

        return effects;
    }
}