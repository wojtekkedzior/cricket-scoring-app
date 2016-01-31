package common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.GameSettings;
import game.Player;
import game.Rule;
import game.Team;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import beans.BattingOverBean;

public class RuleExecutorTest {
	
	private int BOWLER_1_ID = 1; //under the limit
	private int BOWLER_2_ID = 2; //on the limit i.e. Bowled Out
	
	private int BOWLER_3_ID = 3; 
	private int BOWLER_4_ID = 4;
	private int BOWLER_5_ID = 5;

	private Team bowlingTeam = new Team(1);
	private Player bowler1 = new Player("bowler1", BOWLER_1_ID);
	private Player bowler2 = new Player("bowler2", BOWLER_2_ID);
	private Player bowler3 = new Player("bowler3", BOWLER_3_ID);
	private Player bowler4 = new Player("bowler4", BOWLER_4_ID);
	private Player bowler5 = new Player("bowler5", BOWLER_5_ID);
	
	private Team battingTeam = new Team(1);
	private Player batsman1 = new Player("batsman1", 1);
	private Player batsman2 = new Player("batsman2", 2);
	
	private RuleExecutor ruleExecutor;
	private GameSettings gameSettings;
	
	private List<Rule> rules;
	
	@Before
	public void setUp() throws Exception {
		bowlingTeam.addPlayer(bowler1);
		bowlingTeam.addPlayer(bowler2);

		battingTeam.addPlayer(batsman1);
		battingTeam.addPlayer(batsman2);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule(1, 1, 1));
		
		ruleExecutor = new RuleExecutor(rules, bowlingTeam, battingTeam);
	}
	
	@Test
	public void testTwentyTwent() {
		gameSettings = new GameSettings(GameType.TwentyTwenty);
		ruleExecutor = new RuleExecutor(gameSettings.getRules(), bowlingTeam, battingTeam);
		
		//under limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(1, BOWLER_1_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(2, BOWLER_1_ID));
		
		//on the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(3, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(4, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(5, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(6, BOWLER_2_ID));
		
		//over the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(7, BOWLER_3_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(8, BOWLER_3_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(9, BOWLER_3_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(10, BOWLER_3_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(11, BOWLER_3_ID));
		
		ruleExecutor.executeRules();
		
		List<Player> availableBowlers = bowlingTeam.getBowlerNamesWhoCanBowl();
		ArrayList<String> availableBowlerNames = new ArrayList<String>();
		
		for (Player player : availableBowlers) {
			availableBowlerNames.add(player.getName());
		}
		
		assertEquals(1, availableBowlerNames.size());
		assertEquals("bowler1", (availableBowlerNames.get(0)));
	} 
	
	@Test
	public void testMultipleBowlers() {
		gameSettings = new GameSettings(GameType.TwentyTwenty);
		ruleExecutor = new RuleExecutor(gameSettings.getRules(), bowlingTeam, battingTeam);
		
		//under limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(1, BOWLER_1_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(2, BOWLER_1_ID));
		
		//on the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(3, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(4, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(5, BOWLER_2_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(6, BOWLER_2_ID));

		bowlingTeam.addPlayer(bowler3); //one over - ie Under the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(7, BOWLER_3_ID));
		
		bowlingTeam.addPlayer(bowler4); //0 overs - ie Under the limit
		
		bowlingTeam.addPlayer(bowler5); //on the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(8, BOWLER_5_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(9, BOWLER_5_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(10, BOWLER_5_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(11, BOWLER_5_ID));
		
		ruleExecutor.executeRules();
		
		List<Player> availableBowlers = bowlingTeam.getBowlerNamesWhoCanBowl();
		ArrayList<String> availableBowlerNames = new ArrayList<String>();
		
		for (Player player : availableBowlers) {
			availableBowlerNames.add(player.getName());
		}
		
		assertEquals(3, availableBowlerNames.size());

		assertEquals("bowler1", (availableBowlerNames.get(0)));
		assertEquals("bowler3", (availableBowlerNames.get(1)));
		assertEquals("bowler4", (availableBowlerNames.get(2)));
	}

	@Test
	public void testGetRuleViolations() {
		ruleExecutor = new RuleExecutor(rules, bowlingTeam, battingTeam);
		
		//over the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(1, BOWLER_1_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(2, BOWLER_2_ID));
		
		ruleExecutor.executeRules();
		assertFalse(ruleExecutor.getRuleViolations().isEmpty());
		
		List<Player> availableBowlers = bowlingTeam.getBowlerNamesWhoCanBowl();
		assertTrue(availableBowlers.isEmpty());
		
		assertEquals(Integer.valueOf(2), ruleExecutor.getRuleViolations().get(1));
	}
	
		
	@Test
	public void testExecutorWithBowledOutBowlers() {
		ruleExecutor = new RuleExecutor(rules, bowlingTeam, battingTeam);
		
		//over the limit
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(1, BOWLER_1_ID));
		batsman1.getBattingScore().addBattingOver(new BattingOverBean(2, BOWLER_2_ID));
		
		bowler2.setBowlingStatus(BowlingStatus.BowledOut);

		ruleExecutor.executeRules();
		assertEquals(BowlingStatus.BowledOut, bowler1.getBowlingStatus());

		assertFalse(ruleExecutor.getRuleViolations().isEmpty());
		
		List<Player> availableBowlers = bowlingTeam.getBowlerNamesWhoCanBowl();
		assertTrue(availableBowlers.isEmpty());
		assertEquals(Integer.valueOf(1), ruleExecutor.getRuleViolations().get(1));
	}
}
