package game.interfaces.effects;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that can apply curing effects to ground tiles.
 * Classes that implement this interface can transform or heal cursed
 * or damaged ground at specific locations on the game map.
 *
 * @author Ng Jun Xian
 */
public interface GroundCureEffect{

    /**
     * Applies a ground curing effect at the specified location.
     * This typically involves transforming cursed or damaged ground
     * into normal terrain like soil.
     *
     * @param location the location where the ground cure effect should be applied
     * @param map the game map containing the location
     */
    void applyGroundCureEffect(Location location, GameMap map);
}