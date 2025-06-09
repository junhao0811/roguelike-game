package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.crops.Crop;
import game.items.watercontainers.WaterContainer;

public class WaterAction extends Action {

    private final Crop target;
    private final WaterContainer waterContainer;

    /**
     * Constructor
     *
     * @param target the target to water
     * @param waterContainer the WaterContainer used to water the target
     */
    public WaterAction(Crop target, WaterContainer waterContainer) {
        this.target = target;
        this.waterContainer = waterContainer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return waterContainer.water(actor, map, target);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + target + " with " + waterContainer;
    }
}
