package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing an Effect
 */
public abstract class Effect {

    private final Actor target;

    /**
     * Constructor
     *
     * @param target the target of the effect
     */
    protected Effect(Actor target) {
        this.target = target;
    }

    /**
     * Constructor with no target as default
     */
    protected Effect() {
        this.target = null;
    }

    /**
     * Applies the effect
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened
     */
    public abstract String applyEffect(Actor actor, GameMap map);

    /**
     * Returns the target, if it is non-null.
     * If it is null, then the actor (other potential target)
     * in the argument is returned instead.
     *
     * @param actor the other potential target
     * @return a target Actor
     */
    protected Actor getTarget(Actor actor) {
        if (target != null) {
            return target;
        }

        return actor;
    }
}
