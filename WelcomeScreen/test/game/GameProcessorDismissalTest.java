package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import beans.BattingWicketBean;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;

public class GameProcessorDismissalTest extends GameProcessorBase {
	private Player fielder = new Player("fielder", 1);

	@Test
	public void testBowled() {
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		checkBatsman(0, DismisalType.BOWLED);
	}

	@Test
	public void testRunOut() {
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.SCORING, DismisalType.RUNOUT);
		checkBatsman(0, DismisalType.RUNOUT);
	}

	// run out - first run counts
	// http://www.livestrong.com/article/347920-rules-of-run-out-in-cricket/
	@Test
	public void testRunOutWithRun() {
		gameProcessor.runOut(fielder, 1, 0, 0, BallType.SCORING, DismisalType.RUNOUT);
		checkBatsman(1, DismisalType.RUNOUT);
	}

	@Test
	public void testRunOutNonStriker() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, nonStriker.getBattingStatus());
		assertEquals(0, nonStriker.getBattingScore().getRuns());
		assertEquals(0, nonStriker.getBattingScore().getBallsFaced());

		assertNull(battingScore.getBattingWicketBean());

		BattingWicketBean nonStrikerWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikerWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), nonStrikerWicketBean.getFielderId());
	}
		
	@Test
	public void testRunOutNoBallRun() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.NO_BALL_RUN, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, nonStriker.getBattingStatus());
		assertEquals(0, nonStriker.getBattingScore().getRuns());
		assertEquals(0, nonStriker.getBattingScore().getBallsFaced());

		assertNull(battingScore.getBattingWicketBean());

		BattingWicketBean nonStrikerWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikerWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), nonStrikerWicketBean.getFielderId());
	}
	
	@Test
	public void testRunOutNonStrikerWithRunToStriker() {
		Player nonStriker = game.getBattingTeam().getNonStriker();

		gameProcessor.runOut(fielder, 1, 0, 0, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
		assertEquals(1, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, nonStriker.getBattingStatus());
		assertEquals(0, nonStriker.getBattingScore().getRuns());
		assertEquals(0, nonStriker.getBattingScore().getBallsFaced());

		assertNull(battingScore.getBattingWicketBean());

		BattingWicketBean nonStrikerWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikerWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), nonStrikerWicketBean.getFielderId());
	}

	@Test
	public void testLBW() {
		gameProcessor.dismisal(DismisalType.LBW, null, 0);
		checkBatsman(0, DismisalType.LBW);
	}

	@Test
	public void testCaught() {
		gameProcessor.dismisal(DismisalType.CAUGHT, fielder, 0);
		checkBatsman(0, DismisalType.CAUGHT);
	}

	@Test
	public void testStumped() {
		gameProcessor.dismisal(DismisalType.STUMPED, fielder, 0);
		checkBatsman(0, DismisalType.STUMPED);
	}

	private void checkBatsman(int runs, DismisalType dismisalType) {
		assertEquals(runs, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(dismisalType, battingScore.getBattingWicketBean().getDismisalType());
	}

	@Test
	public void testProcessStumpedOfWide() {
		gameProcessor.stumpedOfWide(fielder, 1, 0);

		assertEquals(6, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.STUMPED, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getWides());
		assertEquals(1, bowlingScore.getWideRuns());
	}

	@Test
	public void testRunoutWithWide() {
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.WIDE, DismisalType.RUNOUT);

		assertEquals(6, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getWides());
		assertEquals(1, bowlingScore.getWideRuns());
	}

	@Test
	public void testRunoutWithNoBall() {
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.NO_BALL_EXTRA, DismisalType.RUNOUT);

		assertEquals(6, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getNoBalls());
		assertEquals(1, bowlingScore.getNoBallRuns());
	}

	@Test
	public void testRunoutWithLegBye() {
		gameProcessor.runOut(fielder, 3, 0, 0, BallType.LEG_BYE, DismisalType.RUNOUT);

		assertEquals(5, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getLegByes());
		assertEquals(3, bowlingScore.getLegByeRuns());
	}

	@Test
	public void testRunoutWithBye() {
		gameProcessor.runOut(fielder, 3, 0, 0, BallType.BYE, DismisalType.RUNOUT);

		assertEquals(5, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getByes());
		assertEquals(3, bowlingScore.getByeRuns());
	}

	@Test
	public void testNonStrikerRunoutWithWide() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.WIDE, DismisalType.NON_STRIKER_RUNOUT);
		assertNull(battingScore.getBattingWicketBean());

		assertEquals(6, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getWides());
		assertEquals(1, bowlingScore.getWideRuns());
	}

	@Test
	public void testNonStrikerRunoutWithNoBall() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.NO_BALL_EXTRA, DismisalType.NON_STRIKER_RUNOUT);
		assertEquals(6, gameProcessor.getBallsLeft());
		
		assertNull(battingScore.getBattingWicketBean());
		BattingWicketBean battingWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getNoBalls());
		assertEquals(1, bowlingScore.getNoBallRuns());
	}

	@Test
	public void testNonStrikerRunoutWithLegBye() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 3, 0, 0, BallType.LEG_BYE, DismisalType.NON_STRIKER_RUNOUT);
		assertNull(battingScore.getBattingWicketBean());

		assertEquals(5, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getLegByes());
		assertEquals(3, bowlingScore.getLegByeRuns());
	}

	@Test
	public void testNonStrikerRunoutWithBye() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		gameProcessor.runOut(fielder, 3, 0, 0, BallType.BYE, DismisalType.NON_STRIKER_RUNOUT);
		assertNull(battingScore.getBattingWicketBean());

		assertEquals(5, gameProcessor.getBallsLeft());
		BattingWicketBean battingWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(battingWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(1, bowlingScore.getByes());
		assertEquals(3, bowlingScore.getByeRuns());
	}

	@Test
	public void testNonStrikerRunoutThrowsException() {
		try {
			gameProcessor.runOut(fielder, 3, 250, 250, BallType.DOT_BALL, DismisalType.NON_STRIKER_RUNOUT);
			fail();
		} catch (Exception e) {
			assertEquals("Unsupported Ball Type: Dot Ball", e.getMessage());
		}
	}

	@Test
	public void testProcessDismisalThrowsException() {
		try {
			gameProcessor.dismisal(DismisalType.RUNOUT, fielder, 0);
			fail();
		} catch (Exception e) {
			assertEquals("Dont use this method for run outs", e.getMessage());
		}

		try {
			gameProcessor.dismisal(DismisalType.NON_STRIKER_RUNOUT, fielder, 0);
			fail();
		} catch (Exception e) {
			assertEquals("Dont use this method for run outs", e.getMessage());
		}
	}

	@Test
	public void testBowledInRunOutMethod() {
		try {
			gameProcessor.runOut(fielder, 0, 0, 0, BallType.SCORING, DismisalType.BOWLED);
			fail();
		} catch (Exception e) {
			assertEquals("This method is used only for runouts", e.getMessage());
		}
	}
	
	@Test
	public void testNonStrikerRunOutWithNewBatsman() {
		Player nonStriker = game.getBattingTeam().getNonStriker();
		
		game.getBattingTeam().getPlayerById(5).setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.NO_BALL_RUN, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, nonStriker.getBattingStatus());
		assertEquals(0, nonStriker.getBattingScore().getRuns());
		assertEquals(0, nonStriker.getBattingScore().getBallsFaced());

		assertNull(battingScore.getBattingWicketBean());

		BattingWicketBean nonStrikerWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikerWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), nonStrikerWicketBean.getFielderId());
	}
	
	@Test
	public void testRunOutWithNewBatsman() {
		Player striker = game.getBattingTeam().getStriker();
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.NO_BALL_RUN, DismisalType.RUNOUT);
		
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, striker.getBattingStatus());
		assertEquals(0, striker.getBattingScore().getRuns());
		assertEquals(1, striker.getBattingScore().getBallsFaced());

		assertNotNull(battingScore.getBattingWicketBean());
		
		BattingWicketBean strikerWicketBean = striker.getBattingScore().getBattingWicketBean();
		assertNotNull(strikerWicketBean);
		assertEquals(DismisalType.RUNOUT, strikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), strikerWicketBean.getFielderId());
	}
	
	@Test
	public void testRunOutLastWicketNoBallRun() {
		Player striker = game.getBattingTeam().getStriker();
		
		game.getBattingTeam().getPlayerById(5).setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		
		gameProcessor.runOut(fielder, 0, 0f, 0f, BallType.NO_BALL_RUN, DismisalType.RUNOUT);
		
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBallsFaced());

		assertEquals(BattingStatus.Out, striker.getBattingStatus());
		assertEquals(0, striker.getBattingScore().getRuns());
		assertEquals(1, striker.getBattingScore().getBallsFaced());

		assertNotNull(battingScore.getBattingWicketBean());
		
		BattingWicketBean strikerWicketBean = striker.getBattingScore().getBattingWicketBean();
		assertNotNull(strikerWicketBean);
		assertEquals(DismisalType.RUNOUT, strikerWicketBean.getDismisalType());
		assertEquals(fielder.getId(), strikerWicketBean.getFielderId());
	}
}