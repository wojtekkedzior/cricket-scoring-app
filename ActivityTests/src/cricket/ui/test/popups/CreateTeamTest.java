package cricket.ui.test.popups;

import game.Game;
import game.Player;
import game.Team;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import common.Keys;

import cricket.ui.R;
import cricket.ui.popups.CreateTeam;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class CreateTeamTest extends TestBase<CreateTeam> {
	
	private Game game;
	private Team team;
	private Button doneButton;
	
	private EditText player6Name;
	private EditText player7Name;
	
	public CreateTeamTest() {
		super(CreateTeam.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mInstrumentation = getInstrumentation();
		
		game = new Game();
		team = game.getTeam(1);
		team.setTeamName("teamName");
		team.addPlayer(new Player("player1", 101));
		team.addPlayer(new Player("player2", 102));
		team.addPlayer(new Player("player3", 103));
		team.addPlayer(new Player("player4", 104));
		team.addPlayer(new Player("player5", 105));
		
		extras.putInt(Keys.BATTING_TEAM_INDEX, 1);
		extras.putSerializable(Keys.GAME, game);
		
		activity = launchActivity("cricket.ui", CreateTeam.class, extras);
		
		player6Name = (EditText) activity.findViewById(R.id.player6Name);
		player7Name = (EditText) activity.findViewById(R.id.player7Name);
		
		doneButton = (Button) activity.findViewById(R.id.doneButton);
	}
	
	public void testAddPlayerNames() {
		checkInitialFivePlayers();
		assertFalse(doneButton.isEnabled());
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				player6Name.setText("player6");
				player7Name.setText("player7");
			}
		});
		
		mInstrumentation.waitForIdleSync();
		assertTrue(doneButton.isEnabled());
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				player7Name.setText("");
			}
		});
		
		mInstrumentation.waitForIdleSync();
		assertFalse(doneButton.isEnabled());
	}

	public void testAddMorePlayers() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		checkInitialFivePlayers();
		
		final EditText teamName = (EditText) activity.findViewById(R.id.teamNameText);
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				teamName.setText("teamNameRenamed");
				player6Name.setText("player6");
				player7Name.setText("player7");
			}
		});
		
		mInstrumentation.waitForIdleSync();
		
		assertTrue(doneButton.isEnabled());
		
		clickButton(ButtonType.Done);
		
		Intent intent = getIntentWithResultCode(Keys.CREATE_TEAM_SCREEN);
		
		Bundle bundle = intent.getExtras();
		Game game = (Game) bundle.getSerializable(Keys.GAME);
		Team team = game.getTeam(1);
		
		assertEquals("teamNameRenamed", team.getTeamName());
		
		ArrayList<String> allPlayerNames = team.getAllPlayerNames();
		assertEquals(7, allPlayerNames.size());
		assertTrue(allPlayerNames.contains("player6"));
		assertTrue(allPlayerNames.contains("player7"));
	}
	
	private void checkInitialFivePlayers() {
		EditText teamName = (EditText) activity.findViewById(R.id.teamNameText);
		assertEquals("teamName", teamName.getEditableText().toString());
		
		EditText player1Name = (EditText) activity.findViewById(R.id.player1Name);
		assertEquals("player1", player1Name.getEditableText().toString());
		
		EditText player2Name = (EditText) activity.findViewById(R.id.player2Name);
		assertEquals("player2", player2Name.getEditableText().toString());
		
		EditText player3Name = (EditText) activity.findViewById(R.id.player3Name);
		assertEquals("player3", player3Name.getEditableText().toString());
		
		EditText player4Name = (EditText) activity.findViewById(R.id.player4Name);
		assertEquals("player4", player4Name.getEditableText().toString());
		
		EditText player5Name = (EditText) activity.findViewById(R.id.player5Name);
		assertEquals("player5", player5Name.getEditableText().toString());
	}
}