package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Sellable;

public class Katana extends WeaponItem implements Sellable {
    public Katana() {
        super("Katana", 'j', 50, "hits", 60);
    }

    public String sell(Actor actor, GameMap map) {
        return "";
    }
}