package cricket.ui;

import game.Game;
import game.GameSettings;
import game.Team;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import backend.Storage;

import common.BattingStatus;
import common.GameType;
import common.Keys;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.popups.CreateTeam;
import cricket.ui.popups.CustomSettings;

public class WelcomeScreen extends Activity {

	private Game game;
	private Spinner battingTeamSpinner;
	private Spinner firstBatsmanSpinner;
	private Spinner secondBatsmanSpinner;
	private RadioGroup gameTypeRadioGroup;
	private TextView team1label;
	private TextView team2label;
	private TextView battingTeamLabel;
	private Button startButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcome_screen);

		team1label = (TextView) findViewById(R.id.team1Label);
		team2label = (TextView) findViewById(R.id.team2Label);

		battingTeamLabel = (TextView) findViewById(R.id.battingTeamLabel);
		battingTeamLabel.setVisibility(View.INVISIBLE);
		
		battingTeamSpinner = (Spinner) findViewById(R.id.battingTeam);
		battingTeamSpinner.setVisibility(View.INVISIBLE);

		firstBatsmanSpinner = (Spinner) findViewById(R.id.firstBatsmanSpinner);
		secondBatsmanSpinner = (Spinner) findViewById(R.id.secondBatsmanSpinner);
		
		gameTypeRadioGroup = (RadioGroup) findViewById(R.id.gameTypeRadioGroup);
		gameTypeRadioGroup.setOnCheckedChangeListener(listener);
		
		startButton = (Button) findViewById(R.id.startButton); 

		Bundle extras = getIntent().getExtras();

		if (extras == null) {
			game = Storage.getGame(this);
			
			if(game.getGameSettings() == null) { //TODO probably make this nicer
				startButton.setText("Start");
			} else {
				//TODO ask user here "found a game : blah blah. Do you want to use it or start new"
				startButton.setText("Resume");
			}

		} else { //we get here when we press the back button on the scoring screen
			game = (Game) extras.getSerializable(Keys.GAME);
			startButton.setText("Resume");
		}
		
		populateUIfromGame();
	}
	
	@Override
	public void onBackPressed() {
		//TODO need to ask user here if they want to just exit and not save their settings
		Storage.write(game, this);
		finish();
	}
	
	@Override
	protected void onPause() {
		Storage.write(game, this);
		super.onPause();
	}
	
//	@Override
//	protected void onResume() {
//		System.err.println("");
//		super.onResume();
//	}
	
	//TODO add test
	public void onExit(View view) {
		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(resultCode == 69) {
				Bundle extras = data.getExtras();
				game = (Game) extras.getSerializable(Keys.GAME);
				game.setResuming(true);
			}
		 else if(data != null) { //user canceled
			if(requestCode == Keys.CREATE_TEAM_SCREEN) {
				game = (Game) data.getExtras().getSerializable(Keys.GAME);
				populateUIfromGame();
			}
		}
	}

	private void populateUIfromGame() {
		String team1Name = game.getTeam(1).getTeamName();
		String team2Name = game.getTeam(2).getTeamName();

		if (team1Name != null && !team1Name.isEmpty()) {
			team1label.setText(team1Name);
		}
		if (team2Name != null && !team2Name.isEmpty()) {
			team2label.setText(team2Name);
		}

		if (team1Name != null && !team1Name.isEmpty() && team2Name != null && !team2Name.isEmpty()) {
			battingTeamSpinner.setVisibility(View.VISIBLE);
			battingTeamLabel.setVisibility(View.VISIBLE);

			List<String> teams = new ArrayList<String>();
			teams.add(team1Name);
			teams.add(team2Name);

			battingTeamSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, teams));
			battingTeamSpinner.setSelection(0);
			
			game.getTeam(1).setTeamBattingStatus(TeamBattingStatus.Batting);
			
			battingTeamSpinner.setOnItemSelectedListener(battingTeamSelection);

			firstBatsmanSpinner.setOnItemSelectedListener(onItemSelectedListener);
			secondBatsmanSpinner.setOnItemSelectedListener(onItemSelectedListener);
		}
		
		if(game.getGameSettings() != null) {
			GameType gameType = game.getGameSettings().getGameType();
			
			switch (gameType) {
			case FortyForty:
				gameTypeRadioGroup.check(R.id.fortyForty);
				break;
			case TwentyTwenty:
				gameTypeRadioGroup.check(R.id.twentyTwenty);
				break;
			case Custom:
				throw new UnsupportedOperationException();
			default:
				throw new UnsupportedOperationException();
			}
		}
	}

	public void onStart(View view) {
		Team battingTeam = game.getBattingTeam();
		
		if(battingTeam.getStriker() != null && battingTeam.getNonStriker() != null && game.getGameSettings() != null) {
			showScoringScreen();
		}
	}
	
	@Override
	public void finishFromChild(Activity child) {
		// TODO Auto-generated method stub
		super.finishFromChild(child);
	}

	private void updateOpeningBatmanSeletion() {
		Team battingFirstTeam = game.getBattingTeam();
		ArrayList<String> playerNames = battingFirstTeam.getAllPlayerNames();

		firstBatsmanSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, playerNames));
		
		if(battingFirstTeam.getStriker() != null) {
			int index = playerNames.indexOf(battingFirstTeam.getStriker().getName());
			firstBatsmanSpinner.setSelection(index);
		} else {
			firstBatsmanSpinner.setSelection(0);
		}
		
		secondBatsmanSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, playerNames));
		
		if(battingFirstTeam.getNonStriker() != null) {
			int index = playerNames.indexOf(battingFirstTeam.getNonStriker().getName());
			secondBatsmanSpinner.setSelection(index);
		} else {
			secondBatsmanSpinner.setSelection(1);
		}
	}

	private final OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Team battingFirstTeam = game.getBattingTeam();

			if (arg0.getId() == (R.id.firstBatsmanSpinner)) {
				battingFirstTeam.addOpeningBatsman(arg0.getSelectedItem().toString(), BattingStatus.Striker);
			} else { // secondBatsmanSpinner
				battingFirstTeam.addOpeningBatsman(arg0.getSelectedItem().toString(), BattingStatus.NonStriker);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	private final OnItemSelectedListener battingTeamSelection = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			if (arg0.getSelectedItem().toString().equals(game.getTeam(1).getTeamName())) {
				game.getTeam(1).setTeamBattingStatus(TeamBattingStatus.Batting);
				game.getTeam(1).setTeamBowlingStatus(TeamBowlingStatus.YetToBowl);
				
				game.getTeam(2).setTeamBowlingStatus(TeamBowlingStatus.Bowling);
				game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.YetToBat);
			} else { // team two selected
				game.getTeam(2).setTeamBattingStatus(TeamBattingStatus.Batting);
				game.getTeam(2).setTeamBowlingStatus(TeamBowlingStatus.YetToBowl);
				
				game.getTeam(1).setTeamBowlingStatus(TeamBowlingStatus.Bowling);
				game.getTeam(1).setTeamBattingStatus(TeamBattingStatus.YetToBat);
			}

			updateOpeningBatmanSeletion();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(checkedId == R.id.twentyTwenty) {
				game.setGameSettings(new GameSettings(GameType.TwentyTwenty));
			} else if (checkedId  == R.id.fortyForty) {
				game.setGameSettings(new GameSettings(GameType.FortyForty));
			} else {
				throw new UnsupportedOperationException();
			}
		}
	};
	
	public void onCreateTeam(View view) {
		if (view.getId() == R.id.team1Label) {
			changeScreen(1);
		} else if (view.getId() == R.id.team2Label) {
			changeScreen(2);
		}
	}
	
	public void onCustomSettings(View view) {
		Intent intent = new Intent(this, CustomSettings.class);

		Bundle bundle = new Bundle();
//		Team battingFirstTeam = getBattingTeam();
//		bundle.putInt(Keys.BATTING_TEAM_INDEX, battingFirstTeam.getTeamIndex());
//		bundle.putSerializable(Keys.GAME, game);
		intent.putExtras(bundle);

		startActivityForResult(intent, Keys.CUSTOM_SETTINGS);
	}

	private void showScoringScreen() {
		Intent intent = new Intent(this, ScoringScreen.class);

		Bundle bundle = new Bundle();
//		bundle.putSerializable(Keys.GAME_SETTINGS, new GameSettings(GameType.TwentyTwenty));
		bundle.putSerializable(Keys.GAME, game);
		intent.putExtras(bundle);

		startActivityForResult(intent, 69);
	}

	private void changeScreen(int teamIndex) {
		Intent intent = new Intent(this, CreateTeam.class);

		Bundle bundle = new Bundle();
		bundle.putInt(Keys.BATTING_TEAM_INDEX, teamIndex);
		bundle.putSerializable(Keys.GAME, game);

		intent.putExtras(bundle);
		startActivityForResult(intent, Keys.CREATE_TEAM_SCREEN);
	}
}