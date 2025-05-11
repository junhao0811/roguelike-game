package game.capabilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The SproutEffect interface represents the behavior of a sprouting effect that can occur when a seed is planted
 * or an action triggers the sprout effect in the game. Classes implementing this interface define the specific
 * effect that occurs to the planting actor and their surrounding environment on the game map.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public interface SproutEffect {
    
    /**
     * This method is called when the sprout effect is triggered.
     *
     * @param actor The actor performing the action
     * @param map   The map the actor is on
     */
    void sprout(Actor actor, GameMap map) ;
}
