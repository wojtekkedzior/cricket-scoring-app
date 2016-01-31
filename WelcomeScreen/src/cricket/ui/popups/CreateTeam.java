package cricket.ui.popups;

import game.Game;
import game.Player;
import game.Team;

import java.util.Map;
import java.util.Map.Entry;

import common.Keys;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cricket.ui.GenericActivity;
import cricket.ui.R;

public class CreateTeam extends GenericActivity {

	private Game game;
	private int teamIndex;

	private EditText player1Name;
	private EditText player2Name;
	private EditText player3Name;
	private EditText player4Name;
	private EditText player5Name;
	private EditText player6Name;
	private EditText player7Name;
	private EditText player8Name;
	private EditText player9Name;
	private EditText player10Name;
	private EditText player11Name;
	
	private Button doneButton;
	
	private SparseArray<EditText> playerNameFields;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_create_team);

		Bundle extras = getIntent().getExtras();
		teamIndex = extras.getInt(Keys.BATTING_TEAM_INDEX);
		game = (Game) extras.getSerializable(Keys.GAME);
		
		playerNameFields = new SparseArray<EditText>(11);
		
		doneButton = (Button) findViewById(R.id.doneButton);
		doneButton.setEnabled(false);
		
		Team team = game.getTeam(teamIndex);
		((EditText) findViewById(R.id.teamNameText)).setText(team.getTeamName());

		player1Name = (EditText) findViewById(R.id.player1Name);
		player2Name = (EditText) findViewById(R.id.player2Name);
		player3Name = (EditText) findViewById(R.id.player3Name);
		player4Name = (EditText) findViewById(R.id.player4Name);
		player5Name = (EditText) findViewById(R.id.player5Name);
		player6Name = (EditText) findViewById(R.id.player6Name);
		player7Name = (EditText) findViewById(R.id.player7Name);
		player8Name = (EditText) findViewById(R.id.player8Name);
		player9Name = (EditText) findViewById(R.id.player9Name);
		player10Name = (EditText) findViewById(R.id.player10Name);
		player11Name = (EditText) findViewById(R.id.player11Name);
		
		player1Name.addTextChangedListener(watcher);
		player2Name.addTextChangedListener(watcher);
		player3Name.addTextChangedListener(watcher);
		player4Name.addTextChangedListener(watcher);
		player5Name.addTextChangedListener(watcher);
		player6Name.addTextChangedListener(watcher);
		player7Name.addTextChangedListener(watcher);
		player8Name.addTextChangedListener(watcher);
		player9Name.addTextChangedListener(watcher);
		player10Name.addTextChangedListener(watcher);
		player11Name.addTextChangedListener(watcher);
		
		playerNameFields.put(1, player1Name);
		playerNameFields.put(2, player2Name);
		playerNameFields.put(3, player3Name);
		playerNameFields.put(4, player4Name);
		playerNameFields.put(5, player5Name);
		playerNameFields.put(6, player6Name);
		playerNameFields.put(7, player7Name);
		playerNameFields.put(8, player8Name);
		playerNameFields.put(9, player9Name);
		playerNameFields.put(10, player10Name);
		playerNameFields.put(11, player11Name);
		
		populatePlayerNames(team);
	}
	
	private void populatePlayerNames(Team team) {
 		Map<Integer, Player> players = team.getPlayers();
		int teamSpecificPlayerId = 0;
		
		if (teamIndex == 1) {
		teamSpecificPlayerId = 100;
		} else {
			teamSpecificPlayerId = 200; 
		}
		
		for (Entry<Integer, Player> player : players.entrySet()) {
			int playerNumber = player.getKey() - teamSpecificPlayerId;
			
			if(playerNameFields.get(playerNumber) != null) { //ignore empty places maybe TODO
				playerNameFields.get(playerNumber).setText(player.getValue().getName());
			}
		}
	}
	
	TextWatcher watcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			checkPlayerNames();
		}
	};
	
	//TODO: optimise
	private void checkPlayerNames() {
		int totalTeamMembers = 0;

		if (player1Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player2Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player3Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player4Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player5Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player6Name.getText().length() > 0) {
			totalTeamMembers++;
		}
		if (player7Name.getText().length() > 0) {
			totalTeamMembers++;
			
			if(totalTeamMembers == 7) {
				doneButton.setEnabled(true);
				return;
			}
		} else {
			doneButton.setEnabled(false);
		}
//		if (player8Name.getText().length() > 0) {
//			totalTeamMembers++;
//		}
//		if (player9Name.getText().length() > 0) {
//			totalTeamMembers++;
//		}
//		if (player10Name.getText().length() > 0) {
//			totalTeamMembers++;
//		}
//		if (player11Name.getText().length() > 0) {
//			totalTeamMembers++;
//		}

//		if (totalTeamMembers >= 7) {
//			doneButton.setEnabled(true);
//		} else {
//			doneButton.setEnabled(false);
//		}
	}
	
	@Override
	public void onDone(View view) {
		String teamName = ((EditText) findViewById(R.id.teamNameText)).getText().toString();

		Team team = game.getTeam(teamIndex);
		team.setTeamName(teamName);
		
		
		int teamSpecificPlayerId = 0;
		
		if (teamIndex == 1) {
			teamSpecificPlayerId = 100;
		} else {
			teamSpecificPlayerId = 200;
		}
		
		team.getPlayers().clear();
		
		addPlayer(team, player1Name, ++teamSpecificPlayerId);
		addPlayer(team, player2Name, ++teamSpecificPlayerId);
		addPlayer(team, player3Name, ++teamSpecificPlayerId);
		addPlayer(team, player4Name, ++teamSpecificPlayerId);
		addPlayer(team, player5Name, ++teamSpecificPlayerId);
		addPlayer(team, player6Name, ++teamSpecificPlayerId);
		addPlayer(team, player7Name, ++teamSpecificPlayerId);
		addPlayer(team, player8Name, ++teamSpecificPlayerId);
		addPlayer(team, player9Name, ++teamSpecificPlayerId);
		addPlayer(team, player10Name, ++teamSpecificPlayerId);
		addPlayer(team, player11Name, ++teamSpecificPlayerId);

		changeScreen();
	}
	
	private void addPlayer(Team team, EditText editText, int playerId) {
		if(editText.getText().toString().length() > 0) {
			team.addPlayer(new Player(editText.getText().toString(), playerId));
		}
	}
	 
	private void changeScreen() {
		Intent intent = new Intent();

		Bundle bundle = new Bundle();
		bundle.putSerializable(Keys.GAME, game);

		intent.putExtras(bundle);
		
		setResult(Keys.CREATE_TEAM_SCREEN, intent);
		finish();
	}
}