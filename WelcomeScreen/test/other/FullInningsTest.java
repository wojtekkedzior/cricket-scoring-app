package other;

import static org.junit.Assert.assertEquals;
import game.Game;
import game.GameProcessor;
import game.Player;
import game.Team;

import org.junit.Before;
import org.junit.Test;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;

public class FullInningsTest {

	// http://www.espncricinfo.com/new-zealand-v-zimbabwe-2012/engine/match/527012.html
	private GameProcessor gameProcessor;

	private GameData gameData = new GameData();
	
	private Game game;
	private Team nzTeam;
	private Team zimbabweTeam;

	@Before
	public void setUp() {
		game = gameData.getGame(true);
		gameProcessor = new GameProcessor(game);
		nzTeam = game.getTeam(1);
		zimbabweTeam = game.getTeam(2);
		
		innings();
	}

	public void innings() {
		int over = 0;
		
		firstOver(over++);
		secondOver(over++);
		thirdOver(over++);
		forthOver(over++);
		fithOver(over++);
		sixthOver(over++);
		seventhOver(over++);
		eigthOver(over++);
		ninthOver(over++);
		tenthOver(over++);
		eleventhOver(over++);
		twelthOver(over++);
		thirteenthOver(over++);
		fourteenthOver(over++);
		fithteenthOver(over++);
		sixteenthOver(over++);
		seventeenthOver(over++);
		eighteenthOver(over++);
		nineteenthOver(over++);
		twentiethOver(over++);
	}

	private void firstOver(int overNumber) {
		gameData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		assertEquals(2, gameData.masakadza.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.masakadza.getBattingScore().getRuns());
		assertEquals(1, gameData.masakadza.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.masakadza.getBattingStatus());

		assertEquals(4, gameData.chibhabha.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.chibhabha.getBattingScore().getRuns());
		assertEquals(0, gameData.chibhabha.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chibhabha.getBattingStatus());

		checkTeamAndOverTotals(1, overNumber, 1, 0, gameData.mills);
	}

	private void secondOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(6, gameData.masakadza.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.masakadza.getBattingScore().getRuns());
		assertEquals(8, gameData.masakadza.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.masakadza.getBattingStatus());

		assertEquals(6, gameData.chibhabha.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.chibhabha.getBattingScore().getRuns());
		assertEquals(1, gameData.chibhabha.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chibhabha.getBattingStatus());

		checkTeamAndOverTotals(10, overNumber, 9, 0, gameData.bracewell);
	}

	private void thirdOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameData.brendonTaylor.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.nathanMcCullum, 0);
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);

		assertEquals(7, gameData.masakadza.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.masakadza.getBattingScore().getRuns());
		assertEquals(0, gameData.masakadza.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.masakadza.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.masakadza.getBattingStatus());

		assertEquals(0, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(0, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(11, gameData.chibhabha.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.chibhabha.getBattingScore().getRuns());
		assertEquals(8, gameData.chibhabha.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chibhabha.getBattingStatus());

		checkTeamAndOverTotals(18, overNumber, 8, 0, gameData.bracewell);
	}

	private void forthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		assertEquals(6, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(4, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(11, gameData.chibhabha.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.chibhabha.getBattingScore().getRuns());
		assertEquals(0, gameData.chibhabha.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.chibhabha.getBattingStatus());

		checkTeamAndOverTotals(22, overNumber, 4, 0, gameData.bracewell);
	}

	private void fithOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameData.mutizwa.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.brendonMcCullum, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(12, gameData.chibhabha.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.chibhabha.getBattingScore().getRuns());
		assertEquals(0, gameData.chibhabha.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.chibhabha.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.chibhabha.getBattingStatus());

		assertEquals(7, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(1, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(4, gameData.mutizwa.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.mutizwa.getBattingScore().getRuns());
		assertEquals(2, gameData.mutizwa.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.mutizwa.getBattingStatus());

		checkTeamAndOverTotals(25, overNumber, 3, 0, gameData.mills);
	}

	private void sixthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 3, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(8, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(6, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(1, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(9, gameData.mutizwa.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.mutizwa.getBattingScore().getRuns());
		assertEquals(8, gameData.mutizwa.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.mutizwa.getBattingStatus());

		checkTeamAndOverTotals(34, overNumber, 9, 0, gameData.oram);
	}

	private void seventhOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.oram.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		
		assertEquals(11, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(8, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(2, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(12, gameData.mutizwa.getBattingScore().getBallsFaced());
		assertEquals(13, gameData.mutizwa.getBattingScore().getRuns());
		assertEquals(3, gameData.mutizwa.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.mutizwa.getBattingStatus());

		checkTeamAndOverTotals(39, overNumber, 5, 0, gameData.woodcock);
	}

	private void eigthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(15, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(3, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(14, gameData.mutizwa.getBattingScore().getBallsFaced());
		assertEquals(15, gameData.mutizwa.getBattingScore().getRuns());
		assertEquals(2, gameData.mutizwa.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.mutizwa.getBattingStatus());

		checkTeamAndOverTotals(44, overNumber, 5, 0, gameData.oram);
	}

	private void ninthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.oram.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		
		gameData.waller.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.LBW, null, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(16, gameData.mutizwa.getBattingScore().getBallsFaced());
		assertEquals(16, gameData.mutizwa.getBattingScore().getRuns());
		assertEquals(1, gameData.mutizwa.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.LBW, gameData.mutizwa.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.mutizwa.getBattingStatus());

		assertEquals(17, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(16, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(5, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(2, gameData.waller.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.waller.getBattingScore().getRuns());
		assertEquals(1, gameData.waller.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.waller.getBattingStatus());

		checkTeamAndOverTotals(51, overNumber, 7, 0, gameData.woodcock);
	}

	private void tenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(22, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(21, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(5, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(3, gameData.waller.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.waller.getBattingScore().getRuns());
		assertEquals(1, gameData.waller.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker,gameData. waller.getBattingStatus());

		checkTeamAndOverTotals(57, overNumber, 6, 0, gameData.nathanMcCullum);
	}

	private void eleventhOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 3, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		
		assertEquals(25, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(26, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(5, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(6, gameData.waller.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.waller.getBattingScore().getRuns());
		assertEquals(2, gameData.waller.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.waller.getBattingStatus());

		checkTeamAndOverTotals(64, overNumber, 7, 0, gameData.woodcock);
	}

	private void twelthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameData.coventry.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.woodcock, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(10, gameData.waller.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.waller.getBattingScore().getRuns());
		assertEquals(6, gameData.waller.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.waller.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.waller.getBattingStatus());

		assertEquals(27, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(28, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(2, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(0, gameData.coventry.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.coventry.getBattingScore().getRuns());
		assertEquals(0, gameData.coventry.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.coventry.getBattingStatus());

		checkTeamAndOverTotals(72, overNumber, 8, 0, gameData.nathanMcCullum);
	}

	private void thirteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.franklin.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.NO_BALL_EXTRA, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.NO_BALL_RUN, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(31, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(34, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(6, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(4, gameData.coventry.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.coventry.getBattingScore().getRuns());
		assertEquals(2, gameData.coventry.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.coventry.getBattingStatus());

		checkTeamAndOverTotals(82, overNumber, 10, 2, gameData.franklin);
	}

	private void fourteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.franklin.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(33, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(35, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(1, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(8, gameData.coventry.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.coventry.getBattingScore().getRuns());
		assertEquals(2, gameData.coventry.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.coventry.getBattingStatus());

		checkTeamAndOverTotals(85, overNumber, 3, 0, gameData.mills);
	}

	private void fithteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		
		assertEquals(35, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(40, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(5, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(12, gameData.coventry.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.coventry.getBattingScore().getRuns());
		assertEquals(6, gameData.coventry.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.coventry.getBattingStatus());

		checkTeamAndOverTotals(96, overNumber, 11, 0, gameData.woodcock);
	}

	private void sixteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameData.chigumbura.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.rossTaylor, 0);
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0); // batsman crossed
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(13, gameData.coventry.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.coventry.getBattingScore().getRuns());
		assertEquals(0, gameData.coventry.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.coventry.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.coventry.getBattingStatus());

		assertEquals(39, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(42, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(2, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(1, gameData.chigumbura.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.chigumbura.getBattingScore().getRuns());
		assertEquals(1, gameData.chigumbura.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chigumbura.getBattingStatus());

		checkTeamAndOverTotals(99, overNumber, 3, 0, gameData.nathanMcCullum);
	}

	private void seventeenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.NO_BALL_RUN, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		assertEquals(40, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(43, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(1, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(7, gameData.chigumbura.getBattingScore().getBallsFaced());
		assertEquals(7, gameData.chigumbura.getBattingScore().getRuns());
		assertEquals(6, gameData.chigumbura.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chigumbura.getBattingStatus());

		checkTeamAndOverTotals(107, overNumber, 8, 1, gameData.bracewell);
	}

	private void eighteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		assertEquals(43, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(47, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(4, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(10, gameData.chigumbura.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.chigumbura.getBattingScore().getRuns());
		assertEquals(3, gameData.chigumbura.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.chigumbura.getBattingStatus());

		checkTeamAndOverTotals(114, overNumber, 7, 0, gameData.oram);
	}

	private void nineteenthOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.oram.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameData.chakabva.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.rossTaylor, 0);
		
		assertEquals(15, gameData.chigumbura.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.chigumbura.getBattingScore().getRuns());
		assertEquals(4, gameData.chigumbura.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.chigumbura.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.chigumbura.getBattingStatus());

		assertEquals(44, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(48, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(1, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.brendonTaylor.getBattingStatus());

		assertEquals(0, gameData.chakabva.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.chakabva.getBattingScore().getRuns());
		assertEquals(0, gameData.chakabva.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.chakabva.getBattingStatus());

		checkTeamAndOverTotals(119, overNumber, 5, 0, gameData.bracewell);
	}

	private void twentiethOver(int overNumber) {
		gameProcessor.startNewOver();
		gameData.bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		gameData.oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameData.utseya.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, gameData.brendonMcCullum, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);

		gameProcessor.runOut(gameData.oram, 0, 0, 0,  BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
		
		assertEquals(3, gameData.chakabva.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.chakabva.getBattingScore().getRuns());
		assertEquals(0, gameData.chakabva.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.chakabva.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(46, gameData.brendonTaylor.getBattingScore().getBallsFaced());
		assertEquals(50, gameData.brendonTaylor.getBattingScore().getRuns());
		assertEquals(2, gameData.brendonTaylor.getBattingScore().getRunsForOver(overNumber));

		assertEquals(1, gameData.utseya.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.utseya.getBattingScore().getRuns());
		assertEquals(1, gameData.utseya.getBattingScore().getRunsForOver(overNumber));

		checkTeamAndOverTotals(123, overNumber, 4, 1, gameData.oram);
	}

	private void checkTeamAndOverTotals(int expectedTeamTotal, int overNumber, int runsInOver, int extrasInOver, Player bowler) {
		int battingRunsScored = zimbabweTeam.getRunsScored();

		int bowlingRunsGiven = nzTeam.getAllBowlingExtras();
		assertEquals(expectedTeamTotal, (battingRunsScored + bowlingRunsGiven));

		int battingRunsScoredForOver = zimbabweTeam.getRunsScoredForOver(overNumber);
		assertEquals(runsInOver, (battingRunsScoredForOver + extrasInOver));
	}

	@Test
	public void testCheckMcCullum() {
		assertEquals(2, zimbabweTeam.getWicketsForBowler(gameData.nathanMcCullum.getId()));

		int bowlingExtras = gameData.nathanMcCullum.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.nathanMcCullum.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.nathanMcCullum.getId());
		assertEquals(17, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(3, zimbabweTeam.getNumberOfOversByBowler(gameData.nathanMcCullum.getId()));
		// TODO find a way to show values to 2dp
		assertEquals(5.6666665f,
				(Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.nathanMcCullum.getId())), 0);
		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.nathanMcCullum.getId()));
	}

	@Test
	public void testCheckWoodcock() {
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.woodcock.getId()));

		int bowlingExtras = gameData.woodcock.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.woodcock.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.woodcock.getId());
		assertEquals(30, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(4, zimbabweTeam.getNumberOfOversByBowler(gameData.woodcock.getId()));
		assertEquals(7.5f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.woodcock.getId())), 0);
		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.woodcock.getId()));
	}

	@Test
	public void testCheckMills() {
		assertEquals(2, zimbabweTeam.getWicketsForBowler(gameData.mills.getId()));

		int bowlingExtras = gameData.mills.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.mills.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.mills.getId());
		assertEquals(15, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(4, zimbabweTeam.getNumberOfOversByBowler(gameData.mills.getId()));
		assertEquals(3.75f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.mills.getId())), 0);
		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.mills.getId()));
	}

	@Test
	public void testCheckFranklin() {
		assertEquals(0, zimbabweTeam.getWicketsForBowler(gameData.franklin.getId()));

		int bowlingExtras = gameData.franklin.getBowlingScore().getBowlerExtras();
		assertEquals(2, bowlingExtras);
		assertEquals(0, gameData.franklin.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.franklin.getId());
		assertEquals(10, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(1, zimbabweTeam.getNumberOfOversByBowler(gameData.franklin.getId()));
		assertEquals(10f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.franklin.getId())), 0);

		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.franklin.getId()));
	}

	@Test
	public void testCheckBracewell() {
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.bracewell.getId()));

		int bowlingExtras = gameData.bracewell.getBowlingScore().getBowlerExtras();
		assertEquals(1, bowlingExtras);
		assertEquals(0, gameData.bracewell.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.bracewell.getId());
		assertEquals(26, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(4, zimbabweTeam.getNumberOfOversByBowler(gameData.bracewell.getId()));
		assertEquals(6.50f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.bracewell.getId())), 0);
		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.bracewell.getId()));
	}

	@Test
	public void testCheckOram() {
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.oram.getId()));

		int bowlingExtras = gameData.oram.getBowlingScore().getBowlerExtras();
		assertEquals(1, bowlingExtras);
		assertEquals(0, gameData.oram.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = zimbabweTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.oram.getId());
		assertEquals(25, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(4, zimbabweTeam.getNumberOfOversByBowler(gameData.oram.getId()));
		assertEquals(6.25f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / zimbabweTeam.getNumberOfOversByBowler(gameData.oram.getId())), 0);
		assertEquals(0, zimbabweTeam.getMaidensForBowler(gameData.oram.getId()));
	}

	@Test
	public void testGetCatchesForPlayer() {
		assertEquals(1, zimbabweTeam.getCatchesForPlayer(gameData.nathanMcCullum.getId()));
		assertEquals(2, zimbabweTeam.getCatchesForPlayer(gameData.brendonMcCullum.getId()));
		assertEquals(1, zimbabweTeam.getCatchesForPlayer(gameData.woodcock.getId()));
		assertEquals(2, zimbabweTeam.getCatchesForPlayer(gameData.rossTaylor.getId()));

		assertEquals(0, zimbabweTeam.getCatchesForPlayer(gameData.franklin.getId()));
	}

	@Test
	public void testGetWicketsByDismissal() {
		assertEquals(6, zimbabweTeam.getWicketsByDismissal(DismisalType.CAUGHT));
		assertEquals(1, zimbabweTeam.getWicketsByDismissal(DismisalType.LBW));

		int runout = zimbabweTeam.getWicketsByDismissal(DismisalType.RUNOUT) + zimbabweTeam.getWicketsByDismissal(DismisalType.NON_STRIKER_RUNOUT);
		assertEquals(1, runout);

		assertEquals(0, zimbabweTeam.getWicketsByDismissal(DismisalType.BOWLED));
	}

	@Test
	public void testGetAllWickets() {
		assertEquals(8, zimbabweTeam.getWicketsLost());
	}

	@Test
	public void testGetWicketsForPlayer() {
		assertEquals(2, zimbabweTeam.getWicketsForBowler(gameData.nathanMcCullum.getId()));
		assertEquals(2, zimbabweTeam.getWicketsForBowler(gameData.mills.getId()));
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.bracewell.getId()));
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.oram.getId()));
		assertEquals(1, zimbabweTeam.getWicketsForBowler(gameData.woodcock.getId()));
	}

	@Test
	public void testGetWicketsForPlayerByDismisal() {
		assertEquals(2, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.nathanMcCullum.getId(), DismisalType.CAUGHT));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.nathanMcCullum.getId(), DismisalType.BOWLED));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.nathanMcCullum.getId(), DismisalType.LBW));

		assertEquals(2, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.mills.getId(), DismisalType.CAUGHT));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.mills.getId(), DismisalType.BOWLED));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.mills.getId(), DismisalType.LBW));

		assertEquals(1, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.CAUGHT));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.BOWLED));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.LBW));

		assertEquals(1, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.oram.getId(), DismisalType.CAUGHT));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.oram.getId(), DismisalType.BOWLED));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.oram.getId(), DismisalType.LBW));

		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.woodcock.getId(), DismisalType.CAUGHT));
		assertEquals(0, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.woodcock.getId(), DismisalType.BOWLED));
		assertEquals(1, zimbabweTeam.getWicketsForBowlerByDismisal(gameData.woodcock.getId(), DismisalType.LBW));
	}

	@Test
	public void testRunRate() {
		float runsScored = zimbabweTeam.getRunsScored() + nzTeam.getAllExtras();
		float numberOfOvers = zimbabweTeam.getNumberOfOvers();
		numberOfOvers++; //because overs start from 0
		assertEquals(6.15f, runsScored / numberOfOvers, 0);
	}
}