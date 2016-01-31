package game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {
	
	private Rule rule;

	@Before
	public void setUp() {
		rule = new Rule(1, 5, 4);
	}

	@Test
	public void testRule() {
		rule = new Rule(1, 5, 4);
		assertEquals(Integer.valueOf(1), rule.getRuleNumber());
		assertEquals(Integer.valueOf(5), rule.getNumberOfBowlers());
		assertEquals(Integer.valueOf(4), rule.getMaxOversAllowed());
	}

	@Test
	public void testGetRuleNumber() {
		assertEquals(Integer.valueOf(1), rule.getRuleNumber());
	}

	@Test
	public void testSetRuleNumber() {
		assertEquals(Integer.valueOf(1), rule.getRuleNumber());
		rule.setRuleNumber(2);
		assertEquals(Integer.valueOf(2), rule.getRuleNumber());
	}

	@Test
	public void testGetNumberOfBowlers() {
		assertEquals(Integer.valueOf(5), rule.getNumberOfBowlers());
	}

	@Test
	public void testSetNumberOfBowlers() {
		assertEquals(Integer.valueOf(5), rule.getNumberOfBowlers());
		rule.setNumberOfBowlers(4);
		assertEquals(Integer.valueOf(4), rule.getNumberOfBowlers());
	}

	@Test
	public void testGetMaxOversAllowed() {
		assertEquals(Integer.valueOf(4), rule.getMaxOversAllowed());
	}

	@Test
	public void testSetMaxOversAllowed() {
		assertEquals(Integer.valueOf(4), rule.getMaxOversAllowed());
		rule.setMaxOversAllowed(5);
		assertEquals(Integer.valueOf(5), rule.getMaxOversAllowed());
	}

	@Test
	public void testCompareTo() {
		Rule rule1 = new Rule(1, 2 ,3);
		assertEquals(-1, rule.compareTo(rule1));
		
		rule1 = new Rule(1, 5, 4);
		assertEquals(0, rule.compareTo(rule1));
	}

}
