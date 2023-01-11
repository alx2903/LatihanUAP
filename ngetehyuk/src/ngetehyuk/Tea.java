package ngetehyuk;

public class Tea extends Drink {
	
	private String sugarType;

	public Tea(String drinkID, String drinkName, String drinkType, int drinkPrice, String sugarType) {
		super(drinkID, drinkName, drinkType, drinkPrice, 2000);
		// TODO Auto-generated constructor stub
		this.sugarType = sugarType;
	}

	public String getSugarType() {
		return sugarType;
	}

	public void setSugarType(String sugarType) {
		this.sugarType = sugarType;
	}
	
	

}
