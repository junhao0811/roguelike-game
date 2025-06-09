package game.items.watercontainers;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.effects.attributeeffects.SwapAttributeEffect;
import game.enums.Status;

public class CursedPot extends WaterContainer {

    public CursedPot() {
        super("Cursed Pot", 'c');
        this.addCapability(Status.CURSED);
        this.addEffect(new SwapAttributeEffect(BaseActorAttributes.HEALTH,
                BaseActorAttributes.STAMINA));
    }
}
