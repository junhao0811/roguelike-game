package game.bossparts;

public class Leaf extends BossPart {

    /**
     * Constructor for Leaf
     */
    public Leaf() {
        super("Leaf", false);
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    @Override
    public BossPart growPart() {
        return null; // A leaf cannot grow further
    }

}
