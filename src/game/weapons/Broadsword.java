package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.Effect;
import game.effects.attributeeffects.RestoreAttributeEffect;

import java.util.ArrayList;
import java.util.List;

public class Broadsword extends WeaponItem implements Sellable {

    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }

    @Override
    public List<Effect> getEffects() {
        List<Effect> effects = new ArrayList<>();

        effects.add(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 10));

        return effects;
    }
}