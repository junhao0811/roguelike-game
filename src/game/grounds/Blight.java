package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;
import game.capabilities.Curable;

/**
 * A class representing a blight covering the ground of the valley.
 *
 * @author Adrian Kristanto, modified by Chong Jun Hao and Lee Hao Zhe
 */
public class Blight extends Ground implements Curable {
    
    /**
     * Constructor
     */
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.CURSED);
    }

    /**
     * Cures the blight using a Talisman, revealing the soil underneath it,
     * thus allowing the Farmer to plant crops on it
     *
     * @param actor the actor performing the action.
     */
    @Override
    public String cure(Actor actor, GameMap map) {
        int staminaCost = 50;

        if (actor.hasAttribute(BaseActorAttributes.STAMINA) &&
                actor.getAttribute(BaseActorAttributes.STAMINA) >= staminaCost) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE, staminaCost);
            map.locationOf(actor).setGround(new Soil());

            return actor + " cures the " + this + ", consuming " + staminaCost +
                    " stamina to reveal the Soil underneath it";
        } else {
            return actor + " does not have enough stamina to cure the " + this;
        }
    }
}
