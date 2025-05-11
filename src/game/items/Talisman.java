package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Ability;
import game.Status;
import game.actions.CureAction;
import game.capabilities.Curable;
import game.grounds.Blight;

/**
 * A class representing a Talisman that an actor can pick up and drop
 * @author Adrian Kristanto, modified by Chong Jun Hao
 */
public class Talisman extends Item {
    public Talisman() {
        super("Talisman", 'o', true);
        this.addCapability(Ability.CURING);
    }
}
