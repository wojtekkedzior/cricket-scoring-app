package cricket.ui.popups;

import game.Game;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import common.BallType;
import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class Scoring extends GenericActivity{
	
	private Game game;
	
	private RadioGroup extrasGroup;
	private RadioGroup runGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.popup_scoring);
		setFinishOnTouchOutside(false);
		
		game = (Game)  getIntent().getExtras().get(Keys.GAME);
		
		TextView bowlerNameView = (TextView) findViewById(R.id.bowlerName);
		TextView batsmanNameView = (TextView) findViewById(R.id.strikingBatsmanName);
		
		batsmanNameView.setText(game.getBattingTeam().getStriker().getName());
		bowlerNameView.setText(game.getBowlingTeam().getCurrentBowler().getName());
		
		extrasGroup = (RadioGroup) findViewById(R.id.radiogroup1);
		runGroup = (RadioGroup) findViewById(R.id.radiogroup2);
	}
	
//	If the batsman hits the ball he may take runs as normal. 
//	These are scored as runs by the batsman, as normal. 
//	Runs may also be scored without the batsman hitting the ball, but these are recorded as no ball extras rather than byes or leg byes.
	
	@Override
	public void onDone(View view) {
		createIntent(new Intent(), Keys.SCORING_SCREEN, false);
	}

	private BallType getBallType() {
		int checkedExtra = extrasGroup.getCheckedRadioButtonId();
		
		BallType ballType;
		
		switch (checkedExtra) {
			case R.id.wideRadioButton : ballType = BallType.WIDE; break;
			case R.id.noBallExtraRadioButton : ballType = BallType.NO_BALL_EXTRA; break;
			case R.id.noBallRunRadioButton : ballType = BallType.NO_BALL_RUN; break;
			case R.id.byeRadioButton : ballType = BallType.BYE; break;
			case R.id.legByeRadioButton : ballType = BallType.LEG_BYE; break;
			default: ballType = BallType.SCORING; 
		}
		return ballType;
	}

	private int getRuns() {
		int checkedRun = runGroup.getCheckedRadioButtonId();
		
		if(checkedRun == -1) {
			return 0;
		}
		
		int runs;
		
		switch (checkedRun) {
		  case R.id.oneRun : runs = 1; break;
		  case R.id.twoRuns : runs = 2;break;
		  case R.id.threeRuns : runs = 3;break;
		  case R.id.fourRuns : runs = 4;break;
		  case R.id.fiveRuns : runs = 5;break;
		  case R.id.sixRuns : runs = 6; break;
		  default: runs=0;
		}
		return runs;
	}
	
	public void onClear(View view) {
		((RadioGroup) findViewById(R.id.radiogroup2)).clearCheck();
		((RadioGroup) findViewById(R.id.radiogroup1)).clearCheck();
	}
	
	public void onWicket(View view) {
		Intent intent = new Intent(this, Wicket.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable(Keys.GAME, game);
		bundle.putSerializable(Keys.BALL_TYPE, getBallType());
		
		intent.putExtras(bundle);
		startActivityForResult(intent, Keys.WICKET_SCREEN);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data != null) { //user canceled
			if(requestCode == Keys.WICKET_SCREEN) {
				createIntent(data, Keys.WICKET_SCREEN, true);
			}
		}
	}

	private void createIntent(Intent data, int resultCode, boolean wicket) {
		int runs = getRuns();
		BallType ballType = getBallType();
		
		//to make sure that a Bye or LegBye get a run. 
		if((ballType.equals(BallType.BYE) || ballType.equals(BallType.LEG_BYE)) && runs == 0) {
			runs = 1;
		}
		
		data.putExtra(Keys.RUNS, runs);
		data.putExtra(Keys.BALL_TYPE, ballType);
		data.putExtra(Keys.IS_WICKET, wicket);
		
		setResult(resultCode, data);
		finish();
	}
}