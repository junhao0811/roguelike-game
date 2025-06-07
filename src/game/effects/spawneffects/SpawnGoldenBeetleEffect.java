package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.creatures.GoldenBeetle;
import game.enums.BehaviourSelection;
import java.util.Random;
import game.actors.creatures.Creature;

/**
 * Class representing the spawn Golden Beetle effect
 *
 * @author Tan Choong Sheng and Lee Hao Zhe
 */
public class SpawnGoldenBeetleEffect extends SpawnEffect {

    private Random random = new Random();

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    public SpawnGoldenBeetleEffect(Actor target) {
        super(target);
    }

    /**
     * Constructor with no target as default
     */
    public SpawnGoldenBeetleEffect() {
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
