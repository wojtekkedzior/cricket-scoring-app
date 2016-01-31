package cricket.ui.popups;

import game.Team;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import common.BattingStatus;
import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class NewInnings extends GenericActivity {
	
	private Team battingTeam;
	private Spinner firstBatsmanSpinner;
	private Spinner secondBatsmanSpinner;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.popup_new_innings);
		setFinishOnTouchOutside(false);
		
		Bundle extras = getIntent().getExtras();
		
		battingTeam = (Team) extras.getSerializable(Keys.BATTING_TEAM);
		
		ArrayList<String> batsmanNames = battingTeam.getAvailableBatsmanNames();
		
		firstBatsmanSpinner = (Spinner) findViewById(R.id.firstBatsmanSpinner);
		secondBatsmanSpinner = (Spinner) findViewById(R.id.secondBatsmanSpinner);
		
		firstBatsmanSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, batsmanNames));
		firstBatsmanSpinner.setSelection(0);
		
		secondBatsmanSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, batsmanNames));
		secondBatsmanSpinner.setSelection(1);
		
		firstBatsmanSpinner.setOnItemSelectedListener(onItemSelectedListener);
		secondBatsmanSpinner.setOnItemSelectedListener(onItemSelectedListener);
	}
	
	private final OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			if (arg0.getId() == (R.id.firstBatsmanSpinner)) {
				battingTeam.addOpeningBatsman(arg0.getSelectedItem().toString(), BattingStatus.Striker);
			} else { // secondBatsmanSpinner
				battingTeam.addOpeningBatsman(arg0.getSelectedItem().toString(), BattingStatus.NonStriker);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	
	@Override 
	public void onDone(View view) {
		Intent intent = new Intent();
		intent.putExtra(Keys.BATTING_TEAM, battingTeam);
		setResult(Keys.NEW_INNINGS_SCREEN, intent);
		finish();
	}
}
