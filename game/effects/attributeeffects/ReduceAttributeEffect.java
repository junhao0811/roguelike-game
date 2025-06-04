package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * Class representing a restore attribute effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class ReduceAttributeEffect extends ModAttributeEffect {

    /**
     * Constructor
     *
     * @param attribute the attribute to modify
     * @param value the value to modify the attribute by
     * @param target the target of the effect
     */
    public ReduceAttributeEffect(BaseActorAttributes attribute, int value, Actor target) {
        super(attribute, ActorAttributeOperations.DECREASE, value, "loses", target);
    }

    /**
     * Constructor with no target as default
     *
     * @param attribute the attribute to modify
     * @param value the value to modify the attribute by
     */
    public ReduceAttributeEffect(BaseActorAttributes attribute, int value) {
        super(attribute, ActorAttributeOperations.DECREASE, value, "loses");
    }
}
