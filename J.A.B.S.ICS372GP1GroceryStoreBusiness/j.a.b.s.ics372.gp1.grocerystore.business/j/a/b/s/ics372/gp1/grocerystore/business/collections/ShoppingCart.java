package j.a.b.s.ics372.gp1.grocerystore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import j.a.b.s.ics372.gp1.grocerystore.business.entities.SellableProduct;

/**
 * This class generates a shopping cart object that is associated with a
 * memberId as unique identifier. It has access to the ProductList collection
 * 
 * updated 3/172023
 */
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ProductList productList;
	private List<SellableProduct> cart;
	private double totalPrice;
	private int memberId;

	private String stringDate = null;

	/**
	 * Initializes @param cart to an empty <code>ArrayList<Product</code> 
	 * set @param totalPrice to zero. Gets an instance of 
	 * <code>ProductList</code> singleton.
	 * 
	 * TODOS -------
	 * <p>
	 * Ideally would prefer to not get <code>ProductList</code> instance. This
	 * approach might have side effects and could lead to multiple instances of
	 * the singleton. Better practice would be to invoke as needed through an 
	 * iterator. like he showed in class.
	 * </p>
	 * 
	 */
	public ShoppingCart() {
		productList = ProductList.instance();
		cart = new LinkedList<SellableProduct>();
		totalPrice = 0.00;
	} // close constructor

	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * Try using this search
	 * 
	 * @param productId
	 * @return
	 */
	public SellableProduct search(int productId) {
		for (Iterator<SellableProduct> iterator = cart.iterator(); 
				iterator.hasNext();) {
			SellableProduct product = iterator.next();
			if (product.getId() == productId) {
				return product;
			}
		}
		return null;
	}

	/**
	 * @param object the checkOutDate to set
	 */
	public void setCheckOutDate(int month, int day, int year) {
		stringDate = (month + 1) + "/" + day + "/" + year;
	}

	/**
	 * @return the stringDate
	 */
	public String getStringDate() {
		return stringDate;
	}

	/**
	 * @param stringDate the stringDate to set
	 */
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	/**
	 * @param sellableProduct
	 * @return boolean
	 */
	/*
	public boolean addSellableProduct(SellableProduct sellableProduct) {
		return add(sellableProduct);
	} // addSellableProduct*/

	/**
	 * @param sellableProduct
	 * @return boolean
	 */
	public boolean add(SellableProduct sellableProduct) {
		totalPrice += sellableProduct.computeTotalCost();
		return cart.add(sellableProduct);
	} //

	/**
	 * @param sellableProduct
	 * @return boolean
	 */
	public boolean remove(SellableProduct sellableProduct) {
		if (!cart.contains(sellableProduct)) {
			return false;
		}
		return cart.remove(sellableProduct);
	} // close remove

	/**
	 * @return double
	 */
	public double getTotalPrice() {
		return totalPrice;
	} // close getTotalPrice

	/**
	 * Removes all items from <code>Shopping<Cart</code> restoring to initial state
	 */
	public void empty() {
		this.cart = new LinkedList<SellableProduct>();
		totalPrice = 0;
	} // close empty

	/**
	 * Returns an iterator to all items in cart
	 * 
	 * @return iterator to the collection
	 */
	public Iterator<SellableProduct> iterator() {
		return (Iterator<SellableProduct>) cart.iterator();
	}

	/**
	 * new toString that works using StringBuilder
	 * Creates a string conveying information about the cart's state
	 * 
	 * @return string
	 */
	public String toString() {
		StringBuilder string = new StringBuilder("\n----Shopping Cart------------------------------------");

		if (this.cart.isEmpty()) {
			return "The cart is empty";
		} else {
			for (SellableProduct sellableProduct : this.cart) {
				string.append(sellableProduct.toString());
			}
		}
		string.append(String.format("\n*** Grocery Cart Total = %.2f", totalPrice));
		string.append("\n--------------------------------------------------------------------------");
		return string.toString();
	} // close toString
} // end class ShoppingCart