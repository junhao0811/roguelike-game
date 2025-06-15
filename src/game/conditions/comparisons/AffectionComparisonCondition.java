package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Class representing the affection comparison condition
 *
 * @author Chong Jun Hao
 */
public class AffectionComparisonCondition extends ComparisonCondition {

    /**
     * Constructor
     *
     * @param operation the comparison operation used for checking
     * @param value the value to check with
     */
    public AffectionComparisonCondition(ComparisonOperations operation, int value) {
        super(operation, value);
    }

    @Override
    public boolean check(Actor actor, Location location) {
        return super.comparisonCheck(actor.getAttribute(Status.AFFECTION));
    }
}
