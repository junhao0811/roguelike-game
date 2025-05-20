package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class Condition {

    public abstract boolean check(Actor actor, GameMap map);
}
