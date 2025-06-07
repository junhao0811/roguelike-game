package game.actors.creatures;

import game.enums.Ability;
import game.enums.BehaviourSelection;

/**
 * Class representing the Bed of Chaos, a creature in the game.
 */
public class BedOfChaos extends Creature {

    /**
     * Constructor for BedOfChaos.
     */
    public BedOfChaos(BehaviourSelection selectionStrategy) {
        super("Bed of Chaos", 'T', 1000, selectionStrategy);
        this.addCapability(Ability.GROW);
    }
}