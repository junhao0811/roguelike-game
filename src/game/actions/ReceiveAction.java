package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Receivable;
import game.enums.Ability;

/**
 * Action for receiving items from NPCs
 *
 * @author Chong Jun Hao
 */
public class ReceiveAction extends Action {
    
    private Receivable npc;
    private Item item;
    
    /**
     * Constructor
     *
     * @param npc the NPC from whom the item is received
     * @param item the item being received
     */
    public ReceiveAction(Receivable npc, Item item) {
        this.npc = npc;
        this.item = item;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.RECEIVE)) {
            npc.receive(actor, item, map);
            return menuDescription(actor);
        }
        return actor + " cannot receive " + item + " from " + npc + ".";
    }
    
    @Override
    public String menuDescription(Actor actor) {
        return npc + " receives " + item + " from " + actor + ".";
    }
}