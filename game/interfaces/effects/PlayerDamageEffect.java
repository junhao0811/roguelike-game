package game.interfaces.effects;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for objects that can apply damage effects to players.
 * Classes that implement this interface can cause damage to player actors
 * at specific locations on the game map.
 *
 * @author Ng Jun Xian
 */
public interface PlayerDamageEffect {

    /**
     * Applies a damage effect to a player at the specified location.
     *
     * @param location the location where the damage effect should be applied
     * @param map the game map containing the location
     */
    void applyPlayerDamageEffect(Location location, GameMap map);
}
