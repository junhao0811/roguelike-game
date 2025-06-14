package game.actors.creatures;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.GrowthBehaviour;
import game.behaviours.SmackBehaviour;
import game.bossparts.BossPart;
import game.bossparts.Branch;
import game.bossparts.Leaf;
import game.capabilities.Growable;
import game.enums.Ability;
import game.enums.BehaviourSelection;
import game.items.affectionitems.BossTrophy;
import game.weapons.BossWeapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing the Bed of Chaos, a creature in the game.
 * The Bed of Chaos is a unique creature that can only be attacked by the player.
 * It has a special behavior that allows it to attack the player when they are within range.
 *
 * @author Ng Jun Xian
 */
public class BedOfChaos extends Creature implements Growable {

    private final List<BossPart> growthParts;
    private final Random random = new Random();
    
    /**
     * Constructor
     *
     @param selection the Bed of Chaos' strategy for selecting behaviours
     */
    public BedOfChaos(BehaviourSelection selection) {
        super("Bed of Chaos", 'T', 1000, selection);
        this.growthParts = new ArrayList<>();
    }

    /**
     * Constructor with default behaviour selection as default
     */
    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1);
        this.growthParts = new ArrayList<>();
    }

    @Override
    protected void setup() {
        this.addBehaviour(Integer.MAX_VALUE, new SmackBehaviour());
        this.addBehaviour(Integer.MAX_VALUE - 1, new GrowthBehaviour(this));
        this.addCapability(Ability.GROW);
    }

    @Override
    public String grow(GameMap map) {
        StringBuilder growthDescription = new StringBuilder();
        BossPart currentPart = getRandomGrowthPart();

        // Continue growing until we get a part that cannot grow further
        while (currentPart != null && currentPart.canGrow()) {
            growthParts.add(currentPart);
            growthDescription.append(this).append(" is growing...\n");
            growthDescription.append("it grows a ").append(currentPart).append("\n");

            // Apply effects for the current part
            String effectResult = applyPartEffects(currentPart, map);
            if (!effectResult.isEmpty()) {
                growthDescription.append(effectResult).append("\n");
            }

            // Get the next part in the growth chain
            currentPart = currentPart.growPart(); // Move to next part or null to end chain
        }

        // Add the final part that cannot grow further
        if (currentPart != null) {
            growthParts.add(currentPart);
            growthDescription.append(this).append(" is growing...\n");
            growthDescription.append("it grows a ").append(currentPart).append("\n");

            String finalEffectResult = applyPartEffects(currentPart, map);
            if (!finalEffectResult.isEmpty()) {
                growthDescription.append(finalEffectResult).append("\n");
            }
        }

        String result = growthDescription.toString().trim();
        return result.isEmpty() ? this + " cannot grow further" : result;
    }

    /**
     * Applies the effects of a grown part
     *
     */
    private String applyPartEffects(BossPart part, GameMap map) {
        StringBuilder effects = new StringBuilder();

        // Apply effects through the part's effect system
        String effectResult = part.applyGrowEffect(this, map);
        if (!effectResult.isEmpty()) {
            effects.append(effectResult);
        }

        return effects.toString();
    }
    
    /**
     * Calculates total attack bonus from all grown parts
     * Uses the getAttackBonus() method instead of effects system
     * Refactored for better readability and performance
     */
    private int getTotalAttackBonus() {
        int totalBonus = 0;
        
        // Simple iteration for clarity
        for (BossPart part : growthParts) {
            totalBonus += part.getAttackBonus();
        }
        
        return totalBonus;
    }


    /**
     * Returns a random growth part (either Branch or Leaf)
     * Used for the initial growth of the Bed of Chaos
     * Randomly selects between Branch and Leaf
     *
     * @return a randomly selected growth part
     */
    private BossPart getRandomGrowthPart() {

        return random.nextBoolean() ? new Branch() : new Leaf();
    }

    /**
     * Returns the behaviours of the Bed of Chaos
     * Overrides the getBehaviours method to include growth and smack behaviours
     *
     * @return a list of behaviours for the Bed of Chaos
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        super.unconscious(map);
        
        String result = null;
        
        if (currentLocation != null) {
            // drops BossTrophy
            BossTrophy bossTrophy = new BossTrophy();
            currentLocation.addItem(bossTrophy);
            result = this + " drops a trophy as a proof of your victory!";
        }
        
        return result;
    }
    
    /**
     * Handles dropping BossTrophy when Bed of Chaos dies
     * Always drops (100% drop rate)
     */
    private String dropBossTrophy(GameMap map) {
        String result = null;
        Location currentLocation = map.locationOf(this);
        if (currentLocation != null) {
            BossTrophy bossTrophy = new BossTrophy();
            currentLocation.addItem(bossTrophy);
            
            
            result = "The " + this + " drops a Boss Trophy as proof of your victory!";
        }
        
        return result;
    }

    /**
     * Returns the intrinsic weapon for the Bed of Chaos
     * Uses the BossWeapon with 25 damage and 75% hit rate
     *
     * @return BossWeapon intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int bonusDamage = getTotalAttackBonus();
        return new BossWeapon(bonusDamage);
    }

}