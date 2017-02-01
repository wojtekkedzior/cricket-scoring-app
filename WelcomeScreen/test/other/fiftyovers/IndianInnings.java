package other.fiftyovers;

import static org.junit.Assert.assertEquals;
import game.Game;

import common.BattingStatus;
import common.DismisalType;

public class IndianInnings {

	private GameData50overs gameData;
	private Game game;

	public IndianInnings(GameData50overs gameData, Game game) {
		this.gameData = gameData;
		this.game = game;

		int over = 0;

		indiaOver1(over++);
		indiaOver2(over++);
		indiaOver3(over++);
		indiaOver4(over++);
		indiaOver5(over++);
		indiaOver6(over++);
		indiaOver7(over++);
		indiaOver8(over++);
		indiaOver9(over++);
		indiaOver10(over++);

		indiaOver11(over++);
		indiaOver12(over++);
		indiaOver13(over++);
		indiaOver14(over++);
		indiaOver15(over++);
		indiaOver16(over++);
		indiaOver17(over++);
		indiaOver18(over++);
		indiaOver19(over++);
		indiaOver20(over++);

		indiaOver21(over++);
		indiaOver22(over++);
		indiaOver23(over++);
		indiaOver24(over++);
		indiaOver25(over++);
		indiaOver26(over++);
		indiaOver27(over++);
		indiaOver28(over++);
		indiaOver29(over++);
		indiaOver30(over++);

		indiaOver31(over++);
		indiaOver32(over++);
		indiaOver33(over++);
		indiaOver34(over++);
	}
	
	private void checkTeamAndOverTotals(int expectedTeamTotal, int overNumber, int runsInOver, int extrasInOver) {
		int battingRunsScored = game.getBattingTeam().getRunsScored();

		int bowlingRunsGiven = game.getBowlingTeam().getAllBowlingExtras();
		int allFieldingExtras = game.getBowlingTeam().getAllFieldingExtras();
		assertEquals(expectedTeamTotal, (battingRunsScored + bowlingRunsGiven + allFieldingExtras));

		int battingRunsScoredForOver = game.getBattingTeam().getRunsScoredForOver(overNumber);
		assertEquals(runsInOver, (battingRunsScoredForOver + extrasInOver));
	}

	private void indiaOver1(int overNumber) {
		gameData.indiaOver1();

		assertEquals(6, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.sharma.getBattingScore().getRuns());
		assertEquals(4, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(0, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(4, overNumber, 4, 0);
		
	}

	private void indiaOver2(int overNumber) {
		gameData.indiaOver2();

		assertEquals(8, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(4, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.rahane.getBattingScore().getRuns());
		assertEquals(1, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver3(int overNumber) {
		gameData.indiaOver3();
		assertEquals(9, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(9, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(6, gameData.rahane.getBattingScore().getRuns());
		assertEquals(5, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(11, overNumber, 6, 1);
		
	}

	private void indiaOver4(int overNumber) {
		gameData.indiaOver4();
		assertEquals(9, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.sharma.getBattingStatus());

		assertEquals(15, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.rahane.getBattingScore().getRuns());
		assertEquals(8, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(19, overNumber, 8, 0);
		
	}

	private void indiaOver5(int overNumber) {
		gameData.indiaOver5();
		assertEquals(15, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.sharma.getBattingScore().getRuns());
		assertEquals(6, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(15, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(25, overNumber, 6, 0);
		
	}

	private void indiaOver6(int overNumber) {
		gameData.indiaOver6();
		
		assertEquals(15, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.sharma.getBattingStatus());

		assertEquals(21, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(26, gameData.rahane.getBattingScore().getRuns());
		assertEquals(12, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(37, overNumber, 12, 0);
	}

	private void indiaOver7(int overNumber) {
		gameData.indiaOver7();
		
		assertEquals(16, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.sharma.getBattingScore().getRuns());
		assertEquals(1, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.sharma.getBattingStatus());

		assertEquals(26, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(32, gameData.rahane.getBattingScore().getRuns());
		assertEquals(6, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(44, overNumber, 7, 0);
	}

	private void indiaOver8(int overNumber) {
		gameData.indiaOver8();
		
		assertEquals(22, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(13, gameData.sharma.getBattingScore().getRuns());
		assertEquals(2, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(26, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(32, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());

		checkTeamAndOverTotals(46, overNumber, 2, 0);
	}

	private void indiaOver9(int overNumber) {
		gameData.indiaOver9();
		
		assertEquals(24, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.sharma.getBattingScore().getRuns());
		assertEquals(1, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.sharma.getBattingStatus());

		assertEquals(30, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(1, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.rahane.getBattingStatus());
		
		checkTeamAndOverTotals(49, overNumber, 3, 1);
		
	}

	private void indiaOver10(int overNumber) {
		gameData.indiaOver10();
		
		assertEquals(26, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(14, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.LBW, gameData.sharma.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.sharma.getBattingStatus());
		
		assertEquals(30, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.kohli.getBattingScore().getRuns());
		assertEquals(0, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());

		checkTeamAndOverTotals(49, overNumber, 0, 0);
	}

	private void indiaOver11(int overNumber) {
		gameData.indiaOver11();
		
		assertEquals(33, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.rahane.getBattingStatus());
		
		assertEquals(7, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.kohli.getBattingScore().getRuns());
		assertEquals(5, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());

		checkTeamAndOverTotals(62, overNumber, 13, 8);
	}

	private void indiaOver12(int overNumber) {
		gameData.indiaOver12();
		
		assertEquals(33, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());
		
		assertEquals(13, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.kohli.getBattingScore().getRuns());
		assertEquals(0, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		checkTeamAndOverTotals(62, overNumber, 0, 0);
	}

	private void indiaOver13(int overNumber) {
		gameData.indiaOver13();
		
		assertEquals(34, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.rahane.getBattingStatus());
		assertEquals(DismisalType.CAUGHT, gameData.rahane.getBattingScore().getBattingWicketBean().getDismisalType());
		
		assertEquals(13, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.kohli.getBattingScore().getRuns());
		assertEquals(0, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(5, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.pandey.getBattingScore().getRuns());
		assertEquals(4, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(67, overNumber, 5, 1);
	}

	private void indiaOver14(int overNumber) {
		gameData.indiaOver14();
		assertEquals(18, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(13, gameData.kohli.getBattingScore().getRuns());
		assertEquals(8, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(6, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(77, overNumber, 10, 1);
	}

	private void indiaOver15(int overNumber) {
		gameData.indiaOver15();		
		assertEquals(22, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(16, gameData.kohli.getBattingScore().getRuns());
		assertEquals(3, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(8, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.pandey.getBattingScore().getRuns());
		assertEquals(0, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(80, overNumber, 3, 0);
	}

	private void indiaOver16(int overNumber) {
		gameData.indiaOver16();	
		assertEquals(25, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(18, gameData.kohli.getBattingScore().getRuns());
		assertEquals(2, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(11, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(7, gameData.pandey.getBattingScore().getRuns());
		assertEquals(2, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(84, overNumber, 4, 0);
	}

	private void indiaOver17(int overNumber) {
		gameData.indiaOver17();		
		assertEquals(28, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(19, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(14, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(9, gameData.pandey.getBattingScore().getRuns());
		assertEquals(2, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(87, overNumber, 3, 0);
	}

	private void indiaOver18(int overNumber) {
		gameData.indiaOver18();		
		assertEquals(31, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(22, gameData.kohli.getBattingScore().getRuns());
		assertEquals(3, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(17, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.pandey.getBattingScore().getRuns());
		assertEquals(2, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(92, overNumber, 5, 0);
	}

	private void indiaOver19(int overNumber) {
		gameData.indiaOver19();		
		
		assertEquals(34, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(24, gameData.kohli.getBattingScore().getRuns());
		assertEquals(2, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(20, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(16, gameData.pandey.getBattingScore().getRuns());
		assertEquals(5, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());
		
		checkTeamAndOverTotals(100, overNumber, 8, 1);
	}

	private void indiaOver20(int overNumber) {
		gameData.indiaOver20();
		
		assertEquals(37, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(29, gameData.kohli.getBattingScore().getRuns());
		assertEquals(5, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(22, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(17, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(DismisalType.CAUGHT, gameData.pandey.getBattingScore().getBattingWicketBean().getDismisalType());
		assertEquals(BattingStatus.Out, gameData.pandey.getBattingStatus());
		
		assertEquals(1, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());
		
		checkTeamAndOverTotals(107, overNumber, 7, 0);
	}

	private void indiaOver21(int overNumber) {
		gameData.indiaOver21();		
		assertEquals(42, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(39, gameData.kohli.getBattingScore().getRuns());
		assertEquals(10, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(2, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(2, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(118, overNumber, 11, 0);
	}

	private void indiaOver22(int overNumber) {
		gameData.indiaOver22();		
		
		assertEquals(43, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(39, gameData.kohli.getBattingScore().getRuns());
		assertEquals(0, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(7, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(3, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(119, overNumber, 1, 0);
	}

	private void indiaOver23(int overNumber) {
		gameData.indiaOver23();		
		
		assertEquals(46, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(41, gameData.kohli.getBattingScore().getRuns());
		assertEquals(2, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(10, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(5, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(2, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(123, overNumber, 4, 0);
	}

	private void indiaOver24(int overNumber) {
		gameData.indiaOver24();		
		
		assertEquals(48, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(46, gameData.kohli.getBattingScore().getRuns());
		assertEquals(5, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(14, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(11, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(6, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(135, overNumber, 12, 1);
	}

	private void indiaOver25(int overNumber) {
		gameData.indiaOver25();		
		
		assertEquals(52, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(47, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(16, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(12, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(137, overNumber, 2, 0);
	}

	private void indiaOver26(int overNumber) {
		gameData.indiaOver26();		
		
		assertEquals(55, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(50, gameData.kohli.getBattingScore().getRuns());
		assertEquals(3, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(19, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(19, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(7, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(147, overNumber, 10, 0);
	}

	private void indiaOver27(int overNumber) {
		gameData.indiaOver27();		
		
		assertEquals(59, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(52, gameData.kohli.getBattingScore().getRuns());
		assertEquals(2, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(21, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(20, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(150, overNumber, 3, 0);
	}

	private void indiaOver28(int overNumber) {
		gameData.indiaOver28();		
		
		assertEquals(65, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(62, gameData.kohli.getBattingScore().getRuns());
		assertEquals(10, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(21, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(20, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(0, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());
		
		checkTeamAndOverTotals(160, overNumber, 10, 0);
	}
	
	private void indiaOver29(int overNumber) {
		gameData.indiaOver29();		
		
		assertEquals(66, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(63, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(24, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(21, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Out, gameData.dhoni.getBattingStatus());
		assertEquals(DismisalType.RUNOUT, gameData.dhoni.getBattingScore().getBattingWicketBean().getDismisalType());
		
		assertEquals(2, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(0, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(162, overNumber,2, 0);
	}

	private void indiaOver30(int overNumber) {
		gameData.indiaOver30();		
		
		assertEquals(72, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(70, gameData.kohli.getBattingScore().getRuns());
		assertEquals(7, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(2, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(0, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(169, overNumber, 7, 0);
	}

	private void indiaOver31(int overNumber) {
		gameData.indiaOver31();		
		assertEquals(76, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(72, gameData.kohli.getBattingScore().getRuns());
		assertEquals(2, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(172, overNumber, 3, 0);
	}

	private void indiaOver32(int overNumber) {
		gameData.indiaOver32();
		assertEquals(80, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(79, gameData.kohli.getBattingScore().getRuns());
		assertEquals(7, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.kohli.getBattingStatus());
		
		assertEquals(6, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(6, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(5, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(184, overNumber, 12, 0);
	}

	private void indiaOver33(int overNumber) {
		gameData.indiaOver33();		
		assertEquals(80, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(79, gameData.kohli.getBattingScore().getRuns());
		assertEquals(0, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(12, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(4, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(188, overNumber, 4, 0);
	}

	private void indiaOver34(int overNumber) {
		gameData.indiaOver34();
		assertEquals(81, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(85, gameData.kohli.getBattingScore().getRuns());
		assertEquals(6, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(12, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(10, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(0, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(194, overNumber, 6, 0);
	}
}