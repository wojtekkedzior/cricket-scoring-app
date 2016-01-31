package common;

import java.io.Serializable;
 
public final class Keys implements Serializable {

	private static final long serialVersionUID = -8364200795581153783L;

	public static final int DEFAULT = 0;
	public static final int SCORING_SCREEN = 1;
	public static final int WICKET_SCREEN = 2;
	public static final int NEW_BOWLER_SCREEN = 3;
	public static final int SCORE_CARD = 4;
	public static final int CUSTOM_SETTINGS = 5;
	public static final int CREATE_TEAM_SCREEN = 6;
	public static final int NEW_INNINGS_SCREEN = 7;
	
	public static final String EXTRA_TYPE = "EXTRA_TYPE";
	public static final String RUNS = "RUNS";

	public static final String RAW_Y = "RAW_Y";
	public static final String RAW_X = "RAW_X";
	
	public static final String BATTING_TEAM_INDEX = "battingTeamIndex";
	public static final String GAME = "game";
	public static final String BOWLER = "BOWLER";
	public static final String BATSMAN = "BATSMAN";
	public static final String BOWLER_ID = "BOWELER_ID";
	public static final String BALL_TYPE = "BALL_TYPE";
	public static final String DISMISSED_BATSMAN = "dismissedBatsman";
	public static final String DISMISSAL_TYPE = "dismissalType";
	public static final String STRIKING_BATSMAN = "strikingBatsman";
	public static final String NON_STRIKING_BATSMAN = "nonStrikingBatsman";
	public static final String FIELDERS = "fielders";
	public static final String FIELDER = "fielder";
	public static final String BOWLERS = "bowlers";
	public static final String CURRENT_BOWLER = "currentBowler";
	public static final String OPENING_BATSMAN_1 = "openingBatsman1";
	public static final String OPENING_BATSMAN_2 = "openingBatsman2";
	public static final String BATTING_TEAM = "battingTeam";
	public static final String BOWLING_TEAM = "bowlingTeam";
	public static final String IS_WICKET = "is_wicket";
	public static final String ALL_OUT = "allOut";
	
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;

	//First team
	public static final Integer TEAM_1_SUMMARY = 1;

	public static final Integer TEAM_1_TOP_BATSMAN = 2;
	public static final Integer TEAM_1_SECOND_BATSMAN = 3;

	public static final Integer TEAM_2_TOP_BOWLER = 4;
	public static final Integer TEAM_2_SECOND_BOWLER = 5;


	//Second team
	public static final Integer TEAM_2_SUMMARY = 7;

	public static final Integer TEAM_2_TOP_BATSMAN = 8;
	public static final Integer TEAM_2_SECOND_BATSMAN =9;

	public static final Integer TEAM_1_TOP_BOWLER = 10;
	public static final Integer TEAM_1_SECOND_BOWLER = 11;

	public static final Integer WINNING_TEAM = 12;


	


} 
