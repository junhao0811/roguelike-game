package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Reproducable;


/**
 * Action for creatures to lay eggs.
 * This action is triggered by a behavior and not meant to be displayed in the menu.
 * 
 * @author Ng Jun Xian
 */
public class LayEggAction extends Action {
    
    private final Reproducable reproducer;
    private final Location location;
    
    /**
     * Constructor for LayEggAction.
     * 
     * @param reproducer the entity laying the egg
     * @param location the location to lay the egg
     */
    public LayEggAction(Reproducable reproducer, Location location) {
        this.reproducer = reproducer;
        this.location = location;
    }
    
    /**
     * Performs the egg-laying action.
     * 
     * @param actor the actor laying the egg
     * @param map the map where the action occurs
     * @return a description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        reproducer.reproduce(actor, location, map);
        return actor + " lays an egg";
    }
    
    /**
     * Returns null since this action should not appear in the menu.
     * This is an automatic action triggered by a behavior.
     * 
     * @param actor the actor performing the action
     * @return null, so this action doesn't appear in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}