package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class RestoreAttributeEffect extends ModAttributeEffect {

    public RestoreAttributeEffect(BaseActorAttributes attribute, int value) {
        super(attribute, ActorAttributeOperations.INCREASE, value, "recovers");
    }

    public RestoreAttributeEffect(BaseActorAttributes attribute, int value, Actor target) {
        super(attribute, ActorAttributeOperations.INCREASE, value, "recovers", target);
    }
}
