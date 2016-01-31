package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.BallType;
import common.BattingStatus;

public class GameProcessorScoringTest extends GameProcessorBase{

	@Test
	public void testProcessBall1() {
		processBallOddRuns(1);
	}

	@Test
	public void testProcessBall3() {
		processBallOddRuns(3);
	}

	@Test
	public void testProcessBall5() {
		processBallOddRuns(5);
	}

	@Test
	public void testProcessBall2() {
		processBallEvenRuns(2);
	}

	@Test
	public void testProcessBall4() {
		processBallEvenRuns(4);
	}

	@Test
	public void testProcessBall6() {
		processBallEvenRuns(6);
	}

	private void processBallOddRuns(int runs) {
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());
		gameProcessor.delivery(BallType.SCORING, runs, 0, 0);

		assertEquals("Chibhabha", game.getBattingTeam().getStriker().getName());
		assertEquals("Masakadza", game.getBattingTeam().getNonStriker().getName());

		BattingScore battingScore = game.getBattingTeam().getNonStriker().getBattingScore();
		assertEquals(runs, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(runs, game.getBattingTeam().getRunsGivenByBowlerAgainstAllBatsman(testData.mills.getId()));
	}

	private void processBallEvenRuns(int runs) {
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());
		gameProcessor.delivery(BallType.SCORING, runs, 0, 0);
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());

		BattingScore battingScore = game.getBattingTeam().getStriker().getBattingScore();
		assertEquals(runs, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(runs, game.getBattingTeam().getRunsGivenByBowlerAgainstAllBatsman(testData.mills.getId()));
	}

	@Test
	public void testProcessDotBall() {
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());

		BattingScore battingScore = game.getBattingTeam().getStriker().getBattingScore();
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(0, game.getBattingTeam().getRunsGivenByBowlerAgainstAllBatsman(testData.mills.getId()));
	}

	@Test
	public void testProcessDeadBall() {
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());
		gameProcessor.delivery(BallType.DEAD_BALL, 0, 0, 0);
		assertEquals("Masakadza", game.getBattingTeam().getStriker().getName());

		BattingScore battingScore = game.getBattingTeam().getStriker().getBattingScore();
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBallsFaced());

		assertEquals(0, game.getBattingTeam().getRunsGivenByBowlerAgainstAllBatsman(testData.mills.getId()));
	}

	@Test
	public void testDelivery_multipleDeliveries() {
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		assertEquals(2, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		assertEquals(4, battingScore.getRuns());
		assertEquals(2, battingScore.getBallsFaced());

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals(4, battingScore.getRuns());
		assertEquals(3, battingScore.getBallsFaced());

		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		assertEquals(10, battingScore.getRuns());
		assertEquals(4, battingScore.getBallsFaced());
	}

	@Test
	public void testProcessBall_Wide() {
		gameProcessor.delivery(BallType.WIDE, 1, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBallsFaced());

		assertEquals(1, bowlingScore.getWides());
		assertEquals(2, bowlingScore.getWideRuns());

		gameProcessor.delivery(BallType.WIDE, 4, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBallsFaced());

		assertEquals(2, bowlingScore.getWides());
		assertEquals(7, bowlingScore.getWideRuns());
	}

	@Test
	public void testProcessBall_NoBall() {
		gameProcessor.delivery(BallType.NO_BALL_EXTRA, 0, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(1, bowlingScore.getNoBalls());
		assertEquals(1, bowlingScore.getNoBallRuns());

		gameProcessor.delivery(BallType.NO_BALL_RUN, 1, 0, 0);

		assertEquals(1, battingScore.getRuns());
		assertEquals(2, battingScore.getBallsFaced());

		assertEquals(2, bowlingScore.getNoBalls());
		assertEquals(2, bowlingScore.getNoBallRuns());
	}

	@Test
	public void testProcessBall_NoBall_Boundry() {
		gameProcessor.delivery(BallType.NO_BALL_RUN, 4, 0, 0);

		assertEquals(4, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(1, bowlingScore.getNoBalls());
		assertEquals(1, bowlingScore.getNoBallRuns());
	}

	@Test
	public void testProcessBall_Bye() {
		gameProcessor.delivery(BallType.BYE, 1, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(1, bowlingScore.getByes());
		assertEquals(1, bowlingScore.getByeRuns());

		// bring the same batsman on strike again
		game.getBattingTeam().getNonStriker().setBattingStatus(BattingStatus.Striker);
		gameProcessor.delivery(BallType.BYE, 4, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(2, battingScore.getBallsFaced());

		assertEquals(2, bowlingScore.getByes());
		assertEquals(5, bowlingScore.getByeRuns());
	}

	@Test
	public void testProcessBall_LegBye() {
		gameProcessor.delivery(BallType.LEG_BYE, 1, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(1, bowlingScore.getLegByes());
		assertEquals(1, bowlingScore.getLegByeRuns());

		// bring the same batsman on strike again
		game.getBattingTeam().getNonStriker().setBattingStatus(BattingStatus.Striker);
		gameProcessor.delivery(BallType.LEG_BYE, 4, 0, 0);

		assertEquals(0, battingScore.getRuns());
		assertEquals(2, battingScore.getBallsFaced());

		assertEquals(2, bowlingScore.getLegByes());
		assertEquals(5, bowlingScore.getLegByeRuns());
	}
	
	@Test
	public void testIsOverFinished() {
		OverTracker overTracker = new OverTracker(3);
		overTracker.setNumberOfBallsLeft(0);
		gameProcessor.setOverTracker(overTracker);
		assertTrue(gameProcessor.isOverFinished());
	}
	
	@Test
	public void testStartNewInnings() {
		gameProcessor.startNewInnings();
		assertEquals(6, gameProcessor.getBallsLeft());
		assertEquals(0, gameProcessor.getOverNumber());
	}
	
	@Test
	public void testIsFirstOver() {
		OverTracker overTracker = new OverTracker(3);
		gameProcessor.setOverTracker(overTracker);
		assertTrue(gameProcessor.isFirstOver());
		
		overTracker.setOverNumber(1);
		assertFalse(gameProcessor.isFirstOver());
	}
	
	@Test
	public void testGetBallsBowled() {
		assertEquals(0, gameProcessor.getBallsBowled());
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals(2, gameProcessor.getBallsBowled());
	}
	
	@Test
	public void testGetOverTracker() {
		OverTracker overTracker = new OverTracker(3);
		gameProcessor.setOverTracker(overTracker);
		assertEquals(overTracker, gameProcessor.getOverTracker());
	}
	
	@Test
	public void testGetOverAsString() {
		assertEquals("0.0", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.1", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.2", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.3", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.4", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.5", gameProcessor.getOverAsString());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("1", gameProcessor.getOverAsString());
		
		gameProcessor.startNewOver();
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("1.1", gameProcessor.getOverAsString());
	}
	
	@Test
	public void testGetOverAsStringPercentage() {
		assertEquals("0.0", gameProcessor.getOverAsStringPercentage());

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.16", gameProcessor.getOverAsStringPercentage());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.33", gameProcessor.getOverAsStringPercentage());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.50", gameProcessor.getOverAsStringPercentage());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.66", gameProcessor.getOverAsStringPercentage());

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.83", gameProcessor.getOverAsStringPercentage());
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		assertEquals("0.0", gameProcessor.getOverAsStringPercentage());
	}
	
	@Test
	public void testUpdateGame() {
		gameProcessor.delivery(BallType.WICKET, 0, 0, 0);
		gameProcessor.updateGame(game);
		
		Player striker = game.getBattingTeam().getStriker();
		assertEquals(1, striker.getBattingScore().getBallsFaced());
	}
}