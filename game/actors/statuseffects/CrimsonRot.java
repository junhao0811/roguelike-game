package game.actors.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that represents the crimson rot status effect, a slow and endless decay resulting
 * from the work of an Outer God, and it devours those who live and those who don't
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class CrimsonRot extends StatusEffect {

    private int countdownTimer;

    /**
     * Constructor
     *
     * @param duration the number of turns until the creature disappears
     */
    public CrimsonRot(int duration) {
        super("Crimson Rot");
        this.countdownTimer = duration;
    }

    @Override
    public void tick(Location location, Actor actor) {
        Display display = new Display();

        if (countdownTimer == 0) {
            display.println(actor + " succumbs to the " + this + " and perishes");
            actor.unconscious(location.map());
        }

        countdownTimer--;
    }
}
