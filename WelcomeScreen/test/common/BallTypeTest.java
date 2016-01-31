package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BallTypeTest {
	@Test
	public void testGet() {
		assertEquals(BallType.WIDE, BallType.get(BallType.WIDE.getBallTypeName()));
		assertEquals(BallType.NO_BALL_EXTRA, BallType.get(BallType.NO_BALL_EXTRA.getBallTypeName()));
		assertEquals(BallType.DOT_BALL, BallType.get(BallType.DOT_BALL.getBallTypeName()));
		assertEquals(BallType.SCORING, BallType.get(BallType.SCORING.getBallTypeName()));
		assertEquals(BallType.WICKET, BallType.get(BallType.WICKET.getBallTypeName()));
		assertEquals(BallType.BYE, BallType.get(BallType.BYE.getBallTypeName()));
		assertEquals(BallType.LEG_BYE, BallType.get(BallType.LEG_BYE.getBallTypeName()));
		assertEquals(BallType.DEAD_BALL, BallType.get(BallType.DEAD_BALL.getBallTypeName()));
	}
}
