package cricket.ui;

import game.Game;
import game.GameProcessor;
import game.GameSettings;
import game.Player;
import game.Team;

import java.util.Stack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.Keys;
import common.RuleExecutor;
import common.TeamBattingStatus;

import cricket.ui.Ball.IMyEventListener;
import cricket.ui.popups.BowlerSelection;
import cricket.ui.popups.GameOver;
import cricket.ui.popups.NewInnings;
import cricket.ui.popups.ScoreCard;
import cricket.ui.popups.Scoring;

public class ScoringScreen extends GenericActivity {
	private Ball ball;
	
	private Button newBowlerButton;
	private Button previousBowlerButton;
	private Button dotBallButton;
	private Button undoButton;
	private Button newInningsButton;
	private Button endGameButton;
	
	private boolean battingTeamIsAllOut = false;
	
	private GameProcessor gameProcessor;
	private Game game;
	
	private Stack<String> overSummary;
	private GameSettings gameSettings;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoring_screen);
		setFinishOnTouchOutside(false);
		
		newBowlerButton = (Button) findViewById(R.id.newBowlerButton);
		previousBowlerButton = (Button) findViewById(R.id.previousBowlerButton);
		dotBallButton = (Button) findViewById(R.id.dotBallButton);
		undoButton = (Button) findViewById(R.id.undoButton);
		newInningsButton = (Button) findViewById(R.id.newInningsButton);
		endGameButton = (Button) findViewById(R.id.endGameButton);
		
		newBowlerButton.setEnabled(true);
		dotBallButton.setEnabled(false);
		previousBowlerButton.setEnabled(false);
		undoButton.setEnabled(false);
		
		ball = new Ball(this);
		ball.setId(999);
		ball.setEnabled(false);
		
		IMyEventListener mEventListener = new IMyEventListener() {
			@Override
			public void onEventOccured(float rawX, float rawY) {
				//The new over check needs to be here, becase if it's changed the updated team object is not sent to other views
				//once all other view no longer depend on the team object, this check can go somewhere else
				if(gameProcessor.isOverFinished()) { //start new over
					gameProcessor.startNewOver();
				}
				showScoringScreen();
			}
		};
		ball.setEventListener(mEventListener);
		
		LinearLayout main = (LinearLayout) findViewById(R.id.mainLayout);
		main.addView(ball);

		Bundle extras = getIntent().getExtras();
		game = (Game) extras.getSerializable(Keys.GAME);
		gameSettings = game.getGameSettings();

		gameProcessor = new GameProcessor(game);
		overSummary = new Stack<String>();
		
		//TODO re-think this update approach
		updateBatsmanUI();
		updateTeamTotals(0);
		updateCommonBowlerUI();
		
		if(game.getOverSummary() != null) {
			((TextView) findViewById(R.id.overSummary)).setText(game.getOverSummary().toString());
		} 
//		else {
//			updateOverSummary();
//		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data != null) { //user canceled
			if(requestCode == Keys.SCORING_SCREEN) {
				int runs = data.getExtras().getInt(Keys.RUNS);
				BallType ballType = (BallType) data.getExtras().getSerializable(Keys.BALL_TYPE);
				
				boolean isWicket = data.getExtras().getBoolean(Keys.IS_WICKET);
				if(isWicket) {
					Team battingTeam = (Team) data.getExtras().getSerializable(Keys.BATTING_TEAM); //remove this BattingTeam and replac with Game
					//this is hacky. each time a battingTeam changes we need to update where it's used
					//need to figure out a better way to do this
					game.setTeam(battingTeam);
					gameProcessor.updateGame(game);
					
					battingTeamIsAllOut = data.getExtras().getBoolean(Keys.ALL_OUT);
					
					if(battingTeamIsAllOut) {
						if(game.getGameStatus().getTarget() != 0) { //second innings is finished
							newInningsButton.setEnabled(false);
							Button endGameButton = (Button) findViewById(R.id.endGameButton);
							endGameButton.setVisibility(Button.VISIBLE);
						} else {
							newInningsButton.setEnabled(true);
							Button endGameButton = (Button) findViewById(R.id.endGameButton);
							endGameButton.setVisibility(Button.INVISIBLE);
						}
					} 
					
					DismisalType dismisalType = (DismisalType) data.getExtras().getSerializable(Keys.DISMISSAL_TYPE);
					Player fielder = (Player) data.getExtras().getSerializable(Keys.FIELDER);
					
					if (dismisalType == DismisalType.STUMPED && ballType == BallType.WIDE) { // stumped of a wide
						gameProcessor.stumpedOfWide(fielder, 0, runs);
					} else if (dismisalType == DismisalType.RUNOUT || dismisalType == DismisalType.NON_STRIKER_RUNOUT) {
							gameProcessor.runOut(fielder, runs, ball.getRawX(), ball.getRawY(), ballType, dismisalType);
					} else { // Every other dismisal
						gameProcessor.dismisal(dismisalType, fielder, 0);
					}
					
					updateEverything(0, BallType.WICKET);
				} else { //just a normal scoring shot
					gameProcessor.delivery(ballType, runs, ball.getRawX(), ball.getRawY());
					updateEverything(runs, ballType);
				}
			} else if(requestCode == Keys.NEW_BOWLER_SCREEN) {
				Team bowlingTeam = (Team) data.getExtras().getSerializable(Keys.BOWLING_TEAM);
				game.setTeam(bowlingTeam);
				gameProcessor.updateGame(game);
				updateCommonBowlerUI();

//				if(overTracker.isFirstOver()) //first over, delete this?
//					updateBatsmanUI();
			}  else if(requestCode == Keys.NEW_INNINGS_SCREEN) {
				Team battingTeam = (Team) data.getExtras().getSerializable(Keys.BATTING_TEAM);
				game.setTeam(battingTeam);
				gameProcessor.updateGame(game);
				gameProcessor.startNewInnings();
				
				updateBatsmanUI();
				updateTeamTotals(0);
			} 
		}
    }
	
	public void onScoreCard(View view) {
		Intent intent = new Intent(this, ScoreCard.class);
		intent.putExtra(Keys.GAME, game);
		startActivity(intent);
	}
	
	public void onNewInningsClick(View view) {
		Intent intent = new Intent(this, NewInnings.class);
		int target = game.getBattingTeam().getRunsScored() + game.getBowlingTeam().getAllExtras();
		game.getGameStatus().setTarget(target);

		if(battingTeamIsAllOut) {
			game.changeTeamsAround(TeamBattingStatus.All_Out);
		} else {
			game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		}
		
		intent.putExtra(Keys.BATTING_TEAM, game.getBattingTeam());
		startActivityForResult(intent, Keys.NEW_INNINGS_SCREEN);
	}
	
	public void onDotBallClick(View view) {
		if(gameProcessor.isOverFinished()) { 
			gameProcessor.startNewOver();
		}
		
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		updateEverything(0, BallType.DOT_BALL);
	}

	public void onPreviousBowlerClick(View view) {
		Player currentBowler = game.getBowlingTeam().getCurrentBowler();
		Player otherBowler = game.getBowlingTeam().getOtherBowler();
		
		if(currentBowler == null) {
			if(otherBowler == null) {
				dotBallButton.setEnabled(false);
				ball.setEnabled(false);
				
				previousBowlerButton.setEnabled(false);
				undoButton.setEnabled(false);
				newBowlerButton.setEnabled(true);
				return;
			} else {
				otherBowler.setBowlingStatus(BowlingStatus.CurrentlyBowling);
			}
		} else if (otherBowler == null) {
			currentBowler.setBowlingStatus(BowlingStatus.OtherBowler);
		} else { //both bowlers set, swap them
			currentBowler.setBowlingStatus(BowlingStatus.OtherBowler);
			otherBowler.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		}
		
		updateCommonBowlerUI();
	}
	
	@Override
	public void onBackPressed() {
		StringBuffer strBuff = new StringBuffer();
		
		for (String ball : overSummary) {
			strBuff.append(ball);
		}
		
		game.setOverSummary(strBuff.toString());
		game.setOverNumber(gameProcessor.getOverNumber());
		game.setBallsLeft(gameProcessor.getBallsLeft());
		
		Intent intent = new Intent();
		intent.putExtra(Keys.GAME, game);
		setResult(69, intent);
		super.onBackPressed();
	}
	
	public void onNewBowlerClick(View view) {
		Intent intent = new Intent(this, BowlerSelection.class); //rename to NewBowlerPopup
		
		Bundle bundle = new Bundle();
		RuleExecutor ruleExecutor = new RuleExecutor(gameSettings.getRules(), game.getBowlingTeam(), game.getBattingTeam());
		ruleExecutor.executeRules();
		
		bundle.putSerializable(Keys.BOWLING_TEAM, game.getBowlingTeam());
		
		intent.putExtras(bundle);
		startActivityForResult(intent, Keys.NEW_BOWLER_SCREEN);
	}
	
	public void onEndGame(View view) {
		Intent intent = new Intent(this, GameOver.class);
		intent.putExtra(Keys.GAME, game);
		startActivity(intent);
	}
	
	public void onUndo(View view) {
		gameProcessor.undo();
		
		updateBatsmanUI();
		updateBowler(BowlingStatus.CurrentlyBowling);
		
		undoButton.setEnabled(true);
		
		if(gameProcessor.isOverFinished()) { // first ball was balled, but it was then undone
			newBowlerButton.setEnabled(true);
			enablePreviousBowlerButton();
			dotBallButton.setEnabled(false);
			ball.setEnabled(false);
		} else {
			newBowlerButton.setEnabled(false);
			previousBowlerButton.setEnabled(false);
			dotBallButton.setEnabled(true);
			ball.setEnabled(true);
		} 
		
		if(gameProcessor.getBallsLeft() == 6) {
			undoButton.setEnabled(false);
		}
		
		removeLastEntryInOverSummary();
		updateTeamTotals(gameProcessor.getBallsLeft());
	}
	
	private void enablePreviousBowlerButton() {
		if(gameProcessor.isFirstOver()) { //this can only be enabled after 2 overs
			previousBowlerButton.setEnabled(false);
		} else {
			previousBowlerButton.setEnabled(true);
		}
	}
	
	private void updateCommonBowlerUI() {
		updateBowlerUI(0);
		overSummary.clear();
		updateOverSummary(); //probably not needed because if the oveSummary is cleared above than what will be updated? nothing
		
		previousBowlerButton.setEnabled(false);
		undoButton.setEnabled(false);
		newBowlerButton.setEnabled(true);
		
		dotBallButton.setEnabled(true);
		ball.setEnabled(true);
	}
	
	private void removeLastEntryInOverSummary() {
		overSummary.pop();
		updateOverSummary();
	}

	private void updateOverSummary() {
		StringBuffer strBuff = new StringBuffer();
		
		for (String ball : overSummary) {
			strBuff.append(ball);
		}
		
		((TextView) findViewById(R.id.overSummary)).setText(strBuff.toString());
	}

	private String getOverLabels(Player bowler) {
		int numberOfOvers = game.getBattingTeam().getNumberOfOversByBowler(bowler.getId());
		
		if(gameProcessor.isOverFinished()) { //over finished
			return numberOfOvers+"";
		} else {
			if(numberOfOvers == 0) { //bowlers first over
				return numberOfOvers + "."+(gameProcessor.getBallsBowled());
			} else {
				// -1 because the code before this would add another over bean already.
				return (numberOfOvers - 1) + "."+(gameProcessor.getBallsBowled());
			}
		}
	}
	
	private void updateBatsmanUI() {
		updateBatsman(BattingStatus.Striker);
		updateBatsman(BattingStatus.NonStriker);
	}
	
	private void updateBowlerUI(int ballsLeft) {
		updateBowler(BowlingStatus.CurrentlyBowling);
		updateBowler(BowlingStatus.OtherBowler);
	}
	
	private void updateEverything(int runs, BallType ballType) {
		Integer ballsLeft = gameProcessor.getBallsLeft();
		adjustUI(runs, ballType,  ballsLeft);
		updateBatsmanUI();
		updateBowler(BowlingStatus.CurrentlyBowling);
		updateTeamTotals(ballsLeft);
	}
	
	private void updateTeamTotals(int ballsLeft) {
		int runs = game.getBattingTeam().getRunsScored() + game.getBowlingTeam().getAllExtras();

		int wickets = game.getBattingTeam().getWicketsLost();
		((TextView) findViewById(R.id.totalRuns)).setText(runs + " for " + wickets);

		String runRate;

		if (gameProcessor.isOverFinished()) {
			runRate = formatFloat(runs / (Float.valueOf(gameProcessor.getOverNumber()) + 1));
		} else { // we are in the middle of an over i.e: 15.4
			runRate = formatFloat(runs / (Float.valueOf(gameProcessor.getOverAsStringPercentage())));
		}

		((TextView) findViewById(R.id.runRate)).setText("R/R: " + (runRate.equals("NaN") ? "0.00" : runRate));
		((TextView) findViewById(R.id.totalOvers)).setText("Overs: " + gameProcessor.getOverAsString());
		
		//is game finished?
		if(game.getGameStatus().getTarget() == 0) { //first innings
			//overs are 0 based, so add 1
			if (gameProcessor.getOverNumber() + 1 == gameSettings.getMaxOvers() && gameProcessor.isOverFinished()) { // end of over/innings
				updateInningsEndButtons();
				newInningsButton.setEnabled(true);
			}
		} else { //second innings
			if (gameProcessor.getOverNumber() + 1 == gameSettings.getMaxOvers()) { // end of over/innings - don't need to check if over is finished - last ball of last over finish
				updateInningsEndButtons();
				endGameButton.setVisibility(Button.VISIBLE);
			} else if (runs > game.getGameStatus().getTarget()) { //chasing team chase down the total before max overs
				updateInningsEndButtons();
				endGameButton.setVisibility(Button.VISIBLE);
			}
		}
	}

	private void updateInningsEndButtons() {
		newInningsButton.setEnabled(false);
		previousBowlerButton.setEnabled(false);
		newBowlerButton.setEnabled(false);
		dotBallButton.setEnabled(false);
		ball.setEnabled(false);
	}

	private void adjustUI(int runs, BallType balltype, int ballsLeft) {
		updateOverSummary(runs, balltype);

		undoButton.setEnabled(true);
		
		if(ballsLeft == 0) {
			newBowlerButton.setEnabled(true);
			dotBallButton.setEnabled(false);
			ball.setEnabled(false);
			enablePreviousBowlerButton();
		} else {
			newBowlerButton.setEnabled(false);
			previousBowlerButton.setEnabled(false);
			dotBallButton.setEnabled(true);
			ball.setEnabled(true);
		}
	}
	
	private void updateOverSummary(int runs, BallType ballType) {
		switch (ballType) {
		case BYE:
			overSummary.push((runs==0? "" : runs) + "B  ");
			break;
		case DEAD_BALL:
			overSummary.push("DB  ");
			break;
		case DOT_BALL:
			overSummary.push(".  ");
			break;
		case LEG_BYE:
			overSummary.push((runs==0? "" : runs) + "LB  ");
			break;
		case NO_BALL_EXTRA:
			overSummary.push("NB" + (runs==0? "" : "+" + runs) + "  "); 
			break;
		case NO_BALL_RUN:
			overSummary.push("NB" + (runs==0? "" : "+" + runs) + "  "); 
			break;
		case SCORING:
			overSummary.push((runs==0? "." : runs) + "  "); 
			break;
		case WICKET:
			overSummary.push("W  "); 
			break;
		case WIDE:
			overSummary.push("WD" + (runs==0 ? "" : "+" + runs) + "  "); 
			break;
		default:
			break;
		}
		
		updateOverSummary();
	}
	
	private void showScoringScreen() {
		Intent intent = new Intent(this, Scoring.class);
		intent.putExtra(Keys.GAME, game);
		startActivityForResult(intent, Keys.SCORING_SCREEN);
	}
	
	private void updateBatsman(BattingStatus status) {
		Player striker = game.getBattingTeam().getBatsmanByStatus(status);
		if(striker != null) { //striker can be null if last batsman is dismissed
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1name : R.id.batsman2name)).setText(striker.getName());
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1runs : R.id.batsman2runs)).setText(striker.getBattingScore().getRuns()+"");
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1strikeRate : R.id.batsman2strikeRate)).setText(formatFloat(striker.getBattingScore().getStrikeRate()));
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1balls : R.id.batsman2balls)).setText(striker.getBattingScore().getBallsFaced()+"");
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1fours : R.id.batsman2fours)).setText(striker.getBattingScore().getNumberOfRuns(4)+"");
			((TextView) findViewById(status == BattingStatus.Striker ? R.id.batsman1sixes : R.id.batsman2sixes)).setText(striker.getBattingScore().getNumberOfRuns(6)+"");
		}
	}
	
	private void updateBowler(BowlingStatus status) {
		Player bowler = game.getBowlingTeam().getBowlerByStatus(status);
		
		if(bowler == null) { //can be null if the bowler has bowled out
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1name : R.id.bowler2name)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1overs : R.id.bowler2overs)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1maidens : R.id.bowler2maidens)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1wickets : R.id.bowler2wickets)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1runs : R.id.bowler2runs)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1economy : R.id.bowler2economy)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1fours : R.id.bowler2fours)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1sixes : R.id.bowler2sixes)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1wides : R.id.bowler2wides)).setText("");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1noBalls : R.id.bowler2noBalls)).setText("");
		} else {
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1name : R.id.bowler2name)).setText(bowler.getName());
			
			String overs = getOverLabels(bowler);
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1overs : R.id.bowler2overs)).setText(overs);
			
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1maidens : R.id.bowler2maidens)).setText(game.getBattingTeam().getMaidensForBowler(bowler.getId()) + "");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1wickets : R.id.bowler2wickets)).setText(game.getBattingTeam().getWicketsForBowler(bowler.getId()) + "");
			
			int totalRunFromBowler = game.getBattingTeam().getTotalRunsFromBowler(bowler);
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1runs : R.id.bowler2runs)).setText(totalRunFromBowler + "");
			
			float economy = totalRunFromBowler / Float.valueOf(overs);
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1economy : R.id.bowler2economy)).setText(formatFloat(economy));
			
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1fours : R.id.bowler2fours)).setText(game.getBattingTeam().getTotalNumberOfRunsOfBowlerAgainstAllBatsman(bowler.getId(), Keys.FOUR)+"");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1sixes : R.id.bowler2sixes)).setText(game.getBattingTeam().getTotalNumberOfRunsOfBowlerAgainstAllBatsman(bowler.getId(), Keys.SIX)+"");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1wides : R.id.bowler2wides)).setText(bowler.getBowlingScore().getWides()+"");
			((TextView) findViewById(status == BowlingStatus.CurrentlyBowling ? R.id.bowler1noBalls : R.id.bowler2noBalls)).setText(bowler.getBowlingScore().getNoBalls()+"");
		}
	}
}