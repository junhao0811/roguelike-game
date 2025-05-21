package game.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Effect&gt;</code> that does not allow nulls to be added.
 */
public class EffectList implements Iterable<Effect> {
    /**
     * List of effects
     */
    private final ArrayList<Effect> effects = new ArrayList<>();

    /**
     * Constructs an empty list of effects.
     */
    public EffectList() {}

    /**
     * Constructs a collection containing a single (non-null) Effect.
     * @param effect the Effect to add
     */
    public EffectList(Effect effect) {
        add(effect);
    }

    /**
     * Appends the contents of another Effects list to this one.
     * @param effects the Effects to append
     */
    public void add(EffectList effects) {
        for(Effect effect : effects) {
            add(effect);
        }
    }

    /**
     * Appends the contents of any List&lt;Effect&gt; to this one.
     * This overload allows the use of an unmodifiableList to prevent privacy leaks.
     * @param effects the List&lt;Effect&gt; to append
     */
    public void add(List<Effect> effects) {
        for (Effect effect : effects) {
            add(effect);
        }
    }

    /**
     * Appends a single Effect to this collection, if it is non-null.
     * If it is null, then it is ignored.
     * @param effect the Effect to append
     * @return true unconditionally
     */
    public boolean add(Effect effect) {
        if (effect != null) {
            effects.add(effect);
        }
        return true;
    }

    /**
     * Returns an Iterator for the underlying collection.
     * Implementing this method means that Effects implements the Iterable interface, which allows
     * you to use it in a foreach, e.g. <code>for (Effect a: effects) {
     *    ...
     *    </code>
     * @return an iterator
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Effect> iterator() {
        return Collections.unmodifiableList(effects).iterator();
    }

    /**
     * Returns a sorted copy of the list of Effects.
     * @param comparator an object that can compare two Effects and determine their ordering
     * @return a sorted shallow copy of the list of Effects
     */
    public List<Effect> sorted(Comparator<Effect> comparator) {
        ArrayList<Effect> sortedEffects = new ArrayList<>(effects);
        sortedEffects.sort(comparator);
        return sortedEffects;
    }

    /**
     * Delete the contents of this collection, leaving it empty.
     */
    public void clear() {
        effects.clear();
    }

    /**
     * Count the number of Effects in the collection.
     * @return the number of Effects in the collection.
     */
    public int size() {
        return effects.size();
    }

    /**
     * Remove the first occurrence of an Effect from the collection, if it is present.
     * If it is not present, the list is unchanged.
     * @param effect the Effect to remove
     */
    public void remove(Effect effect) {
        effects.remove(effect);
    }

    /**
     * Return the <code>i</code>'th Effect in the collection.
     * @param i index of the Effect to retrieve
     * @return the <code>i</code>'th Effect in the collection
     * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
     */
    public Effect get(int i) {
        return effects.get(i);
    }

    /**
     * Create and return an unmodifiable copy of the contents of the collection.
     * @return an unmodifiable list of Effect
     */
    public List<Effect> getUnmodifiableEffectList() {
        return Collections.unmodifiableList(effects);
    }
}
