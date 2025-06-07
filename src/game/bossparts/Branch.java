package game.bossparts;

import java.util.Random;

public class Branch extends BossPart {

    private Random random = new Random();

    /**
     * Constructor for Branch
     *
     */
    public Branch() {
        super("Branch", true);
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart growPart() {
        if (random.nextBoolean()) {
            return new Branch();
        } else {
            return new Leaf();
        }
    }

}
