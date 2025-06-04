package game.capabilities;

import edu.monash.fit2099.engine.positions.Location;

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

    /**
     * Hatching condition for the Egg. The Egg can hatch into other creatures with different
     * hatching conditions
     *
     * @param counter the number of turns the Egg has been in the world
     * @param location the location of the Egg
     * @return true if the hatching condition of the Egg is met, false otherwise
     */
    boolean isReadyToHatch(int counter, Location location);
}
