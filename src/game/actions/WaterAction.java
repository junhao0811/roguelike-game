package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.items.watercontainers.WaterContainer;

/**
 * Class representing an action to water
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public class WaterAction extends Action {

    private final Ground target;
    private final WaterContainer waterContainer;
    private final Action bloomAction;

    /**
     * Constructor
     *
     * @param target the target to water
     * @param waterContainer the WaterContainer used to water the target
     * @param bloomAction the Action that will be executed when watering the target to cause it to bloom
     */
    public WaterAction(Ground target, WaterContainer waterContainer, Action bloomAction) {
        this.target = target;
        this.waterContainer = waterContainer;
        this.bloomAction = bloomAction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return waterContainer.water(actor, map, target, bloomAction);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + target + " with " + waterContainer;
    }
}
