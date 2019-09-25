package p1;

public class Demo {

	public static void main(String[] args) {
		Calculator calc = new Calculator(20);
		calc.times(5000);
		System.out.println(calc.getAmount());
		calc.divide(2);
		System.out.println(calc.getAmount());
	}

}
