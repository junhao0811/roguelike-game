package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.capabilities.Reproducible;

/**
 * An action that handles reproduction of actors
 */
public class ReproduceAction extends Action {

    /**
     * The Reproducible that is to reproduce
     */
    private final Reproducible reproducible;

    /**
     * Constructor
     *
     * @param reproducible The reproducible entity
     */
    public ReproduceAction(Reproducible reproducible) {
        this.reproducible = reproducible;
    }

    /**
     * Execute the reproduction
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        reproducible.reproduce(actor, map.locationOf(actor));
        return menuDescription(actor);
    }

    /**
     * Returns a description of this reproduction suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String describing the reproduction
     */
    @Override
    public String menuDescription(Actor actor) {
        if (actor.hasCapability(Ability.PRODUCE_OFFSPRING)) {
            return actor + " successfully reproduces";
        } else if (actor.hasCapability(Ability.PRODUCE_EGG)) {
            return actor + " lays an egg";
        }
        return actor + " is unable to reproduce";
    }
}