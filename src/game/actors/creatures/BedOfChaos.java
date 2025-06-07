package game.actors.creatures;

import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.GrowthBehaviour;
import game.behaviours.SmackBehaviour;
import game.bossparts.BossPart;
import game.bossparts.Branch;
import game.bossparts.Leaf;
import game.capabilities.Growable;
import game.enums.Ability;
import game.enums.BehaviourSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing the Bed of Chaos, a creature in the game.
 *
 * @author Ng Jun Xian
 */
public class BedOfChaos extends Creature implements Growable {

    private final List<BossPart> growthParts;
    private Random random = new Random();

    /**
     * Constructor for BedOfChaos.
     */
    public BedOfChaos(BehaviourSelection selectionStrategy) {
        super("Bed of Chaos", 'T', 1000, selectionStrategy);
        this.addCapability(Ability.GROW);
        this.growthParts = new ArrayList<>();
        this.addBehaviour(Integer.MAX_VALUE, new SmackBehaviour());
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
            BossPart nextPart = currentPart.growPart();
            currentPart = nextPart; // Move to next part or null to end chain
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

    private BossPart getRandomGrowthPart() {

        return random.nextBoolean() ? new Branch() : new Leaf();
    }
}