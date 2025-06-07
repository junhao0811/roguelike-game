package game.actors.creatures;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.NPC;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.enums.Status;
import game.actions.EatAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReproduceBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Edible;
import game.capabilities.Hatchable;
import game.capabilities.Reproducible;
import game.items.Egg;

import java.util.Random;

/**
 * Class representing a Golden Beetle
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class GoldenBeetle extends Creature implements Reproducible, Hatchable, Edible {

    private int reproductionCounter = 0;

    private Random random = new Random();
    /**
     * Constructor
     */
    public GoldenBeetle(BehaviourSelection selection) {
        super("Golden Beetle", 'b', 25, selection);
        this.addBehaviour(Integer.MAX_VALUE, new WanderBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new FollowBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 2, new ReproduceBehaviour(this));
        this.addCapability(Ability.PRODUCE_EGG);
    }

    @Override
    public void reproduce(Actor actor, Location location) {
        location.addItem(new Egg(this, "Golden Egg"));
    }

    @Override
    public boolean canReproduce(Actor actor, GameMap map) {
        reproductionCounter++;
        return reproductionCounter % 5 == 0;
    }

    @Override
    public Creature hatch() {
        BehaviourSelection offspringBehavior;
        if (random.nextBoolean()) {
            offspringBehavior = BehaviourSelection.RANDOM;
        } else {
            offspringBehavior = BehaviourSelection.DEFAULT;
        }
        return new GoldenBeetle(offspringBehavior);
    }

    @Override
    public boolean isReadyToHatch(int counter, Location location) {
        for (Exit exit : location.getExits()) {
            Location nearby = exit.getDestination();
            if (nearby.getGround().hasCapability(Status.CURSED)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.EAT)) {
            actor.heal(15);
            actor.addBalance(1000);
            this.unconscious(actor, map);
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Ability.EAT)) {
            actions.add(new EatAction(this));
        }

        return actions;
    }
}