package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for entities that can hatch into creatures.
 * This interface is implemented by eggs that can hatch after certain conditions are met.
 * 
 * @author Ng Jun Xian
 */
public interface Hatchable {
    
    /**
     * Performs the hatching action when conditions are met.
     * Implementation should check if hatching conditions are met and then perform hatching.
     * 
     * @param location the location where hatching occurs
     * @return true if hatching was successful, false otherwise
     */
    boolean hatch(Location location);
}