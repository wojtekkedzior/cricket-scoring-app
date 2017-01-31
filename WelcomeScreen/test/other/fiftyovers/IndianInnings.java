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
		indiaOver35(over++);
		indiaOver36(over++);
		indiaOver37(over++);
		indiaOver38(over++);
		indiaOver39(over++);
		indiaOver40(over++);

		indiaOver41(over++);
		indiaOver42(over++);
		indiaOver43(over++);
		indiaOver44(over++);
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
		// TODO Auto-generated method stub
		
	}

	private void indiaOver4(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver5(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver6(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver7(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver8(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver9(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver10(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver11(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver12(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver13(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver14(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver15(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver16(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver17(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver18(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver19(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver20(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver21(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver22(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver23(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver24(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver25(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver26(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver27(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver28(int overNumber) {
		// TODO Auto-generated method stub
		
	}
	
	private void indiaOver29(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver30(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver31(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver32(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver33(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver34(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver35(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver36(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver37(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver38(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver39(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver40(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver41(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver42(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver43(int overNumber) {
		// TODO Auto-generated method stub
		
	}

	private void indiaOver44(int overNumber) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
