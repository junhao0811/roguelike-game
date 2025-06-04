package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * Class representing a weapon item called a broadsword.
 * This weapon item deals 30 damage points with a 50% chance to hit the target.
 *
 * @author Tan Choong Sheng
 */
public class BossWeapon extends IntrinsicWeapon {

    public BossWeapon() {
        super(25, "smacks", 75);
    }
}