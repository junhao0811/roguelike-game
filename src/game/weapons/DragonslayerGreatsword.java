package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.capabilities.Sellable;
import game.effects.Effect;
import game.effects.attributeeffects.IncreaseMaxAttributeEffect;

import java.util.ArrayList;
import java.util.List;

public class DragonslayerGreatsword extends WeaponItem implements Sellable {

    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D',
                70, "strikes", 75);
    }

    @Override
    public List<Effect> getEffects() {
        List<Effect> effects = new ArrayList<>();

        effects.add(new IncreaseMaxAttributeEffect(BaseActorAttributes.HEALTH, 15));

        return effects;
    }
}