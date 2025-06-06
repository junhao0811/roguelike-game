package game.watercontainers;

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

public abstract class WaterContainer extends Item {

    private final EffectList effects = new EffectList();

    protected WaterContainer(String name, char displayChar) {
        super(name, displayChar, true);
    }

    public String water(Actor actor, GameMap map, Crop crop) {
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
            actions.add(new WaterAction((Crop) ground, this));
        }

        return actions;
    }
}
