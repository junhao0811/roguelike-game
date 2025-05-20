package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class DecreaseMaxAttributeEffect extends ModAttributeMaxEffect {

    public DecreaseMaxAttributeEffect(BaseActorAttributes attribute, int value) {
        super(attribute, ActorAttributeOperations.DECREASE, value, "loses");
    }

    public DecreaseMaxAttributeEffect(BaseActorAttributes attribute, int value, Actor target) {
        super(attribute, ActorAttributeOperations.DECREASE, value, "loses", target);
    }
}
