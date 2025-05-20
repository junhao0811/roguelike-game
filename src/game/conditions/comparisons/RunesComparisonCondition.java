package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conditions.Condition;

public class RunesComparisonCondition extends Condition {

    private final ComparisonOperations operation;
    private final int value;

    public RunesComparisonCondition(ComparisonOperations operation, int value) {
        this.operation = operation;
        this.value = value;
    }

    @Override
    public boolean check(Actor actor, GameMap map) {
        if (operation == ComparisonOperations.EQ &&
                actor.getBalance() == value) {
            return true;
        }

        if (operation == ComparisonOperations.LT &&
                actor.getBalance() < value) {
            return true;
        }

        if (operation == ComparisonOperations.GT &&
                actor.getBalance() > value) {
            return true;
        }

        if (operation == ComparisonOperations.LE &&
                actor.getBalance() <= value) {
            return true;
        }

        if (operation == ComparisonOperations.GE &&
                actor.getBalance() >= value) {
            return true;
        }

        return false;
    }
}
