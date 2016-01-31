package cricket.ui.test;

import game.Game;
import game.Player;
import game.Team;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import backend.Storage;

import common.BattingStatus;
import common.GameType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.R;
import cricket.ui.WelcomeScreen;

public class WelcomeScreenTest extends TestBase<WelcomeScreen> {

	private Game game;
	
	private TextView battingTeamLabel;
	private Spinner battingTeamSpinner;
	
	private Spinner firstBatsmanSpinner;
	private Spinner secondBatsmanSpinner;
	
	private Button startButton;
	
	public WelcomeScreenTest() {
		super(WelcomeScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		extras = null; //fist time the app is run
		activity = launchActivity("cricket.ui", WelcomeScreen.class, extras);
		
		startButton = (Button) activity.findViewById(cricket.ui.R.id.startButton);
	}
	
	public void testBattingTeamLabelIsInvisible() {
		battingTeamLabel = (TextView) activity.findViewById(R.id.battingTeamLabel);
		assertEquals(View.INVISIBLE, battingTeamLabel.getVisibility());

		Spinner battingTeamSpinner = (Spinner) activity.findViewById(R.id.battingTeam);
		assertEquals(View.INVISIBLE, battingTeamSpinner.getVisibility());
	}
	
	public void testOpeningBatsmanSpinnerIsEmpty() {
		firstBatsmanSpinner = (Spinner) activity.findViewById(R.id.firstBatsmanSpinner);
		secondBatsmanSpinner = (Spinner) activity.findViewById(R.id.secondBatsmanSpinner);
		
		assertNull(firstBatsmanSpinner.getSelectedItem());
		assertNull(secondBatsmanSpinner.getSelectedItem());
	}
	
	public void testSelectGameType() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		final RadioGroup gameTypeRadioGroup = (RadioGroup) activity.findViewById(R.id.gameTypeRadioGroup);
		
		assertEquals(-1, gameTypeRadioGroup.getCheckedRadioButtonId());
		
		game = (Game) getValueFromField("game", activity);
		
		assertNull(game.getGameSettings());
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				gameTypeRadioGroup.check(R.id.twentyTwenty);
			}
		});
		
		mInstrumentation.waitForIdleSync();
		
		game = (Game) getValueFromField("game", activity);
		assertEquals(GameType.TwentyTwenty, game.getGameSettings().getGameType());
		
		//Select 40/40
		activity.runOnUiThread(new Runnable() {
			public void run() {
				gameTypeRadioGroup.check(R.id.fortyForty);
			}
		});
		
		mInstrumentation.waitForIdleSync();
		
		game = (Game) getValueFromField("game", activity);
		assertEquals(GameType.FortyForty, game.getGameSettings().getGameType());
	}
	
	public void testTeamNames() {
		createGameAndStartActivity();
		
		TextView team1label = (TextView) activity.findViewById(R.id.team1Label);
		TextView team2label = (TextView) activity.findViewById(R.id.team2Label);
		
		assertEquals("New Zealand", team1label.getText());
		assertEquals("Zimbabwe", team2label.getText());
	}
	
	public void testTeamBattingFirst() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		createGameAndStartActivity();
		
		battingTeamLabel = (TextView) activity.findViewById(R.id.battingTeamLabel);
		assertEquals(View.VISIBLE, battingTeamLabel.getVisibility());
		
		battingTeamSpinner = (Spinner) activity.findViewById(R.id.battingTeam);
		assertEquals(View.VISIBLE, battingTeamSpinner.getVisibility());
		
		assertEquals("New Zealand", battingTeamSpinner.getSelectedItem());
		
		//Get the game object from the activity - since we are not waiting until we finish the activity
		game = (Game) getValueFromField("game", activity);
		assertTrue(game.getTeam(1).getTeamBattingStatus().equals(TeamBattingStatus.Batting));
		assertTrue(game.getTeam(1).getTeamBowlingStatus().equals(TeamBowlingStatus.YetToBowl));
		
		assertTrue(game.getTeam(2).getTeamBattingStatus().equals(TeamBattingStatus.YetToBat));
		assertTrue(game.getTeam(2).getTeamBowlingStatus().equals(TeamBowlingStatus.Bowling));
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				battingTeamSpinner.setSelection(1);
			}
		});
		
		mInstrumentation.waitForIdleSync();
		assertEquals("Zimbabwe", battingTeamSpinner.getSelectedItem());
		
		assertTrue(game.getTeam(1).getTeamBattingStatus().equals(TeamBattingStatus.YetToBat));
		assertTrue(game.getTeam(1).getTeamBowlingStatus().equals(TeamBowlingStatus.Bowling));
		
		assertTrue(game.getTeam(2).getTeamBattingStatus().equals(TeamBattingStatus.Batting));
		assertTrue(game.getTeam(2).getTeamBowlingStatus().equals(TeamBowlingStatus.YetToBowl));
	}
	
	public void testOpeningBatsman() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		createGameAndStartActivity();
		
		firstBatsmanSpinner = (Spinner) activity.findViewById(R.id.firstBatsmanSpinner);
		secondBatsmanSpinner = (Spinner) activity.findViewById(R.id.secondBatsmanSpinner);
		
		assertEquals(0, firstBatsmanSpinner.getSelectedItemPosition());
		assertEquals(1, secondBatsmanSpinner.getSelectedItemPosition());
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				firstBatsmanSpinner.setSelection(3);
				secondBatsmanSpinner.setSelection(6);
			}
		});
		
		mInstrumentation.waitForIdleSync();
		
		game = (Game) getValueFromField("game", activity);
		Team battingTeam = game.getTeam(1);
		
		String selectedFirstBatsman = (String) firstBatsmanSpinner.getSelectedItem();
		String selectedSecondBatsman = (String) secondBatsmanSpinner.getSelectedItem();
		
		Player striker = battingTeam.getBatsmanByStatus(BattingStatus.Striker);
		Player nonStriker = battingTeam.getBatsmanByStatus(BattingStatus.NonStriker);
		
		assertEquals(selectedFirstBatsman, striker.getName());
		assertEquals(selectedSecondBatsman, nonStriker.getName());
	}
	
	public void testBackButtonPressed() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InterruptedException {
		game = (Game) getValueFromField("game", activity);
		activity.onBackPressed();
		//Need a small delay here for the file to be written before we go retrieving it. 
		Thread.sleep(1000);
		assertEquals(game, Storage.getGame(activity));
	}
	
	public void testResumeButton() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		assertEquals("Start", startButton.getText());
		
		createGameAndStartActivity();
		
		startButton = (Button) activity.findViewById(cricket.ui.R.id.startButton);
		assertEquals("Resume", startButton.getText());
	}
	
	public void testStartButton() {
		assertEquals("Start", startButton.getText());
	}
	
	private void createGameAndStartActivity() {
		activity.finish();
		
		extras = new Bundle();
		extras.putSerializable(Keys.GAME, testData.getGame(false));
		activity = launchActivity("cricket.ui", WelcomeScreen.class, extras);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		while(!activity.isDestroyed()) {
			//need to wait until the activity is destroyed before cleaning the cache
		}
		
		Storage.clear(activity);
	}
}