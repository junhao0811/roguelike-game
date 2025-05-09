package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.ability.Ability;

/**
 * A class representing a Talisman that an actor can pick up and drop.
 * The Talisman provides the CURING ability to its holder,
 * allowing them to cure curable entities in the game.
 *
 * @author Adrian Kristanto
 * modified by Ng Jun Xian
 */
public class Talisman extends Item {

    /**
     * Constructor for Talisman.
     * Creates a Talisman with display character 'o' that can be picked up and carried.
     * The Talisman has the CURING ability, which is granted to any actor carrying it.
     */
    public Talisman() {
        super("Talisman", 'o', true);
        this.addCapability(Ability.CURING);
    }
}
