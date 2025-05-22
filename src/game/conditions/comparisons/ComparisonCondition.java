package game.conditions.comparisons;

import game.conditions.Condition;

public abstract class ComparisonCondition extends Condition {

    private final ComparisonOperations operation;
    private final int value;

    protected ComparisonCondition(ComparisonOperations operation, int value) {
        this.operation = operation;
        this.value = value;
    }

    protected boolean comparisonCheck(int checkValue) {
        if (operation == ComparisonOperations.EQ &&
                checkValue == value) {
            return true;
        }

        if (operation == ComparisonOperations.LT &&
                checkValue < value) {
            return true;
        }

        if (operation == ComparisonOperations.GT &&
                checkValue > value) {
            return true;
        }

        if (operation == ComparisonOperations.LE &&
                checkValue <= value) {
            return true;
        }

        if (operation == ComparisonOperations.GE &&
                checkValue >= value) {
            return true;
        }

        return false;
    }
}
