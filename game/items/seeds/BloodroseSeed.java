package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.crops.Bloodrose;
import game.crops.Crop;
import game.displays.FancyMessage;
import game.interfaces.effects.PlayerDamageEffect;

/**
 * A class representing a Bloodrose seed that can be planted to grow a Bloodrose crop.
 * This seed requires stamina to plant and damages the player when planted.
 * When successfully planted, it creates a Bloodrose crop that damages surrounding actors.
 *
 * @author Ng Jun Xian
 */
public class BloodroseSeed extends Seed implements PlayerDamageEffect {

    /**
     * Constructor for BloodroseSeed.
     * Initializes the seed with the name "Bloodrose Seed".
     */
    public BloodroseSeed() {
        super("Bloodrose Seed");
    }

    /**
     * Returns the stamina cost required to plant this seed.
     *
     * @return the stamina cost (75 points)
     */
    @Override
    public int getStaminaCost() {
        return 75; // Bloodrose costs 75 stamina to plant
    }

    /**
     * Creates and returns a new Bloodrose crop.
     *
     * @return a new instance of the Bloodrose crop
     */
    @Override
    public Crop createCrop() {
        return new Bloodrose();
    }

    /**
     * Returns the string representation of this seed.
     *
     * @return the name of this seed ("Bloodrose Seed")
     */
    @Override
    public String toString() {
        return "Bloodrose Seed";
    }

    /**
     * Plants this seed at the specified location if possible.
     * If planting is successful, applies damage effect to the player.
     *
     * @param actor the actor planting the seed
     * @param location the location to plant the seed
     * @return true if planting was successful, false otherwise
     */
    @Override
    public boolean plant(Actor actor, Location location) {
        if (super.plant(actor, location)) {
            applyPlayerDamageEffect(location, location.map());
            return true;
        }
        return false;
    }

    /**
     * Applies damage effect to the player at the specified location.
     * Deals 5 damage to the actor if they have the stamina attribute.
     *
     * @param location the location where the damage effect is applied
     * @param map the game map containing the location
     */
    @Override
    public void applyPlayerDamageEffect(Location location, GameMap map) {
        if (location.containsAnActor()) {
            Actor target = location.getActor();
            if (target != null && target.hasAttribute(BaseActorAttributes.STAMINA)) {
                target.hurt(5);
                System.out.println("The planting of Bloodrose Seed has hurt the farmer by 5 HP.");
                if (!target.isConscious()){
                    System.out.println(target.unconscious(map));
                    for (String line : FancyMessage.YOU_DIED.split("\n")) {
                        new Display().println(line);
                        try {
                            Thread.sleep(200);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}