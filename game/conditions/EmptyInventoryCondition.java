package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the empty inventory condition
 *
 * @author Abaan Anwar
 */
public class EmptyInventoryCondition extends Condition {

    @Override
    public boolean check(Actor actor, GameMap map) {
        return actor.getItemInventory().isEmpty();
    }
}
