package ngetehyuk;

public class Transaction {
	private String transactionID;
	private String drinkId;
	private String buyerName;
	private int quantity;
	
	public Transaction(String transactionID, String drinkId, String buyerName, int quantity) {
		super();
		this.transactionID = transactionID;
		this.drinkId = drinkId;
		this.buyerName = buyerName;
		this.quantity = quantity;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(String drinkId) {
		this.drinkId = drinkId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
