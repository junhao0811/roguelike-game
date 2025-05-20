package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Sellable;

public class Broadsword extends WeaponItem implements Sellable {
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }

    public String sell(Actor actor, GameMap map) {
        return "";
    }
}