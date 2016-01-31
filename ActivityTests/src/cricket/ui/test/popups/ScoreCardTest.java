package cricket.ui.test.popups;

import game.Game;
import game.Player;
import game.Team;
import android.widget.TextView;
import beans.BattingOverBean;
import beans.BattingWicketBean;

import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.R;
import cricket.ui.popups.ScoreCard;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class ScoreCardTest extends TestBase<ScoreCard> {
	
	private Team battingTeam;
	private Team bowlingTeam;
	private Player batsman1;
	private Player bowler1;
	
	public ScoreCardTest() {
		super(ScoreCard.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mInstrumentation = getInstrumentation();
		
		Game game = new Game();
		
		battingTeam = game.getTeam(Game.TEAM_ONE);
		battingTeam.setTeamBattingStatus(TeamBattingStatus.Batting);

		bowlingTeam = game.getTeam(Game.TEAM_TWO);
		bowlingTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);
		
		bowler1 = new Player("bowler1", 2);
		bowler1.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		bowlingTeam.addPlayer(bowler1);
		
		batsman1 = new Player("batsman1", 1);
		batsman1.getBattingScore().setBattingSlot(1);
		batsman1.setBattingStatus(BattingStatus.Striker);
		battingTeam.addPlayer(batsman1);
		
		addExtraTestDataForBowlerTotals();
		
		BattingOverBean bean = new BattingOverBean(1, 2);
		bean.addRun(1);
		bean.addRun(2);
		bean.addRun(3);
		bean.addRun(4);
		bean.addRun(5);
		bean.addRun(6);
		bean.addRun(0);
		bean.addRun(0);
		batsman1.getBattingScore().addBattingOver(bean); 
		
		extras.putSerializable(Keys.GAME, game);
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
	}
	
	//This stuff is mainly to verify the figures for the bowler.
	private void addExtraTestDataForBowlerTotals() {
		Player batsman2 = new Player("batsman2", 3);
		batsman2.setBattingStatus(BattingStatus.Out);
		BattingWicketBean battingWicketBean = new BattingWicketBean(2, DismisalType.BOWLED, 1, 1, 1);
		batsman2.getBattingScore().setBattingWicketBean(battingWicketBean);
		battingTeam.addPlayer(batsman2);
		
		BattingOverBean maiden = new BattingOverBean(2, 2);
		maiden.addRun(0);
		maiden.addRun(0);
		maiden.addRun(0);
		maiden.addRun(0);
		maiden.addRun(0);
		maiden.addRun(0);
		batsman2.getBattingScore().addBattingOver(maiden);
		
		bowler1.getBowlingScore().addNoBallAndExtras(0);
		bowler1.getBowlingScore().addWideAndExtras(1);
	}

	public void testBatsmanName() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_NAME_ID_PREFEIX+batsman1.getId());
		assertEquals("1. batsman1*", textView.getText());
	}
	
	public void testBatsmanRuns() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_RUNS_ID_PREFEIX+batsman1.getId());
		assertEquals("21", textView.getText());
	}
	
	public void testBatsmanStrikeRate() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_STRIKE_RATE_ID_PREFEIX+batsman1.getId());
		assertEquals("262.50", textView.getText());
	}
	
	public void testBatsmanBallsFaced() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_BALLS_FACED_ID_PREFEIX+batsman1.getId());
		assertEquals("8", textView.getText());
	}
	
	public void testBatsmanFours() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_FOURS_ID_PREFEIX+batsman1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBatsmanSixes() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BATSMAN_SIXES_ID_PREFEIX+batsman1.getId());
		assertEquals("1", textView.getText());
	}
	
	
	/**
	 * Bowler section
	 */
	public void testBowlerName() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_NAME_ID_PREFEIX+bowler1.getId());
		assertEquals("bowler1", textView.getText());
	}
	
	public void testBowlerOvers() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_OVERS_ID_PREFEIX+bowler1.getId());
		assertEquals("2", textView.getText());
	}
	
	public void testBowlerMaidens() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_MAIDENS_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBowlerRuns() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_RUNS_ID_PREFEIX+bowler1.getId());
		assertEquals("24", textView.getText());
	}
	
	public void testBowlerWickets() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_WICKETS_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBowlerEconomy() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_ECONOMY_ID_PREFEIX+bowler1.getId());
		assertEquals("12.00", textView.getText());
	}
	
	public void testBowlerEconomyIsFormattedCorrectly() {
		activity.finish();
		
		BattingOverBean bean = new BattingOverBean(3, 2);
		bean.addRun(1);
		bean.addRun(2);
		bean.addRun(3);
		bean.addRun(4);
		bean.addRun(5);
		bean.addRun(6);
		bean.addRun(2);
		bean.addRun(0);
		batsman1.getBattingScore().addBattingOver(bean); 
		
		extras.putSerializable(Keys.BATTING_TEAM, battingTeam);
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_ECONOMY_ID_PREFEIX+bowler1.getId());
		assertEquals("15.67", textView.getText());
	}
	
	public void testBowlerFours() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_FOURS_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBowlerSixes() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_SIXES_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBowlerWides() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_WIDES_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	public void testBowlerNoBalls() {
		TextView textView = (TextView) activity.findViewById(ScoreCard.BOWLER_NO_BALLS_ID_PREFEIX+bowler1.getId());
		assertEquals("1", textView.getText());
	}
	
	/**
	 * Totals section
	 */
	public void testTotals() {
		activity.finish();
		bowler1.getBowlingScore().addWideAndExtras(0); //one wide.
		bowler1.getBowlingScore().addNoBallAndExtras(0); 
		bowler1.getBowlingScore().addLegBye(2); 
		bowler1.getBowlingScore().addBye(1); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.extrasAndTotal);
		assertEquals("+ Extras: 8   Total: 29", textView.getText());
	}
	
	public void testRunsFromWides() {
		activity.finish();
		bowler1.getBowlingScore().addWideAndExtras(0); //one wide.
		bowler1.getBowlingScore().addWideAndExtras(2);
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.runsFromWides);
		assertEquals("W: 6", textView.getText());
	}
	
	public void testRunsFromNoBalls() {
		activity.finish();
		bowler1.getBowlingScore().addNoBallAndExtras(2); 
		bowler1.getBowlingScore().addNoBallAndExtras(0); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.runsFromNoBalls);
		assertEquals("NB: 5", textView.getText());
	}
	
	public void testRunsFromLegByes() {
		activity.finish();
		bowler1.getBowlingScore().addLegBye(2); 
		bowler1.getBowlingScore().addLegBye(1); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.runsFromLegByes);
		assertEquals("LB: 3", textView.getText());
	}
	
	public void testRunsFromByes() {
		activity.finish();
		bowler1.getBowlingScore().addBye(2); 
		bowler1.getBowlingScore().addBye(1); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.runsFromByes);
		assertEquals("B: 3", textView.getText());
	}
	
	public void testDots() {
		TextView textView = (TextView) activity.findViewById(R.id.dots);
		assertEquals("Dots: 8", textView.getText());
	}
	
	public void testOnes() {
		TextView textView = (TextView) activity.findViewById(R.id.ones);
		assertEquals("1's: 1", textView.getText());
	}
	
	public void testTwos() {
		TextView textView = (TextView) activity.findViewById(R.id.twos);
		assertEquals("2's: 1", textView.getText());
	}
	
	public void testThrees() {
		TextView textView = (TextView) activity.findViewById(R.id.threes);
		assertEquals("3's: 1", textView.getText());
	}
	
	public void testFours() {
		TextView textView = (TextView) activity.findViewById(R.id.fours);
		assertEquals("4's: 1", textView.getText());
	}
	
	public void testFives() {
		TextView textView = (TextView) activity.findViewById(R.id.fives);
		assertEquals("5's: 1", textView.getText());
	}
	
	public void testSixes() {
		TextView textView = (TextView) activity.findViewById(R.id.sixes);
		assertEquals("6's: 1", textView.getText());
	}
	
	public void testTotalWides() {
		activity.finish();
		bowler1.getBowlingScore().addWideAndExtras(0); 
		bowler1.getBowlingScore().addWideAndExtras(2); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.totalWides);
		assertEquals("# of Wides: 3", textView.getText());
	}
	
	public void testTotalNoBalls() {
		activity.finish();
		bowler1.getBowlingScore().addNoBallAndExtras(0); 
		bowler1.getBowlingScore().addNoBallAndExtras(2); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.totalNoBalls);
		assertEquals("# of No Balls: 3", textView.getText());
	}
	
	public void testTotalLegByes() {
		activity.finish();
		bowler1.getBowlingScore().addLegBye(0); 
		bowler1.getBowlingScore().addLegBye(2); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.totalLegByes);
		assertEquals("# of Leg Byes: 2", textView.getText());
	}
	
	public void testTotalByes() {
		activity.finish();
		bowler1.getBowlingScore().addBye(0); 
		bowler1.getBowlingScore().addBye(2); 
		
		activity = launchActivity("cricket.ui", ScoreCard.class, extras);
		
		TextView textView = (TextView) activity.findViewById(R.id.totalByes);
		assertEquals("# of Byes: 2", textView.getText());
	}
	
	public void testOnClose() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		clickButton(ButtonType.Done);
		getIntentWithResultCode(Keys.DEFAULT);
	}
}
