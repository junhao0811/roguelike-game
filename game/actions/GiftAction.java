package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Giftable;
import game.enums.Ability;
import game.actors.npcs.NPC;

/**
 * Action for giving items to NPCs
 * Completely avoids downcasting using polymorphism
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public class GiftAction extends Action {
    
    private final NPC target;
    private Giftable giftable;
    private final String direction;
    
    public GiftAction(NPC target, Giftable giftable, String direction) {
        this.target = target;
        this.giftable = giftable;
        this.direction = direction;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        return giftable.gift(target, actor, map);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + giftable + " to " + target + " (" + direction + ")";
    }
}