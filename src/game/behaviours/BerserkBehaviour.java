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

public class BerserkBehaviour implements Behaviour {

    private final Condition condition;
    private final Random random = new Random();

    public BerserkBehaviour(Condition condition) {
        this.condition = condition;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target  = destination.getActor();

                if (condition.check(target, map)) {
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
