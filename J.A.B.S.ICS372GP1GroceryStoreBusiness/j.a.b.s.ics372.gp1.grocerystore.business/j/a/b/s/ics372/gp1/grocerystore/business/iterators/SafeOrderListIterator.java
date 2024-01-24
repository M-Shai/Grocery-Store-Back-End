package j.a.b.s.ics372.gp1.grocerystore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import j.a.b.s.ics372.gp1.grocerystore.business.entities.Order;
import j.a.b.s.ics372.gp1.grocerystore.business.facade.Result;

/**
 * The user of SafeIterator must supply an Iterator to OrderList.
 * 
 * @param iterator Iterator<Order>
 * 
 * updated 3/17/2023
 */
public class SafeOrderListIterator implements Iterator<Result> {
	private Iterator<Order> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to
	 * 
	 * @param iterator
	 */
	public SafeOrderListIterator(Iterator<Order> iterator) {
		this.iterator = iterator;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	public Result next() {
		if (iterator.hasNext()) {
			result.setOrderFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
