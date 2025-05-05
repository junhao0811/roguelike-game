package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Bloodrose;
import game.grounds.Inheritree;
import game.grounds.Soil;

/**
 * A class representing the "Bloodrose" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class BloodroseSeed extends Seed {
    /**
     * Constructor.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed");
    }
    
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);
        map.locationOf(actor).setGround(new Bloodrose());
    }
}
