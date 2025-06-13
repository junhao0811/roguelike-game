package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ReceiveAction;
import game.capabilities.Receivable;

/**
 * Behaviour for receiving items or affection from other actors.
 *
 * @author Chong Jun Hao
 */
public class ReceiveBehaviour implements Behaviour {
    private Receivable npc;
    
    /**
     * Constructor for ReceiveBehaviour.
     *
     * @param npc the NPC that can receive items or affection
     */
    public ReceiveBehaviour(Receivable npc) {
        this.npc = npc;
    }
    
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new ReceiveAction(npc);
    }
}
