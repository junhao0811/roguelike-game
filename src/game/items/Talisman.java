package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Ability;
import game.actions.CureAction;
import game.capabilities.Curable;
import game.grounds.Blight;

/**
 * A class representing a Talisman that an actor can pick up and drop
 * @author Adrian Kristanto, modified by Chong Jun Hao
 */
public class Talisman extends Item {
    public Talisman() {
        super("Talisman", 'o', true);
        this.addCapability(Ability.TO_CURE);
    }
    
    /**
     * Create and return an action to use this Talisman.
     *
     * @return a new CureAction
     */
    public CureAction getCureAction(Curable curable) {
        return new CureAction(curable);
    }
    
    /**
     * List of allowable actions that can be performed on the Talisman
     *
     * @param actor the Actor acting
     * @param map   the GameMap the Actor is on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        Ground ground = map.locationOf(actor).getGround();
        if (ground.hasCapability(Ability.RECEIVE_CURE)) {
            actions.add(getCureAction(new Blight()));
        }
        return actions;
    }
}
