package game.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.displays.FancyMessage;
import game.interfaces.effects.AreaDamageEffect;

/**
 * A class representing the Bloodrose crop.
 * The Bloodrose damages actors in surrounding locations each turn.
 * It is represented by the 'r' character on the game map.
 *
 * @author Ng Jun Xian
 */
public class Bloodrose extends Crop implements AreaDamageEffect {

    /**
     * Constructor for Bloodrose.
     * Initializes a new Bloodrose with display character 'r' and the name "Bloodrose".
     */
    public Bloodrose() {
        super('w', "Bloodrose");
    }

    /**
     * Applies the periodic effect of this Bloodrose by damaging nearby actors.
     * This method is called every turn to apply area damage to surrounding actors.
     *
     * @param location the location of this Bloodrose crop
     * @param map the game map containing this crop
     */
    @Override
    public void applyPeriodicEffects(Location location, GameMap map) {
        applyAreaDamageEffect(location, map);
    }

    /**
     * Applies area damage effect to all actors in adjacent locations.
     * Each actor in a surrounding location will receive 10 points of damage.
     * If the actor is unconscious, it will be removed fom the map.
     *
     * @param location the location from which to apply the area damage effect
     * @param map the game map containing this location
     */
    @Override
    public void applyAreaDamageEffect(Location location, GameMap map) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                target.hurt(10);
                System.out.println("Surrounding actors have been hurt");
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

