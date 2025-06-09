package game.items.watercontainers;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.effects.attributeeffects.SwapAttributeEffect;
import game.enums.Status;

/**
 * Class representing a water container called the Cursed Pot.
 * This water container is cursed and swaps the user's stamina and health
 * (both max and current value) when used
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class CursedPot extends WaterContainer {

    /**
     * Constructor
     */
    public CursedPot() {
        super("Cursed Pot", 'c');
        this.addCapability(Status.CURSED);
        this.addEffect(new SwapAttributeEffect(BaseActorAttributes.HEALTH,
                BaseActorAttributes.STAMINA));
    }
}
