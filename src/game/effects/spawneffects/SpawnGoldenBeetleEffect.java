package game.effects.spawneffects;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.npcs.GoldenBeetle;

public class SpawnGoldenBeetleEffect extends SpawnEffect {

    public SpawnGoldenBeetleEffect(Actor target) {
        super(new GoldenBeetle(), target);
    }

    public SpawnGoldenBeetleEffect() {
        super(new GoldenBeetle());
    }
}
