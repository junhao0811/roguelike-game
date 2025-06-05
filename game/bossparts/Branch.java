package game.bossparts;


import java.util.Random;

public class Branch extends BossPart {

    private Random random = new Random();
    private static final int ATTACK_BONUS = 3;

    /**
     * Constructor for Branch
     * 
     * @param name the name of the branch part
     * @param canGrow whether this branch can continue growing
     */
    public Branch() {
        super("Branch", true, ATTACK_BONUS);   
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart grow() {
        if (random.nextBoolean()) {
            return new Branch();
        } else {
            return new Leaf();
        }
    }
    
}
