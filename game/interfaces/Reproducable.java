package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Reproducable {
    void reproduce(Actor actor, Location location, GameMap map);
}
