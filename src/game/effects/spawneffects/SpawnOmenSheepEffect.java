package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.creatures.Creature;
import game.actors.creatures.GoldenBeetle;
import game.enums.BehaviourSelection;
import java.util.Random;

/**
 * Class representing the spawn Omen Sheep effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnOmenSheepEffect extends SpawnEffect {

    private Random random = new Random();

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnOmenSheepEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnOmenSheepEffect() {
        super();
    }

    @Override
    protected Creature spawnCreature() {
        // Randomly decide the behavior selection strategy for the spawned creature
        BehaviourSelection offspringBehavior;
        if (random.nextBoolean()) {
            offspringBehavior = BehaviourSelection.RANDOM;
        } else {
            offspringBehavior = BehaviourSelection.DEFAULT;
        }

        return new GoldenBeetle(offspringBehavior);
    }
}
