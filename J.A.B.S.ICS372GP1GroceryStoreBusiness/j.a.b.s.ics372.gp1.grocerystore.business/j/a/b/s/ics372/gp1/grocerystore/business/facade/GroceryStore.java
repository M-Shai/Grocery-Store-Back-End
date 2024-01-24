package j.a.b.s.ics372.gp1.grocerystore.business.facade;

/**
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import j.a.b.s.ics372.gp1.grocerystore.business.collections.MemberList;
import j.a.b.s.ics372.gp1.grocerystore.business.collections.OrderList;
import j.a.b.s.ics372.gp1.grocerystore.business.collections.ProductList;
import j.a.b.s.ics372.gp1.grocerystore.business.collections.ShoppingCart;
import j.a.b.s.ics372.gp1.grocerystore.business.collections.TransactionList;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Member;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Order;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Product;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.SellableProduct;
import j.a.b.s.ics372.gp1.grocerystore.business.iterators.SafeMemberListIterator;
import j.a.b.s.ics372.gp1.grocerystore.business.iterators.SafeOrderListIterator;
import j.a.b.s.ics372.gp1.grocerystore.business.iterators.SafeProductListIterator;
import j.a.b.s.ics372.gp1.grocerystore.business.test.AutomatedTester;

/**
 * @author Brahma Dathan
 * @modified by Say Vang
 * 
 * Facade GroceryStore
 * Implements store business
 * 
 * updated 3/17/2023
 */
public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList productList = ProductList.instance();
	private MemberList members = MemberList.instance();
	private OrderList orders = OrderList.instance();
	private static GroceryStore singletonGroceryStore;

	/**
	 * Private constructor
	 * Access through instance
	 * 
	 */
	private GroceryStore() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (singletonGroceryStore == null) {
			return singletonGroceryStore = new GroceryStore();
		} else {
			return singletonGroceryStore;
		}
	}

	

	/**
	 * @modified by Abshir
	 * 
	 * Organizes the operations for adding a product
	 * 
	 * @param int  productId
	 * @param String name
	 * @param double book id
	 * @return Product obj
	 */
	@SuppressWarnings("unchecked")
	public Result addProduct(Request request) {
		Result result = new Result();
		// making Product
		Product product = new Product(request.getProductId(), 
				request.getProductName(), request.getProductPrice(),
				request.getProductMinimumReorderLevel(), 
				request.getProductInStock());
		if (productList.insertProduct(product)) {
			result.setResultCode(Result.PRODUCT_ADDED);
			result.setProductFields(product);
			return result;
		}
		result.setResultCode(Result.PRODUCT_NOT_ADDED);
		return result;
	}

	/**
	 * @modified by Say Vang
	 * 
	 * Operations for adding a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), 
				request.getMemberAddress(), request.getMemberPhone(), 
				request.getDate(), request.getMemberFee());
		if (members.addMember(member)) {
			result.setResultCode(Result.MEMBER_ADDED);
			result.setMemberFields(member);
			return result;
		}
		else {
			result.setResultCode(Result.MEMBER_NOT_ADDED);
			return result;
		}
	}

	/**
	 * @modified by Say Vang
	 * 
	 * Searches for a given member
	 * 
	 * @param Request Object
	 * @return Result Object
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveMember(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			return result;
		} else {
			result.setResultCode(Result.MEMBER_FOUND);
			result.setMemberFields(member);
			return result;
		}
	}
	
	/**
	 * @modified by Say Vang
	 * 
	 * Removes a member
	 * 
	 * @param Request object
	 * @return Result object
	 */
	@SuppressWarnings("unchecked")
	public Result removeMember(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if(members.removeMember(request.getMemberId())){
			//System.out.println("MEMBER_REMOVED = " + 
					//Result.MEMBER_REMOVED);
			result.setResultCode(Result.MEMBER_REMOVED);
			result.setMemberFields(member);
			return result;
		}
		else {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			return result;
		}
	}


	/**
	 * Returns an iterator to productList. Iterator is a copy, 
	 * returns only copies
	 * of the product list
	 * 
	 * @return an Iterator to Result - only the Product fields are 
	 * valid. modified
	 * by Abshir
	 */
	public Iterator<Result> getProductList() {
		return new SafeProductListIterator(productList.iterator());
	} // end of getProductList

	/**
	 * Removes a specific product from the catalog/productList
	 * 
	 * @param request object of the Request
	 * @return a result object modified by Abshir
	 */
	@SuppressWarnings("unchecked")
	public Result removeProduct(Request request) {
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		if (product.getProductId() == 0) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		result.setProductFields(product);

		if (productList.removeProduct(request.getProductId())) {
			result.setResultCode(Result.SUCCESS);
			return result;
		} else {
			result.setResultCode(Result.ERROR);
			return result;
		}
	}// end of removeProduct method

	
	/**
	 * Returns an iterator to Member info. The Iterator returned is a 
	 * safe one, in the sense that only copies of the Member 
	 * fields are assembled into the objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
	public Iterator<Result> getMembers() {
		return new SafeMemberListIterator(members.iterator());
	}

	/*
	 * this method seems doing the same thing as getProductList() 
	 * method except ithas a paramater
	 * 
	 * @param request object of Request.
	 * 
	 * @modified by Abshir
	 * 
	 * @ return objectList iff the product existed or null otherwise
	 * 
	 */
	public Iterator<Result> getProducts(Request request) {
		Product product = productList.searchProduct(request.getProductId());
		if (product == null) {
			return null;
		} else {
			return new SafeProductListIterator(productList.iterator());
		}
	}
	
	/*
	 * Retrieves a specific product
	 * 
	 * @param a request object of the Request
	 * 
	 * @return result object modified by Abshir
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Result retreiveProduct(Request request) {
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		if (product.getProductId() == 0) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			//System.out.println(
					//"Line 114, GS.addProduct.products.addProduct(product): "
					//+ "ResultCode = " + result.getResultCode());
		} else {
			result.setResultCode(Result.PRODUCT_FOUND);
			result.setProductFields(product);
		}
		return result;
		
	} // end of retrieveProduct method
	
	/*
	 * Changes the old price of a product into new price
	 * 
	 * @param request, result Objects
	 * 
	 * @return a new result object with new price attribute
	 * 
	 * @author Abshir
	 */
	public Result changeProductPrice(Request request) {
		Result resultLocal = new Result();
		//get the product from the list;
		Product newProduct = productList.searchProduct(request.getProductId());
		// update the price of the product
		if(newProduct.setPrice(request.getProductPrice())) {
			resultLocal.setResultCode(Result.PRICE_CHANGED);
		}
		else {
			resultLocal.setResultCode(Result.PRICE_NOT_CHANGED);
		}
		
		resultLocal.setProductPrice(newProduct.getPrice());
		return resultLocal;

	}// end of changeProductPrice class
	
	
	 
	@Override
	public String toString() {
		return productList + "\n" + members;
	}
	
	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * creates a sellableProduct
	 * adds sellableProduct to a shopping cart
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	public Result addToShoppingCart(Request request) {
		Member member = members.search(request.getMemberId());
		Result result = new Result();
		Product product = productList.searchProduct(request.getProductId());
		SellableProduct sellableProduct = 
				new SellableProduct(product.getProductName(), 
						product.getProductId(), product.getPrice(), 0);
		int requestedAmount = request.getRequestedAmount();
		int difference = processOrder(product, requestedAmount);
		ShoppingCart currentCart = member.getShoppingCart();
		if (difference >= 0) {
			sellableProduct.setQuantity(requestedAmount);
			if(currentCart.add(sellableProduct)) {
				result.setResultCode(Result.PRODUCT_ADDED);
			}
		}
		else if (difference < 0 && (difference + requestedAmount) >= 0) {
			sellableProduct.setQuantity(difference + requestedAmount);
			if(currentCart.add(sellableProduct)) {
				result.setResultCode(Result.PARTIAL_PRODUCT_ADDED);
			}
		}
		
		else {
			sellableProduct.setQuantity(0);
			if(currentCart.add(sellableProduct)) {
				result.setResultCode(Result.PRODUCT_NOT_ADDED);
			}
			
		}
		result.setResultString(currentCart.toString());
		return result;
	}
	
	/**
	 * @author Say Chaleon Vang
	 * 
	 * @param product
	 * @param requestedAmount
	 * @return int difference
	 */
	@SuppressWarnings("exports")
	public int processOrder(Product product, int requestedAmount) {
		int difference = (product.getProductStock() - requestedAmount);
		if (difference >= product.getMinimumReorderLevel()) {
			product.setProductStock(difference);
		}
		else {
			//System.out.println("G.S proOrd line 368 diff = " + 
					//difference + " : min = " + 
					//product.getMinimumReorderLevel());
			Order orderProduct = new Order(product);
			orders.insertOrder(orderProduct);
			if(difference > 0) {
				product.setProductStock(difference);
			}
			else {
				product.setProductStock(0);
			}
		}
		return difference;
	}
	
	/**
	 * @modified by Say Vang and Jeffrey
	 * 
	 * process the orderList update the
	 * product stock
	 * 
	 * @return Result obj
	 */
	public Result processShipment() {
		Result result = new Result();
		StringBuilder string = new StringBuilder("Order shipment processed");
		for(Order order: orders) {
			Product product = productList.searchProduct(order.getId());
			product.setProductStock(product.getProductStock() + 
					order.getAmount());
			string.append(product.shipment());
		}
		if(orders.empty()) {
			result.setResultCode(Result.SHIPMENT_PROCESSED);
		}
		else {
			result.setResultCode(Result.SHIPMENT_NOT_PROCESSED);
		}
			
		string.append("\nOrder list is empty, end of shipment");
		result.setResultString(string.toString());
		return result;
	}
	
	
	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	@SuppressWarnings({ })
	public Result getShoppingCart(Request request) {
		ShoppingCart currentCart = (members.search
				(request.getMemberId())).getShoppingCart();
		TransactionList transactionList = (members.search
				(request.getMemberId())).getTransactionList();
		transactionList.add(currentCart);
		Result result = new Result();
		result.setResultString(currentCart.toString());
		return result;
	}
	
	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param Request obj
	 * @return Result obj
	 */
	public Result checkOut(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		ShoppingCart currentCart = member.getShoppingCart();
		TransactionList transactionList = member.getTransactionList();
		Calendar calendar = Calendar.getInstance();
		currentCart.setCheckOutDate(calendar.get(Calendar.MONTH), 
				calendar.get(Calendar.DAY_OF_MONTH), 
				calendar.get(Calendar.YEAR));
		currentCart.setMemberId(request.getMemberId());
		transactionList.addTransaction(currentCart);
		if(currentCart.getTotalPrice() < .01) {
			//System.out.println("if");
			result.setResultString(null);
			result.setResultCode(Result.SHOPPING_CART_PENDING);
		}
		else {
			//System.out.println("else");
			result.setResultString(String.format("Shopping cart has been " + 
					"paid in full\n" + " - %.2f", 
					currentCart.getTotalPrice()));
			ShoppingCart newCart = new ShoppingCart();
			result.setResultCode(Result.SHOPPING_CART_PAID);
			member.setShoppingCart(newCart);
		}
		return result;
	}
	
	/**
	 * @author Say Chaleon Vang and Banji
	 * 
	 * @param request
	 * @return
	 */
	public Result printTransaction(Request request) {
		Member member = (members.search(request.getMemberId()));
		TransactionList list = member.getTransactionList();
		Result result = new Result();
		result.setResultString(list.getTransactionsOnDate
				(request.getStringDate()));
		return result;
	}
	
	/**
	 * @author 
	 * 
	 * @return
	 */
	
	public Iterator<Result> getOrderList() {
		return new SafeOrderListIterator(orders.iterator());
	}
	
	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		/*buffer reader does not serialize*/
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = 
						new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	
	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	
	public void runAutomatedTester(Request request) {
		AutomatedTester autoTester = new AutomatedTester();
		Result result = new Result();
		if(!request.isAutoTester()) {
			if (yesOrNo("Test addMember()?")) {
				result = autoTester.testAddMember(request);
			}
			if (yesOrNo("Test removeMember()?")) {
				System.out.println("memberId = " + result.getMemberId());
				request.setMemberId(result.getMemberId());
				autoTester.testRemoveMember(request);
			}
			if (yesOrNo("Test addProduct?")) {
				autoTester.testAddProduct(request);
			}
			if (yesOrNo("Test changeProductPrice()?")) {
				autoTester.testChangeProductPrice(request);
			}
			if (yesOrNo("Test checkOut()?")) {
				autoTester.testCheckOut(request);
			}
			if (yesOrNo("Test processShipment()?")) {
				autoTester.testProcessShipment(request);
			}
			System.out.println("\n*****Passed all manual tests\n");
		}
		else {
			AutomatedTester.main(null);
		}
		System.out.println("\n*****Passed all auto tests\n");
	}
	
	/**
	 * Retrieves GroceryStore from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			singletonGroceryStore = (GroceryStore) input.readObject();
			Member.retrieve(input);
			return singletonGroceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Serializes the GroceryStore object
	 * 
	 * @return boolean 
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(singletonGroceryStore);
			System.out.println("Member save is " + Member.save(output));
			//Member.save(output);
			//Product.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
