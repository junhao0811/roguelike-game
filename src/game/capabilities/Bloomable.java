package game.capabilities;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Things that are Bloomable can bloom on the soil.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface Bloomable {
    
    /**
     * Bloom the object in the soil.
     *
     * @param location the location of the object
     */
    void bloom(Location location);
}
