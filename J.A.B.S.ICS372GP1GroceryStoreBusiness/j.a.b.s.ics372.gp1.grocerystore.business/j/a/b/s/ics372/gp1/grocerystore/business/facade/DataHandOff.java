package j.a.b.s.ics372.gp1.grocerystore.business.facade;

import java.util.Calendar;

import j.a.b.s.ics372.gp1.grocerystore.business.entities.Member;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Order;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Product;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.SellableProduct;

/**
 * <code>DataTransfer</code> assures there is separation between the interface 
 * and data stores (ProductList, OrderList, and MemberList).  By maintaining 
 * separation there is separation between the businesslogic and the interface.  
 * While the work in <code>DataTransfer</code> could be implemented by a using
 * a <code>Transferable</code> interface which requires the implementation of 
 * copy constructors there could be data leakage. While a lot of fields are 
 * duplicated so entities can be created to exist only in the iterface or facade
 * care must be taken to assure there is consistency.
 * 
 * NOTE
 * ----
 * All transferable data items must be atomic (no collections can be used. 
 * Because the items in the collection will have to be processed individually
 * 
 * ABOUT THE GETTERS AND SETTERS
 * -------------------------------
 * Whereas instances of transfered objects are created in methods whose names are 
 * prefixed with setField mutation and access to properties in either the interface 
 * or data store occuer with getters and setters.
 * 
 * @author Say Vang, Jeffrey, Abshir, Banji Lawal
 * @param <T>
 *
 * updated 3/15/2023
 */
public abstract class DataHandOff<T> {
	
	/*
	 * All fields in all objects are needed here so they can be 
	 * recreated independently.  
	 * 
	 * Naming Convention
	 * -------------------
	 * Entities can exist in differing places depending on their is-a or has-a 
	 * relationship with other objects.  To show what object the field creates 
	 * the target object prefixes the field
	 */
	
	// Fields required to create <code>Product</code> instance
	private int productId;
	private String productName;
	private double productPrice;
	private double productNewPrice;
	private int productMinimumReorderLevel;
	private int productInStock;
	
	// Fields required to create <code>SellableProduct</code> instance.
	// despite SellableProduct being derived from <code>Product</code>
	private int sellableProductId;
	private String sellableProductName;
	private double sellableProductPrice;
	private int sellableProductQuantity;
	private int sellableProductDifference;
	private double sellableProductTotalCost = 0;
	private int requestedAmount;
	
	// Fields for recreating <code>Member</code> instance
	private int memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private double memberFee;
	//private int orderAmount;
	
	// Fields for recreating <code>Order</code> instance
	private String orderProductName;
	private int orderProductId;
	private int orderAmount;
	
	// Transaction Data Handoff Fields
	private int transactionMemberId;
	private int transactionProductId;
	private double transactionProductPrice;
	private int transactionProductQuantity;
	private int transactionProductDifference;
	private int transactionProductRequestedAmount;
	private static double transactionProductTotalCost = 0;

	private Calendar transactionDate;
	private String transactionProductName;
	
	private boolean paid;
	private String resultString = null;
	private String stringDate = null;
	private boolean autoTester = true;
	
	
   //Getters and Setters for Order Instance Transfers 
	/**
	 * @return the orderProductName
	 */
	public String getOrderProductName() {
		return orderProductName;
	}

	/**
	 * @param orderProductName the orderProductName to set
	 */
	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

	/**
	 * @return the orderProductId
	 */
	public int getOrderProductId() {
		return orderProductId;
	}

	/**
	 * @param orderProductId the orderProductId to set
	 */
	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	/**
	 * This sets all fields to "none".
	 */
	public DataHandOff() {
		reset();
	}
	
	
	//Getters and Setters for Member Instance Transfers

	/**
	 * @return int memberId
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * @param int memberId
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * @param double memberFee the memberFee to set
	 */
	public void setMemberFee(double memberFee) {
		this.memberFee = memberFee;
	}
	
	/**
	 * @return double memberFee
	 */
	public double getMemberFee() {
		return memberFee;
	}

	/**
	 * @return String memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param String memberName
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return String phoneNumber
	 */
	public String getMemberPhone() {
		return memberPhone;
	}

	/**
	 * @param String memberPhone
	 */
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	
	/**
	 * @return String memberAdress
	 */
	public String getMemberAddress() {
		return memberAddress;
	}

	/**
	 * @param String memberAddress
	 */
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	/**
	 * Sets all the entity-related.
	 * 
	 * @param an abstract object.
	 */
	public void setMemberFields(T t) {
		memberId = ((Member) t).getId();
		memberName = ((Member) t).getName();
		memberPhone = ((Member) t).getPhoneNumber();
		memberAddress = ((Member) t).getAddress();
		memberFee = ((Member) t).getFeePaid();
	}
	
	/////   Product field setters getters   /////
	
	/**
	 * @return int productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param int productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return String productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param String productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * @param double productPrice
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	/**
	 * @return double productPrice
	 */
	public double getProductPrice() {
		
		return productPrice;
	}
	
	/**
	 * @return int the productMinimumReorderLevel
	 */
	public int getProductMinimumReorderLevel() {
		return productMinimumReorderLevel;
	}

	/**
	 * @param int productMinimumReorderLevel 
	 */
	public void setProductMinimumReorderLevel(int productMinimumReorderLevel) {
		this.productMinimumReorderLevel = productMinimumReorderLevel;
	}
	
	/**
	 * @return the productInStock
	 */
	public int getProductInStock() {
		return productInStock;
	}

	/**
	 * @param productInStock the productInStock to set
	 */
	public void setProductInStock(int productInStock) {
		this.productInStock = productInStock;
	}
	
	/**
	 * @return the productNewPrice
	 */
	public double getProductNewPrice() {
		return productNewPrice;
	}

	/**
	 * @param productNewPrice the productNewPrice to set
	 */
	public void setProductNewPrice(double productNewPrice) {
		this.productNewPrice = productNewPrice;
	}

	/**
	 * If the transfer object is a Product sets all their product fields
	 * 
	 * @param Entity 
	 */
	public void setProductFields(T t) {
		this.productId = ((Product) t).getProductId();
		this.productName = ((Product) t).getProductName();
		this.productPrice = ((Product) t).getPrice();
		this.productMinimumReorderLevel = ((Product) t).getMinimumReorderLevel();
		this.productInStock = ((Product) t).getProductStock();
	}
	
	/**
	 * If the transfer object is a SellableProduct sets all their product fields
	 * 
	 * @param Entity 
	 */
	public void setSellableProductFields(T t) {
		this.sellableProductId = ((SellableProduct) t).getId();
		this.sellableProductName = ((SellableProduct) t).getName();
		this.sellableProductPrice = ((SellableProduct) t).getPrice();
		this.sellableProductQuantity = ((SellableProduct) t).getQuantity();
		this.sellableProductDifference = ((SellableProduct) t).getDifference();
		sellableProductTotalCost += ((SellableProduct) t).computeTotalCost();
	}
	
	/**
	 * If the transfer object is a Order sets all their product fields
	 * 
	 * @param Entity 
	 */
	public void setOrderFields(T t) {
        this.productName = ((Order) t).getName();
        this.productId = ((Order) t).getId();
        this.orderAmount = ((Order) t).getAmount();
    }

	/**
	 * @return the sellableProductId
	 */
	public int getSellableProductId() {
		return sellableProductId;
	}

	/**
	 * @param sellableProductId the sellableProductId to set
	 */
	public void setSellableProductId(int sellableProductId) {
		this.sellableProductId = sellableProductId;
	}

	/**
	 * @return the sellableProductName
	 */
	public String getSellableProductName() {
		return sellableProductName;
	}

	/**
	 * @param sellableProductName the sellableProductName to set
	 */
	public void setSellableProductName(String sellableProductName) {
		this.sellableProductName = sellableProductName;
	}

	/**
	 * @return the sellableProductPrice
	 */
	public double getSellableProductPrice() {
		return sellableProductPrice;
	}

	/**
	 * @param sellableProductPrice the sellableProductPrice to set
	 */
	public void setSellableProductPrice(int sellableProductPrice) {
		this.sellableProductPrice = sellableProductPrice;
	}

	/**
	 * @return the sellableProductTotalCost
	 */
	public double getSellableProductTotalCost() {
		return sellableProductTotalCost;
	}

	/**
	 * @param sellableProductTotalCost the sellableProductTotalCost to set
	 */
	public void setSellableProductTotalCost(double sellableProductTotalCost) {
		this.sellableProductTotalCost = sellableProductTotalCost;
	}

	/**
	 * @param sellableProductPrice the sellableProductPrice to set
	 */
	public void setSellableProductPrice(double sellableProductPrice) {
		this.sellableProductPrice = sellableProductPrice;
	}

	/**
	 * @return the sellableProductQuantity
	 */
	public int getSellableProductQuantity() {
		return sellableProductQuantity;
	}

	/**
	 * @param sellableProductQuantity the sellableProductQuantity to set
	 */
	public void setSellableProductQuantity(int sellableProductQuantity) {
		this.sellableProductQuantity = sellableProductQuantity;
	}

	/**
	 * @return the sellableProductDifference
	 */
	public int getSellableProductDifference() {
		return sellableProductDifference;
	}

	/**
	 * @param sellableProductDifference the sellableProductDifference to set
	 */
	public void setSellableProductDifference(int sellableProductDifference) {
		this.sellableProductDifference = sellableProductDifference;
	}
	
	/**
	 * @return the requestedAmount
	 */
	public int getRequestedAmount() {
		return requestedAmount;
	}

	/**
	 * @param int requestedAmount
	 */
	public void setRequestedAmount(int requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	/**
	 * @return the resultString
	 */
	public String getResultString() {
		return resultString;
	}

	/**
	 * @param resultString the resultString to set
	 */
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	/**
	 * @return the paid
	 */
	public boolean getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	/**
	 * @return int transactionMemberId
	 */
	public int getTransactionMemberId () {
		return transactionMemberId;
	} //
	
	/**
	 * @return int orderAmount
	 */
	public int getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @return int transactionProductId
	 */
	public int getTransactionProductId() {
		return transactionProductId;
	}

	/**
	 * @return double transactionProductPrice
	 */
	public double getTransactionProductPrice() {
		return transactionProductPrice;
	}

	/**
	 * @return int transactionProductQuantity
	 */
	public int getTransactionProductQuantity() {
		return transactionProductQuantity;
	}

	/**
	 * @return int difference
	 */
	public int getTransactionProductDifference() {
		return transactionProductDifference;
	}

	/**
	 * @return double total cost
	 */
	public static double getTransactionProductTotalCost() {
		return transactionProductTotalCost;
	}

	/**
	 * @return int requested amount
	 */
	public int getTransactionProductRequestedAmount() {
		return transactionProductRequestedAmount;
	}

	/**
	 * @return String transactionProductName
	 */
	public String getTransactionProductName() {
		return transactionProductName;
	}

	/** 
	 * @return Calendar transactionDate
	 */
	public Calendar getTransactionDate () {
		return transactionDate;
	}
	
	/**
	 * @return String date
	 */
	public String printTransactionDate () {
		String string = transactionDate.get(java.util.Calendar.MONTH) 
				+ "/" + transactionDate.get(Calendar.DATE) 
				+ "/" + transactionDate.get(Calendar.YEAR);
		return string;
	}
	
	// Setters
	
	/**
	 * @param int transactionMemberId
	 */
	public void setTransactionMemberId (int transactionMemberId) {
		this.transactionMemberId = transactionMemberId;
	} //
	
	/**
	 * @param int transactionProductId
	 */
	public void setTransactionProductId(int transactionProductId) {
		this.transactionProductId = transactionProductId;
	}

	/**
	 * @param double transactionProductPrice
	 */
	public void setTransactionProductPrice(double transactionProductPrice) {
		this.transactionProductPrice = transactionProductPrice;
	}

	/**
	 * @param int transactionProductQuantity
	 */
	public void setTransactionProductQuantity(int transactionProductQuantity) {
		this.transactionProductQuantity = transactionProductQuantity;
	}

	/**
	 * @param int transactionProductDifference
	 */
	public void setTransactionProductDifference(int transactionProductDifference) {
		this.transactionProductDifference = transactionProductDifference;
	}

	/**
	 * @param double transactionProductTotalCost
	 */
	public static void setTransactionProductTotalCost(double transactionProductTotalCost) {
		DataHandOff.transactionProductTotalCost = transactionProductTotalCost;
	}

	/**
	 * @param double transactionProductRequestedAmount
	 */
	public void setTransactionProductrequestedAmount(int transactionProductRequestedAmount) {
		this.transactionProductRequestedAmount = transactionProductRequestedAmount;
	}

	/**
	 * @param String transactionProductName
	 */
	public void setTransactionProductName(String transactionProductName) {
		this.transactionProductName = transactionProductName;
	}

	/**
	 * @param  Calendar transactionDate
	 */
	public void setTransactionDate (Calendar transactionDate) {
		this.transactionDate = transactionDate;
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
	 * @return the autoTester
	 */
	public boolean isAutoTester() {
		return autoTester;
	}

	/**
	 * @param autoTester the autoTester to set
	 */
	public void setAutoTester(boolean autoTester) {
		this.autoTester = autoTester;
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		
		productId = 0;
		productName = "Null";
		productPrice = 0;
		productMinimumReorderLevel = 0;
		productInStock = 0;
		memberId = 0;
		memberName = "Null";
		memberPhone = "Null";
		memberAddress = "Null";
		memberFee = 0;
		sellableProductId = 0;
		sellableProductName = null;
		sellableProductPrice = 0;
		sellableProductQuantity = 0;
		sellableProductDifference = 0;
		sellableProductTotalCost = 0;
		resultString = null;
		requestedAmount = 0;
		stringDate = null;
		autoTester = true;

	}
}


