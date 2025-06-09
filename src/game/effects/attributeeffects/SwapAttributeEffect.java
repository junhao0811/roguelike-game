package game.effects.attributeeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.Effect;

/**
 * Class representing a swap attribute effect
 *
 * @author Lee Hao Zhe
 */
public class SwapAttributeEffect extends Effect {

    private final BaseActorAttributes firstAttribute;
    private final BaseActorAttributes secondAttribute;

    /**
     * Constructor
     *
     * @param firstAttribute the first attribute to swap
     * @param secondAttribute the second attribute to swap
     * @param target the target of the effect
     */
    public SwapAttributeEffect(BaseActorAttributes firstAttribute,
                               BaseActorAttributes secondAttribute,
                               Actor target) {
        super(target);
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
    }

    /**
     * Constructor with no target as default
     *
     * @param firstAttribute the attribute to swap
     * @param secondAttribute the second attribute to swap
     */
    public SwapAttributeEffect(BaseActorAttributes firstAttribute,
                               BaseActorAttributes secondAttribute) {
        super();
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
    }

    @Override
    public String applyEffect(Actor actor, GameMap map) {
        Actor target = this.getTarget(actor);

        int first = actor.getAttribute(firstAttribute);
        int firstMax = actor.getAttributeMaximum(firstAttribute);

        int second = actor.getAttribute(secondAttribute);
        int secondMax = actor.getAttributeMaximum(secondAttribute);

        actor.modifyAttributeMaximum(firstAttribute, ActorAttributeOperations.UPDATE, secondMax);
        actor.modifyAttribute(firstAttribute, ActorAttributeOperations.UPDATE, second);

        actor.modifyAttributeMaximum(secondAttribute, ActorAttributeOperations.UPDATE, firstMax);
        actor.modifyAttribute(secondAttribute, ActorAttributeOperations.UPDATE, first);

        return target + " has its " + firstAttribute.toString().toLowerCase() +
                " swapped with its " + secondAttribute.toString().toLowerCase();
    }
}
