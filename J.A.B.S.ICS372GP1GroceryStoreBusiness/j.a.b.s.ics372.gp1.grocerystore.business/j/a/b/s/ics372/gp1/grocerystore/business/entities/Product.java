package j.a.b.s.ics372.gp1.grocerystore.business.entities;

import java.util.Objects;

/**
 * Product represent a product object
 * 
 * @author: Abshir
 * 
 * updated 3/17/2023
 */

public class Product extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double price;
	private int minimumReorderLevel;
	private int productStock;

	public Product(int productId, String productName, double price, 
			int minimumReorderLevel, int productStock) {
		super(productName, productId);
		this.price = price;
		this.minimumReorderLevel = minimumReorderLevel;
		this.productStock = productStock;
	}
	
	/**
	 * @author Say and Banji
	 * 
	 * overload, for subclass SellableProduct
	 * 
	 * @param name
	 * @param price
	 * @param price2
	 */
	public Product(String name, int id, double price) {
		super(name, id);
		this.price = price;
	}
	
	/**
	 * @return int id
	 */
	public int getProductId() {
		return super.getId();
	}

	/**
	 * @return String name
	 */
	public String getProductName() {
		return super.getName();
	}

	/**
	 * @return double prce
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return int min
	 */
	public int getMinimumReorderLevel() {
		return minimumReorderLevel;
	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 * @return int productStock
	 */
	public int getProductStock() {
		return productStock;
	}

	/**
	 * @author Say Chaleon Vang
	 * 
	 * @param productStock the productStock to set
	 */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	
	/**
	 * @return String data fields
	 */
	public String shipment() {
		return  "\n[productId=" + super.getId() + ", productName=" + 
				super.getName() + ", minimumReorderLevel=" + 
				minimumReorderLevel + ", stock=" + productStock + "]";
	}
	
	/**
	 * @return String data feilds
	 */
	@Override
	public String toString() {
		return "\n[productId=" + super.getId() + ", productName=" + 
				super.getName() + ", price=" + price + 
				", minimumReorderLevel=" + minimumReorderLevel + 
				", stock=" + productStock + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(minimumReorderLevel, price, 
				super.getId(), super.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		return minimumReorderLevel == other.minimumReorderLevel
				&& Double.doubleToLongBits(price) == 
					Double.doubleToLongBits(other.price)
				&& super.getId() == other.getId() 
				&& Objects.equals(super.getName(), other.getName());
	}

	/**
	 * @param productPrice
	 * @return boolean
	 */
	public boolean setPrice(double productPrice) {
		double oldPrice = price;
		this.price = productPrice;
		return (oldPrice != price); 
	}
} // end of the class

