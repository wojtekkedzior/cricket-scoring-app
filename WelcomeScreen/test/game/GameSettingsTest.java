package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import common.GameType;

public class GameSettingsTest {
	
	private GameSettings gameSettings;
	
	@Test
	public void testGetRulesForTwentTwenty() {
		checkRule(GameType.TwentyTwenty, 4);
	}
	
	@Test
	public void testGetRulesForFortyForty() {
		checkRule(GameType.FortyForty, 10);
	}
	
	@Test 
	public void testAddToRules() {
		gameSettings = new GameSettings(GameType.Custom);
		
		assertTrue(gameSettings.getRules().isEmpty());
		
		gameSettings.addToRules(new Rule (1, 1, 1));
		gameSettings.addToRules(new Rule (2, 1, 1));
		gameSettings.addToRules(new Rule (3, 1, 1));
	
		assertEquals(3, gameSettings.getRules().size());
	}
	
	@Test 
	public void testAddToRulesThrowsExceptions() {
		gameSettings = new GameSettings(GameType.Custom);
		
		assertTrue(gameSettings.getRules().isEmpty());
		
		gameSettings.addToRules(new Rule (1, 1, 1));
		assertEquals(1, gameSettings.getRules().size());
		
		try {
			gameSettings.addToRules(new Rule (1, 1, 1));
			fail();
		} catch (Exception e) {
			assertEquals("you cant have the same rule number for two rules", e.getMessage());
		}
	}
	
	@Test
	public void testGetGameType() {
		gameSettings = new GameSettings(GameType.Custom);
		assertEquals(GameType.Custom, gameSettings.getGameType());
	}
	
	private void checkRule(GameType gameType, int maxOversAllowed) {
		GameSettings gameSettings = new GameSettings(gameType);
		List<Rule> rules = gameSettings.getRules();
		assertTrue(rules.size() == 1);
		
		assertTrue(rules.get(0).getRuleNumber() == 1);
		assertTrue(rules.get(0).getNumberOfBowlers() == 11);
		assertTrue(rules.get(0).getMaxOversAllowed() == maxOversAllowed);
	}
	
	@Test
	public void testGetMaxOvers() {
		gameSettings = new GameSettings(GameType.Custom);
		assertEquals(1, gameSettings.getMaxOvers());
		
		gameSettings = new GameSettings(GameType.FortyForty);
		assertEquals(40, gameSettings.getMaxOvers());
		
		gameSettings = new GameSettings(GameType.TwentyTwenty);
		assertEquals(20, gameSettings.getMaxOvers());
		
		
		gameSettings = new GameSettings(GameType.Other);
		assertEquals(0, gameSettings.getMaxOvers());
		
	}
}