package ngetehyuk;

public class MilkTea extends Drink {
	
	private String milkType;

	public MilkTea(String drinkID, String drinkName, String drinkType, int drinkPrice, String milkType) {
		super(drinkID, drinkName, drinkType, drinkPrice, 3500);
		// TODO Auto-generated constructor stub
		this.milkType = milkType;
	}

	public String getMilkType() {
		return milkType;
	}

	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}
	
	

}
