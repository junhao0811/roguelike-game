package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.capabilities.Growable;

/**
 * Action for the Bed of Chaos to grow new parts
 * Follows Command pattern for encapsulating growth logic
 *
 * @author Ng Jun Xian
 */
public class GrowthAction extends Action {

    private Growable boss;

    /**
     * Constructor
     *
     * @param boss The reproducible entity
     */
    public GrowthAction(Growable boss) {
        this.boss = boss;
    }

    /**
     * Executes the growth action
     * Checks if the actor has the ability to grow
     * If so, it calls the grow method on the boss
     *
     * @param actor The actor performing the action
     * @param map The current game map
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.GROW)) {
            return boss.grow(map);
        }
        return actor + " cannot grow any further";
    }

    /**
     * Returns a description of the action for the menu
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is growing...";
    }
}