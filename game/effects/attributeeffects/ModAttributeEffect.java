package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;

/**
 * Class representing a modify attribute effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public abstract class ModAttributeEffect extends Effect {

    private final BaseActorAttributes attribute;
    private final ActorAttributeOperations operation;
    private final int value;
    private final String verb;

    /**
     * Constructor
     *
     * @param attribute the attribute to modify
     * @param operation the operation to modify the attribute by
     * @param value the value to modify the attribute by
     * @param verb the verb uses to describe the attribute modification
     * @param target the target of the effect
     */
    public ModAttributeEffect(BaseActorAttributes attribute,
                              ActorAttributeOperations operation,
                              int value, String verb, Actor target) {
        super(target);
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
        this.verb = verb;
    }

    /**
     * Constructor with no target as default
     *
     * @param attribute the attribute to modify
     * @param operation the operation to modify the attribute by
     * @param value the value to modify the attribute by
     * @param verb the verb uses to describe the attribute modification
     */
    public ModAttributeEffect(BaseActorAttributes attribute,
                              ActorAttributeOperations operation,
                              int value, String verb) {
        super();
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
        this.verb = verb;
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = this.getTarget(actor);

        actor.modifyAttribute(attribute, operation, value);

        return target + " " + verb + " " + value + " " + attribute.toString().toLowerCase();
    }
}
