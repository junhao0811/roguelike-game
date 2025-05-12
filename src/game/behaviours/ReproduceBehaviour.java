package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Reproducible;

import java.util.Random;

public class ReproduceBehaviour implements Behaviour {

    private final Random random = new Random();
    private final Reproducible reproducible;


    public ReproduceBehaviour(Reproducible reproducible){
        this.reproducible = reproducible;
    }

    @Override
    public Action getAction(Actor actor, GameMap map){
        return null;
    }
}