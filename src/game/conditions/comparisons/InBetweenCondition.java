package game.conditions.comparisons;

import game.conditions.Condition;

/**
 * Class representing an in between condition
 *
 * @author Chong Jun Hao
 */
public abstract class InBetweenCondition extends Condition {

    private final int value;
    private final int value2;
    private final boolean inclusive;

    /**
     * Constructor
     *
     * @param value the first value to check with
     * @param value2 the second value to check with
     * @param inclusive the boolean to determine whether the in between condition is inclusive
     */
    protected InBetweenCondition(int value, int value2, boolean inclusive) {
        this.value = value;
        this.value2 = value2;
        this.inclusive = inclusive;
    }

    /**
     * Helper method to check whether the other value passes the check in between
     * the first and second value to check with.
     * Inclusive and non-inclusive checks are different
     *
     * @param checkValue the other value used to check with the value attribute
     * @return the result of checking the condition (either true or false)
     */
    protected boolean inbetweenCheck(int checkValue) {
        if (inclusive) {
            if (value > value2) {
                return value >= checkValue & checkValue >= value2;
            }

            else {
                return value <= checkValue & checkValue <= value2;
            }
        }

        else {
            if (value > value2) {
                return value > checkValue & checkValue > value2;
            }

            else {
                return value < checkValue & checkValue < value2;
            }
        }
    }
}
