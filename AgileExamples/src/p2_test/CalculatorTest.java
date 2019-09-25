package p2_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testTimes() {
		Calculator c = new Calculator(5);
		c.times(2);
		assertEquals(10, c.getAmount(), 0.01);
		
	}

	@Test
	void testDivide() {
		Calculator c = new Calculator(5);
		c.divide(0);
		assertNotEquals(5, c.getAmount(), 0.0000001);
		
	}

	@Test
	void testRoundAmount() {
		Calculator c = new Calculator(1234.12345);
		System.out.println(c.roundAmount(1234.12345));
		assertEquals(1,234.12, c.roundAmount(1234.12345));
		
	}

	@Test
	void testDenomIsZero() {
		Calculator c = new Calculator(5);
		assertTrue(c.denomIsZero(5));
		
	}
}
