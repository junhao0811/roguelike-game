package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.Effect;
import game.effects.attributeeffects.ReduceAttributeEffect;

import java.util.ArrayList;
import java.util.List;

public class Katana extends WeaponItem implements Sellable {

    public Katana() {
        super("Katana", 'j', 50, "hits", 60);
    }

    @Override
    public List<Effect> getEffects() {
        List<Effect> effects = new ArrayList<>();

        effects.add(new ReduceAttributeEffect(BaseActorAttributes.HEALTH, 25));

        return effects;
    }
}