package game.items.watercontainers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.effects.attributeeffects.ReduceAttributeEffect;
import game.enums.Status;

/**
 * Class representing a water container called the Demon Jug.
 * This water container hurts the user when used, but is not usable on crops blessed by grace.
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class DemonJug extends WaterContainer {

    /**
     * Constructor
     */
    public DemonJug() {
        super("Demon Jug", 'd');
        this.addEffect(new ReduceAttributeEffect(BaseActorAttributes.HEALTH, 25));
    }

    @Override
    public String water(Actor actor, GameMap map, Ground target) {
        if (target.hasCapability(Status.BLESSED)) {
            return actor + " is unable to water the " + target + " with " + this +
                    " as the " + target + " is blessed by grace";
        }

        return super.water(actor, map, target);
    }
}
