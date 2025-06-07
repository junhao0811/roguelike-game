package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


/**
 * Class representing a weapon item called a broadsword.
 * This weapon item deals 30 damage points with a 50% chance to hit the target.
 *
 * @author Ng Jun Xian
 */
public class BossWeapon extends IntrinsicWeapon {

    private static final int BASE_DAMAGE = 25;
    private static final String VERB = "smacks";
    private static final int HIT_RATE = 75;

    public BossWeapon() {
        super(BASE_DAMAGE, VERB, HIT_RATE);
    }

    public BossWeapon(int bonusDamage){
        super(BASE_DAMAGE + bonusDamage, VERB, HIT_RATE);
    }

}