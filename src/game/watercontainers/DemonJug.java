package game.watercontainers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.attributeeffects.ReduceAttributeEffect;
import game.enums.Status;
import game.grounds.crops.Crop;

public class DemonJug extends WaterContainer {

    public DemonJug() {
        super("Demon Jug", 'd');
        this.addEffect(new ReduceAttributeEffect(BaseActorAttributes.HEALTH, 25));
    }

    @Override
    public String water(Actor actor, GameMap map, Crop crop) {
        if (crop.hasCapability(Status.BLESSED)) {
            return actor + " is unable to water the " + crop + " with " + this +
                    " as the " + crop + " is blessed by grace";
        }

        return super.water(actor, map, crop);
    }
}
