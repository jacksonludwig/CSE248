package p1;

import java.util.Scanner;

public class Calculator {

	private Scanner input;
	private double amount;

	public Calculator(double amount) {
		this.amount = amount;
		input = new Scanner(System.in);

	}

	public void times(double multiplier) {
		amount *= multiplier;
	}

	public double getAmount() {
		return amount;
	}

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
