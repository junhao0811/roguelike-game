package game.items.watercontainers;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.enums.Status;

public class HolyGrail extends WaterContainer {

    public HolyGrail() {
        super("Holy Grail", 'h');
        this.addCapability(Status.BLESSED);
        this.addEffect(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 15));
    }
}
