package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Class representing the affection in between condition
 *
 * @author Chong Jun Hao
 */
public class AffectionInBetweenCondition extends InBetweenCondition {

    /**
     * Constructor
     *
     * @param value the first value to check with
     * @param value2 the second value to check with
     * @param inclusive the boolean to determine whether the in between condition is inclusive
     */
    public AffectionInBetweenCondition(int value, int value2, boolean inclusive) {
        super(value, value2, inclusive);
    }

    @Override
    public boolean check(Actor actor, Location location) {
        return super.inbetweenCheck(actor.getAttribute(Status.AFFECTION));
    }
}
