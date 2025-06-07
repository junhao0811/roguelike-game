package game.bossparts;

/**
 * Abstract class representing a growth part of the Bed of Chaos boss
 * Follows Strategy pattern for different part behaviors
 *
 * @author Ng Jun Xian
 */
public abstract class BossPart {

    protected final String name;
    protected final boolean canGrow;

    /**
     * Constructor for BossGrowthPart
     *
     * @param name the name of the part
     * @param canGrow whether this part can continue growing
     */
    protected BossPart(String name, boolean canGrow) {
        this.name = name;
        this.canGrow = canGrow;
    }

    /**
     * Creates the next growth part (if this part can grow)
     * Returns null if this part cannot grow
     */
    public abstract BossPart growPart();

    /**
     * Checks if this part can grow further
     *
     * @return true if this part can grow, false otherwise
     */
    public boolean canGrow() {
        return canGrow;
    }

    /**
     * Gets the name of the part
     *
     * @return the name of the part
     */
    @Override
    public String toString() {
        return name;
    }
}