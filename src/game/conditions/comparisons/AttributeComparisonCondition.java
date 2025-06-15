package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing an attribute comparison condition
 *
 * @author Abaan Anwar
 */
public class AttributeComparisonCondition extends ComparisonCondition {

    private final BaseActorAttributes attribute;

    /**
     * Constructor
     *
     * @param operation the comparison operation used for checking
     * @param attribute the attribute to check for
     * @param value the value to check with
     */
    public AttributeComparisonCondition(ComparisonOperations operation, BaseActorAttributes attribute, int value) {
        super(operation, value);
        this.attribute = attribute;
    }

    @Override
    public boolean check(Actor actor, Location location) {
        return super.comparisonCheck(actor.getAttribute(attribute));
    }
}
