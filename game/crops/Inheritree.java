package game.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.effects.HealingEffect;

/**
 * A class representing the Inheritree crop.
 * The Inheritree heals actors in surrounding locations each turn.
 * It also restores stamina to actors who have that attribute.
 * It is represented by the 't' character on the game map.
 *
 * @author Ng Jun Xian
 */
public class Inheritree extends Crop implements HealingEffect {

    /**
     * Constructor for Inheritree.
     * Initializes a new Inheritree with display character 't' and the name "Inheritree".
     */
    public Inheritree() {
        super('t', "Inheritree");
    }

    /**
     * Applies the periodic effect of this Inheritree by healing nearby actors.
     * This method is called every turn to apply healing effects to surrounding actors.
     *
     * @param location the location of this Inheritree crop
     * @param map the game map containing this crop
     */
    @Override
    public void applyPeriodicEffects(Location location, GameMap map) {
        applyHealingEffect(location, map);
    }

    /**
     * Applies healing effects to all actors in adjacent locations.
     * Each actor in a surrounding location will be healed for 5 hit points.
     * Additionally, actors with stamina will have their stamina increased by 5 points.
     *
     * @param location the location from which to apply the healing effect
     * @param map the game map containing this location
     */
    @Override
    public void applyHealingEffect(Location location, GameMap map) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                target.heal(5);
                System.out.println("Surrounding actors have been healed");

                if (target.hasAttribute(BaseActorAttributes.STAMINA)) {
                    target.modifyAttribute(BaseActorAttributes.STAMINA,
                            ActorAttributeOperations.INCREASE,
                            5);
                    System.out.println("Surrounding actors have restored their stamina");
                }
            }
        }
    }
}
