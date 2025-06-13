package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Reproducible;

/**
 * Class representing an action to reproduce
 *
 * @author Ng Jun Xian
 */
public class ReproduceAction extends Action {

    private final Reproducible reproducible;

    /**
     * Constructor
     *
     * @param reproducible The reproducible entity
     */
    public ReproduceAction(Reproducible reproducible) {
        this.reproducible = reproducible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return reproducible.reproduce(actor, map.locationOf(actor));
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " reproduces";
    }
}