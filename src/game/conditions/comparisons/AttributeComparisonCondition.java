package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;

public class AttributeComparisonCondition extends Condition {

    private final ComparisonOperations operation;
    private final BaseActorAttributes attribute;
    private final int value;

    public AttributeComparisonCondition(ComparisonOperations operation, BaseActorAttributes attribute, int value) {
        this.operation = operation;
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public boolean check(Actor actor, GameMap map) {
        if (operation == ComparisonOperations.EQ &&
                actor.getAttribute(attribute) == value) {
            return true;
        }

        if (operation == ComparisonOperations.LT &&
                actor.getAttribute(attribute) < value) {
            return true;
        }

        if (operation == ComparisonOperations.GT &&
                actor.getAttribute(attribute) > value) {
            return true;
        }

        if (operation == ComparisonOperations.LE &&
                actor.getAttribute(attribute) <= value) {
            return true;
        }

        if (operation == ComparisonOperations.GE &&
                actor.getAttribute(attribute) >= value) {
            return true;
        }

        return false;
    }
}
