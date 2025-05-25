package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Curable;

/**
 * Class representing an action to cure
 *
 * @author Lee Hao Zhe
 */
public class CureAction extends Action {

    /**
     * The Curable that is to be cured
     */
    private final Curable target;

    /**
     * The direction of incoming cure
     */
    private final String direction;

    /**
     * Item used for the cure
     */
    private final String item;

    /**
     * Constructor
     *
     * @param target the Curable to cure
     * @param direction the direction where the cure should be performed (only used for display purposes)
     * @param item      the item used for the cure (only used for display purposes)
     */
    public CureAction(Curable target, String direction, Item item) {
        this.target = target;
        this.direction = " at " + direction;
        this.item = " with " + item;
    }

    /**
     * Constructor with no direction as default
     *
     * @param target the Curable to cure
     * @param item    the item used for the cure (only used for display purposes)
     */
    public CureAction(Curable target, Item item) {
        this.target = target;
        this.direction = "";
        this.item = " with " + item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return target.cure(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " cures " + target + direction + item;
    }
}
