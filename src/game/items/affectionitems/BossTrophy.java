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
    
    @Override
    protected String applySpecificEffect(Actor giver, Actor receiver, GameMap map) {
        return receiver + " is impressed by the legendary trophy" +
                "\n" + giver + " gains prestige from showing the trophy" +
                "\n" + receiver + " regards you with newfound respect";
    }
}
