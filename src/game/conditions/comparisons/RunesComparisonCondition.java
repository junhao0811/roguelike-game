package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the runes comparison condition
 *
 * @author Abaan Anwar
 */
public class RunesComparisonCondition extends ComparisonCondition {

    /**
     * Constructor
     *
     * @param operation the comparison operation used for checking
     * @param value the value to check with
     */
    public RunesComparisonCondition(ComparisonOperations operation, int value) {
        super(operation, value);
    }

    @Override
    public boolean check(Actor actor, Location location) {
        return super.comparisonCheck(actor.getBalance());
    }
}
