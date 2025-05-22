package game.conditions.comparisons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class RunesComparisonCondition extends ComparisonCondition {

    public RunesComparisonCondition(ComparisonOperations operation, int value) {
        super(operation, value);
    }

    @Override
    public boolean check(Actor actor, GameMap map) {
        return super.comparisonCheck(actor.getBalance());
    }
}
