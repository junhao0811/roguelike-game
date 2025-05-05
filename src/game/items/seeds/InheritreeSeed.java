package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Bloodrose;
import game.grounds.Inheritree;
import game.grounds.Soil;

/**
 * A class representing the "Inheritree" seed in the valley.
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class InheritreeSeed extends Seed {
    /**
     * Constructor.
     */
    public InheritreeSeed() {
        super("Inheritree Seed");
    }
    
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);
        map.locationOf(actor).setGround(new Inheritree());
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location tile = exit.getDestination();
            if (tile.getGround().getDisplayChar() == 'x') {
                tile.setGround(new Soil());
            }
        }
    }
}
