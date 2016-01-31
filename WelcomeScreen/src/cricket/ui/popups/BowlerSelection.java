package cricket.ui.popups;

import game.Player;
import game.Team;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import common.BowlingStatus;
import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class BowlerSelection extends GenericActivity {
	
	private Spinner newBowlerSpinner;
	private EditText newBowlerText;
	
	private Team bowlingTeam;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_bowler_selection);
		setFinishOnTouchOutside(false);
		
		Bundle extras = getIntent().getExtras();
		
		bowlingTeam = (Team) extras.getSerializable(Keys.BOWLING_TEAM);
		
		ArrayList<String> listOfBowlers = new ArrayList<String>();

		for (Player player : bowlingTeam.getBowlerNamesWhoCanBowl()) {
			listOfBowlers.add(player.getName());
		}
		
		newBowlerText = (EditText) findViewById(R.id.newBowlerText);
		
		if(bowlingTeam.getPlayers().size() == 11) {
			newBowlerText.setEnabled(false);
		}
		
		newBowlerSpinner = (Spinner) findViewById(R.id.newBowlerSpinner);
		newBowlerSpinner.setAdapter(new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_dropdown_item, listOfBowlers));
	}
	
	@Override
	public void onDone(View view) {
		Intent intent = new Intent();
		
		int teamSpecificPlayerId = 0;
		
		if (bowlingTeam.getTeamIndex() == 1) {
			teamSpecificPlayerId = 100;
		} else {
			teamSpecificPlayerId = 200;
		}
		
		String newBowlerName = newBowlerText.getText().toString();
		
		if(!newBowlerName.equals("")) {
			bowlingTeam.addPlayer(new Player(newBowlerName, teamSpecificPlayerId+10));
		} else {
			newBowlerName = (String) newBowlerSpinner.getSelectedItem();
		}
		
		if(bowlingTeam.getOtherBowler() != null) {
			bowlingTeam.getOtherBowler().setBowlingStatus(BowlingStatus.Available);
		}
		
		//current bowler becomes other bowler
		if(bowlingTeam.getCurrentBowler() != null) { //first over
			bowlingTeam.getCurrentBowler().setBowlingStatus(BowlingStatus.OtherBowler);
		}
		
		bowlingTeam.setBowlingStatusForBowler(newBowlerName, BowlingStatus.CurrentlyBowling);
		
		intent.putExtra(Keys.BOWLING_TEAM, bowlingTeam);
		
		setResult(Keys.NEW_BOWLER_SCREEN, intent);
		finish();
	}
}
