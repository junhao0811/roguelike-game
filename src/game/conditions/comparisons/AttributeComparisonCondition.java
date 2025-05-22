package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class AttributeComparisonCondition extends ComparisonCondition {

    private final BaseActorAttributes attribute;

    public AttributeComparisonCondition(ComparisonOperations operation, BaseActorAttributes attribute, int value) {
        super(operation, value);
        this.attribute = attribute;
    }

    @Override
    public boolean check(Actor actor, GameMap map) {
        return super.comparisonCheck(actor.getAttribute(attribute));
    }
}
