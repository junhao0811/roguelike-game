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
import game.items.affectionitems.BossTrophy;
import game.weapons.BossWeapon;
import java.util.Random;
import game.capabilities.Growable;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;

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
        this.addBehaviour(Integer.MAX_VALUE - 1, new GrowthBehaviour(this));
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
            BossPart nextPart = currentPart.grow();
            currentPart = nextPart; // Move to next part or null to end chain
        }
        
        // Add the final part that cannot grow further (like a Leaf)
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
     * Handles attack bonuses and healing without downcasting
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

    private BossPart getRandomGrowthPart() {

        return random.nextBoolean() ? new Branch() : new Leaf();
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Drop BossTrophy before becoming unconscious
        super.unconscious(map);
        return dropBossTrophy(map);
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