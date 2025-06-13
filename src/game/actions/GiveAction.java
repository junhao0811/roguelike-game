package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Receivable;
import edu.monash.fit2099.engine.items.Item;
import game.enums.Ability;

/**
 * Action for giving items to NPCs
 * Completely avoids downcasting using polymorphism
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public class GiveAction extends Action {
    
    private final Receivable targetNPC;
    private final Item gift;
    private String direction = "";
    
    public GiveAction(Receivable targetNPC, Item gift, String direction) {
        this.targetNPC = targetNPC;
        this.gift = gift;
        this.direction = " at " + direction;
    }
    
    public GiveAction(Receivable targetNPC, Item gift) {
        this.targetNPC = targetNPC;
        this.gift = gift;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.GIVE)) {
            actor.removeItemFromInventory(gift);
        }
        return targetNPC.receive(actor, gift, map);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + gift + " to " + targetNPC + direction;
    }
}