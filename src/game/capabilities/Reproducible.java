package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Reproducible {
    void reproduce(Actor actor, Location location);

    boolean canReproduce(Actor actor, GameMap map);
}
