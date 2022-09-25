package Systemutveckling.Java.Labb6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class CurrencyConverterMain {

	private static double amount;
	private static String fromUnit;
	private static String toUnit;

	private static ArrayList<String> currencys = new ArrayList<String>(Arrays.asList("USD", "GBP", "EUR"));

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		int choice1 = 0;
		int choice2 = 0;
		boolean exitProgram = false;

		System.out.println("Welcome to money converter, what would to like to do? ");

		while (!exitProgram) {
			printMenu();
			choice1 = sc.nextInt();
			switch (choice1) {

			case 1:
				fromUnit = "USD";
				printConvert("USD");

				choice2 = sc.nextInt();
				toUnit = convertToUnit(currencys, fromUnit, choice2);
				printAmount();
				amount = sc.nextInt();
				handleConvert(fromUnit, toUnit, amount);

				break;

			case 2:
				fromUnit = "GBP";
				printConvert("GBP");

				choice2 = sc.nextInt();
				toUnit = convertToUnit(currencys, fromUnit, choice2);
				printAmount();
				amount = sc.nextInt();
				handleConvert(fromUnit, toUnit, amount);

				break;
			case 3:

				fromUnit = "EUR";
				printConvert("EUR");

				choice2 = sc.nextInt();
				toUnit = convertToUnit(currencys, fromUnit, choice2);
				printAmount();
				amount = sc.nextInt();
				handleConvert(fromUnit, toUnit, amount);
				break;
			
			case 5:
				printConvertedHistory();	
				pressEnterToContinue();
				break;

			case 8:
				System.out.println("Goodbye");
				sc.close();
				exitProgram = !exitProgram;
				break;

			default:
				System.out.println("Invalid input, try agin");
				printMenu();
			}

		}
	}

	private static void printMenu() {

		System.out.println("Choose by pressing a number.");
		System.out.println("---------------------------");
		for (int i = 0; i < currencys.size(); i++) {
			System.out.println("(" + (i + 1) + ") " + "Convert from " + currencys.get(i));
		}
		System.out.println("( )");
		System.out.println("(5) - Show history");
		System.out.println("( )");
		System.out.println("(8) - Exit");
	}

	private static void printConvert(String currency) {

		System.out.println("Convert money from " + currency + " to: ");

		ArrayList<String> tempArr = new ArrayList<String>(currencys);
		tempArr = removeUnit(tempArr, currency);

		for (int i = 0; i < tempArr.size(); i++) {
			System.out.println("(" + (i + 1) + ") " + "Convert to " + tempArr.get(i));
		}

	}

	private static String convertToUnit(ArrayList<String> currencys, String fromUnit, int n) {

		ArrayList<String> tempArr = new ArrayList<String>(currencys);
		tempArr = removeUnit(tempArr, fromUnit);

		String payload = null;

		for (int i = 0; i < tempArr.size(); i++) {
			if ((i + 1) == n) {
				payload = tempArr.get(i);
			}
		}

		return payload;
	}

	private static ArrayList<String> removeUnit(ArrayList<String> allCurrencys, String currency) {

		ArrayList<String> payload = allCurrencys;

		for (int i = 0; i < payload.size(); i++) {
			if (payload.get(i) == currency) {
				payload.remove(i);
			}
		}
		return payload;
	}

	private static void printAmount() {
		System.out.println("Amount to convert: ");
	}

	private static void handleConvert(String fromUnit, String toUnit, double amount) {

		CurrencyConverter converter = new CurrencyConverter();

		Money money = Money.parse(fromUnit + "" + amount);
		CurrencyUnit from = CurrencyUnit.of(fromUnit);
		CurrencyUnit to = CurrencyUnit.of(toUnit);

		Money convertedMoney = converter.convert(money, from, to);
		
		saveConvertToFile(money, convertedMoney);
		
		System.out.println(money + " = " + convertedMoney);
		System.out.println("---------------");

	}

	private static void saveConvertToFile(Money moneyFrom, Money moneyTo) {

		String file = "src/test/resources/test.txt";
		String data = moneyFrom.toString() + " = " + moneyTo.toString(); 

		try {
			FileWriter wr = new FileWriter(file, true);
			wr.write(data +"\n");
			wr.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} 
	}
	
	private static void printConvertedHistory() {

		ArrayList<String> history = new ArrayList<String>();
		String[] his;
		
		Scanner sc;
		try {
			sc = new Scanner(new File("src/test/resources/test.txt"));
			while(sc.hasNextLine()) {
//				System.out.println(sc.nextLine());
				history.add(sc.nextLine());
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(history);
		
		history.sort(String::compareTo);
		System.out.println(history);

		
	}
	private static void pressEnterToContinue() {
		System.out.println("Press <Enter> to continue!");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
