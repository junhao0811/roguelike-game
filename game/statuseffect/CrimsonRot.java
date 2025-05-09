package game.statuseffect;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A status effect that represents Crimson Rot affliction.
 * When applied to an actor, it counts down each turn.
 * When the countdown reaches zero, the actor becomes unconscious and disappears from the map.
 * This represents a terminal disease that eventually kills its victim.
 *
 * @author Ng Jun Xian
 */
public class CrimsonRot extends StatusEffect {
    private int countdown;

    /**
     * Constructor for CrimsonRot.
     *
     * @param countdown the number of turns before the Crimson Rot kills the actor
     */
    public CrimsonRot(int countdown){
        super("CrimsonRot");
        this.countdown = countdown;
    }

    /**
     * Called once per turn. Decrements the countdown and makes the actor unconscious when it reaches zero.
     * Displays the remaining turns before the effect kills the actor.
     *
     * @param location the location of the actor with this status effect
     * @param actor the actor affected by the Crimson Rot
     */
    @Override
    public void tick(Location location, Actor actor){
        if (countdown <= 0){
            actor.unconscious(location.map());
            System.out.println(actor + "has disappeared due to Crimson Rot.");
        } else {
            System.out.println(actor + ": " + countdown + " turns remaining.");
            countdown--;
        }
    }
}
