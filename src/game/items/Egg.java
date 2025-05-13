package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Ability;

/**
 * A class representing an Egg that an actor can pick up and drop.
 * The egg has the ability to hatch into a OmenSheep.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Egg extends Item {

    /**
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
        this.addCapability(Ability.HATCH);
    }
}
