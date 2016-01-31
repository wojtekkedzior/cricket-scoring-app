package cricket.ui.test.scoringScreen;

import game.Player;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.Button;
import android.widget.Spinner;

import common.BowlingStatus;
import common.Keys;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class SelectNewBowlerTest extends ScoringScreenTestBase {

	public SelectNewBowlerTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
	}
	
	public void testNewBowlerButtonEnabled() {
		assertTrue(newBowlerButton.isEnabled());
		selectNewBowler("Mills");
		clickButton(ButtonType.Dot);
		assertFalse(newBowlerButton.isEnabled());
	}
	
	public void testCanSelectNewBowler() {
		assertTrue(newBowlerButton.isEnabled());
		
		selectNewBowler("Mills");
		assertTrue(newBowlerButton.isEnabled());
		
		selectNewBowler("Bracewell");
		assertTrue(newBowlerButton.isEnabled());
		
		clickButton(ButtonType.Dot);
		assertFalse(newBowlerButton.isEnabled());
	}
	
	public void testCannotSelectSameBowlerAsLastOver() {
		selectNewBowler("Mills"); 
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		assertTrue(newBowlerButton.isEnabled());
		
		//make sure that the current bowler is not in the list again.
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 

		//get BowlerSelect popup
		Activity bowlerSelectActivity = bolwerSelectMonitor.waitForActivity();
		
		//select the bowler we want
		final Spinner newBowlerSpinner = (Spinner) bowlerSelectActivity.findViewById(R.id.newBowlerSpinner);
		
		for (int i = 0; i < newBowlerSpinner.getAdapter().getCount(); i++) {
			String bowlerName = (String) newBowlerSpinner.getAdapter().getItem(i);
			if(bowlerName.equals("Mills")) {
				fail("The bowler: " + bowlerName + " should not be available as he has just bowled the last over");
			}
		}
		
		bowlerSelectActivity.finish();
		mInstrumentation.waitForIdleSync(); 
		
		//Select a new bowler and again check if the current bowler, who completed the over (Mills) is not in the list 
		selectNewBowler("Bracewell");
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 

		for (int i = 0; i < newBowlerSpinner.getAdapter().getCount(); i++) {
			String bowlerName = (String) newBowlerSpinner.getAdapter().getItem(i);
			if(bowlerName.equals("Mills")) {
				fail("The bowler: " + bowlerName + " should not be available as he has just bowled the last over");
			}
		}
		
		//get the popup and close it
		bowlerSelectActivity = bolwerSelectMonitor.waitForActivity();
		bowlerSelectActivity.finish();
	}
	
	public void testCannotSelectBowledOutBowler() {
		//Have to finish the activity started in setUp because we are modifying the bundle
		activity.finish();
		
		Player bowledOutBowler = game.getBowlingTeam().getPlayerByName("Mills");
		bowledOutBowler.setBowlingStatus(BowlingStatus.BowledOut);
		extras.putSerializable(Keys.GAME, game);
		activity = launchActivity("cricket.ui", ScoringScreen.class, extras);
		
		//make sure that the current bowler is not in the list again.
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 

		//get BowlerSelect popup
		Activity bowlerSelectActivity = bolwerSelectMonitor.waitForActivity();
		
		//select the bowler we want
		final Spinner newBowlerSpinner = (Spinner) bowlerSelectActivity.findViewById(R.id.newBowlerSpinner);
		
		for (int i = 0; i < newBowlerSpinner.getAdapter().getCount(); i++) {
			String bowlerName = (String) newBowlerSpinner.getAdapter().getItem(i);
			if(bowlerName.equals("Mills")) {
				fail("The bowler: " + bowlerName + " should not be available as he has just bowled the last over");
			}
		}
		
		bowlerSelectActivity.finish();
	}
}
