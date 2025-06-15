package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.conditions.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing the berserk behaviour
 *
 * @author Abaan Anwar
 */
public class BerserkBehaviour implements Behaviour {

    private final Condition condition;
    private final Random random = new Random();

    /**
     * Constructor
     *
     * @param condition the condition to be checked onto the potential targets
     *                  to register them as possible targets
     */
    public BerserkBehaviour(Condition condition) {
        this.condition = condition;
    }

    /**
     * Returns an AttackAction to attack a random actor nearby, if it passes
     * the condition.
     * If no possible targets are available, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target  = destination.getActor();

                if (condition.check(target, map.locationOf(actor))) {
                    actions.add(new AttackAction(target));
                }
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }

        return null;
    }
}
