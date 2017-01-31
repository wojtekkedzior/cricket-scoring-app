package other.fiftyovers;

import static org.junit.Assert.assertEquals;
import game.Game;
import game.GameProcessor;

import common.BattingStatus;
import common.DismisalType;

public class NzInnings {

	private GameData50overs gameData;
	private Game game;

	public NzInnings(GameData50overs gameData, Game game) {
		this.gameData = gameData;
		this.game = game;

		int over = 0;

		nzOver1(over++);
		nzOver2(over++);
		nzOver3(over++);
		nzOver4(over++);
		nzOver5(over++);
		nzOver6(over++);
		nzOver7(over++);
		nzOver8(over++);
		nzOver9(over++);
		nzOver10(over++);

		nzOver11(over++);
		nzOver12(over++);
		nzOver13(over++);
		nzOver14(over++);
		nzOver15(over++);
		nzOver16(over++);
		nzOver17(over++);
		nzOver18(over++);
		nzOver19(over++);
		nzOver20(over++);

		nzOver21(over++);
		nzOver22(over++);
		nzOver23(over++);
		nzOver24(over++);
		nzOver25(over++);
		nzOver26(over++);
		nzOver27(over++);
		nzOver28(over++);
		nzOver29(over++);
		nzOver30(over++);

		nzOver31(over++);
		nzOver32(over++);
		nzOver33(over++);
		nzOver34(over++);
		nzOver35(over++);
		nzOver36(over++);
		nzOver37(over++);
		nzOver38(over++);
		nzOver39(over++);
		nzOver40(over++);

		nzOver41(over++);
		nzOver42(over++);
		nzOver43(over++);
		nzOver44(over++);
	}

	private void checkTeamAndOverTotals(int expectedTeamTotal, int overNumber, int runsInOver, int extrasInOver) {
		int battingRunsScored = game.getBattingTeam().getRunsScored();

		int bowlingRunsGiven = game.getBowlingTeam().getAllBowlingExtras();
		int allFieldingExtras = game.getBowlingTeam().getAllFieldingExtras();
		assertEquals(expectedTeamTotal, (battingRunsScored + bowlingRunsGiven + allFieldingExtras));

		int battingRunsScoredForOver = game.getBattingTeam().getRunsScoredForOver(overNumber);
		assertEquals(runsInOver, (battingRunsScoredForOver + extrasInOver));
	}

	private void nzOver1(int overNumber) {
		gameData.nzOver1();

		assertEquals(6, gameData.guptill.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.guptill.getBattingScore().getRuns());
		assertEquals(0, gameData.guptill.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.guptill.getBattingStatus());

		assertEquals(0, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		checkTeamAndOverTotals(1, overNumber, 1, 1);
	}

	private void nzOver2(int overNumber) {
		gameData.nzOver2();

		assertEquals(11, gameData.guptill.getBattingScore().getBallsFaced());
		assertEquals(12, gameData.guptill.getBattingScore().getRuns());
		assertEquals(12, gameData.guptill.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.guptill.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.guptill.getBattingStatus());

		assertEquals(1, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(0, gameData.williamson.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.williamson.getBattingScore().getRuns());
		assertEquals(0, gameData.williamson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.williamson.getBattingStatus());

		checkTeamAndOverTotals(14, overNumber, 13, 0);
	}

	private void nzOver3(int overNumber) {
		gameData.nzOver3();

		assertEquals(1, gameData.williamson.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.williamson.getBattingScore().getRuns());
		assertEquals(1, gameData.williamson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.williamson.getBattingStatus());

		assertEquals(6, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.latham.getBattingScore().getRuns());
		assertEquals(3, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		checkTeamAndOverTotals(18, overNumber, 4, 0);
	}

	private void nzOver4(int overNumber) {
		gameData.nzOver4();

		assertEquals(4, gameData.williamson.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.williamson.getBattingScore().getRuns());
		assertEquals(1, gameData.williamson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.williamson.getBattingStatus());

		assertEquals(9, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(12, gameData.latham.getBattingScore().getRuns());
		assertEquals(8, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		checkTeamAndOverTotals(27, overNumber, 9, 0);
	}

	private void nzOver5(int overNumber) {
		gameData.nzOver5();

		assertEquals(9, gameData.williamson.getBattingScore().getBallsFaced());
		assertEquals(3, gameData.williamson.getBattingScore().getRuns());
		assertEquals(1, gameData.williamson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.williamson.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.williamson.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(10, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(13, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(0, gameData.taylor.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.taylor.getBattingScore().getRuns());
		assertEquals(0, gameData.taylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.taylor.getBattingStatus());

		checkTeamAndOverTotals(29, overNumber, 2, 0);
	}

	private void nzOver6(int overNumber) {
		gameData.nzOver6();

		assertEquals(16, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(17, gameData.latham.getBattingScore().getRuns());
		assertEquals(4, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(0, gameData.taylor.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.taylor.getBattingScore().getRuns());
		assertEquals(0, gameData.taylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.taylor.getBattingStatus());

		checkTeamAndOverTotals(33, overNumber, 4, 0);
	}

	private void nzOver7(int overNumber) {
		gameData.nzOver7();

		assertEquals(16, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(17, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(1, gameData.taylor.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.taylor.getBattingScore().getRuns());
		assertEquals(0, gameData.taylor.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.taylor.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.taylor.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(5, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.anderson.getBattingScore().getRuns());
		assertEquals(0, gameData.anderson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.anderson.getBattingStatus());

		checkTeamAndOverTotals(35, overNumber, 2, 2);
	}

	private void nzOver8(int overNumber) {
		gameData.nzOver8();

		assertEquals(20, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(18, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(7, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.anderson.getBattingScore().getRuns());
		assertEquals(0, gameData.anderson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.anderson.getBattingStatus());

		checkTeamAndOverTotals(36, overNumber, 1, 0);
	}

	private void nzOver9(int overNumber) {
		gameData.nzOver9();

		assertEquals(21, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(19, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(12, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.anderson.getBattingScore().getRuns());
		assertEquals(4, gameData.anderson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.anderson.getBattingStatus());

		checkTeamAndOverTotals(41, overNumber, 5, 0);
	}

	private void nzOver10(int overNumber) {
		gameData.nzOver10();

		assertEquals(27, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(20, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(12, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.anderson.getBattingScore().getRuns());
		assertEquals(0, gameData.anderson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.anderson.getBattingStatus());

		checkTeamAndOverTotals(42, overNumber, 1, 0);
	}

	private void nzOver11(int overNumber) { // there is a bug here with the Striker and Non Stiker states at the end of the over
		gameData.nzOver11();

		assertEquals(29, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(21, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(14, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.anderson.getBattingScore().getRuns());
		assertEquals(0, gameData.anderson.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.anderson.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.anderson.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(2, gameData.ronchi.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.ronchi.getBattingScore().getRuns());
		assertEquals(0, gameData.ronchi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.ronchi.getBattingStatus());

		checkTeamAndOverTotals(43, overNumber, 1, 0);
	}

	private void nzOver12(int overNumber) {
		gameData.nzOver12();

		assertEquals(35, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(25, gameData.latham.getBattingScore().getRuns());
		assertEquals(4, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(2, gameData.ronchi.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.ronchi.getBattingScore().getRuns());
		assertEquals(0, gameData.ronchi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.ronchi.getBattingStatus());

		checkTeamAndOverTotals(48, overNumber, 5, 1);
	}

	private void nzOver13(int overNumber) {
		gameData.nzOver13();

		assertEquals(38, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(25, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(3, gameData.ronchi.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.ronchi.getBattingScore().getRuns());
		assertEquals(0, gameData.ronchi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.ronchi.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.ronchi.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(2, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.neesham.getBattingScore().getRuns());
		assertEquals(1, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(49, overNumber, 1, 0);
	}

	private void nzOver14(int overNumber) {
		gameData.nzOver14();

		assertEquals(40, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(27, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(6, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.neesham.getBattingScore().getRuns());
		assertEquals(1, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(53, overNumber, 4, 1);
	}

	private void nzOver15(int overNumber) {
		gameData.nzOver15();

		assertEquals(41, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(28, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(11, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(3, gameData.neesham.getBattingScore().getRuns());
		assertEquals(1, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(55, overNumber, 2, 0);
	}

	private void nzOver16(int overNumber) {
		gameData.nzOver16();

		assertEquals(45, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(29, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(13, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.neesham.getBattingScore().getRuns());
		assertEquals(1, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(57, overNumber, 2, 0);
	}

	private void nzOver17(int overNumber) {
		gameData.nzOver17();

		assertEquals(46, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(30, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(18, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.neesham.getBattingScore().getRuns());
		assertEquals(1, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(59, overNumber, 2, 0);
	}

	private void nzOver18(int overNumber) {
		gameData.nzOver18();

		assertEquals(48, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(30, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(22, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.neesham.getBattingScore().getRuns());
		assertEquals(5, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.neesham.getBattingStatus());

		checkTeamAndOverTotals(65, overNumber, 6, 1);
	}

	private void nzOver19(int overNumber) {
		gameData.nzOver19();

		assertEquals(49, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(30, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(25, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.neesham.getBattingScore().getRuns());
		assertEquals(0, gameData.neesham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.neesham.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.neesham.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(1, gameData.santner.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.santner.getBattingScore().getRuns());
		assertEquals(0, gameData.santner.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.santner.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.santner.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(1, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(66, overNumber, 1, 0);
	}

	private void nzOver20(int overNumber) {
		gameData.nzOver20();

		assertEquals(50, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(31, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(6, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(68, overNumber, 2, 0);
	}

	private void nzOver21(int overNumber) {
		gameData.nzOver21();

		assertEquals(53, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(9, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(3, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(71, overNumber, 3, 0);
	}

	private void nzOver22(int overNumber) {
		gameData.nzOver22();

		assertEquals(55, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(34, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(13, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(73, overNumber, 2, 0);
	}

	private void nzOver23(int overNumber) {
		gameData.nzOver23();

		assertEquals(60, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(37, gameData.latham.getBattingScore().getRuns());
		assertEquals(3, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(14, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(78, overNumber, 5, 1);
	}

	private void nzOver24(int overNumber) {
		gameData.nzOver24();

		assertEquals(61, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(38, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(19, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(0, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(79, overNumber, 1, 0);
	}

	private void nzOver25(int overNumber) {
		gameData.nzOver25();

		assertEquals(65, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(39, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(21, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(6, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(81, overNumber, 2, 0);
	}

	private void nzOver26(int overNumber) {
		gameData.nzOver26();

		assertEquals(67, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(40, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(25, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(7, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(83, overNumber, 2, 0);
	}

	private void nzOver27(int overNumber) {
		gameData.nzOver27();

		assertEquals(69, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(42, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(29, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(2, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(88, overNumber, 5, 1);
	}

	private void nzOver28(int overNumber) {
		gameData.nzOver28();

		assertEquals(73, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(47, gameData.latham.getBattingScore().getRuns());
		assertEquals(5, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(31, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(2, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(95, overNumber, 7, 0);
	}

	private void nzOver29(int overNumber) {
		gameData.nzOver29();

		assertEquals(76, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(49, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(34, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(13, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(2, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(99, overNumber, 4, 0);
	}

	private void nzOver30(int overNumber) {
		gameData.nzOver30();

		assertEquals(78, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(51, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(38, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(102, overNumber, 3, 0);
	}

	private void nzOver31(int overNumber) {
		gameData.nzOver31();

		assertEquals(80, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(53, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(42, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(15, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(1, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.bracewell.getBattingStatus());

		checkTeamAndOverTotals(105, overNumber, 3, 0);
	}

	private void nzOver32(int overNumber) {
		gameData.nzOver32();

		assertEquals(81, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(54, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(46, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(15, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(0, gameData.bracewell.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.bracewell.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.bracewell.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(1, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.southee.getBattingScore().getRuns());
		assertEquals(1, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus()); // BUG

		checkTeamAndOverTotals(107, overNumber, 2, 0);
	}

	private void nzOver33(int overNumber) {
		gameData.nzOver33();

		assertEquals(81, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(54, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(7, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.southee.getBattingScore().getRuns());
		assertEquals(0, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.southee.getBattingStatus()); // BUG

		checkTeamAndOverTotals(107, overNumber, 0, 0);
	}

	private void nzOver34(int overNumber) {
		gameData.nzOver34();

		assertEquals(83, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(55, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(11, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.southee.getBattingScore().getRuns());
		assertEquals(1, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(109, overNumber, 2, 0);
	}

	private void nzOver35(int overNumber) {
		gameData.nzOver35();

		assertEquals(84, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(56, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(16, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.southee.getBattingScore().getRuns());
		assertEquals(9, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(119, overNumber, 10, 0);
	}

	private void nzOver36(int overNumber) {
		gameData.nzOver36();

		assertEquals(86, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(58, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(20, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(17, gameData.southee.getBattingScore().getRuns());
		assertEquals(6, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(127, overNumber, 8, 0);
	}

	private void nzOver37(int overNumber) {
		gameData.nzOver37();

		assertEquals(87, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(59, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(25, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(24, gameData.southee.getBattingScore().getRuns());
		assertEquals(7, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(135, overNumber, 8, 0);
	}

	private void nzOver38(int overNumber) {
		gameData.nzOver38();

		assertEquals(89, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(60, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(29, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(31, gameData.southee.getBattingScore().getRuns());
		assertEquals(7, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(143, overNumber, 8, 0);
	}

	private void nzOver39(int overNumber) {
		gameData.nzOver39();

		assertEquals(89, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(60, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(35, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(37, gameData.southee.getBattingScore().getRuns());
		assertEquals(6, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(151, overNumber, 8, 2);
	}

	private void nzOver40(int overNumber) {
		gameData.nzOver40();

		assertEquals(91, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(62, gameData.latham.getBattingScore().getRuns());
		assertEquals(2, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(39, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(49, gameData.southee.getBattingScore().getRuns());
		assertEquals(12, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(165, overNumber, 14, 0);
	}

	private void nzOver41(int overNumber) {
		gameData.nzOver41();

		assertEquals(94, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(67, gameData.latham.getBattingScore().getRuns());
		assertEquals(5, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(42, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(51, gameData.southee.getBattingScore().getRuns());
		assertEquals(2, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.southee.getBattingStatus());

		checkTeamAndOverTotals(173, overNumber, 8, 1);
	}

	private void nzOver42(int overNumber) {
		gameData.nzOver42();

		assertEquals(95, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(68, gameData.latham.getBattingScore().getRuns());
		assertEquals(1, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(45, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(55, gameData.southee.getBattingScore().getRuns());
		assertEquals(4, gameData.southee.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.southee.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.southee.getBattingScore().getBattingWicketBean().getDismisalType());

		assertEquals(2, gameData.sodhi.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.sodhi.getBattingScore().getRuns());
		assertEquals(1, gameData.sodhi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.sodhi.getBattingStatus());

		checkTeamAndOverTotals(179, overNumber, 6, 0);
	}

	private void nzOver43(int overNumber) {
		gameData.nzOver43();

		assertEquals(95, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(68, gameData.latham.getBattingScore().getRuns());
		assertEquals(0, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.latham.getBattingStatus());

		assertEquals(8, gameData.sodhi.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.sodhi.getBattingScore().getRuns());
		assertEquals(0, gameData.sodhi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sodhi.getBattingStatus());

		checkTeamAndOverTotals(179, overNumber, 0, 0);
	}

	private void nzOver44(int overNumber) {
		gameData.nzOver44();

		assertEquals(98, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(79, gameData.latham.getBattingScore().getRuns());
		assertEquals(11, gameData.latham.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.latham.getBattingStatus());

		assertEquals(10, gameData.sodhi.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.sodhi.getBattingScore().getRuns());
		assertEquals(0, gameData.sodhi.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.sodhi.getBattingStatus());
		assertEquals(DismisalType.LBW, gameData.sodhi.getBattingScore().getBattingWicketBean().getDismisalType());

		checkTeamAndOverTotals(190, overNumber, 11, 0);
	}
}