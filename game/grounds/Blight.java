package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import game.interfaces.Curable;
import game.ability.Ability;
import game.actions.CureAction;
import game.status.Status;


/**
 * A class representing a blight covering the ground of the valley.
 * Blight is a cursed terrain that can be cured by actors with the CURING ability
 * if they have sufficient stamina.
 * It is represented by the 'x' character on the game map.
 *
 * @author Adrian Kristanto
 * modified by Ng Jun Xian
 */
public class Blight extends Ground implements Curable {

    /**
     * Constructor for Blight.
     * Initializes a new Blight with display character 'x', the name "Blight",
     * and marks it as having the CURSED capability.
     */
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.CURSED); //req 2
    }

    /**
     * Cures the blight at the specified location if the actor has enough stamina.
     * Requires 50 stamina points to cure, which are consumed in the process.
     * If curing is successful, the blight is replaced with soil.
     *
     * @param actor the actor performing the cure action
     * @param location the location of the blight to be cured
     * @return true if curing was successful, false otherwise
     */
    public boolean cure(Actor actor, Location location) {
        if (actor.hasAttribute(BaseActorAttributes.STAMINA) &&
                actor.getAttribute(BaseActorAttributes.STAMINA) >= 50) {

            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE,
                    50);
            location.setGround(new Soil());
            System.out.println(actor + " cured the blight!");
            return true;
        }
        System.out.println(actor + " does not have enough stamina to cure the blight!");
        return false;
    }

    /**
     * Returns a collection of actions that can be performed on this blight by another actor.
     * Actors with CURING ability can cure the blight.
     *
     * @param owner the Actor that might perform an action on this blight
     * @param location the location of the blight
     * @param direction the direction of the blight relative to the actor
     * @return a collection of actions that can be performed by owner
     */
    public ActionList allowableActions(Actor owner, Location location, String direction) {
        ActionList actions = super.allowableActions(owner, location, direction);

        if (owner.hasCapability(Ability.CURING)) {
            actions.add(new CureAction(this, location, direction));
        }

        return actions;
    }
}
