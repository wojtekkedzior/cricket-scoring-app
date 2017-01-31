package cricket.ui.test.scoringScreen;

import game.Game;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.Ball;
import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.popups.BowlerSelection;
import cricket.ui.popups.GameOver;
import cricket.ui.popups.NewInnings;
import cricket.ui.popups.Scoring;
import cricket.ui.popups.Wicket;
import cricket.ui.test.TestBase;

public abstract class ScoringScreenTestBase extends TestBase<ScoringScreen> {

	protected ActivityMonitor bolwerSelectMonitor;
	protected ActivityMonitor scoreMonitor;
	protected ActivityMonitor wicketMonitor;
	protected ActivityMonitor newInningsMonitor;
	protected ActivityMonitor gameOverMonitor;
	
	protected Button newBowlerButton;
	
	protected Ball ball; 
			
	protected TextView strikerName;
	protected TextView strikerRuns;
	protected TextView strikerStrikeRate;
	protected TextView strikerBalls;
	protected TextView strikerFours;
	protected TextView strikerSixes;
	
	protected Game game;
	
	public ScoringScreenTestBase(Class<ScoringScreen> activityClass) {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		game = testData.getGame(false);
		
		game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.Batting);
		game.getTeam(1).setTeamBowlingStatus(TeamBowlingStatus.Bowling);
		
		game.getBattingTeam().getPlayerById(1).setBattingStatus(BattingStatus.Striker);
		game.getBattingTeam().getPlayerById(2).setBattingStatus(BattingStatus.NonStriker);
		
//		game.setGameSettings(new GameSettings(GameType.TwentyTwenty));
		extras.putSerializable(Keys.GAME, game);
//		extras.putSerializable(Keys.GAME_SETTINGS, new GameSettings(GameType.TwentyTwenty));
		
		activity = launchActivity("cricket.ui", ScoringScreen.class, extras);
		
		ActivityResult activityResult = new ActivityResult(Keys.DEFAULT, new Intent());
		bolwerSelectMonitor = new ActivityMonitor(BowlerSelection.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(bolwerSelectMonitor);
		
		scoreMonitor = new ActivityMonitor(Scoring.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(scoreMonitor);
		
		wicketMonitor = new ActivityMonitor(Wicket.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(wicketMonitor);
		
		newInningsMonitor = new ActivityMonitor(NewInnings.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(newInningsMonitor);
		
		gameOverMonitor = new ActivityMonitor(GameOver.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(gameOverMonitor);
	}
	
	protected void selectRunsWithExtra(final int runs, final BallType ballType) {
		final Ball ball = (Ball) activity.findViewById(999);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				ball.setEnabled(true);
				MotionEvent event = MotionEvent.obtain(1, 2, MotionEvent.ACTION_DOWN, 250, 250, 1, 1, 1, 2, 2, 1, 1);
				ball.dispatchTouchEvent(event);
				event.recycle();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		Activity lastActivity = scoreMonitor.waitForActivity();
		
		final RadioGroup runGroup = (RadioGroup) lastActivity.findViewById(R.id.radiogroup2);
		final RadioGroup extrasGroup = (RadioGroup) lastActivity.findViewById(R.id.radiogroup1);
		
		final Button doneButton = (Button) lastActivity.findViewById(R.id.doneButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				switch (runs) {
				  case 1 : runGroup.check(R.id.oneRun); break;
				  case 2 : runGroup.check(R.id.twoRuns); break;
				  case 3 : runGroup.check(R.id.threeRuns); break;
				  case 4 : runGroup.check(R.id.fourRuns); break;
				  case 5 : runGroup.check(R.id.fiveRuns); break;
				  case 6 : runGroup.check(R.id.sixRuns);  break;
				  default: runGroup.clearCheck();
				}
				
				switch (ballType) {
				case BYE: extrasGroup.check(R.id.byeRadioButton); break;
				case DEAD_BALL:
					break;
				case DOT_BALL:
					break;
				case LEG_BYE: extrasGroup.check(R.id.legByeRadioButton); break;
				case NO_BALL_EXTRA: extrasGroup.check(R.id.noBallExtraRadioButton); break;
				case NO_BALL_RUN: extrasGroup.check(R.id.noBallRunRadioButton); break;
				case SCORING:
					break;
				case WICKET:
					break;
				case WIDE: extrasGroup.check(R.id.wideRadioButton); break;
				default:
					break;
				
				}
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
	}
	
	protected void selectNewBowler(final String newBowlerName) {
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 

		//get BowlerSelect popup
		Activity lastActivity = bolwerSelectMonitor.waitForActivity();
		
		//select the bowler we want
		final Spinner newBowlerSpinner = (Spinner) lastActivity.findViewById(R.id.newBowlerSpinner);
		int bowlerPos = 0;
		for (int i = 0; i < newBowlerSpinner.getAdapter().getCount(); i++) {
			String bowlerName = (String) newBowlerSpinner.getAdapter().getItem(i);
			if(bowlerName.equals(newBowlerName)) {
				bowlerPos = (int) newBowlerSpinner.getAdapter().getItemId(i);
				break;
			}
		}
		
		final int finalBowerPos = bowlerPos;
		final Button doneButton = (Button) lastActivity.findViewById(R.id.doneButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerSpinner.setSelection(finalBowerPos);
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
	}
	
	protected void selectRuns(final int runs) {
		ball = (Ball) activity.findViewById(999);
		activity.runOnUiThread(new Runnable() {
			public void run() {
				ball.setEnabled(true);
				MotionEvent event = MotionEvent.obtain(1, 2, MotionEvent.ACTION_DOWN, 250, 250, 1, 1, 1, 2, 2, 1, 1);
				ball.dispatchTouchEvent(event);
				event.recycle();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		Activity lastActivity = scoreMonitor.waitForActivity();
		
		final RadioGroup runGroup = (RadioGroup) lastActivity.findViewById(R.id.radiogroup2);
		final Button doneButton = (Button) lastActivity.findViewById(R.id.doneButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				switch (runs) {
				  case 1 : runGroup.check(R.id.oneRun); break;
				  case 2 : runGroup.check(R.id.twoRuns);break;
				  case 3 : runGroup.check(R.id.threeRuns);break;
				  case 4 : runGroup.check(R.id.fourRuns);break;
				  case 5 : runGroup.check(R.id.fiveRuns);break;
				  case 6 : runGroup.check(R.id.sixRuns); break;
				  default: runGroup.clearCheck();
				}
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		scoreMonitor.waitForActivity();
	}
	
	protected void selectWicket(final boolean newBatsmanOnStrike) {
		final Ball ball = (Ball) activity.findViewById(999);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				ball.setEnabled(true); //REMOVE
				MotionEvent event = MotionEvent.obtain(1, 2, MotionEvent.ACTION_DOWN, 250, 250, 1, 1, 1, 2, 2, 1, 1);
				ball.dispatchTouchEvent(event);
				event.recycle();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		//go to score popuop
		Activity lastActivity = scoreMonitor.waitForActivity();
		
		final Button wicketButton = (Button) lastActivity.findViewById(R.id.wicketButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				wicketButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
		
		//go to wicket popup
		Activity wicketScreenActivity = wicketMonitor.waitForActivity();
		final Button doneButton = (Button) wicketScreenActivity.findViewById(R.id.doneButton);

		final RadioGroup onStrike  = (RadioGroup) wicketScreenActivity.findViewById(R.id.onStrikeGroup);
		
		wicketScreenActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				onStrike.check(newBatsmanOnStrike ? R.id.onStrike : R.id.notOnStrike);
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void selectWicket(final boolean newBatsmanOnStrike, DismisalType dismisalType) {
		final Ball ball = (Ball) activity.findViewById(999);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				ball.setEnabled(true); //REMOVE
				MotionEvent event = MotionEvent.obtain(1, 2, MotionEvent.ACTION_DOWN, 250, 250, 1, 1, 1, 2, 2, 1, 1);
				ball.dispatchTouchEvent(event);
				event.recycle();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		//go to score popuop
		Activity lastActivity = scoreMonitor.waitForActivity();
		
		final Button wicketButton = (Button) lastActivity.findViewById(R.id.wicketButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				wicketButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
		
		//go to wicket popup
		Activity wicketScreenActivity = wicketMonitor.waitForActivity();
		final Button doneButton = (Button) wicketScreenActivity.findViewById(R.id.doneButton);

		final RadioGroup onStrike  = (RadioGroup) wicketScreenActivity.findViewById(R.id.onStrikeGroup);
		final Spinner dismissalTypesSpinner = (Spinner) wicketScreenActivity.findViewById(R.id.dismissalTypeSpinner);
		
		int bowlerPos = 0;
		for (int i = 0; i < dismissalTypesSpinner.getAdapter().getCount(); i++) {
			String dismsialTypes = (String) dismissalTypesSpinner.getAdapter().getItem(i);
			if(dismsialTypes.equals(dismisalType.getDismissalTypeName())) {
				bowlerPos = (int) dismissalTypesSpinner.getAdapter().getItemId(i);
				break;
			}
		}
		
		final int finalBowerPos = bowlerPos;
		final Spinner fieldersSpinner = (Spinner) wicketScreenActivity.findViewById(R.id.fielderSpinner);
		
		wicketScreenActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				onStrike.check(newBatsmanOnStrike ? R.id.onStrike : R.id.notOnStrike);
				dismissalTypesSpinner.setSelection(finalBowerPos);
				fieldersSpinner.setSelection(2);
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
	}
	
	
	protected void selectWicketWithExtra(final boolean newBatsmanOnStrike, final BallType ballType, final int runs, DismisalType dismisalType) {
		final Ball ball = (Ball) activity.findViewById(999);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				ball.setEnabled(true); //REMOVE
				MotionEvent event = MotionEvent.obtain(1, 2, MotionEvent.ACTION_DOWN, 250, 250, 1, 1, 1, 2, 2, 1, 1);
				ball.dispatchTouchEvent(event);
				event.recycle();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		//go to score popuop
		Activity lastActivity = scoreMonitor.waitForActivity();
		
		final RadioGroup runGroup = (RadioGroup) lastActivity.findViewById(R.id.radiogroup2);
		final RadioGroup extrasGroup = (RadioGroup) lastActivity.findViewById(R.id.radiogroup1);
		
		final Button wicketButton = (Button) lastActivity.findViewById(R.id.wicketButton);
		
		lastActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				switch (runs) {
				  case 1 : runGroup.check(R.id.oneRun); break;
				  case 2 : runGroup.check(R.id.twoRuns); break;
				  case 3 : runGroup.check(R.id.threeRuns); break;
				  case 4 : runGroup.check(R.id.fourRuns); break;
				  case 5 : runGroup.check(R.id.fiveRuns); break;
				  case 6 : runGroup.check(R.id.sixRuns);  break;
				  default: runGroup.clearCheck();
				}
				
				switch (ballType) {
				case BYE: extrasGroup.check(R.id.byeRadioButton); break;
				case DEAD_BALL:
					break;
				case DOT_BALL:
					break;
				case LEG_BYE: extrasGroup.check(R.id.legByeRadioButton); break;
				case NO_BALL_EXTRA: extrasGroup.check(R.id.noBallExtraRadioButton); break;
				case NO_BALL_RUN: extrasGroup.check(R.id.noBallRunRadioButton); break;
				case SCORING:
					break;
				case WICKET:
					break;
				case WIDE: extrasGroup.check(R.id.wideRadioButton); break;
				default:
					break;
				
				}
				wicketButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		//go to wicket popup
		Activity wicketScreenActivity = wicketMonitor.waitForActivity();

		final RadioGroup onStrike  = (RadioGroup) wicketScreenActivity.findViewById(R.id.onStrikeGroup);
		final Button doneButton = (Button) wicketScreenActivity.findViewById(R.id.doneButton);
		
		final Spinner dismissalTypesSpinner = (Spinner) wicketScreenActivity.findViewById(R.id.dismissalTypeSpinner);
		
		int bowlerPos = 0;
		for (int i = 0; i < dismissalTypesSpinner.getAdapter().getCount(); i++) {
			String dismisalTypeNames = (String) dismissalTypesSpinner.getAdapter().getItem(i);
			if(dismisalTypeNames.equals(dismisalType.getDismissalTypeName())) {
				bowlerPos = (int) dismissalTypesSpinner.getAdapter().getItemId(i);
				break;
			}
		}
		
		final int finalBowerPos = bowlerPos;
		
		wicketScreenActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				onStrike.check(newBatsmanOnStrike ? R.id.onStrike : R.id.notOnStrike);
				dismissalTypesSpinner.setSelection(finalBowerPos);
				doneButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
	}
	
	protected void checkCurrentBowler(String name, String overs, String maidens, String wickets, String runs, String economy, String fours, String sixes, String wides, String noBalls) {
		TextView bowlerName = (TextView) activity.findViewById(R.id.bowler1name);
		TextView bowlerovers = (TextView) activity.findViewById(R.id.bowler1overs);
		TextView bowlerMaidens = (TextView) activity.findViewById(R.id.bowler1maidens);
		TextView bowlerWickets = (TextView) activity.findViewById(R.id.bowler1wickets);
		TextView bowlerRuns = (TextView) activity.findViewById(R.id.bowler1runs);
		TextView bowlerEconomy = (TextView) activity.findViewById(R.id.bowler1economy);
		TextView bowlerFours = (TextView) activity.findViewById(R.id.bowler1fours);
		TextView bowlerSixes = (TextView) activity.findViewById(R.id.bowler1sixes);
		TextView bowlerWides = (TextView) activity.findViewById(R.id.bowler1wides);
		TextView bowlerNoBalls = (TextView) activity.findViewById(R.id.bowler1noBalls);
		
		assertEquals(name, bowlerName.getText().toString());
		assertEquals(overs, bowlerovers.getText().toString());
		assertEquals(maidens, bowlerMaidens.getText().toString());
		assertEquals(wickets, bowlerWickets.getText().toString());
		assertEquals(runs, bowlerRuns.getText().toString());
		assertEquals(economy, bowlerEconomy.getText().toString());
		assertEquals(fours, bowlerFours.getText().toString());
		assertEquals(sixes, bowlerSixes.getText().toString());
		assertEquals(wides, bowlerWides.getText().toString());
		assertEquals(noBalls, bowlerNoBalls.getText().toString());
	}
	
	protected void checkNonStriker(String name, String runs, String strikeRate, String balls, String fours, String sixes) {
		TextView nonStrikerName = (TextView) activity.findViewById(R.id.batsman2name);
		TextView nonStrikerRuns = (TextView) activity.findViewById(R.id.batsman2runs);
		TextView nonStrikerStrikeRate = (TextView) activity.findViewById(R.id.batsman2strikeRate);
		TextView nonStrikerBalls = (TextView) activity.findViewById(R.id.batsman2balls);
		TextView nonStrikerFours = (TextView) activity.findViewById(R.id.batsman2fours);
		TextView nonStrikerSixes = (TextView) activity.findViewById(R.id.batsman2sixes);
		
		assertEquals(name, nonStrikerName.getText().toString());
		assertEquals(runs, nonStrikerRuns.getText().toString().toString());
		assertEquals(strikeRate, nonStrikerStrikeRate.getText().toString());
		assertEquals(balls, nonStrikerBalls.getText().toString());
		assertEquals(fours, nonStrikerFours.getText().toString());
		assertEquals(sixes, nonStrikerSixes.getText().toString());
	}
	
	protected void checkStriker(String name, String runs, String strikeRate, String balls, String fours, String sixes) {
		TextView strikerName = (TextView) activity.findViewById(R.id.batsman1name);
		TextView strikerRuns = (TextView) activity.findViewById(R.id.batsman1runs);
		TextView strikerStrikeRate = (TextView) activity.findViewById(R.id.batsman1strikeRate);
		TextView strikerBalls = (TextView) activity.findViewById(R.id.batsman1balls);
		TextView strikerFours = (TextView) activity.findViewById(R.id.batsman1fours);
		TextView strikerSixes = (TextView) activity.findViewById(R.id.batsman1sixes);
		
		assertEquals(name, strikerName.getText());
		assertEquals(runs, strikerRuns.getText().toString());
		assertEquals(strikeRate, strikerStrikeRate.getText().toString());
		assertEquals(balls, strikerBalls.getText().toString());
		assertEquals(fours, strikerFours.getText().toString());
		assertEquals(sixes, strikerSixes.getText().toString());
	}
}
