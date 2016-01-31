package cricket.ui.test.longTests;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import backend.Storage;

import common.Keys;

import cricket.ui.Ball;
import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.WelcomeScreen;
import cricket.ui.popups.BowlerSelection;
import cricket.ui.popups.Scoring;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class BackButtonTest extends TestBase<WelcomeScreen> {

	private ActivityMonitor scoreMonitor;
	private ActivityMonitor bolwerSelectMonitor;
	private ActivityMonitor scoringScreenMonitor;
	
	private TextView overSummary;
	
	private TextView overs;
	private TextView totalRuns;
	private TextView runRate;

	public BackButtonTest() {
		super(WelcomeScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		extras.putSerializable(Keys.GAME, testData.getGame(false));
		activity = launchActivity("cricket.ui", WelcomeScreen.class, extras);
		
		ActivityResult activityResult = new ActivityResult(Keys.DEFAULT, new Intent());
		
		scoreMonitor = new ActivityMonitor(Scoring.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(scoreMonitor);
		
		bolwerSelectMonitor = new ActivityMonitor(BowlerSelection.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(bolwerSelectMonitor);

		scoringScreenMonitor = new ActivityMonitor(ScoringScreen.class.getName(), activityResult, false);
		mInstrumentation.addMonitor(scoringScreenMonitor);
	}
	
	@LargeTest
	public void testBackButtonPressed() {
		Storage.clear(activity);
		assertFalse(Storage.doesFileExist(activity));
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				activity.findViewById(cricket.ui.R.id.startButton).callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		adddRunsAndPressBackButton();
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				activity.findViewById(cricket.ui.R.id.startButton).callOnClick(); 
			}
		});
		mInstrumentation.waitForIdleSync();
		
		final Activity newlyLoadedScoringScreen = scoringScreenMonitor.waitForActivity();
		
		overSummary = (TextView) newlyLoadedScoringScreen.findViewById(R.id.overSummary);
		assertEquals(".  .  .  4  .  ", overSummary.getText().toString());
		
		overs = (TextView) newlyLoadedScoringScreen.findViewById(R.id.totalOvers);
		assertEquals("Overs: 0.5", overs.getText().toString());
		
		totalRuns = (TextView) newlyLoadedScoringScreen.findViewById(R.id.totalRuns);
		assertEquals("4 for 0", totalRuns.getText().toString());
		
		runRate = (TextView) newlyLoadedScoringScreen.findViewById(R.id.runRate);
		assertEquals("R/R: 4.82", runRate.getText().toString());
	}

	private void adddRunsAndPressBackButton() {
		final Activity firstScoringScreen = scoringScreenMonitor.waitForActivity();
		
		firstScoringScreen.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				firstScoringScreen.findViewById(R.id.newBowlerButton).callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
		
		final Activity  lastActivity1 = bolwerSelectMonitor.waitForActivity();
		lastActivity1.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				((Spinner) lastActivity1.findViewById(R.id.newBowlerSpinner)).setSelection(1);
			}
		});
		mInstrumentation.waitForIdleSync();
		
		clickButton(ButtonType.Done, lastActivity1);
		
		clickButton(ButtonType.Dot, firstScoringScreen);
		clickButton(ButtonType.Dot, firstScoringScreen);
		clickButton(ButtonType.Dot, firstScoringScreen);
		selectRuns(4, firstScoringScreen);
		clickButton(ButtonType.Dot, firstScoringScreen);
		
		firstScoringScreen.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				firstScoringScreen.onBackPressed();
			}
		});
		mInstrumentation.waitForIdleSync();
	}
	
	//TODO extract and maybe reuse in ScoringTestBase
	protected void selectRuns(final int runs, Activity activity) {
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
	
}
