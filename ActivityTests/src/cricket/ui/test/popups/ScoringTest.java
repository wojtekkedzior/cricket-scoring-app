package cricket.ui.test.popups;

import game.Game;
import game.Player;
import game.Team;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.popups.Scoring;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class ScoringTest extends TestBase<Scoring> {
	
	private Team battingTeam;
	private Team bowlingTeam;
	
	public ScoringTest() {
		super(Scoring.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Game game = new Game();
		battingTeam = game.getTeam(Game.TEAM_ONE);
		battingTeam.setTeamBattingStatus(TeamBattingStatus.Batting);

		bowlingTeam = game.getTeam(Game.TEAM_TWO);
		bowlingTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);

		Player batsman1 = new Player("batsman1", 1);
		batsman1.setBattingStatus(BattingStatus.Striker);
		battingTeam.addPlayer(batsman1);
		
		Player bowler1 = new Player("bowler1", 2);
		bowler1.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		bowlingTeam.addPlayer(bowler1);
		
		extras.putSerializable(Keys.GAME, game);
		activity = launchActivity("cricket.ui", Scoring.class, extras);
	}
	
	public void testOnClear() {
		final RadioGroup extrasGroup = (RadioGroup) activity.findViewById(cricket.ui.R.id.radiogroup1);
		
		assertEquals(-1, extrasGroup.getCheckedRadioButtonId());
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				extrasGroup.check(1);
			}
		});
		mInstrumentation.waitForIdleSync();
		
		assertEquals(1, extrasGroup.getCheckedRadioButtonId());
		
		clickButton(ButtonType.Clear);
		
		assertEquals(-1, extrasGroup.getCheckedRadioButtonId());
	}
	
	public void testGetRuns() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		final RadioGroup runGroup = (RadioGroup) activity.findViewById(cricket.ui.R.id.radiogroup2);
		
		assertEquals(-1, runGroup.getCheckedRadioButtonId());
	
		clickButton(ButtonType.Done);
		
		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);
		
		Bundle bundle = intent.getExtras();
		assertEquals(0, bundle.get(Keys.RUNS));
		assertEquals(BallType.SCORING, bundle.get(Keys.BALL_TYPE));
		assertEquals(false, bundle.get(Keys.IS_WICKET));
	}
	
	public void testGetSelectedRuns() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		final RadioGroup runGroup = (RadioGroup) activity.findViewById(cricket.ui.R.id.radiogroup2);
		
		assertEquals(-1, runGroup.getCheckedRadioButtonId());
	
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				runGroup.check(cricket.ui.R.id.oneRun);
				getButton(ButtonType.Done, activity).callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();
		
		Intent intent = getIntentWithResultCode(Keys.SCORING_SCREEN);
		
		Bundle bundle = intent.getExtras();
		assertEquals(1, bundle.get(Keys.RUNS));
		assertEquals(BallType.SCORING, bundle.get(Keys.BALL_TYPE));
		assertEquals(false, bundle.get(Keys.IS_WICKET));
	}
}
