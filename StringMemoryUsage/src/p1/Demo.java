package p1;

public class Demo {

	public static void main(String[] args) {
//		String name1 = "Alan";
//		name1 = "Alan2"; // makes new object to replace
//		String name4 = new String("Alan"); // different object
//		System.out.println(name4.contentEquals(name1));

		StringBuilder sb1 = new StringBuilder("Alan"); // editing a String doesn't make new obj
		System.out.println(sb1.reverse());
	}

}
