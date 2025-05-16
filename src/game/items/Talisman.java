package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.Status;
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
        this.addCapability(Ability.CURE);
    }

    /**
     * Method to get a new CureAction instance associated with the Talisman
     */
    public CureAction getCureAction(Curable curable) {
        return new CureAction(curable, this);
    }

    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();

        if (map.locationOf(actor).getGround().hasCapability(Status.CURSED))
            actions.add(this.getCureAction(new Blight()));

        return actions;
    }
}
