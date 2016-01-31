package cricket.ui.popups;

import game.BattingScore;
import game.Game;
import game.Player;
import game.Team;

import java.util.List;
import java.util.Map.Entry;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class ScoreCard extends GenericActivity {
	
	public static final int BATSMAN_NAME_ID_PREFEIX = 100;
	public static final int BATSMAN_RUNS_ID_PREFEIX = 200;
	public static final int BATSMAN_STRIKE_RATE_ID_PREFEIX = 300;
	public static final int BATSMAN_BALLS_FACED_ID_PREFEIX = 400;
	public static final int BATSMAN_FOURS_ID_PREFEIX = 500;
	public static final int BATSMAN_SIXES_ID_PREFEIX = 600;
	
	public static final int BOWLER_NAME_ID_PREFEIX = 150;
	public static final int BOWLER_OVERS_ID_PREFEIX = 250;
	public static final int BOWLER_MAIDENS_ID_PREFEIX = 350;
	public static final int BOWLER_RUNS_ID_PREFEIX = 450;
	public static final int BOWLER_WICKETS_ID_PREFEIX = 550;
	public static final int BOWLER_ECONOMY_ID_PREFEIX = 650;
	public static final int BOWLER_FOURS_ID_PREFEIX = 750;
	public static final int BOWLER_SIXES_ID_PREFEIX = 850;
	public static final int BOWLER_WIDES_ID_PREFEIX = 950;
	public static final int BOWLER_NO_BALLS_ID_PREFEIX = 1050;
	
	private Team battingTeam;
	private Team bowlingTeam;
	private Game game;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_card);
		
		setFinishOnTouchOutside(false);
		
		game = (Game) getIntent().getExtras().get(Keys.GAME);
		battingTeam =  game.getBattingTeam();
		bowlingTeam =  game.getBowlingTeam();
		
		TableLayout batsmanTableLayout = (TableLayout) findViewById(R.id.batsmanTableLayout);
		
		List<Player> battingTeamPlayers = battingTeam.getPlayersAsList();
		
		boolean oddRow = true;
		
		for (Player batsman : battingTeamPlayers) {
			TableRow row = new TableRow(getApplicationContext());
			row.setPadding(0, 3, 0, 3);

			TextView playerNameTextField = new TextView(getApplicationContext());
			playerNameTextField.setId(BATSMAN_NAME_ID_PREFEIX+batsman.getId());
			
			String batsmanNameAndSlot;
			
			if(batsman.getBattingScore().getBattingSlot() == BattingScore.DEFAULT_BATTING_SLOT) {
				batsmanNameAndSlot = "   " + batsman.getName();
			} else {
				batsmanNameAndSlot = batsman.getBattingScore().getBattingSlot() + ". " + batsman.getName();
			}
			
			if(batsman.equals(battingTeam.getStriker())) {
				playerNameTextField.setTextColor(getResources().getColor(R.color.yellow));
				batsmanNameAndSlot = batsmanNameAndSlot + "*";
			} else if (batsman.equals(battingTeam.getNonStriker())){
				playerNameTextField.setTextColor(getResources().getColor(R.color.yellow));
			} else if(batsman.getBattingStatus().equals(BattingStatus.Out)) {
				playerNameTextField.setTextColor(getResources().getColor(R.color.dismissedBatsman));
			}

			playerNameTextField.setText(batsmanNameAndSlot);

			row.addView(playerNameTextField);
			
			//TODO: finish adding the how out.
			boolean addHowOut = false; 
			if(addHowOut) {
				TextView howOut = new TextView(getApplicationContext());
//				Integer bowlerId = player.getBattingScore().getBattingWicketBean().getBowlerId();
//				DismisalType dismisalType = player.getBattingScore().getBattingWicketBean().getDismisalType();
				howOut.setText(batsmanNameAndSlot);
				row.addView(howOut);
			}
			
			TextView runs = new TextView(getApplicationContext());
			runs.setId(BATSMAN_RUNS_ID_PREFEIX+batsman.getId());
			runs.setText(batsman.getBattingScore().getRuns() + "");
			runs.setGravity(Gravity.CENTER_HORIZONTAL);
			row.addView(runs);
			
			TextView strikeRate = new TextView(getApplicationContext());
			strikeRate.setId(BATSMAN_STRIKE_RATE_ID_PREFEIX+batsman.getId());
			strikeRate.setGravity(Gravity.CENTER_HORIZONTAL);
			strikeRate.setText(formatFloat(batsman.getBattingScore().getStrikeRate()));
			row.addView(strikeRate);
			
			TextView balls = new TextView(getApplicationContext());
			balls.setId(BATSMAN_BALLS_FACED_ID_PREFEIX+batsman.getId());
			balls.setGravity(Gravity.CENTER_HORIZONTAL);
			balls.setText(batsman.getBattingScore().getBallsFaced() + "");
			row.addView(balls);
			
			TextView fours = new TextView(getApplicationContext());
			fours.setId(BATSMAN_FOURS_ID_PREFEIX+batsman.getId());
			fours.setGravity(Gravity.CENTER_HORIZONTAL);
			fours.setText(batsman.getBattingScore().getNumberOfRuns(4) + "");
			row.addView(fours);
			
			TextView sixes = new TextView(getApplicationContext());
			sixes.setId(BATSMAN_SIXES_ID_PREFEIX+batsman.getId());
			sixes.setGravity(Gravity.CENTER_HORIZONTAL);
			sixes.setText(batsman.getBattingScore().getNumberOfRuns(6) + "");
			row.addView(sixes);
			
			Resources resource = getApplicationContext().getResources();
			
			if(oddRow == false) {
				oddRow = true;
			} else {
				oddRow = false;
			}
			
			if(!oddRow)
				row.setBackgroundColor(resource.getColor(R.color.red));
			
			batsmanTableLayout.addView(row);
		}
		
		TableLayout bowlerTableLayout = (TableLayout) findViewById(R.id.bowlerTableLayout);
		
		//make the first row in the table have a light background
		oddRow = true;

		for (Entry<Integer, Player> bowlerEntry : bowlingTeam.getPlayers().entrySet()) {
			Player bowler = bowlerEntry.getValue();
			
			int numberOfOversByBowler = battingTeam.getNumberOfOversByBowler(bowler.getId());
			
			if(numberOfOversByBowler == 0) {
				continue;
			}
			
			TableRow row = new TableRow(getApplicationContext());
//			row.setPadding(0, 5, 0, 5);
			row.setId(bowler.getId());
			
			TextView bowlerName = new TextView(getApplicationContext());
			bowlerName.setId(BOWLER_NAME_ID_PREFEIX+bowler.getId());
			
			 if(bowler.getBowlingStatus().equals(BowlingStatus.BowledOut)) {
				 bowlerName.setTextColor(getResources().getColor(R.color.dismissedBatsman));
				}
			 
			bowlerName.setTypeface(null,Typeface.BOLD);
			bowlerName.setText(bowler.getName());
			row.addView(bowlerName);
			
			TextView overs = new TextView(getApplicationContext());
			overs.setId(BOWLER_OVERS_ID_PREFEIX+bowler.getId());
			overs.setGravity(Gravity.CENTER_HORIZONTAL);
			overs.setText(numberOfOversByBowler + "");
			row.addView(overs);
			
			TextView maidens = new TextView(getApplicationContext());
			maidens.setId(BOWLER_MAIDENS_ID_PREFEIX+bowler.getId());
			maidens.setGravity(Gravity.CENTER_HORIZONTAL);
			maidens.setText(battingTeam.getMaidensForBowler(bowler.getId()) + "");
			row.addView(maidens);
			
			TextView runs = new TextView(getApplicationContext());
			runs.setId(BOWLER_RUNS_ID_PREFEIX+bowler.getId());
			runs.setText(getRunsFromBowler(bowler) + "");
			runs.setGravity(Gravity.CENTER_HORIZONTAL);
			row.addView(runs);
			
			TextView wickets = new TextView(getApplicationContext());
			wickets.setId(BOWLER_WICKETS_ID_PREFEIX+bowler.getId());
			wickets.setGravity(Gravity.CENTER_HORIZONTAL);
			wickets.setText(battingTeam.getWicketsForBowler(bowler.getId()) + "");
			row.addView(wickets);
			
			TextView economyRate = new TextView(getApplicationContext());
			economyRate.setId(BOWLER_ECONOMY_ID_PREFEIX+bowler.getId());
			economyRate.setGravity(Gravity.CENTER_HORIZONTAL);
			int totalRunFromBowler = getRunsFromBowler(bowler);
			float economy = Float.valueOf(totalRunFromBowler) / Float.valueOf(numberOfOversByBowler);
			economyRate.setText(formatFloat(economy));
			row.addView(economyRate);
			
			TextView fours = new TextView(getApplicationContext());
			fours.setId(BOWLER_FOURS_ID_PREFEIX+bowler.getId());
			fours.setGravity(Gravity.CENTER_HORIZONTAL);
			fours.setText(battingTeam.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(bowler.getId(), Keys.FOUR) + "");
			row.addView(fours);
			
			TextView sixes = new TextView(getApplicationContext());
			sixes.setId(BOWLER_SIXES_ID_PREFEIX+bowler.getId());
			sixes.setGravity(Gravity.CENTER_HORIZONTAL);
			sixes.setText(battingTeam.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(bowler.getId(), Keys.SIX) + "");
			row.addView(sixes);
			
			TextView wides = new TextView(getApplicationContext());
			wides.setId(BOWLER_WIDES_ID_PREFEIX+bowler.getId());
			wides.setGravity(Gravity.CENTER_HORIZONTAL);
			wides.setText(bowler.getBowlingScore().getWides() + "");
			row.addView(wides);
			
			TextView noBalls = new TextView(getApplicationContext());
			noBalls.setId(BOWLER_NO_BALLS_ID_PREFEIX+bowler.getId());
			noBalls.setGravity(Gravity.CENTER_HORIZONTAL);
			noBalls.setText(bowler.getBowlingScore().getNoBalls() + "");
			row.addView(noBalls);
			
			Resources resource = getApplicationContext().getResources();
			
			if(oddRow == false) {
				oddRow = true;
			} else {
				oddRow = false;
			}
			
			if(!oddRow)
				row.setBackgroundColor(resource.getColor(R.color.red));
			
			bowlerTableLayout.addView(row);
		}		
		
		addTotals();
	}

	private void addTotals() {
		//Team part
		int totalRuns = bowlingTeam.getAllExtras() + battingTeam.getRunsScored();
		((TextView) findViewById(R.id.extrasAndTotal)).setText("+ Extras: " + bowlingTeam.getAllExtras() + "   Total: " + totalRuns);
		
		//Batsman part
		((TextView) findViewById(R.id.runsFromWides)).setText("W: " + bowlingTeam.getRunsForBowlerForBallType(BallType.WIDE));
		((TextView) findViewById(R.id.runsFromNoBalls)).setText("NB: " + bowlingTeam.getRunsForBowlerForBallType(BallType.NO_BALL_EXTRA));
		((TextView) findViewById(R.id.runsFromLegByes)).setText("LB: " + bowlingTeam.getRunsForBowlerForBallType(BallType.LEG_BYE));
		((TextView) findViewById(R.id.runsFromByes)).setText("B: " + bowlingTeam.getRunsForBowlerForBallType(BallType.BYE));
		
		((TextView) findViewById(R.id.dots)).setText("Dots: " + battingTeam.getNumberOfRuns(0));

		((TextView) findViewById(R.id.ones)).setText("1's: " + battingTeam.getNumberOfRuns(1));
		((TextView) findViewById(R.id.twos)).setText("2's: " + battingTeam.getNumberOfRuns(2));
		((TextView) findViewById(R.id.threes)).setText("3's: " + battingTeam.getNumberOfRuns(3));
		((TextView) findViewById(R.id.fours)).setText("4's: " + battingTeam.getNumberOfRuns(4));
		((TextView) findViewById(R.id.fives)).setText("5's: " + battingTeam.getNumberOfRuns(5));
		((TextView) findViewById(R.id.sixes)).setText("6's: " + battingTeam.getNumberOfRuns(6));
		
		//Bowling part
		((TextView) findViewById(R.id.totalWides)).setText("# of Wides: " + bowlingTeam.getTotalForBallType(BallType.WIDE));
		((TextView) findViewById(R.id.totalNoBalls)).setText("# of No Balls: " + bowlingTeam.getTotalForBallType(BallType.NO_BALL_EXTRA));
		((TextView) findViewById(R.id.totalLegByes)).setText("# of Leg Byes: " + bowlingTeam.getTotalForBallType(BallType.LEG_BYE));
		((TextView) findViewById(R.id.totalByes)).setText("# of Byes: " + bowlingTeam.getTotalForBallType(BallType.BYE));
	}
	

	private int getRunsFromBowler(Player bowler) {
		int runsGivenByBowler = battingTeam.getRunsGivenByBowlerAgainstAllBatsman(bowler.getId());
		int runFromExtras = bowler.getBowlingScore().getBowlerExtras();
		int totalRunFromBowler = runsGivenByBowler + runFromExtras;
		return totalRunFromBowler;
	}
}