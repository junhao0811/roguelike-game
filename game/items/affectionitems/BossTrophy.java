package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A boss trophy that provides high positive affection
 * Dropped from Bed of Chaos after defeat
 *
 * @author GitHub Copilot
 */
public class BossTrophy extends AffectionItem {
    
    private static final int POSITIVE_AFFECTION = 25;

    
    /**
     * Constructor for BossTrophy
     */
    public BossTrophy() {
        super("Boss Trophy", 'B', POSITIVE_AFFECTION);
    }

    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        StringBuilder result = new StringBuilder();
        
        result.append(receiver).append(" is impressed by the legendary trophy");
        
        result.append("\n").append(giver).append(" gains prestige from showing the trophy");
        
        // Specific reactions based on NPC
        String receiverName = receiver.toString();
        switch (receiverName) {
            case "Sellen":
                result.append("\n").append("\"Incredible! The power emanating from this trophy...\"");
                break;
            case "Guts":
                result.append("\n").append("\"Hmph. You've proven yourself worthy, warrior.\"");
                break;
            case "Merchant Kale":
                result.append("\n").append("\"My word! A genuine boss trophy! This changes everything!\"");
                break;
            default:
                result.append("\n").append(receiver).append(" regards you with newfound respect");
        }
        
        return result.toString();
    }
}
    
