package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Giveable;
import edu.monash.fit2099.engine.items.Item;

/**
 * Action for giving items to NPCs
 * Completely avoids downcasting using polymorphism
 *
 * @author Ng Jun Xian, modified by Chong Jun Hao
 */
public class GiveAction extends Action {
    
    private Giveable targetNPC;
    private Item gift;
    private String direction;
    
    public GiveAction(Giveable targetNPC, Item gift, String direction) {
        this.targetNPC = targetNPC;
        this.gift = gift;
        this.direction = direction;
    }
    
    public GiveAction(Giveable targetNPC, Item gift) {
        this.targetNPC = targetNPC;
        this.gift = gift;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        return targetNPC.gift(actor, gift, map);
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + gift + " to " + targetNPC;
    }
}