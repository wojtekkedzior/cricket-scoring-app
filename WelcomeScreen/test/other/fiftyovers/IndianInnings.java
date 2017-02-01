package other.fiftyovers;

import static org.junit.Assert.assertEquals;

import common.BattingStatus;

import game.Game;
import game.GameProcessor;

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
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());

		assertEquals(27, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(1, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.rahane.getBattingStatus());
		
		checkTeamAndOverTotals(49, overNumber, 3, 1);
		
	}

	private void indiaOver10(int overNumber) {
		gameData.indiaOver10();
		
		assertEquals(8, gameData.sharma.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.sharma.getBattingScore().getRuns());
		assertEquals(0, gameData.sharma.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());
		
		assertEquals(8, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());

		
		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver11(int overNumber) {
		gameData.indiaOver11();
		
		assertEquals(8, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
		
	}

	private void indiaOver12(int overNumber) {
		gameData.indiaOver12();
		
		assertEquals(8, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(4, gameData.rahane.getBattingScore().getRuns());
		assertEquals(0, gameData.rahane.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.NonStriker, gameData.sharma.getBattingStatus());
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver13(int overNumber) {
		gameData.indiaOver13();
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver14(int overNumber) {
		gameData.indiaOver14();
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver15(int overNumber) {
		gameData.indiaOver15();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver16(int overNumber) {
		gameData.indiaOver16();	
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver17(int overNumber) {
		gameData.indiaOver17();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver18(int overNumber) {
		gameData.indiaOver18();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver19(int overNumber) {
		gameData.indiaOver19();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.pandey.getBattingScore().getRuns());
		assertEquals(1, gameData.pandey.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver20(int overNumber) {
		gameData.indiaOver20();
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver21(int overNumber) {
		gameData.indiaOver21();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver22(int overNumber) {
		gameData.indiaOver22();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver23(int overNumber) {
		gameData.indiaOver23();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver24(int overNumber) {
		gameData.indiaOver24();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver25(int overNumber) {
		gameData.indiaOver25();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver26(int overNumber) {
		gameData.indiaOver26();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver27(int overNumber) {
		gameData.indiaOver27();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.dhoni.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver28(int overNumber) {
		gameData.indiaOver28();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(1, gameData.dhoni.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.pandey.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}
	
	private void indiaOver29(int overNumber) {
		gameData.indiaOver29();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver30(int overNumber) {
		gameData.indiaOver30();		
		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver31(int overNumber) {
		gameData.indiaOver31();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver32(int overNumber) {
		gameData.indiaOver32();
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(5, overNumber, 1, 0);
	}

	private void indiaOver33(int overNumber) {
		gameData.indiaOver33();		
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(188, overNumber, 1, 0);
	}

	private void indiaOver34(int overNumber) {
		gameData.indiaOver34();
		assertEquals(4, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.kohli.getBattingScore().getRuns());
		assertEquals(1, gameData.kohli.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.kohli.getBattingStatus());
		
		assertEquals(4, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(1, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(1, gameData.jadhav.getBattingScore().getRunsForOver(overNumber));
		assertEquals(BattingStatus.Striker, gameData.jadhav.getBattingStatus());

		checkTeamAndOverTotals(196, overNumber, 1, 0);
	}

	
	
}
