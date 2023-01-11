package ngetehyuk;

abstract public class Drink {
	private String drinkID;
	private String drinkName;
	private String drinkType;
	private int drinkPrice;
	private int tax;
	
	public Drink(String drinkID, String drinkName, String drinkType, int drinkPrice, int tax) {
		super();
		this.drinkID = drinkID;
		this.drinkName = drinkName;
		this.drinkType = drinkType;
		this.drinkPrice = drinkPrice;
		this.tax = tax;
	}

	public String getDrinkID() {
		return drinkID;
	}

	public void setDrinkID(String drinkID) {
		this.drinkID = drinkID;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}

	public int getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(int drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	
	public String getMilkType() {
		return "-";
	}
	
	public String getSugarType() {
		return "-";
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}
	
	
}
