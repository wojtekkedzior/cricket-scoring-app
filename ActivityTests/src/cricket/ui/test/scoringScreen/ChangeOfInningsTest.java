package cricket.ui.test.scoringScreen;

import game.Game;
import game.GameSettings;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.Spinner;

import common.BattingStatus;
import common.GameType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class ChangeOfInningsTest extends ScoringScreenTestBase {

	private Button previousBowlerButton;
	private Button newBowlerButton;
	private Button dotBallButton;
	private Button undoButton;
	private Button newInningsButton;

	public ChangeOfInningsTest() {
		super(ScoringScreen.class);
	}

	private void localSetup() {
		activity.finish();

		game.setGameSettings(new GameSettings(GameType.Custom));
		game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.Batting);
		game.getTeam(1).setTeamBowlingStatus(TeamBowlingStatus.Bowling);
		
		game.getBattingTeam().getPlayerById(1).setBattingStatus(BattingStatus.Striker);
		game.getBattingTeam().getPlayerById(2).setBattingStatus(BattingStatus.NonStriker);

		game.getTeam(2).getPlayers().remove(3);
		game.getTeam(2).getPlayers().remove(4);
		game.getTeam(2).getPlayers().remove(5);
		game.getTeam(2).getPlayers().remove(6);
		game.getTeam(2).getPlayers().remove(7);
		game.getTeam(2).getPlayers().remove(8);
		game.getTeam(2).getPlayers().remove(9);
		game.getTeam(2).getPlayers().remove(10);
		game.getTeam(2).getPlayers().remove(11);

		extras.putSerializable(Keys.GAME, game);
		activity = launchActivity("cricket.ui", ScoringScreen.class, extras);
		
		newInningsButton = (Button) activity.findViewById(R.id.newInningsButton);
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
		previousBowlerButton = (Button) activity.findViewById(R.id.previousBowlerButton);
		dotBallButton = (Button) activity.findViewById(R.id.dotBallButton);
		undoButton = (Button) activity.findViewById(R.id.undoButton);
	}

	public void testNewInningsDisabled() {
		Button button = (Button) activity.findViewById(R.id.newInningsButton);
		assertFalse(button.isEnabled());
	}

	public void testAllOut() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		localSetup();

		selectNewBowler("Mills");
		selectRuns(1);
		selectWicket(true);

		final Activity lastActivity = wicketMonitor.waitForActivity();
		Spinner batsmanSpinner = (Spinner) lastActivity.findViewById(cricket.ui.R.id.newBatsmanSpinner);
		assertEquals(0, batsmanSpinner.getAdapter().getCount());

		clickButton(ButtonType.Done, lastActivity);

		Intent intent = getIntentFromActivity(lastActivity);
		assertTrue(intent.getExtras().getBoolean(Keys.ALL_OUT));
	}
	
	public void testBowledOut() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		localSetup();

		selectNewBowler("Mills");
		selectRuns(1);
		selectRuns(3);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		
		checkButtonsAndCloseNewInningsPopup(8);
		
		Button endGameButton = (Button) activity.findViewById(R.id.endGameButton);
		assertEquals(Button.VISIBLE, endGameButton.getVisibility());
		assertTrue(endGameButton.isEnabled());
	}

	public void testTargetReached() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		localSetup();
		
		selectNewBowler("Mills");
		selectRuns(1);
		selectRuns(1);
		selectRuns(3);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		
		checkButtonsAndCloseNewInningsPopup(8);

		selectNewBowler("Franklin");
		selectRuns(4);
		selectRuns(6);

		checkAllButtonsEnabled();

		Button endGameButton = (Button) activity.findViewById(R.id.endGameButton);
		assertEquals(Button.VISIBLE, endGameButton.getVisibility());
		assertTrue(endGameButton.isEnabled());
	}
	
	public void testTargetReachedOnLastBall() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		localSetup();

		selectNewBowler("Mills");
		selectRuns(1);
		selectRuns(1);
		selectRuns(3);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		
		checkButtonsAndCloseNewInningsPopup(8);

		selectNewBowler("Franklin");
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(3);

		checkAllButtonsEnabled();

		Button endGameButton = (Button) activity.findViewById(R.id.endGameButton);
		assertEquals(Button.VISIBLE, endGameButton.getVisibility());
		assertTrue(endGameButton.isEnabled());
	}
	
	public void testTargetNotReachedWithInMaxOver() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		localSetup();

		selectNewBowler("Mills");
		selectRuns(4);
		selectRuns(3);
		selectRuns(4);
		selectRuns(4);
		selectRuns(4);
		selectRuns(4);
		
		checkButtonsAndCloseNewInningsPopup(23);

		selectNewBowler("Franklin");
		selectRuns(4);
		selectRuns(1);
		selectRuns(3);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);

		checkAllButtonsEnabled();

		Button endGameButton = (Button) activity.findViewById(R.id.endGameButton);
		assertEquals(Button.VISIBLE, endGameButton.getVisibility());
		assertTrue(endGameButton.isEnabled());
	}

	private void checkAllButtonsEnabled() {
		assertFalse(previousBowlerButton.isEnabled());
		assertFalse(newBowlerButton.isEnabled());
		assertFalse(dotBallButton.isEnabled());
		assertFalse(ball.isEnabled());
		assertFalse(newInningsButton.isEnabled());
		assertTrue(undoButton.isEnabled());
	}
	
	private void checkButtonsAndCloseNewInningsPopup(int expectedTarget) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		assertFalse(previousBowlerButton.isEnabled());
		assertFalse(newBowlerButton.isEnabled());
		assertFalse(dotBallButton.isEnabled());
		assertFalse(ball.isEnabled());
		assertTrue(newInningsButton.isEnabled());
		assertTrue(undoButton.isEnabled());

		activity.runOnUiThread(new Runnable() {
			public void run() {
				newInningsButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();

		Activity newInningsActivity = newInningsMonitor.waitForActivity();
		clickButton(ButtonType.Done, newInningsActivity);
		
		Game g = (Game) getValueFromField("game", activity);
		Integer target = g.getGameStatus().getTarget();
		assertEquals(Integer.valueOf(expectedTarget), target);
	}
}