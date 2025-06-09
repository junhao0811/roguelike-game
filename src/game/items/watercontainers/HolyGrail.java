package game.items.watercontainers;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.effects.spawneffects.SpawnSpiritGoatEffect;
import game.enums.Status;

/**
 * Class representing a water container called the Holy Grail.
 * This water container is blessed by grace, heals the user, and spawns a Spirit Goat when used.
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class HolyGrail extends WaterContainer {

    /**
     * Constructor
     */
    public HolyGrail() {
        super("Holy Grail", 'h');
        this.addCapability(Status.BLESSED);
        this.addEffect(new RestoreAttributeEffect(BaseActorAttributes.HEALTH, 15));
        this.addEffect(new SpawnSpiritGoatEffect());
    }
}
