package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import common.BallType;

public class OverTrackerTest {
	
	private OverTracker overTracker;
	
	@Before
	public void setUp() {
		overTracker = new OverTracker(40);
	}

	@Test
	public void testOverTracker() {
		overTracker = new OverTracker(20);
		assertEquals(20 , overTracker.getOversLeft());
	}
	
	@Test
	public void testGetBallsBowledScoring() {
		assertEquals(0 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals(1 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.WICKET);
		assertEquals(2 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.DOT_BALL);
		assertEquals(3 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.LEG_BYE);
		assertEquals(4 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.BYE);
		assertEquals(5 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals(6 ,overTracker.getBallsBowled());
	}
	
	@Test
	public void testGetBallsBowledExtras() {
		assertEquals(0 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.WIDE);
		assertEquals(0 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.NO_BALL_EXTRA);
		assertEquals(0 ,overTracker.getBallsBowled());
		
		overTracker.updateOver(BallType.NO_BALL_RUN);
		assertEquals(0 ,overTracker.getBallsBowled());
	}

	@Test
	public void testGetNumberOfBallsLeft() {
		overTracker.updateOver(BallType.WIDE);
		assertEquals(6, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.WIDE);
		assertEquals(6, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.DOT_BALL);
		assertEquals(5, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.BYE);
		assertEquals(4, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.LEG_BYE);
		assertEquals(3, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.SCORING);
		assertEquals(2, overTracker.getBallsLeft());

		// Test number of balls left in an over does not go into negative.
		overTracker.setNumberOfBallsLeft(1);
		overTracker.updateOver(BallType.SCORING);
		assertEquals(0, overTracker.getBallsLeft());

		overTracker.updateOver(BallType.SCORING);
		assertEquals(0, overTracker.getBallsLeft());
	}

	@Test
	public void testStartNewOver() {
		assertEquals("0.0", overTracker.getOverAsString());
		overTracker.startNewOver();
		assertEquals("1.0", overTracker.getOverAsString());
	}

	@Test
	public void testGetBallNumber() {
		assertEquals(0 , overTracker.getBallNumber());
		overTracker.updateOver(BallType.SCORING);
		assertEquals(1 , overTracker.getBallNumber());
	}

	@Test
	public void testGetOverNumber() {
		assertEquals(0 , overTracker.getOverNumber());
		overTracker.startNewOver();
		assertEquals(1 , overTracker.getOverNumber());
	}

	@Test
	public void testGetOversLeft() {
		assertEquals(40 , overTracker.getOversLeft());
		overTracker.startNewOver();
		assertEquals(39 , overTracker.getOversLeft());
	}
	
	
	@Test
	public void testGetPercentageOfOver() {
		assertEquals("0.0", overTracker.getOverAsStringPercentage());

		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.16", overTracker.getOverAsStringPercentage());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.33", overTracker.getOverAsStringPercentage());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.50", overTracker.getOverAsStringPercentage());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.66", overTracker.getOverAsStringPercentage());

		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.83", overTracker.getOverAsStringPercentage());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("0.0", overTracker.getOverAsStringPercentage());
	}
	
	@Test
	public void testStartNewInnings() {
		assertEquals(0, overTracker.getOverNumber());
		
		overTracker.setOverNumber(5);
		overTracker.updateOver(BallType.SCORING);
		assertEquals(5, overTracker.getOverNumber());
		assertEquals(5 ,overTracker.getBallsLeft());
		
		overTracker.startNewInnings();
		assertEquals(0, overTracker.getOverNumber());
		assertEquals(6 ,overTracker.getBallsLeft());
	}
	
	@Test
	public void testIsOverFinished() {
		assertFalse(overTracker.isOverFinished());
		
		overTracker.updateOver(BallType.SCORING);
		overTracker.updateOver(BallType.SCORING);
		overTracker.updateOver(BallType.SCORING);
		overTracker.updateOver(BallType.SCORING);
		overTracker.updateOver(BallType.SCORING);
		overTracker.updateOver(BallType.SCORING);

		assertTrue(overTracker.isOverFinished());
	}
	
	@Test
	public void testIsFirstOver() {
		assertTrue(overTracker.isFirstOver());
		
		overTracker.setOverNumber(1);
		assertFalse(overTracker.isFirstOver());
	}
	
	@Test
	public void testGetOverAsString() {
		assertEquals("0.0", overTracker.getOverAsString());
		
		overTracker.setOverNumber(5);
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("5.1", overTracker.getOverAsString());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("5.2", overTracker.getOverAsString());
		
		overTracker.updateOver(BallType.SCORING);		
		assertEquals("5.3", overTracker.getOverAsString());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("5.4", overTracker.getOverAsString());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("5.5", overTracker.getOverAsString());
		
		overTracker.updateOver(BallType.SCORING);
		assertEquals("6", overTracker.getOverAsString());
	}
}
