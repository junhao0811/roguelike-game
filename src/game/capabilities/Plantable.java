package game.capabilities;

/**
 * Things that are Plantable can be planted in the soil.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface Plantable {
    
    /**
     * Plant the object in the soil.
     */
    void plant();
    
    /**
     * Check if the object can be planted based on the ground.
     *
     * @return true if the object can be planted, false otherwise
     */
    boolean canPlant();
}
