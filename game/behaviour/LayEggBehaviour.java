package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.LayEggAction;
import game.interfaces.Reproducable;

/**
 * A behaviour that allows an actor to lay eggs periodically.
 * Returns a LayEggAction when it's time for the actor to lay an egg.
 * 
 * @author Ng Jun Xian
 */
public class LayEggBehaviour implements Behaviour {
    
    private int turnCounter = 0;
    private final int eggLayInterval;
    private final Reproducable reproducable;
    
    /**
     * Constructor for LayEggBehaviour.
     * 
     * @param reproducable the entity capable of reproduction
     * @param interval the number of turns between laying eggs
     */
    public LayEggBehaviour(Reproducable reproducable, int interval) {
        this.reproducable = reproducable;
        this.eggLayInterval = interval;
    }
    
    /**
     * Checks if it's time for the actor to lay an egg, and if so,
     * returns a LayEggAction.
     * 
     * @param actor the actor laying eggs
     * @param map the map the actor is on
     * @return LayEggAction if it's time to lay an egg, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor != reproducable) {
            return null;
        }
        
        turnCounter++;
        
        if (turnCounter >= eggLayInterval) {
            turnCounter = 0;
            Location actorLocation = map.locationOf(actor);
            return new LayEggAction(reproducable, actorLocation);
        }
        
        return null;
    }
}