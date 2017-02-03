package cricket.ui.popups;


import game.Game;
import game.Player;
import game.Team;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;
import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class Wicket extends GenericActivity {
	private RadioGroup onStrike;
	
	private List<String> listOfFielders;
	
	private Team battingTeam;
	private Team bowlingTeam;
	
	Game game;
	
	private Spinner dismissalTypesSpinner;
	private Spinner fieldersSpinner;
	private Spinner batsmanSpinner;
	
	private EditText newBatsmanText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.popup_wicket);
		setFinishOnTouchOutside(false);
		
		Bundle extras = getIntent().getExtras();
		
		game = (Game) extras.getSerializable(Keys.GAME);
		
		battingTeam = game.getBattingTeam();
		bowlingTeam = game.getBowlingTeam();
		
		ArrayList<String> batsmanNames = battingTeam.getAvailableBatsmanNames();
		listOfFielders = bowlingTeam.getAllPlayerNames();
		
		onStrike  = (RadioGroup) findViewById(R.id.onStrikeGroup);
		
		//Set onStrike to default
		RadioButton onStrikeRadio = (RadioButton) findViewById(R.id.onStrike);
		onStrikeRadio.setChecked(true);

		populateDismissalTypes((BallType) extras.getSerializable(Keys.BALL_TYPE));
		
		newBatsmanText = (EditText) findViewById(R.id.newBatsmanText);
		if(battingTeam.getPlayers().size() == 11) {
			newBatsmanText.setEnabled(false);
		}
		
		//A fielder is not required
		listOfFielders.add("");
		fieldersSpinner = (Spinner) findViewById(R.id.fielderSpinner);
		fieldersSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOfFielders));
		//set to the first fielder
		fieldersSpinner.setSelection(1); 
		
		batsmanSpinner = (Spinner) findViewById(R.id.newBatsmanSpinner);
		batsmanSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, batsmanNames));
	}

	private void populateDismissalTypes(BallType ballType) {
		List<String> dismissalTypes= new ArrayList<String>();
		
		switch (ballType) {
		case BYE:
			dismissalTypes.add(DismisalType.RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName());
			break;
		case DEAD_BALL:
			break;
		case DOT_BALL:
			break;
		case LEG_BYE:
			dismissalTypes.add(DismisalType.RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName());
			break;
		case NO_BALL_EXTRA:
			dismissalTypes.add(DismisalType.RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName());
			break;
		case NO_BALL_RUN:
			dismissalTypes.add(DismisalType.RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName());
			break;
		case SCORING:
			for (DismisalType types : DismisalType.values()) {
				dismissalTypes.add(types.getDismissalTypeName());
			}
			break;
		case WICKET:
			// although this is a wicket, the BallType is still a scroing shot. BallType.Wicket is used for other things
			break;
		case WIDE:
			dismissalTypes.add(DismisalType.RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName());
			dismissalTypes.add(DismisalType.STUMPED.getDismissalTypeName());
			break;
		default:
			break;
		}
		
		dismissalTypesSpinner = (Spinner) findViewById(R.id.dismissalTypeSpinner);
		dismissalTypesSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dismissalTypes));
		dismissalTypesSpinner.setOnItemSelectedListener(listener);
	}
	
	private OnItemSelectedListener listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			String selectedDismissalType = arg0.getSelectedItem().toString();
			DismisalType dismissalType = DismisalType.get(selectedDismissalType);

			//by default select the first Fielder in the list
			fieldersSpinner.setSelection(1);
			
			switch (dismissalType) {
			case RUNOUT:
				fieldersSpinner.setEnabled(true);
				onStrike.setEnabled(true);
				break;
			case NON_STRIKER_RUNOUT:
				fieldersSpinner.setEnabled(true);
				onStrike.setEnabled(true);
				break;
			case CAUGHT:
				fieldersSpinner.setEnabled(true);
				onStrike.setEnabled(true);
				break;
			case STUMPED:
				fieldersSpinner.setEnabled(true);
				onStrike.setEnabled(false);
				break;
			case BOWLED:
				fieldersSpinner.setSelection(listOfFielders.indexOf(""));
				fieldersSpinner.setEnabled(false);
				onStrike.setEnabled(false);
				break;
			case LBW:
				fieldersSpinner.setSelection(listOfFielders.indexOf(""));
				fieldersSpinner.setEnabled(false);
				
				onStrike.setEnabled(false);
				break;
			case HIT_WICKET:
				fieldersSpinner.setSelection(listOfFielders.indexOf(""));
				fieldersSpinner.setEnabled(false);
				
				onStrike.setEnabled(false);
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	@Override 
	//This needs to return any changes back to thes coring screen and not the whole Team object.
	public void onDone(View view) {
		Intent intent = new Intent();
		
		String fielderName = (String) fieldersSpinner.getSelectedItem();
		if(!fielderName.equals("")) {
			Player fielder = game.getBowlingTeam().getPlayerByName(fielderName);
			intent.putExtra(Keys.FIELDER, fielder);
		}

		String selectedItem = (String) dismissalTypesSpinner.getSelectedItem();
		intent.putExtra(Keys.DISMISSAL_TYPE, DismisalType.get(selectedItem));
		
		String newBatsman = newBatsmanText.getText().toString();
		
		if(!newBatsman.equals("")) {
			int teamSpecificPlayerId = 0;
			
			if (battingTeam.getTeamIndex() == 1) {
				teamSpecificPlayerId = 100;
			} else {
				teamSpecificPlayerId = 200;
			}
			battingTeam.addPlayer(new Player(newBatsman, teamSpecificPlayerId+10));
		} else {
			if( batsmanSpinner.getAdapter().getCount() == 0) {//all out
				intent.putExtra(Keys.ALL_OUT, true);
			} else {
				newBatsman = (String) batsmanSpinner.getSelectedItem();
				
				int onStrikeId = onStrike.getCheckedRadioButtonId();
				if(onStrikeId == R.id.onStrike) {
					battingTeam.getPlayerByName(newBatsman).setBattingStatus(BattingStatus.NewBatsman);
				} else {
					battingTeam.getPlayerByName(newBatsman).setBattingStatus(BattingStatus.NewBatsmanOffStrike);
				}
				intent.putExtra(Keys.ALL_OUT, false);
			}
		}
		
		intent.putExtra(Keys.BATTING_TEAM, battingTeam);
//		intent.putExtra(Keys.NEW_BATSMAN_ON_STRIKE, onStrikeId == R.id.onStrike ? true : false);
		
		setResult(Keys.SCORING_SCREEN, intent);
		finish();
	}
}