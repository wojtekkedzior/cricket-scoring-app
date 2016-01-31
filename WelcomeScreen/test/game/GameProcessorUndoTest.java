package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import beans.BattingWicketBean;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;

public class GameProcessorUndoTest extends GameProcessorBase {
	
	@Test
	public void testUndoForScoring() {
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		assertEquals(1, battingScore.getRunsOfBowler(bowler.getId()));

		checkUndo();
		assertEquals(0, battingScore.getRunsOfBowler(bowler.getId()));
	}

	@Test
	public void testUndoForNoBall() {
		gameProcessor.delivery(BallType.NO_BALL_EXTRA, 2, 0, 0);
		
		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		
		assertEquals(3, bowlingScore.getNoBallRuns());
		assertEquals(1, bowlingScore.getNoBalls());
		
		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		
		assertEquals(0, bowlingScore.getNoBallRuns());
		assertEquals(0, bowlingScore.getNoBalls());
	}

	private void checkUndo() {
		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(1, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
	}

	@Test
	public void testUndoForBye() {
		gameProcessor.delivery(BallType.BYE, 1, 0, 0);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(1, bowlingScore.getByeRuns());
		assertEquals(1, bowlingScore.getByes());
		assertEquals(1, bowlingScore.getFieldingExtras());

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());

		assertEquals(0, bowlingScore.getByeRuns());
		assertEquals(0, bowlingScore.getByes());
		assertEquals(0, bowlingScore.getFieldingExtras());
	}

	@Test
	public void testUndoForLegBye() {
		gameProcessor.delivery(BallType.LEG_BYE, 1, 0, 0);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(1, bowlingScore.getLegByeRuns());
		assertEquals(1, bowlingScore.getLegByes());
		assertEquals(1, bowlingScore.getFieldingExtras());

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(0, bowlingScore.getLegByeRuns());
		assertEquals(0, bowlingScore.getLegByes());
		assertEquals(0, bowlingScore.getFieldingExtras());
	}

	@Test
	public void testUndoForWide() {
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(1, bowlingScore.getWideRuns());
		assertEquals(1, bowlingScore.getWides());
		assertEquals(0, bowlingScore.getFieldingExtras());

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(0, bowlingScore.getWideRuns());
		assertEquals(0, bowlingScore.getWides());
		assertEquals(0, bowlingScore.getFieldingExtras());
	}

	@Test
	public void testUndoForWideWithExtraRun() {
		gameProcessor.delivery(BallType.WIDE, 1, 0, 0);

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(2, bowlingScore.getWideRuns());
		assertEquals(1, bowlingScore.getWides());
		assertEquals(0, bowlingScore.getFieldingExtras());

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());

		assertEquals(0, bowlingScore.getWideRuns());
		assertEquals(0, bowlingScore.getWides());
		assertEquals(0, bowlingScore.getFieldingExtras());
	}

	@Test
	public void testUndoForDot() {
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		gameProcessor.undo();

		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}

	@Test
	public void testUndoForWicketRunout() {
		//mark the next batsman
		game.getBattingTeam().getPlayerByName("Waller").setBattingStatus(BattingStatus.NewBatsman);
		
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.SCORING, DismisalType.RUNOUT);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(fielder.getId(), battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}
	
	@Test
	public void testUndoForWicketRunoutWithRun() {
		//mark the next batsman
		game.getBattingTeam().getPlayerByName("Waller").setBattingStatus(BattingStatus.NewBatsman);
		
		gameProcessor.runOut(fielder, 4, 0, 0, BallType.SCORING, DismisalType.RUNOUT);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(4, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(4, battingScore.getRunsOfBowler(bowler.getId()));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.RUNOUT, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId()); // bowler does not get credit for a run out
		assertEquals(fielder.getId(), battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(bowler.getId()));
	}

	@Test
	public void testUndoForWicketNonStrikerRunout() { 
		//mark the next batsman
		game.getBattingTeam().getPlayerByName("Waller").setBattingStatus(BattingStatus.NewBatsman);
		
		gameProcessor.runOut(fielder, 0, 0, 0, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNull(battingWicketBean);

		BattingWicketBean nonStrikeWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikeWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikeWicketBean.getDismisalType());
		assertEquals(0, nonStrikeWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), nonStrikeWicketBean.getBowlerId()); // bowler does not get credit for a run out
		assertEquals(fielder.getId(), nonStrikeWicketBean.getFielderId());
		assertEquals(0, nonStrikeWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
		
		assertNull(nonStriker.getBattingScore().getBattingWicketBean());
	}
	
	@Test
	public void testUndoForWicketNonStrikerRunoutWithRuns() { 
		//mark the next batsman
		game.getBattingTeam().getPlayerByName("Waller").setBattingStatus(BattingStatus.NewBatsman);
		
		gameProcessor.runOut(fielder, 4, 0, 0, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT); //(fielder, 1, 4, 0, 0, BallType.SCORING, batsman2, false, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(4, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(4, battingScore.getRunsOfBowler(bowler.getId()));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertNull(battingWicketBean);

		BattingWicketBean nonStrikeWicketBean = nonStriker.getBattingScore().getBattingWicketBean();
		assertNotNull(nonStrikeWicketBean);
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, nonStrikeWicketBean.getDismisalType());
		assertEquals(0, nonStrikeWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), nonStrikeWicketBean.getBowlerId()); // bowler does not get credit for a run out
		assertEquals(fielder.getId(), nonStrikeWicketBean.getFielderId());
		assertEquals(0, nonStrikeWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(bowler.getId()));
		
		assertNull(nonStriker.getBattingScore().getBattingWicketBean());
	}

	@Test
	public void testUndoForWicketStumped() {
		gameProcessor.dismisal(DismisalType.STUMPED, fielder, 1);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.STUMPED, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(fielder.getId(), battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}

	@Test
	public void testUndoForWicketLBW() {
		gameProcessor.dismisal(DismisalType.LBW, null, 1);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.LBW, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(0, battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}

	@Test
	public void testUndoForWicketHitWicket() {
		gameProcessor.dismisal(DismisalType.HIT_WICKET, null, 1);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.HIT_WICKET, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(0, battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}

	@Test
	public void testUndoForWicketCaught() {
		gameProcessor.dismisal(DismisalType.CAUGHT, fielder, 1);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.CAUGHT, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(fielder.getId(), battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));
	}

	@Test
	public void testUndoForWicketBowled() {
		gameProcessor.dismisal(DismisalType.BOWLED, null, 1);

		assertEquals(1, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(1, battingScore.getBattingOverBean(0, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(1));

		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		assertEquals(DismisalType.BOWLED, battingWicketBean.getDismisalType());
		assertEquals(0, battingWicketBean.getBallNumber());
		assertEquals(Integer.valueOf(bowler.getId()), battingWicketBean.getBowlerId());
		assertEquals(0, battingWicketBean.getFielderId());
		assertEquals(0, battingWicketBean.getOverNumber());

		gameProcessor.undo();

		assertNull(battingScore.getBattingWicketBean());
		assertEquals(0, battingScore.getBallsFaced());
		assertEquals(0, battingScore.getRuns());
		assertEquals(0, battingScore.getBattingOverBean(1, 1).getCordinatesOfAllRuns().size());
		assertEquals(0, battingScore.getRunsOfBowler(bowler.getId()));
	}
}
