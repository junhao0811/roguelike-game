package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A sacred tear that provides positive affection
 * Heals the receiver slightly and boosts affection
 *
 * @author Chong Jun Hao
 */
public class SacredTear extends AffectionItem {
    
    private static final int POSITIVE_AFFECTION = 10;
    private static final int HEALING_AMOUNT = 10;
    
    /**
     * Constructor for SacredTear
     */
    public SacredTear() {
        super("Sacred Tear", 'S', POSITIVE_AFFECTION);
    }
    
    /**
     * Applies the specific effect of the Sacred Tear when given to an actor
     *
     * @param giver     the actor giving the item
     * @param receiver  the actor receiving the item
     * @param map       the current game map
     * @return a string describing the effect of giving the item
     */
    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        // Heal the receiver slightly
        receiver.heal(HEALING_AMOUNT);
        result.append("The sacred tear heals ").append(receiver)
                .append(" for ").append(HEALING_AMOUNT).append(" points");
        result.append("\n").append("\"Such purity... it reminds me of the academy's true purpose\"");

        return result.toString();
    }
}