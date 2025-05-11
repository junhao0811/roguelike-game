package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.CureAction;
import game.capabilities.Curable;

/**
 * A class representing a blight covering the ground of the valley.
 *
 * @author Adrian Kristanto, modified by Chong Jun Hao
 */
public class Blight extends Ground implements Curable {
    
    /**
     * Constructor.
     */
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.CURSED);
    }
    
    /**
     * Cures the blight using a Talisman, revealing the soil underneath it, thus allowing the Farmer
     * to plant crops on it
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     */
    public void cure(Actor actor, GameMap map) {
        if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,
                50);
        }
        map.locationOf(actor).setGround(new Soil());
    }

    public ActionList allowableActions(Actor owner, Location location, String direction) {
        ActionList actions = super.allowableActions(owner, location, direction);

        if (owner.hasCapability(Ability.CURING)) {
            actions.add(new CureAction(this));
        }

        return actions;
    }
}
