package cricket.ui.test.popups;

import game.Game;
import game.Player;
import game.Team;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import common.BattingStatus;
import common.Keys;
import common.TeamBattingStatus;

import cricket.ui.R;
import cricket.ui.popups.BowlerSelection;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class BowlerSelectionTest extends TestBase<BowlerSelection> {

	private Spinner newBowlerSpinner;
	private EditText newBatsmanText;

	public BowlerSelectionTest() {
		super(BowlerSelection.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Team bowlingTeam = new Team(1);
		bowlingTeam.setTeamName("New Zealand");

		Player mills = new Player("Mills", 101);
		Player bracewell = new Player("Bracewell", 102);
		Player oram = new Player("Oram", 103);
		
		bowlingTeam.addPlayer(mills);
		bowlingTeam.addPlayer(bracewell);
		bowlingTeam.addPlayer(oram);
		
		extras.putSerializable(Keys.BOWLING_TEAM, bowlingTeam);
		activity = launchActivity("cricket.ui", BowlerSelection.class, extras);
	}

	public void testOnDone() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		newBowlerSpinner = (Spinner) activity.findViewById(R.id.newBowlerSpinner);
		assertEquals(0, newBowlerSpinner.getSelectedItemId()); // first bowler

		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBowlerSpinner.setSelection(1);
			}
		});
		mInstrumentation.waitForIdleSync();

		assertEquals(1, newBowlerSpinner.getSelectedItemId());

		clickButton(ButtonType.Done);

		Intent intent = getIntentWithResultCode(Keys.NEW_BOWLER_SCREEN);

		Bundle bundle = intent.getExtras();
		Team modifiedTeam = (Team) bundle.get(Keys.BOWLING_TEAM);
		assertEquals("Bracewell", modifiedTeam.getCurrentBowler().getName());
	}
	
	public void testCanAddNewBwowler() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		newBowlerSpinner = (Spinner) activity.findViewById(R.id.newBowlerSpinner);
		assertEquals(0, newBowlerSpinner.getSelectedItemId()); // first bowler
		
		newBatsmanText = (EditText) activity.findViewById(R.id.newBowlerText);
		assertEquals("", newBatsmanText.getText().toString()); 
		

		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				newBatsmanText.setText("Bob");
			}
		});
		mInstrumentation.waitForIdleSync();
		
		assertEquals("Bob", newBatsmanText.getText().toString());
		
		clickButton(ButtonType.Done);

		Intent intent = getIntentWithResultCode(Keys.NEW_BOWLER_SCREEN);

		Bundle bundle = intent.getExtras();
		Team modifiedTeam = (Team) bundle.get(Keys.BOWLING_TEAM);
		assertEquals("Bob", modifiedTeam.getCurrentBowler().getName());
	}
	
	public void testCanNotAddMoreThanElevenBowler() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		activity.finish();
		
		Game game = testData.getGame(false);
		game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.Batting);
		
		game.getBattingTeam().getPlayerById(1).setBattingStatus(BattingStatus.Striker);
		game.getBattingTeam().getPlayerById(2).setBattingStatus(BattingStatus.NonStriker);
		
		extras.putSerializable(Keys.BOWLING_TEAM, game.getBowlingTeam());
		activity = launchActivity("cricket.ui", BowlerSelection.class, extras);

		newBatsmanText = (EditText) activity.findViewById(R.id.newBowlerText);
		assertFalse(newBatsmanText.isEnabled());
	}
	
}