package game.interfaces.effects;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that can apply damage effects to an area.
 * Classes that implement this interface can cause damage to actors
 * in an area around a specific location on the game map.
 *
 * @author Ng Jun Xian
 */
public interface AreaDamageEffect{

    /**
     * Applies an area damage effect centered at the specified location.
     * This typically involves damaging actors in surrounding locations.
     *
     * @param location the central location from which the area damage effect radiates
     * @param map the game map containing the location
     */
    void applyAreaDamageEffect(Location location, GameMap map);
}