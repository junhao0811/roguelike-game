package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class Effect {

    private final Actor target;

    public Effect() {
        this.target = null;
    }

    public Effect(Actor target) {
        this.target = target;
    }

    public abstract String applyEffect(Actor actor, GameMap map);

    public Actor getTarget(Actor actor) {
        if (target != null) {
            return target;
        }

        return actor;
    }
}
