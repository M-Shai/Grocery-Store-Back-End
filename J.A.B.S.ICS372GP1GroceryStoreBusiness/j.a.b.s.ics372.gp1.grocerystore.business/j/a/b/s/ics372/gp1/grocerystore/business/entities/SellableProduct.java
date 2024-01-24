package j.a.b.s.ics372.gp1.grocerystore.business.entities;

/**
 * A product that is being sold
 * 
 * updated 3/17/2023
 */
public class SellableProduct extends Entity {

	private static final long serialVersionUID = 1L;
	private int quantity;
	private double price;
	private int difference;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * @param productId
	 * @param price
	 * @param quantityInCart
	 */
	public SellableProduct(String name, int productId, 
			double price, int quantity) {
		super(name, productId);
		this.quantity = quantity;
		this.setPrice(price);
	}
	
	/**
	 * get the quantity in cart
	 * 
	 * @return int quantityInCart
	 */
	public int getQuantity () {
		//System.out.println("SelPro.getQuan ln 30: 
			//this.quantity = " + quantity);
		return quantity;
	}
	
	/**
	 * set the quantity in cart
	 * 
	 * @param quantityInCart
	 */
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the difference
	 */
	public int getDifference() {
		return difference;
	}

	/**
	 * @param difference the difference to set
	 */
	public void setDifference(int difference) {
		this.difference = difference;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * computes the total cost
	 * 
	 * @return price * quantityInCart
	 */
	public double computeTotalCost() {
		return price * quantity;
	}

	@Override
	public String toString() {
		return String.format("\n[id: %s, name: %20s, price: %.2f, quantity: "
				+ "%s, cost: %.2f]", super.getId(), 
				super.getName(), price, quantity, 
				computeTotalCost());
	}
}
