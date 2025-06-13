package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.crops.Crop;

/**
 * Class representing an action to bloom
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class BloomAction extends Action {

    private final Crop target;

    /**
     * Constructor
     *
     * @param target the target to bloom
     */
    public BloomAction(Crop target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return target.bloom();
    }

    @Override
    public String menuDescription(Actor actor) {
        return target + " blooms";
    }
}
