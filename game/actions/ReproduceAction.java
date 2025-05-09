package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Reproducable;

/**
 * An action that handles the reproduction of actors that implement the Reproducable interface.
 * This action is used specifically for Spirit Goats to produce offspring near Inheritrees.
 * This action is triggered by a behavior and not meant to be displayed in the menu.
 *
 * @author Ng Jun Xian
 */
public class ReproduceAction extends Action {

    private final Reproducable reproducer;
    private final Location targetLocation;

    /**
     * Constructor for ReproduceAction.
     *
     * @param reproducer the entity capable of reproduction
     * @param targetLocation the location where offspring will be placed
     */
    public ReproduceAction(Reproducable reproducer, Location targetLocation) {
        this.reproducer = reproducer;
        this.targetLocation = targetLocation;
    }

    /**
     * Executes the reproduction action by calling the reproduce method of the Reproducable entity.
     * The actor parameter is the reproducer itself, which is passed to the reproduce method.
     *
     * @param actor the actor performing the action (the parent)
     * @param map the game map where the action is performed
     * @return a description of the action result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Pass the actor (parent), target location, and map to reproduce
        reproducer.reproduce(actor, targetLocation, map);
        return actor + " produces offspring!";
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