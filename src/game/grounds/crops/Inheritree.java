package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import game.capabilities.BloomEffect;

/**
 * A class representing the "Inheritree" crop in the valley
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class Inheritree extends Crop implements BloomEffect {
    
    /**
     * Constructor
     */
    public Inheritree() {
        super('t', "Inheritree");
    }
    
    /**
     * This method is called when the crop blooms.
     * It will heal all actors in the nearby locations.
     * It will also increase the stamina of the actors by 5.
     *
     * @param location The location of the crop
     */
    @Override
    public void bloom(Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.containsAnActor()) {
                Actor actor = nearby.getActor();
                actor.heal(5);
                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 5);
                }
            }
        }
    }
}