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
    public boolean cure(Actor actor, GameMap map) {
        if (actor.hasAttribute(BaseActorAttributes.STAMINA) &&
                actor.getAttribute(BaseActorAttributes.STAMINA) >= 50) {

            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE,
                    50);
            map.locationOf(actor).setGround(new Soil());
            System.out.println(actor + " cured the blight!");
            return true;
        }
        System.out.println(actor + " does not have enough stamina to cure the blight!");
        return false;
    }

    /**
     * List of allowable actions that can be performed on the blight
     *
     * @param owner the Actor acting
     * @param location the Location the Actor is in
     * @param direction the direction of the Ground from the Actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, Location location, String direction) {
        ActionList actions = super.allowableActions(owner, location, direction);
        if (owner.hasCapability(Ability.CURE)) {
            actions.add(new CureAction(this, direction));
        }
        return actions;
    }
}
