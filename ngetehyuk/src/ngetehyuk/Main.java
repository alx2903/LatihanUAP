package ngetehyuk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	Scanner sc = new Scanner(System.in);
	Connect con = Connect.getConnection();
	
	PreparedStatement ps;
	
	public void add() {
		System.out.println("================================================================================================================================");
		ArrayList<Drink> allDrinks = getAllDrinks();
		
		System.out.printf("|%-5s|", "No");
		System.out.printf("%-10s|", "Drink ID");
		System.out.printf("%-25s|", "Drink Name");
		System.out.printf("%-20s|", "Drink Type");
		System.out.printf("%-20s|", "Drink Price");
		System.out.printf("%-20s|", "Sugar Type");
		System.out.printf("%-20s|\n", "Milk Type");
		System.out.println("================================================================================================================================");
		
		for (int i = 0; i < allDrinks.size(); i++) {
			Drink drink = allDrinks.get(i);
			System.out.printf("|%-5s|", i+1);
			System.out.printf("%-10s|", drink.getDrinkID());
			System.out.printf("%-25s|", drink.getDrinkName());
			System.out.printf("%-20s|", drink.getDrinkType());
			System.out.printf("%-20s|", drink.getDrinkPrice());
			System.out.printf("%-20s|", drink.getSugarType());
			System.out.printf("%-20s|\n", drink.getMilkType());
		}
		
		System.out.println("================================================================================================================================");
		int input = 0;
		do {
			System.out.printf("Input the number of index you want to buy[1 .. %d] : ", allDrinks.size());
			input = sc.nextInt();
			sc.nextLine();
		}while(input < 1 || input > allDrinks.size());
		int quantity = 0;
		do {
			System.out.print("Input quantity [>0] : ");
			quantity = sc.nextInt();
			sc.nextLine();
		}while(quantity < 1);
		String buyerName;
		do {
			System.out.print("Input your name [must be 2 words] : ");
			buyerName = sc.nextLine();
		}while(buyerName.split(" ").length != 2);
		

		Drink chosenDrink = allDrinks.get(input - 1);
		int totalPrice = (chosenDrink.getDrinkPrice() * quantity) + chosenDrink.getTax();
		
		Transaction transaction = saveTransaction(chosenDrink.getDrinkID(), buyerName, quantity);
		
		System.out.println("===========================================");
		System.out.println("|           Detail Transaction            |");
		System.out.println("===========================================");
		System.out.printf("|%-20s:%-20s|\n", "Transaction ID", transaction.getTransactionID());
		System.out.printf("|%-20s:%-20s|\n", "Buyer Name", transaction.getBuyerName());
		System.out.printf("|%-20s:%-20s|\n", "Drink name", chosenDrink.getDrinkName());
		System.out.printf("|%-20s:%-20s|\n", "Drink Price", chosenDrink.getDrinkPrice());
		System.out.printf("|%-20s:%-20s|\n", "Drink Quantity", transaction.getQuantity());
		System.out.printf("|%-20s:%-20s|\n", "Tax", chosenDrink.getTax());
		System.out.printf("|%-20s:%-20s|\n", "Total Price", totalPrice);
		System.out.println("===========================================");
		System.out.println("");
		System.out.println("Successfully inserted new transaction!");
		

		
	}
	
	public Transaction saveTransaction(String drinkID, String buyerName, int quantity) {
		int  lastId = 0;
		ArrayList<Transaction> transactions = getAllTransactions();
		
		for (Transaction t : transactions) {
			lastId = Integer.parseInt(t.getTransactionID().substring(2));
		}
		
		String transactionID = String.format("TR%03d", lastId + 1);
		
		Transaction transaction = new Transaction(transactionID, drinkID, buyerName, quantity);
		
		ps = con.prepareStatement("INSERT INTO transaction VALUES (?,?,?,?)");
		try {
			ps.setString(1, transactionID);
			ps.setString(2, drinkID);
			ps.setString(3, buyerName);
			ps.setInt(4, quantity);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transaction;
		
	}
	

	public ArrayList<Transaction> getAllTransactions() {
		ps = con.prepareStatement("SELECT * FROM Transaction ORDER BY transactionID");
		
		ArrayList<Transaction> allTransactions = new ArrayList<>();
		
		try {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
			String transactionID = rs.getString(1);
			String drinkID = rs.getString(2);
			String buyerName = rs.getString(3);
			int quantity = rs.getInt(4);
			allTransactions.add(new Transaction(transactionID, drinkID, buyerName, quantity));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
			
		return allTransactions;
		
	}
	
	public ArrayList<Drink> getAllDrinks() {
		ps = con.prepareStatement("SELECT * FROM Tea");
		
		ArrayList<Drink> allDrinks = new ArrayList<>();
		
		try {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
			String drinkID = rs.getString(1);
			String drinkName = rs.getString(2);
			String drinkType = rs.getString(3);
			int drinkPrice = rs.getInt(4);
			String sugarType = rs.getString(5);
			allDrinks.add(new Tea(drinkID,drinkName, drinkType, drinkPrice, sugarType ));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ps = con.prepareStatement("SELECT * FROM MilkTea");
	
		try {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
			String drinkID = rs.getString(1);
			String drinkName = rs.getString(2);
			String drinkType = rs.getString(3);
			int drinkPrice = rs.getInt(4);
			String milkType = rs.getString(5);
			allDrinks.add(new MilkTea(drinkID,drinkName, drinkType, drinkPrice, milkType ));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return allDrinks;
		
	}
	
	public void view() {
		ArrayList<Transaction> allTransactions = getAllTransactions();
		
		if (allTransactions.isEmpty()) {
			System.out.println("There is no transaction...");
		} else {
			System.out.println("================================================================================================================================================================");
			System.out.printf("|%-5s|", "ID");
			System.out.printf("%-20s|", "Transaction ID");
			System.out.printf("%-25s|", "Buyer Name");
			System.out.printf("%-20s|", "Drink Name");
			System.out.printf("%-20s|", "Drink Type");
			System.out.printf("%-15s|", "Drink Price");
			System.out.printf("%-15s|", "Quantity");
			System.out.printf("%-15s|", "Tax");
			System.out.printf("%-15s|\n", "Total Price");
			System.out.println("================================================================================================================================================================");
			for (int i = 0; i < allTransactions.size(); i++) {
				Transaction t = allTransactions.get(i);
				Drink d = getDrinkByDrinkId(t.getDrinkId());
				

				int totalPrice = (d.getDrinkPrice() * t.getQuantity()) + d.getTax();
				
				System.out.printf("|%-5s|", i+1);
				System.out.printf("%-20s|", t.getTransactionID());
				System.out.printf("%-25s|", t.getBuyerName());
				System.out.printf("%-20s|", d.getDrinkName());
				System.out.printf("%-20s|", d.getDrinkType());
				System.out.printf("%-15s|", d.getDrinkPrice());
				System.out.printf("%-15s|", t.getQuantity());
				System.out.printf("%-15s|", d.getTax());
				System.out.printf("%-15s|\n", totalPrice);
			}
			System.out.println("================================================================================================================================================================");
			
		}
	}
	
	public Drink getDrinkByDrinkId(String drinkID) {
		ps = con.prepareStatement("SELECT * FROM Tea Where drinkID = ?");

		try {
			ps.setString(1, drinkID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String drinkName = rs.getString(2);
				String drinkType = rs.getString(3);
				int drinkPrice = rs.getInt(4);
				String sugarType = rs.getString(5);
				return new Tea(drinkID,drinkName, drinkType, drinkPrice, sugarType );
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ps = con.prepareStatement("SELECT * FROM MilkTea Where drinkID = ?");

		
		try {
			ps.setString(1, drinkID);
			ResultSet rs = ps.executeQuery();		
			while(rs.next()) {
				
			String drinkName = rs.getString(2);
			String drinkType = rs.getString(3);
			int drinkPrice = rs.getInt(4);
			String milkType = rs.getString(5);
			return new MilkTea(drinkID,drinkName, drinkType, drinkPrice, milkType );
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	public void delete() {
		view();
		
		ArrayList<Transaction> allTransactions = getAllTransactions();
		
		if (allTransactions.isEmpty()) {
			return;
		}
		
		int input = 0;
		do {
			System.out.printf("Input the number of index you want to delete[1 .. %d] : ", allTransactions.size());
			input = sc.nextInt();
			sc.nextLine();
		}while(input < 1 || input > allTransactions.size());
		
		Transaction transaction = allTransactions.get(input-1);
		ps = con.prepareStatement("DELETE FROM transaction WHERE transactionID = ?");
		try {
			ps.setString(1, transaction.getTransactionID());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sucessfully delete transaction!");
	}
	
	public void choice (int menu) {
		switch (menu) {
		case 1 :{
			add();
			break;
		}
		case 2:{
			view();
			break;
		}
		case 3:{
			delete();
			break;
		}
//		case 4:{
//			System.exit(0);
//			break;
//		}
		}

		
		System.out.println("\nPress any key to continue...");
		sc.nextLine();
		menu();
	}
	
	public void menu (){
		System.out.println("Ngeteh Yuk!");
		System.out.println("===========");
		System.out.println("1. Buy Tea");
		System.out.println("2. View Transaction");
		System.out.println("3. Delete Transaction");
		System.out.println("4. Exit");
		int menu;
		do {
			System.out.print(">> ");
			menu = sc.nextInt();
			sc.nextLine();
			choice(menu);
		}while(menu < 1 || menu > 4);
		
	}
	

	public Main() {
		menu();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
