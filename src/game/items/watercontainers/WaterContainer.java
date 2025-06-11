package game.items.watercontainers;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.actions.WaterAction;
import game.effects.Effect;
import game.effects.EffectList;
import game.enums.Ability;
import game.grounds.crops.Crop;

import java.util.List;

/**
 * Class representing a water container that an actor can pick up, drop, and
 * use to water crops
 *
 * @author Lee Hao Zhe and Abaan Anwar
 */
public abstract class WaterContainer extends Item {

    private final EffectList effects = new EffectList();

    /**
     * Constructor
     *
     * @param name        name of the water container
     * @param displayChar character to use for display when water container is on the ground
     */
    protected WaterContainer(String name, char displayChar) {
        super(name, displayChar, true);
    }

    /**
     * Waters the target which allows it to bloom if it has not done so before
     * applying the effects tied to the water container
     *
     * @param actor the actor who performed the watering action
     * @param map   the map on which the watering action was executed
     * @param target  the target of the watering action
     * @return a description of what happened when watering the target
     */
    public String water(Actor actor, GameMap map, Ground target) {
        Crop crop = (Crop) target;
        StringBuilder result = new StringBuilder(actor + " successfully waters the " +
                crop + " with " + this);


        if (!crop.getBloomState()) {
            result.append("\n").append(crop.bloom());
        }

        for (Effect effect : effects) {
            result.append("\n").append(effect.applyEffect(actor, map));

            if (!actor.isConscious()) {
                actor.unconscious(map);
                return result + "\n" + actor + " met their demise at the hands of the " + this;
            }
        }

        return result.toString();
    }

    /**
     * Appends the contents of another Effects list to the EffectList
     * tied to the water container
     *
     * @param effects the Effects to append
     */
    public void addEffect(EffectList effects) {
        this.effects.add(effects);
    }

    /**
     * Appends the contents of any List&lt;Effect&gt; to the EffectList
     * tied to the water container
     *
     * @param effects the List&lt;Effect&gt; to append
     */
    public void addEffect(List<Effect> effects) {
        this.effects.add(effects);
    }

    /**
     * Appends a single Effect to the EffectList tied to the water container
     *
     * @param effect the Effect to append
     */
    public void addEffect(Effect effect) {
        this.effects.add(effect);
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        Ground ground = map.locationOf(owner).getGround();

        if (ground.hasCapability(Ability.CAN_BE_WATERED)) {
            actions.add(new WaterAction(ground, this));
        }

        return actions;
    }
}
