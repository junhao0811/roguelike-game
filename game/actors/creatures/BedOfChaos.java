package game.actors.creatures;


import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.GrowthBehaviour;
import game.behaviours.SmackBehaviour;
import game.bossparts.BossPart;
import game.bossparts.Branch;
import game.bossparts.Leaf;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.weapons.BossWeapon;
import java.util.Random;
import game.capabilities.Growable;

/**
 * Class representing the Bed of Chaos, a creature in the game.
 * 
 * The Bed of Chaos is a unique creature that can only be attacked by the player.
 * It has a special behavior that allows it to attack the player when they are within range.
 */
public class BedOfChaos extends Creature implements Growable {  
    
    private final List<BossPart> growthParts;
    private Random random = new Random();

    /**
     * Constructor for BedOfChaos.
     */
    public BedOfChaos(BehaviourSelection selectionStrategy) {
        super("Bed of Chaos", 'T', 1000, selectionStrategy);
        this.addBehaviour(Integer.MAX_VALUE, new SmackBehaviour());
        this.addCapability(Ability.CAN_GROW);
        this.growthParts = new ArrayList<>();
        this.addBehaviour(Integer.MAX_VALUE, new GrowthBehaviour());
    }

    public 
    private BossPart getRandomGrowthPart() {
        if (growthParts.isEmpty()) {
            return null;
        }

        return random.nextBoolean() ? new Branch() : new Leaf();
    }

    /**
     * Returns the intrinsic weapon for the Bed of Chaos
     * Uses the BossWeapon with 25 damage and 75% hit rate
     *
     * @return BossWeapon intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new BossWeapon();
    }

    
}