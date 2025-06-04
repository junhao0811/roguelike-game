package game.capabilities.merchant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Stock&gt;</code> that does not allow nulls to be added.
 *
 * @author Lee Hao Zhe
 */
public class StockList implements Iterable<Stock<?>> {
    /**
     * List of stocks
     */
    private final ArrayList<Stock<?>> stocks = new ArrayList<>();

    /**
     * Constructs an empty list of stocks.
     */
    public StockList() {}

    /**
     * Constructs a collection containing a single (non-null) Stock.
     * @param stock the Stock to add
     */
    public StockList(Stock<?> stock) {
        add(stock);
    }

    /**
     * Appends the contents of another Stocks list to this one.
     * @param stocks the Stocks to append
     */
    public void add(StockList stocks) {
        for(Stock<?> stock : stocks) {
            add(stock);
        }
    }

    /**
     * Appends the contents of any List&lt;Stock&gt; to this one.
     * This overload allows the use of an unmodifiableList to prevent privacy leaks.
     * @param stocks the List&lt;Stock&gt; to append
     */
    public void add(List<Stock<?>> stocks) {
        for (Stock<?> stock : stocks) {
            add(stock);
        }
    }

    /**
     * Appends a single Stock to this collection, if it is non-null.
     * If it is null, then it is ignored.
     * @param stock the Stock to append
     * @return true unconditionally
     */
    public boolean add(Stock<?> stock) {
        if (stock != null) {
            stocks.add(stock);
        }
        return true;
    }

    /**
     * Returns an Iterator for the underlying collection.
     * Implementing this method means that Stocks implements the Iterable interface, which allows
     * you to use it in a foreach, e.g. <code>for (Stock a: stocks) {
     *    ...
     *    </code>
     * @return an iterator
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Stock<?>> iterator() {
        return Collections.unmodifiableList(stocks).iterator();
    }

    /**
     * Returns a sorted copy of the list of Stocks.
     * @param comparator an object that can compare two Stocks and determine their ordering
     * @return a sorted shallow copy of the list of Stocks
     */
    public List<Stock<?>> sorted(Comparator<Stock<?>> comparator) {
        ArrayList<Stock<?>> sortedStocks = new ArrayList<>(stocks);
        sortedStocks.sort(comparator);
        return sortedStocks;
    }

    /**
     * Delete the contents of this collection, leaving it empty.
     */
    public void clear() {
        stocks.clear();
    }

    /**
     * Count the number of Stocks in the collection.
     * @return the number of Stocks in the collection.
     */
    public int size() {
        return stocks.size();
    }

    /**
     * Remove the first occurrence of a Stock from the collection, if it is present.
     * If it is not present, the list is unchanged.
     * @param stock the Stock to remove
     */
    public void remove(Stock<?> stock) {
        stocks.remove(stock);
    }

    /**
     * Return the <code>i</code>'th Stock in the collection.
     * @param i index of the Stock to retrieve
     * @return the <code>i</code>'th Stock in the collection
     * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
     */
    public Stock<?> get(int i) {
        return stocks.get(i);
    }

    /**
     * Create and return an unmodifiable copy of the contents of the collection.
     * @return an unmodifiable list of Stock
     */
    public List<Stock<?>> getUnmodifiableStockList() {
        return Collections.unmodifiableList(stocks);
    }
}
