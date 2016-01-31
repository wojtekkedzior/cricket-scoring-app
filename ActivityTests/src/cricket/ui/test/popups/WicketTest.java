package cricket.ui.test.popups;

import game.Game;
import game.Player;
import game.Team;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import common.BallType;
import common.DismisalType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.R;
import cricket.ui.popups.Wicket;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class WicketTest extends TestBase<Wicket> {
	private Spinner dismissalTypeSpinner;
	private Spinner fielderSpinner;
	private Spinner batsmanSpinner;
	
	public WicketTest() {
		super(Wicket.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		Game game = new Game();
		Team battingTeam = game.getTeam(Game.TEAM_ONE);
		battingTeam.setTeamBattingStatus(TeamBattingStatus.Batting);

		Team bowlingTeam = game.getTeam(Game.TEAM_TWO);
		bowlingTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);
		
		battingTeam.addPlayer(new Player("batsman1", 1));
		battingTeam.addPlayer(new Player("batsman2", 2));
		battingTeam.addPlayer(new Player("batsman3", 3));
		extras.putSerializable(Keys.BATTING_TEAM, battingTeam);

		bowlingTeam.addPlayer(new Player("fielder1", 1));
		bowlingTeam.addPlayer(new Player("fielder2", 2));
		bowlingTeam.addPlayer(new Player("fielder3", 3));
		
		extras.putSerializable(Keys.GAME, game);
	}

	public void testDismissalTypeWithBye() throws Exception {
		extras.putSerializable(Keys.BALL_TYPE, BallType.BYE);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);
		SpinnerAdapter adapter = dismissalTypeSpinner.getAdapter();
		assertEquals(2, adapter.getCount());
		assertEquals(DismisalType.RUNOUT.getDismissalTypeName(), adapter.getItem(0));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName(), adapter.getItem(1));
	}

	public void testDismissalTypeWithLegBye() throws Exception {
		extras.putSerializable(Keys.BALL_TYPE, BallType.LEG_BYE);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);
		SpinnerAdapter adapter = dismissalTypeSpinner.getAdapter();
		assertEquals(2, adapter.getCount());
		assertEquals(DismisalType.RUNOUT.getDismissalTypeName(), adapter.getItem(0));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName(), adapter.getItem(1));
	}

	public void testDismissalTypeWithNoBall() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.NO_BALL_EXTRA);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);
		SpinnerAdapter adapter = dismissalTypeSpinner.getAdapter();
		assertEquals(2, adapter.getCount());
		assertEquals(DismisalType.RUNOUT.getDismissalTypeName(), adapter.getItem(0));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName(), adapter.getItem(1));
	}

	public void testDismissalTypeWithWide() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.WIDE);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);
		SpinnerAdapter adapter = dismissalTypeSpinner.getAdapter();
		assertEquals(3, adapter.getCount());
		assertEquals(DismisalType.RUNOUT.getDismissalTypeName(), adapter.getItem(0));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName(), adapter.getItem(1));
		assertEquals(DismisalType.STUMPED.getDismissalTypeName(), adapter.getItem(2));
	}

	public void testDismissalTypeWithScoring() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);
		SpinnerAdapter adapter = dismissalTypeSpinner.getAdapter();
		int count = adapter.getCount();
		assertEquals(7, count);

		assertEquals(DismisalType.BOWLED.getDismissalTypeName(), adapter.getItem(0));
		assertEquals(DismisalType.RUNOUT.getDismissalTypeName(), adapter.getItem(1));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName(), adapter.getItem(2));
		assertEquals(DismisalType.CAUGHT.getDismissalTypeName(), adapter.getItem(3));
		assertEquals(DismisalType.STUMPED.getDismissalTypeName(), adapter.getItem(4));
		assertEquals(DismisalType.LBW.getDismissalTypeName(), adapter.getItem(5));
		assertEquals(DismisalType.HIT_WICKET.getDismissalTypeName(), adapter.getItem(6));
	}

	public void testEmptyFielder() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);
		fielderSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.fielderSpinner);

		assertFalse(fielderSpinner.isEnabled());
		assertEquals(3, fielderSpinner.getSelectedItemId());
		assertEquals("", fielderSpinner.getSelectedItem());
	}
	
	public void testOnStrike() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		fielderSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.fielderSpinner);
		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);

		checkIsOnStrikeIsEnabled(1, true); // runout
		checkIsOnStrikeIsEnabled(2, true); // non striker runout
		checkIsOnStrikeIsEnabled(3, true); // caught
		
		checkIsOnStrikeIsEnabled(4, false); // stumped
		checkIsOnStrikeIsEnabled(0, false); // Bowled
		checkIsOnStrikeIsEnabled(5, false); // LBW
		checkIsOnStrikeIsEnabled(6, false); // HitWicket
	}

	private void checkIsOnStrikeIsEnabled(final int selectionId, boolean enabled) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				dismissalTypeSpinner.setSelection(selectionId);
			}
		});
		mInstrumentation.waitForIdleSync();
		
		RadioGroup onStrikeRadio = (RadioGroup) activity.findViewById(R.id.onStrikeGroup);
		if(enabled) {
			assertTrue(onStrikeRadio.isEnabled());
		} else {
			assertFalse(onStrikeRadio.isEnabled());
		}
	}

	public void testFielderSpinnerIsEnabled() {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		fielderSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.fielderSpinner);
		dismissalTypeSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.dismissalTypeSpinner);

		checkFielderSpinnerIsEnabled(1, true); // runout
		checkFielderSpinnerIsEnabled(2, true); // non striker runout
		checkFielderSpinnerIsEnabled(3, true); // caught
		checkFielderSpinnerIsEnabled(4, true); // stumped
		
		checkFielderSpinnerIsEnabled(0, false); // Bowled
		checkFielderSpinnerIsEnabled(5, false); // LBW
		checkFielderSpinnerIsEnabled(6, false); // HitWicket
	}

	private void checkFielderSpinnerIsEnabled(final int selectionId, boolean enabled) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				dismissalTypeSpinner.setSelection(selectionId);
			}
		});
		mInstrumentation.waitForIdleSync();
		
		if(enabled) {
			assertTrue(fielderSpinner.isEnabled());
			assertEquals(1, fielderSpinner.getSelectedItemId());
			assertEquals("fielder2", fielderSpinner.getSelectedItem());
		} else {
			assertFalse(fielderSpinner.isEnabled());
			assertEquals(3, fielderSpinner.getSelectedItemId());
			assertEquals("", fielderSpinner.getSelectedItem());
		}
	}

	public void testOnDone() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		assertFalse(activity.isFinishing());

		clickButton(ButtonType.Done);

		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);

		Bundle bundle = intent.getExtras();
		assertEquals(DismisalType.BOWLED, bundle.get(Keys.DISMISSAL_TYPE));
//		assertEquals("batsman1", bundle.get(Keys.NEW_BATSMAN)); TODO
		assertNull(bundle.get(Keys.FIELDER));
//		assertEquals(true, bundle.get(Keys.NEW_BATSMAN_ON_STRIKE));
	}
	
	public void testOnDoneNewBatsmanNotOnStrike() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		assertFalse(activity.isFinishing());

		final RadioButton notOnStrike  = (RadioButton) activity.findViewById(R.id.notOnStrike);
		assertFalse(notOnStrike.isSelected());
		
		//TODO this check(true) DOESNT WORK WPROPELY
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				notOnStrike.setChecked(true);
				getButton(ButtonType.Done, activity).callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);

		Bundle bundle = intent.getExtras();
		assertEquals(DismisalType.BOWLED, bundle.get(Keys.DISMISSAL_TYPE));
//		assertEquals("batsman1", bundle.get(Keys.NEW_BATSMAN)); TODO
		assertNull(bundle.get(Keys.FIELDER));
//		assertEquals(false, bundle.get(Keys.NEW_BATSMAN_ON_STRIKE));
	}
	
	public void testSelectNewBatsman() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);

		batsmanSpinner = (Spinner) activity.findViewById(cricket.ui.R.id.newBatsmanSpinner);

		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				batsmanSpinner.setSelection(2);
				getButton(ButtonType.Done, activity).callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();

		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);

		Bundle bundle = intent.getExtras();
//		assertEquals("batsman3", bundle.get(Keys.NEW_BATSMAN));TODO

		assertEquals(DismisalType.BOWLED, bundle.get(Keys.DISMISSAL_TYPE));
		assertNull(bundle.get(Keys.FIELDER));
//		assertEquals(true, bundle.get(Keys.NEW_BATSMAN_ON_STRIKE));
	}
	
	public void testCanAddNewBatsman() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		activity = launchActivity("cricket.ui", Wicket.class, extras);
		
		batsmanSpinner = (Spinner) activity.findViewById(R.id.newBatsmanSpinner);
		assertEquals(0, batsmanSpinner.getSelectedItemId());
		
		final EditText newBatsmanText = (EditText) activity.findViewById(R.id.newBatsmanText);
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

		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);

		Bundle bundle = intent.getExtras();
//		assertEquals("Bob", bundle.get(Keys.NEW_BATSMAN)); TODO

		assertEquals(DismisalType.BOWLED, bundle.get(Keys.DISMISSAL_TYPE));
		assertNull(bundle.get(Keys.FIELDER));
//		assertEquals(true, bundle.get(Keys.NEW_BATSMAN_ON_STRIKE));
		
		Team team = (Team) bundle.getSerializable(Keys.BATTING_TEAM);
		assertNotNull("Bob", team.getPlayerByName("Bob"));
	}

	public void testCanNotAddMoreThanElevenBatsman() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		extras.putSerializable(Keys.BALL_TYPE, BallType.SCORING);
		
		Game game = testData.getGame(false);
		game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.Batting);
		extras.putSerializable(Keys.GAME, game);
		
		activity = launchActivity("cricket.ui", Wicket.class, extras);
		
		final EditText newBatsmanText = (EditText) activity.findViewById(R.id.newBatsmanText);
		assertFalse(newBatsmanText.isEnabled());
	}
}