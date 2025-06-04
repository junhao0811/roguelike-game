package game.conditions.comparisons;

import game.conditions.Condition;

/**
 * Class representing a comparison condition
 *
 * @author Abaan Anwar
 */
public abstract class ComparisonCondition extends Condition {

    private final ComparisonOperations operation;
    private final int value;

    /**
     * Constructor
     *
     * @param operation the comparison operation used for checking
     * @param value the value to check with
     */
    protected ComparisonCondition(ComparisonOperations operation, int value) {
        this.operation = operation;
        this.value = value;
    }

    /**
     * Helper method to check whether the other value passes the check when compared
     * using the comparison operation to the value
     *
     * @param checkValue the other value used to check with the value attribute
     * @return the result of checking the condition (either true or false)
     */
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
