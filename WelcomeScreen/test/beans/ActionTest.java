package beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import game.Player;

import org.junit.Before;
import org.junit.Test;

import common.BallType;
import common.DismisalType;

public class ActionTest {

	private static final int OVER_NUMBER = 1;
	private Action action;
	private Player somePlayer;

	@Before
	public void setUp() throws Exception {
		somePlayer = new Player("player", 1);
		action = new Action(somePlayer, OVER_NUMBER);
	}

	@Test
	public void testAction() {
		assertEquals(somePlayer, action.getBowler());
		assertEquals(OVER_NUMBER, action.getOverNumber());
	}

	@Test
	public void testGetBowler() {
		assertEquals(somePlayer, action.getBowler());
	}

	@Test
	public void testGetBatsman() {
		assertNull(action.getBatsman());
	}

	@Test
	public void testSetBatsman() {
		action.setBatsman(somePlayer);
		assertEquals(somePlayer, action.getBatsman());
	}

	@Test
	public void testGetFielder() {
		assertNull(action.getFielder());
	}

	@Test
	public void testSetFielder() {
		action.setFielder(somePlayer);
		assertEquals(somePlayer, action.getFielder());
	}

	@Test
	public void testGetBallType() {
		assertNull(action.getBallType());
	}

	@Test
	public void testSetBallType() {
		action.setBallType(BallType.NO_BALL_EXTRA);
		assertEquals(BallType.NO_BALL_EXTRA, action.getBallType());
	}

	@Test
	public void testGetDismisalType() {
		assertNull(action.getDismisalType());
	}

	@Test
	public void testSetDismisalType() {
		action.setDismisalType(DismisalType.BOWLED);
		assertEquals(DismisalType.BOWLED, action.getDismisalType());
	}

	@Test
	public void testGetBallsLeft() {
		assertTrue(action.getRunsFromExtras() == 0);
	}

	@Test
	public void testSetBallsLeft() {
		action.setBallsLeft(2);
		assertEquals(2, action.getBallsLeft());
	}

	@Test
	public void testGetOverNumber() {
		assertEquals(OVER_NUMBER, action.getOverNumber());
	}

	@Test
	public void testSetOverNumber() {
		assertEquals(OVER_NUMBER, action.getOverNumber());

		action.setOverNumber(2);
		assertEquals(2, action.getOverNumber());
	}

	@Test
	public void testGetRunsFromExtras() {
		assertTrue(action.getRunsFromExtras() == 0);
	}

	@Test
	public void testSetRunsFromExtras() {
		action.setRunsFromExtras(2);
		assertEquals(-2, action.getRunsFromExtras());
	}

	@Test
	public void testSetNonStriker() {
		action.setNonStriker(somePlayer);
		assertEquals(somePlayer, action.getNonStriker());
	}

	@Test
	public void testGetNonStriker() {
		assertNull(action.getNonStriker());
	}

	@Test
	public void testGetBallType2() {
		assertNull(action.getBallType2());
	}

	@Test
	public void testSetBallType2() {
		action.setBallType2(BallType.NO_BALL_EXTRA);
		assertEquals(BallType.NO_BALL_EXTRA, action.getBallType2());
	}
}