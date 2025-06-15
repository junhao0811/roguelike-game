package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class CounterCondition extends Condition {

    private final int value;
    private int counter = 0;

    public CounterCondition(int value) {
        this.value = value;
    }

    @Override
    public boolean check(Actor actor, Location location) {
        counter ++;
        return counter % value == 0;
    }
}
