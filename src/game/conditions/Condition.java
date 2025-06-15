package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing a condition
 *
 * @author Abaan Anwar and Lee Hao Zhe
 */
public abstract class Condition {

    /**
     * Method to check the condition
     *
     * @param actor    The actor the condition is tied to.
     * @param location The location the condition is tied to.
     * @return the result of checking the condition (either true or false)
     */
    public abstract boolean check(Actor actor, Location location);
}
