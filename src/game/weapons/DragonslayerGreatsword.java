package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Sellable;

public class DragonslayerGreatsword extends WeaponItem implements Sellable {
    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D',
                70, "strikes", 75);
    }

    public String sell(Actor actor, GameMap map) {
        return "";
    }
}