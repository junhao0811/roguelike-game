package game.grounds.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import game.actors.Player;
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
    
    @Override
    public void bloom(Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.containsAnActor()) {
                Actor actor = nearby.getActor();
                actor.heal(5);
                actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 5);
            }
        }
    }
    
    @Override
    public void tick(Location location) {
        this.bloom(location);
    }
}