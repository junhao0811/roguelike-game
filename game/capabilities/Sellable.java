package game.capabilities;


import game.effects.EffectList;

/**
 * Interface representing a Sellable
 *
 * @author Tan Choong Sheng
 */
public interface Sellable {

    /**
     * Method to return the list of effects the Sellable holds
     */
    default EffectList getEffects() {
        return new EffectList();
    }
}
