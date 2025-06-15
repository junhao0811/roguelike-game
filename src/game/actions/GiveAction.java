package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.affectionitems.AffectionItem;

/**
 * Action for giving items to NPCs
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public class GiveAction extends Action {
    
    private final Actor target;
    private final AffectionItem gift;
    private String direction = "";
    
    /**
     * Constructor for GiveAction
     *
     * @param target the actor receiving the gift
     * @param gift the item being given
     * @param direction the direction to the target, if applicable
     */
    public GiveAction(Actor target, AffectionItem gift, String direction) {
        this.target = target;
        this.gift = gift;
        this.direction = " at " + direction;
    }
    
    /**
     * Constructor for GiveAction without direction
     *
     * @param target the actor receiving the gift
     * @param gift the item being given
     */
    public GiveAction(Actor target, AffectionItem gift) {
        this.target = target;
        this.gift = gift;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        return gift.give(actor, target, map);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + gift + " to " + target + direction;
    }
}