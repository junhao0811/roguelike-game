package game.actors;

import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * class that represents crimson rot status effect, a slow and endless decay resulting from the work of an Outer God, and it devours those who live and those who don't, especially creatures
 * For example: a creature might disappear from the map after the countdown timer is up
 *
 * @author Chong Jun Hao
 * @version 22.0.1
 */
public class CrimsonRot extends StatusEffect {
    
    private int countdownTimer;
    
    /**
     * Constructor.
     */
    public CrimsonRot() {
        super("Crimson Rot");
    }
    
    /**
     * Constructor.
     */
    public CrimsonRot(int duration) {
        super("Crimson Rot");
        this.countdownTimer = duration;
    }
    
    /**
     * Inform a status effect of the passage of time.
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        countdownTimer--;
        if (countdownTimer <= 0) {
            new Display().println(actor.unconscious(location.map()));
        }
    }
}
