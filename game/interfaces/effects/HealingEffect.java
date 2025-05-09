package game.interfaces.effects;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that can apply healing effects.
 * Classes that implement this interface can heal actors
 * at specific locations on the game map.
 *
 * @author Ng Jun Xian
 */
public interface HealingEffect{

    /**
     * Applies a healing effect at the specified location.
     * This typically involves healing actors at or near the location.
     *
     * @param location the location where the healing effect should be applied
     * @param map the game map containing the location
     */
    void applyHealingEffect(Location location, GameMap map);
}
