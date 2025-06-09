package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.crops.Crop;
import game.items.watercontainers.WaterContainer;

/**
 * Class representing an action to water
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class WaterAction extends Action {

    private final Crop crop;
    private final WaterContainer waterContainer;

    /**
     * Constructor
     *
     * @param crop the crop to water
     * @param waterContainer the WaterContainer used to water the target
     */
    public WaterAction(Crop crop, WaterContainer waterContainer) {
        this.crop = crop;
        this.waterContainer = waterContainer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return waterContainer.water(actor, map, crop);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + crop + " with " + waterContainer;
    }
}
