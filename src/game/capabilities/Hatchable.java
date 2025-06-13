package game.capabilities;

import game.actors.creatures.Creature;

/**
 * Interface representing a Hatchable
 *
 * @author Ng Jun Xian
 */
public interface Hatchable {

    /**
     * Hatches the Egg into a creature
     *
     * @return the Npc that hatches from the Egg
     */
    Creature hatch();
}
