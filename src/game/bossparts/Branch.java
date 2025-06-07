package game.bossparts;

import java.util.Random;

/**
 * Class representing a Branch part of the Bed of Chaos boss
 * This part can grow into either another Branch or a Leaf
 *
 * @author Ng Jun Xian
 */
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
