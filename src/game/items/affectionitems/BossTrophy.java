package game.items.affectionitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A boss trophy that provides high positive affection
 * Dropped from Bed of Chaos after defeat
 *
 * @author Chong Jun Hao
 */
public class BossTrophy extends AffectionItem {
    
    private static final int POSITIVE_AFFECTION = 25;
    
    /**
     * Constructor for BossTrophy
     */
    public BossTrophy() {
        super("Boss Trophy", 'B', POSITIVE_AFFECTION);
    }
    
    /**
     * Applies the specific effect of giving the trophy to an NPC
     * Increases affection and provides a prestige boost
     *
     * @param giver    the actor giving the trophy
     * @param receiver the actor receiving the trophy
     * @param map      the current game map
     * @return a message describing the effect of giving the trophy
     */
    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        return receiver + " is impressed by the legendary trophy" +
                "\n" + giver + " gains prestige from showing the trophy" +
                "\n" + receiver + " regards you with newfound respect";
    }
}
