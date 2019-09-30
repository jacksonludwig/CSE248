package p1;

import java.util.Scanner;

/**
 * 
 * @author
 * @version 
 * 
 * @see
 * 
 */

public class Calculator {

	private Scanner input;
	private double amount;

	/**
	 * 
	 * @param amount
	 */
	public Calculator(double amount) {
		this.amount = amount;
		input = new Scanner(System.in);

	}
	
	/**
	 * 
	 * @param multiplier
	 */
	public void times(double multiplier) {
		amount *= multiplier;
	}

	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public String roundAmount(double d) {
		return String.format("%f,.2", d);
	}

	public void divide(double denom) {
		if (denomIsZero(denom)) {
			denom = getNewDenom();
		}
		amount /= denom;
	}

	public boolean denomIsZero(double denom) {
		return denom == 0.0;
	}

	public double getNewDenom() {
		double denom = 0;
		while (denom == 0) {
			System.out.println("Enter a new Denom");
			denom = input.nextDouble();
		}
		return denom;
	}

}
