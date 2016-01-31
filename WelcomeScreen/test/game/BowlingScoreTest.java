package game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlingScoreTest {

	private BowlingScore bowlingScore;

	@Before
	public void setUp(){
		bowlingScore = new BowlingScore();
	}

	@Test
	public void testAddLegBye() {
		assertEquals(0, bowlingScore.getLegByeRuns());
		assertEquals(0, bowlingScore.getLegByes());

		bowlingScore.addLegBye(2);

		assertEquals(2, bowlingScore.getLegByeRuns());
		assertEquals(1, bowlingScore.getLegByes());
	}

	@Test
	public void testAddBye() {
		assertEquals(0, bowlingScore.getByeRuns());
		assertEquals(0, bowlingScore.getByes());

		bowlingScore.addBye(4);

		assertEquals(4, bowlingScore.getByeRuns());
		assertEquals(1, bowlingScore.getByes());
	}

	@Test
	public void testAddWide() {
		assertEquals(0, bowlingScore.getWideRuns());
		assertEquals(0, bowlingScore.getWides());

		bowlingScore.addWideAndExtras(3);

		assertEquals(4, bowlingScore.getWideRuns());
		assertEquals(1, bowlingScore.getWides());
	}

	@Test
	public void testAddNoBall() {
		assertEquals(0, bowlingScore.getNoBallRuns());
		assertEquals(0, bowlingScore.getNoBalls());

		bowlingScore.addNoBallAndExtras(3);

		assertEquals(4, bowlingScore.getNoBallRuns());
		assertEquals(1, bowlingScore.getNoBalls());
	}
	

	@Test
	public void testAddNoBallWithNoRuns() {
		assertEquals(0, bowlingScore.getNoBallRuns());
		assertEquals(0, bowlingScore.getNoBalls());

		bowlingScore.addNoBallAndExtras(0);

		assertEquals(1, bowlingScore.getNoBallRuns());
		assertEquals(1, bowlingScore.getNoBalls());
	}

	@Test
	public void testGetBowlerExtras() {
		assertEquals(0, bowlingScore.getBowlerExtras());
		bowlingScore.addNoBallAndExtras(3);
		bowlingScore.addWideAndExtras(3);
		bowlingScore.addBye(4);
		bowlingScore.addLegBye(2);
		assertEquals(8, bowlingScore.getBowlerExtras());
	}

	@Test
	public void testGetFieldingExtras() {
		assertEquals(0, bowlingScore.getFieldingExtras());
		bowlingScore.addNoBallAndExtras(3);
		bowlingScore.addWideAndExtras(3);
		bowlingScore.addBye(4);
		bowlingScore.addLegBye(5);
		assertEquals(9, bowlingScore.getFieldingExtras());
	}
}
