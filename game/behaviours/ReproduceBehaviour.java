package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ReproduceAction;
import game.capabilities.Reproducible;

/**
 * Class representing the reproduce behaviour
 *
 * @author Ng Jun Xian
 */
public class ReproduceBehaviour implements Behaviour {

    private final Reproducible reproducible;

    /**
     * Constructor
     *
     * @param reproducible the Reproducible object that will reproduce
     */
    public ReproduceBehaviour(Reproducible reproducible) {
        this.reproducible = reproducible;
    }

    /**
     * Returns a ReproduceAction when an actor should reproduce.
     * Delegates to the actor's canReproduce method to check conditions.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return a ReproduceAction if conditions are met, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if the actor can reproduce (logic in actor classes)
        if (reproducible.canReproduce(actor, map)) {
            return new ReproduceAction(reproducible);
        } else {
            return null;
        }
    }
}