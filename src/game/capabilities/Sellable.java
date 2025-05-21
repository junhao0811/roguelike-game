package game.capabilities;

import game.effects.Effect;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface representing a Sellable
 */
public interface Sellable {

    /**
     * Method to return the list of effects the Sellable holds
     */
    default List<Effect> getEffects() {
        return new ArrayList<Effect>();
    }
}
