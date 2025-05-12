package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Reproducible;

/**
 * A class that represents an action to reproduce.
 */
public class ReproduceAction extends Action {
    private Reproducible reproducible;
    private Location reproduceAt;

    /**
     * Constructor to create an Action that will reproduce an offspring at a given direction
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ReproduceAction(Reproducible reproducible, Location reproduceAt) {
        this.reproducible = reproducible;
        this.reproduceAt = reproduceAt;
    }

    /**
     * Allow the Actor to be reproduced.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of the ReproduceAction suitable for the menu.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        reproducible.reproduce(actor, reproduceAt);
        return menuDescription(actor);
    }

    /**
     * Returns a description of this reproduction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "SpiritGoat gives birth to an offspring at North"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " reproduces successfully >o<";
    }
}
