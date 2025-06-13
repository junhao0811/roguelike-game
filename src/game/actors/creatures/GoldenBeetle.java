package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.capabilities.Consumable;
import game.conditions.CounterCondition;
import game.conditions.NearbyCondition;
import game.effects.attributeeffects.RestoreAttributeEffect;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.enums.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.items.Egg;

/**
 * Class representing a Golden Beetle
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class GoldenBeetle extends Creature implements Reproducible, Hatchable, Consumable {

    /**
     * Constructor
     *
     * @param selection the Golden Beetle's strategy for selecting behaviours
     */
    public GoldenBeetle(BehaviourSelection selection) {
        super("Golden Beetle", 'b', 25, selection);
    }

    /**
     * Constructor with default behaviour selection as default
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
    }

    @Override
    protected void setup() {
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new FollowBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 2,
                new ReproduceBehaviour(this, new CounterCondition(5)));
        this.addCapability(Ability.PRODUCE_EGG);
    }

    @Override
    public String reproduce(Actor actor, Location location) {
        Egg<GoldenBeetle> egg = new Egg<>(this, new NearbyCondition(Status.CURSED),
                "Golden");
        egg.addEffect(new RestoreAttributeEffect(BaseActorAttributes.STAMINA, 20));
        location.addItem(egg);

        return this + " produces a new " + egg;
    }

    @Override
    public Creature hatch() {
        return new GoldenBeetle(getSelection());
    }

    @Override
    public String consume(Actor actor, GameMap map) {
        actor.heal(15);
        actor.addBalance(1000);
        this.unconscious(actor, map);

        return "";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Ability.CONSUME)) {
            actions.add(new ConsumeAction(this));
        }

        return actions;
    }
}