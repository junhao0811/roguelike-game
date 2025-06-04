package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Seed;

/**
 * Class representing an action to plant
 *
 * @author Lee Hao Zhe
 */
public class PlantAction extends Action {

    /**
     * The Seed that is to be planted
     */
    private final Seed seed;

    /**
     * Constructor
     *
     * @param seed the seed that is to be planted
     */
    public PlantAction(Seed seed) {
        this.seed = seed;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return seed.plant(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants the " + seed;
    }
}

