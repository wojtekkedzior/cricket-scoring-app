package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import beans.BattingOverBean;
import beans.BattingWicketBean;

import common.BattingStyle;
import common.DismisalType;

public class BattingScoreTest {

	private BattingScore battingScore;
	private BattingOverBean over1;
	private BattingOverBean over2;

	@Before
	public void setUp() {
		battingScore = new BattingScore();
		over1 = new BattingOverBean(1, 1);
		over2 = new BattingOverBean(2, 2);

		battingScore.addBattingOver(over1);
		battingScore.addBattingOver(over2);

		assertEquals(0, battingScore.getRuns());
	}
	
	@Test
	public void testGetStrikeRate() {
		assertEquals(0.0f, battingScore.getStrikeRate(), 0);
		
		over1.addRun(0);
		assertEquals(0.0f, battingScore.getStrikeRate(), 0);
		
		over1.addRun(1);
		assertEquals(50.0f, battingScore.getStrikeRate(), 0);
		
		over1.addRun(3);
		over1.addRun(3);
		over1.addRun(3);
		assertEquals(200.0f, battingScore.getStrikeRate(), 0);
	}
	
	@Test
	public void testGetFours() {
		assertEquals(0, battingScore.getNumberOfRuns(4));
		
		over1.addRun(4);
		over1.addRun(4);
		assertEquals(2, battingScore.getNumberOfRuns(4));
	}
	
	@Test
	public void testGetSixes() {
		assertEquals(0, battingScore.getNumberOfRuns(6));
		
		over1.addRun(6);
		over1.addRun(6);
		assertEquals(2, battingScore.getNumberOfRuns(6));
	}

	@Test
	public void testGetBattingOvers() {
		assertEquals(2, battingScore.getBattingOvers().size());
	}

	@Test
	public void testGetRuns() {
		over1.addRun(1);

		over1.addRun(1);
		over1.addRun(3);
		assertEquals(5, battingScore.getRuns());
	}

	@Test
	public void testGetRunsForOver() {
		assertEquals(0, battingScore.getRunsForOver(99));
		
		assertEquals(0, battingScore.getRunsForOver(1));

		over1.addRun(1);
		assertEquals(1, battingScore.getRunsForOver(1));

		over2.addRun(10);
		assertEquals(1, battingScore.getRunsForOver(1));

		assertEquals(10, battingScore.getRunsForOver(2));
	}

	@Test
	public void testGetBallsFaced() {
		assertEquals(0, battingScore.getBallsFaced());

		over1.addRun(1);
		assertEquals(1, battingScore.getBallsFaced());

		// batsman got an extra ball
		over1.addRun(-1);
		assertEquals(2, battingScore.getBallsFaced());

		over1.addRun(3);
		assertEquals(3, battingScore.getBallsFaced());

		over2.addRun(10);

		assertEquals(4, battingScore.getBallsFaced());

		over2.addRun(10);
		over2.addRun(10);
		over2.addRun(10);
		assertEquals(7, battingScore.getBallsFaced());
	}

	@Test
	public void testGetBattingOverBean() {
		assertEquals(over1, battingScore.getBattingOverBean(1, 1));
		
		BattingOverBean battingOverBean = battingScore.getBattingOverBean(3, 1);
		assertTrue(battingOverBean.getOverNumber() == 3);
	}

	@Test
	public void testRunsOfBowler() {
		assertEquals(0, battingScore.getRunsOfBowler(1));
		assertEquals(0, battingScore.getRunsOfBowler(2));
		
		over1.addRun(1);

		over2.addRun(1);
		over2.addRun(3);

		assertEquals(1, battingScore.getRunsForOver(1));
		assertEquals(4, battingScore.getRunsForOver(2));
	}

	@Test
	public void testGetNumberOfOversFromBowler() {
		BattingScore battingScore = new BattingScore();
		BattingOverBean over1 = new BattingOverBean(1, 1);
		BattingOverBean over2 = new BattingOverBean(2, 1);

		BattingOverBean over3 = new BattingOverBean(3, 2);
		BattingOverBean over4 = new BattingOverBean(4, 2);

		Map<Integer, Integer> numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(1);
		assertEquals(0, numberOfOversFromBowler.size());

		battingScore.addBattingOver(over1);

		numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(1);
		assertEquals(1, numberOfOversFromBowler.size());
		assertTrue(numberOfOversFromBowler.containsKey(1));

		numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(2);
		assertEquals(0, numberOfOversFromBowler.size());

		numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(3);
		assertEquals(0, numberOfOversFromBowler.size());

		battingScore.addBattingOver(over2);
		battingScore.addBattingOver(over3);
		battingScore.addBattingOver(over4);

		numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(1);
		assertEquals(2, numberOfOversFromBowler.size());
		assertTrue(numberOfOversFromBowler.containsKey(1));
		assertTrue(numberOfOversFromBowler.containsKey(2));

		numberOfOversFromBowler = battingScore.getNumberOfOversFromBowler(2);
		assertEquals(2, numberOfOversFromBowler.size());
		assertTrue(numberOfOversFromBowler.containsKey(4));
		assertTrue(numberOfOversFromBowler.containsKey(4));
	}

	@Test
	public void testGetBattingWicketBean() {
		BattingWicketBean battingWicketBean = new BattingWicketBean(1, DismisalType.RUNOUT, 1, 1, 1);
		battingScore.setBattingWicketBean(battingWicketBean);
		assertEquals(battingWicketBean, battingScore.getBattingWicketBean());
	}

	@Test
	public void testGetAllMaidens() {
		assertEquals(0, battingScore.getAllMaidens());

		over1.addRun(2);
		assertEquals(0, battingScore.getAllMaidens());

		over2.addRun(0);
		assertEquals(0, battingScore.getAllMaidens());

		over2.addRun(0);
		over2.addRun(0);
		over2.addRun(0);
		over2.addRun(0);
		over2.addRun(0);
		assertEquals(1, battingScore.getAllMaidens());
	
		//Over with 1 run - not a maiden
		BattingOverBean over3 = new BattingOverBean(3, 1);
		battingScore.addBattingOver(over3);

		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(1);
		
		assertEquals(1, battingScore.getAllMaidens());
	}

	@Test
	public void testGetMaidensForBowler() {
		BattingOverBean over3 = new BattingOverBean(3, 1);
		battingScore.addBattingOver(over3);

		over1.addRun(2);
		assertEquals(0, battingScore.getMaidenForBowler(1));

		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);
		over3.addRun(0);

		assertEquals(1, battingScore.getMaidenForBowler(1));
		
		//over with 1 run - not a maiden
		BattingOverBean over4 = new BattingOverBean(4, 2);
		battingScore.addBattingOver(over4);

		over4.addRun(0);
		over4.addRun(0);
		over4.addRun(0);
		over4.addRun(0);
		over4.addRun(0);
		over4.addRun(1);

		assertEquals(0, battingScore.getMaidenForBowler(2));
	}
	
	@Test
	public void testGetFoursOfBowler() {
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(1, 4));
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(2, 4));
		
		over1.addRun(4);
		
		assertEquals(1, battingScore.getNumberOfRunsOfBowler(1, 4));
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(2, 4));
	}
	
	@Test
	public void testGetSixesOfBowler() {
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(1, 6));
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(2, 6));
		
		over1.addRun(6);
		
		assertEquals(1, battingScore.getNumberOfRunsOfBowler(1, 6));
		assertEquals(0, battingScore.getNumberOfRunsOfBowler(2, 6));
	}
	
	@Test
	public void testGetScoringShots() {
		assertEquals(0, battingScore.getScoringShots());
	}
	
	@Test
	public void testSetBattingStyle() {
		assertEquals(BattingStyle.Right, battingScore.getBattingStyle());
		
		battingScore.setBattingStyle(BattingStyle.Left);
		assertEquals(BattingStyle.Left, battingScore.getBattingStyle());
	}
	
	@Test
	public void testGetBattingStyle() {
		assertEquals(BattingStyle.Right, battingScore.getBattingStyle());
	}
}
