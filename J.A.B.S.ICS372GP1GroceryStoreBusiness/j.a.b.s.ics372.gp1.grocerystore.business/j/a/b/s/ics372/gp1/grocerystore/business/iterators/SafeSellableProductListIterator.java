package j.a.b.s.ics372.gp1.grocerystore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import j.a.b.s.ics372.gp1.grocerystore.business.entities.SellableProduct;
import j.a.b.s.ics372.gp1.grocerystore.business.facade.Result;

/**
 * The user of SafeIterator must supply an Iterator to SellableProductList.
 * 
 * @param iterator Iterator<SellableProduct>
 */
public class SafeSellableProductListIterator implements Iterator<Result> {
	
	private Iterator<SellableProduct> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to
	 * 
	 * @param iterator
	 * @return 
	 */
	public SafeSellableProductListIterator(Iterator<SellableProduct> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setSellableProductFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
