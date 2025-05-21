package game.conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Condition&gt;</code> that does not allow nulls to be added.
 */
public class ConditionList implements Iterable<Condition> {
    /**
     * List of conditions
     */
    private final ArrayList<Condition> conditions = new ArrayList<>();

    /**
     * Constructs an empty list of conditions.
     */
    public ConditionList() {}

    /**
     * Constructs a collection containing a single (non-null) Condition.
     * @param condition the Condition to add
     */
    public ConditionList(Condition condition) {
        add(condition);
    }

    /**
     * Appends the contents of another Conditions list to this one.
     * @param conditions the Conditions to append
     */
    public void add(ConditionList conditions) {
        for(Condition condition : conditions) {
            add(condition);
        }
    }

    /**
     * Appends the contents of any List&lt;Condition&gt; to this one.
     * This overload allows the use of an unmodifiableList to prevent privacy leaks.
     * @param conditions the List&lt;Condition&gt; to append
     */
    public void add(List<Condition> conditions) {
        for (Condition condition : conditions) {
            add(condition);
        }
    }

    /**
     * Appends a single Condition to this collection, if it is non-null.
     * If it is null, then it is ignored.
     * @param condition the Condition to append
     * @return true unconditionally
     */
    public boolean add(Condition condition) {
        if (condition != null) {
            conditions.add(condition);
        }
        return true;
    }

    /**
     * Returns an Iterator for the underlying collection.
     * Implementing this method means that Conditions implements the Iterable interface, which allows
     * you to use it in a foreach, e.g. <code>for (Condition a: conditions) {
     *    ...
     *    </code>
     * @return an iterator
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Condition> iterator() {
        return Collections.unmodifiableList(conditions).iterator();
    }

    /**
     * Returns a sorted copy of the list of Conditions.
     * @param comparator an object that can compare two Conditions and determine their ordering
     * @return a sorted shallow copy of the list of Conditions
     */
    public List<Condition> sorted(Comparator<Condition> comparator) {
        ArrayList<Condition> sortedConditions = new ArrayList<>(conditions);
        sortedConditions.sort(comparator);
        return sortedConditions;
    }

    /**
     * Delete the contents of this collection, leaving it empty.
     */
    public void clear() {
        conditions.clear();
    }

    /**
     * Count the number of Conditions in the collection.
     * @return the number of Conditions in the collection.
     */
    public int size() {
        return conditions.size();
    }

    /**
     * Remove the first occurrence of a Condition from the collection, if it is present.
     * If it is not present, the list is unchanged.
     * @param condition the Condition to remove
     */
    public void remove(Condition condition) {
        conditions.remove(condition);
    }

    /**
     * Return the <code>i</code>'th Condition in the collection.
     * @param i index of the Condition to retrieve
     * @return the <code>i</code>'th Condition in the collection
     * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
     */
    public Condition get(int i) {
        return conditions.get(i);
    }

    /**
     * Create and return an unmodifiable copy of the contents of the collection.
     * @return an unmodifiable list of Condition
     */
    public List<Condition> getUnmodifiableConditionList() {
        return Collections.unmodifiableList(conditions);
    }
}
