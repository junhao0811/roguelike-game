package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;

public abstract class ModAttributeMaxEffect extends Effect {

    private final BaseActorAttributes attribute;
    private final ActorAttributeOperations operation;
    private final int value;
    private final String verb;

    public ModAttributeMaxEffect(BaseActorAttributes attribute,
                                 ActorAttributeOperations operation,
                                 int value, String verb) {
        super();
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
        this.verb = verb;
    }

    public ModAttributeMaxEffect(BaseActorAttributes attribute,
                                 ActorAttributeOperations operation,
                                 int value, String verb, Actor target) {
        super(target);
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
        this.verb = verb;
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = this.getTarget(actor);
        int updateValue = target.getAttribute(attribute);

        if (operation == ActorAttributeOperations.INCREASE) {
            updateValue += value;
        }

        actor.modifyAttributeMaximum(attribute, operation, value);
        actor.modifyAttribute(attribute, ActorAttributeOperations.UPDATE, updateValue);

        return target + " " + verb + " " + value + " max " + attribute.toString().toLowerCase();
    }
}
