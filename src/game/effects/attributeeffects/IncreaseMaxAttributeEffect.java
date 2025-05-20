package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class IncreaseMaxAttributeEffect extends ModAttributeMaxEffect {

    public IncreaseMaxAttributeEffect(BaseActorAttributes attribute, int value) {
        super(attribute, ActorAttributeOperations.INCREASE, value, "gains");
    }

    public IncreaseMaxAttributeEffect(BaseActorAttributes attribute, int value, Actor target) {
        super(attribute, ActorAttributeOperations.INCREASE, value, "gains", target);
    }
}
