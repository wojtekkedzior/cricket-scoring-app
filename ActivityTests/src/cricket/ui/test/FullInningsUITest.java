package cricket.ui.test;

import game.Game;
import android.app.Activity;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import common.BallType;
import common.DismisalType;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.scoringScreen.ScoringScreenTestBase;


public class FullInningsUITest extends ScoringScreenTestBase {
	
	private TextView overs;
	private TextView totalRuns;
	private TextView runRate;
	
	public FullInningsUITest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@LargeTest
	public void testFirstOver() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		firstOver();
		secondOver();
		thirdOver();
		fourthOver();
		fithOver();
		sitxthOver();
		seventhOver();
		eighthOver();
		ninthOver();
		tenthOver();
		eleventhOver();
		twelthOver();
		thirteenthOver();
		fourteenthOver();
		fithteenthOver();
		sixteenthOver();
		seventeenthOver();
		eighteentOver();
		nineteenthOver();
		twentiethOver();
		
		checkInningsFinished();
		
		firstNZOver();
		secondNZOver();
		thirdNZOver();
		fourthNZOver();
		fithNZOver();
		sixthNZOver();
		seventhNZOver();
		eightNZOver();
		ninthtNZOver();
		tenthNZOver();
		eleventhNZOver();
		twelthNZOver();
		thirteenthNZOver();
		fourteenthNZOver();
		
		checkEndOfGame();
	}

	private void firstOver() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		selectNewBowler(testData.mills.getName()); 
		clickButton(ButtonType.Dot);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		checkCurrentBowler(testData.mills.getName(), "1", "0", "0", "1", "1.00", "0", "0", "0", "0");
		checkNonStriker(testData.masakadza.getName(), "1", "50.00", "2", "0", "0");
		checkStriker(testData.chibhabha.getName(), "0", "0.00", "4", "0", "0");
	}
	
	private void secondOver() {
		selectNewBowler(testData.bracewell.getName()); 
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(6);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.bracewell.getName(), "1", "0", "0", "9", "9.00", "0", "1", "0", "0");
		
		checkNonStriker(testData.masakadza.getName(), "9", "150.00", "6", "0", "1");
		checkStriker(testData.chibhabha.getName(), "1", "16.67", "6", "0", "0");
	}
	
	private void thirdOver() {
		selectNewBowler(testData.mills.getName()); 
		
		selectWicket(false);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		selectRuns(4);
		
		checkCurrentBowler(testData.mills.getName(), "2", "0", "1", "9", "4.50", "2", "0", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "0", "0.00", "0", "0", "0");
		checkStriker(testData.chibhabha.getName(), "9", "81.82", "11", "2", "0");
	}
	
	private void fourthOver() {
		selectNewBowler(testData.bracewell.getName());
		
		clickButton(ButtonType.Dot);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		checkCurrentBowler(testData.bracewell.getName(), "2", "0", "0", "13", "6.50", "1", "1", "0", "0");
		
		checkNonStriker(testData.chibhabha.getName(), "9", "81.82", "11", "2", "0");
		checkStriker(testData.brendonTaylor.getName(), "4", "66.67", "6", "1", "0");
	}
	
	private void fithOver() {
		selectNewBowler(testData.mills.getName()); 
		
		selectWicket(true);
		selectRuns(1);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.mills.getName(), "3", "0", "2", "12", "4.00", "2", "0", "0", "0");
		
		checkNonStriker(testData.mutizwa.getName(), "2", "50.00", "4", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "5", "71.43", "7", "1", "0");
	}
	
	private void sitxthOver() {
		selectNewBowler(testData.oram.getName());
		
		selectRuns(3);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(4);
		selectRuns(1);
		
		checkCurrentBowler(testData.oram.getName(), "1", "0", "0", "9", "9.00", "1", "0", "0", "0");
		
		checkNonStriker(testData.mutizwa.getName(), "10", "111.11", "9", "1", "0");
		checkStriker(testData.brendonTaylor.getName(), "6", "75.00", "8", "1", "0");
	}
	
	private void seventhOver() {
		selectNewBowler(testData.woodcock.getName());
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		
		checkCurrentBowler(testData.woodcock.getName(), "1", "0", "0", "5", "5.00", "0", "0", "0", "0");
		
		checkNonStriker(testData.mutizwa.getName(), "13", "108.33", "12", "1", "0");
		checkStriker(testData.brendonTaylor.getName(), "8", "72.73", "11", "1", "0");
	}
	
	private void eighthOver() {
		selectNewBowler(testData.oram.getName());
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(2);
		selectRuns(1);
		selectRuns(1);
		
		checkCurrentBowler(testData.oram.getName(), "2", "0", "0", "14", "7.00", "1", "0", "0", "0");
		
		checkNonStriker(testData.mutizwa.getName(), "15", "107.14", "14", "1", "0");
		checkStriker(testData.brendonTaylor.getName(), "11", "73.33", "15", "1", "0");
	}
	
	private void ninthOver() {
		selectNewBowler(testData.woodcock.getName());
		
		selectRuns(1);
		selectRuns(4);
		selectRuns(1);
		selectWicket(true);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.woodcock.getName(), "2", "0", "1", "12", "6.00", "1", "0", "0", "0");
		
		checkNonStriker(testData.waller.getName(), "1", "50.00", "2", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "16", "94.12", "17", "2", "0");
	}
	
	private void tenthOver() {
		selectNewBowler(testData.nathanMcCullum.getName());
		
		selectRuns(1);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.nathanMcCullum.getName(), "1", "0", "0", "6", "6.00", "1", "0", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "21", "95.45", "22", "3", "0");
		checkStriker(testData.waller.getName(), "2", "66.67", "3", "0", "0");
	}
	
	private void eleventhOver() {
		selectNewBowler(testData.woodcock.getName());
		
		selectRuns(3);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		selectRuns(1);
		
		checkCurrentBowler(testData.woodcock.getName(), "3", "0", "1", "19", "6.33", "1", "0", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "26", "104.00", "25", "3", "0");
		checkStriker(testData.waller.getName(), "4", "66.67", "6", "0", "0");
	}
	
	private void twelthOver() {
		selectNewBowler(testData.nathanMcCullum.getName());
		
		selectRuns(1);
		selectRuns(6);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectWicket(false);
		selectRuns(1);
		
		checkCurrentBowler(testData.nathanMcCullum.getName(), "2", "0", "1", "14", "7.00", "1", "1", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "28", "103.70", "27", "3", "0");
		checkStriker(testData.coventry.getName(), "0", "0.00", "0", "0", "0");
	}
	
	private void thirteenthOver() {
		selectNewBowler(testData.franklin.getName());
		
		selectRunsWithExtra(0, BallType.NO_BALL_EXTRA);
		selectRuns(1);
		selectRuns(1);
		selectRuns(4);
		selectRunsWithExtra(1, BallType.NO_BALL_RUN);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.franklin.getName(), "1", "0", "0", "10", "10.00", "1", "0", "0", "2");
		
		checkNonStriker(testData.coventry.getName(), "2", "50.00", "4", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "34", "109.68", "31", "4", "0");
	}
	
	private void fourteenthOver() {
		selectNewBowler(testData.mills.getName()); 
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.mills.getName(), "4", "0", "2", "15", "3.75", "2", "0", "0", "0");
		
		checkNonStriker(testData.coventry.getName(), "4", "50.00", "8", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "35", "106.06", "33", "4", "0");
	}
	
	private void fithteenthOver() {
		selectNewBowler(testData.woodcock.getName());
		
		selectRuns(1);
		selectRuns(1);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(4);
		
		checkCurrentBowler(testData.woodcock.getName(), "4", "0", "1", "30", "7.50", "3", "0", "0", "0");
		
		checkNonStriker(testData.coventry.getName(), "10", "83.33", "12", "1", "0");
		checkStriker(testData.brendonTaylor.getName(), "40", "114.29", "35", "5", "0");
	}
	
	private void sixteenthOver() {
		selectNewBowler(testData.nathanMcCullum.getName());
		
		selectWicket(false);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		
		checkCurrentBowler(testData.nathanMcCullum.getName(), "3", "0", "2", "17", "5.67", "1", "1", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "42", "107.69", "39", "5", "0");
		checkStriker(testData.chigumbura.getName(), "1", "100.00", "1", "0", "0");
	}
	
	private void seventeenthOver() {
		selectNewBowler(testData.bracewell.getName());
		
		selectRunsWithExtra(1, BallType.NO_BALL_RUN);
		selectRuns(2);
		selectRuns(2);
		selectRuns(2);
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		checkCurrentBowler(testData.bracewell.getName(), "3", "0", "0", "21", "7.00", "1", "1", "0", "1");
		
		checkNonStriker(testData.brendonTaylor.getName(), "43", "107.50", "40", "5", "0");
		checkStriker(testData.chigumbura.getName(), "7", "100.00", "7", "0", "0");
	}

	private void eighteentOver() {
		selectNewBowler(testData.oram.getName());
		
		selectRuns(2);
		selectRuns(1);
		selectRuns(2);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);

		checkCurrentBowler(testData.oram.getName(), "3", "0", "0", "21", "7.00", "1", "0", "0", "0");
		
		checkNonStriker(testData.brendonTaylor.getName(), "47", "109.30", "43", "5", "0");
		checkStriker(testData.chigumbura.getName(), "10", "100.00", "10", "0", "0");
	}
	
	private void nineteenthOver() {
		selectNewBowler(testData.bracewell.getName());
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		selectWicket(false);
		
		checkCurrentBowler(testData.bracewell.getName(), "4", "0", "1", "26", "6.50", "2", "1", "0", "1");
		
		checkNonStriker(testData.chakabva.getName(), "0", "0.00", "0", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "48", "109.09", "44", "5", "0");
	}
	
	private void twentiethOver() {
		selectNewBowler(testData.oram.getName());
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectWicket(true);

		selectRuns(1);
		selectRunsWithExtra(0, BallType.WIDE);
		selectRuns(2);
		
		selectWicket(false, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler(testData.oram.getName(), "4", "0", "1", "25", "6.25", "1", "0", "1", "0");
		
		checkNonStriker(testData.rayPrice.getName(), "0", "0.00", "0", "0", "0");
		checkStriker(testData.brendonTaylor.getName(), "50", "108.70", "46", "5", "0");
	}
	
	private void checkInningsFinished() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		overs = (TextView) activity.findViewById(R.id.totalOvers);
		totalRuns = (TextView) activity.findViewById(R.id.totalRuns);
		runRate = (TextView) activity.findViewById(R.id.runRate);
		
		assertEquals("Overs: 20", overs.getText().toString());
		assertEquals("123 for 8", totalRuns.getText().toString());
		assertEquals("R/R: 6.15", runRate.getText().toString());
		
		final Button newInningsButton = (Button) activity.findViewById(R.id.newInningsButton);

		activity.runOnUiThread(new Runnable() {
			public void run() {
				newInningsButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();

		Activity newInningsActivity = newInningsMonitor.waitForActivity();

		final Spinner firstBatsmanSpinner = (Spinner) newInningsActivity.findViewById(R.id.firstBatsmanSpinner);
		final Spinner secondBatsmanSpinner = (Spinner) newInningsActivity.findViewById(R.id.secondBatsmanSpinner);

		final int firstBatsmanCount = firstBatsmanSpinner.getAdapter().getCount();
		assertEquals(11, firstBatsmanCount);
		final int secondBatsmanCount = secondBatsmanSpinner.getAdapter().getCount();
		assertEquals(11, secondBatsmanCount);

		newInningsActivity.runOnUiThread(new Runnable() {
			public void run() {
				for (int i = 0; i < firstBatsmanCount; i++) {
					String item = (String) firstBatsmanSpinner.getAdapter().getItem(i);
					if (item.equals(testData.guptill.getName())) {
						firstBatsmanSpinner.setSelection(i);
						assertEquals(testData.guptill.getName(), (String) firstBatsmanSpinner.getSelectedItem());
					}
				}

				for (int i = 0; i < secondBatsmanCount; i++) {
					String item = (String) secondBatsmanSpinner.getAdapter().getItem(i);
					if (item.equals(testData.brendonMcCullum.getName())) {
						secondBatsmanSpinner.setSelection(i);
						assertEquals(testData.brendonMcCullum.getName(), (String) secondBatsmanSpinner.getSelectedItem());
					}
				}
			}
		});

		mInstrumentation.waitForIdleSync();

		clickButton(ButtonType.Done, newInningsActivity);

		Game modifiedGame = (Game) getValueFromField("game", activity);
		assertEquals(TeamBowlingStatus.BowledOut, modifiedGame.getTeam(1).getTeamBowlingStatus());
		assertEquals(TeamBattingStatus.Batting, modifiedGame.getTeam(1).getTeamBattingStatus());

		assertEquals(TeamBowlingStatus.Bowling, modifiedGame.getTeam(2).getTeamBowlingStatus());
		assertEquals(TeamBattingStatus.Batting_Overs_Finished, modifiedGame.getTeam(2).getTeamBattingStatus());

		checkStriker(testData.guptill.getName(), "0", "0.00", "0", "0", "0");
		checkNonStriker(testData.brendonMcCullum.getName(), "0", "0.00", "0", "0", "0");
		
		assertEquals("Overs: 0.0", overs.getText().toString()); //TODO should it be 0, or 0.0?
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
	}
	
	private void firstNZOver() {
		selectNewBowler(testData.kyleJarvis.getName());
		
		selectRunsWithExtra(0, BallType.NO_BALL_RUN);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRunsWithExtra(0, BallType.WIDE);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(2);
		selectRunsWithExtra(0, BallType.WIDE);
		clickButton(ButtonType.Dot);
		
		checkCurrentBowler(testData.kyleJarvis.getName(), "1", "0", "0", "5", "5.00", "0", "0", "2", "1");
		
		checkNonStriker(testData.brendonMcCullum.getName(), "0", "0.00", "0", "0", "0");
		checkStriker(testData.guptill.getName(), "2", "28.57", "7", "0", "0");
	}

	private void secondNZOver() {
		selectNewBowler(testData.rayPrice.getName());
		
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(2);

		checkCurrentBowler(testData.rayPrice.getName(), "1", "0", "0", "5", "5.00", "0", "0", "0", "0");
		
		checkNonStriker(testData.brendonMcCullum.getName(), "2", "50.00", "4", "0", "0");
		checkStriker(testData.guptill.getName(), "5", "55.56", "9", "0", "0");
	}
	
	private void thirdNZOver() {
		selectNewBowler(testData.kyleJarvis.getName());
		
		selectRuns(6);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(4);
		clickButton(ButtonType.Dot);

		checkCurrentBowler("Jarvis", "2", "0", "0", "17", "8.50", "1", "1", "2", "1");
		
		checkNonStriker(testData.guptill.getName(), "6", "54.55", "11", "0", "0");
		checkStriker(testData.brendonMcCullum.getName(), "13", "162.50", "8", "1", "1");
	}
	
	private void fourthNZOver() {
		selectNewBowler(testData.rayPrice.getName());
		
		selectRuns(1);
		selectRunsWithExtra(1, BallType.LEG_BYE);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(4);
		
		checkCurrentBowler(testData.rayPrice.getName(), "2", "0", "0", "11", "5.50", "1", "0", "0", "0");
		
		checkNonStriker(testData.guptill.getName(), "8", "53.33", "15", "0", "0");
		checkStriker(testData.brendonMcCullum.getName(), "17", "170.00", "10", "2", "1");
	}
	
	private void fithNZOver() {
		selectNewBowler(testData.chigumbura.getName());
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRunsWithExtra(0, BallType.WIDE);
		selectRuns(1);
		selectRuns(2);
		
		checkCurrentBowler(testData.chigumbura.getName(), "1", "0", "0", "6", "6.00", "0", "0", "1", "0");
		
		checkNonStriker(testData.guptill.getName(), "10", "55.56", "18", "0", "0");
		checkStriker(testData.brendonMcCullum.getName(), "20", "153.85", "13", "2", "1");
	}
	
	private void sixthNZOver() {
		selectNewBowler(testData.rayPrice.getName());
		
		selectRuns(6);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);

		checkCurrentBowler(testData.rayPrice.getName(), "3", "0", "0", "19", "6.33", "1", "1", "0", "0");
		
		checkStriker(testData.guptill.getName(), "17", "85.00", "20", "0", "1");
		checkNonStriker(testData.brendonMcCullum.getName(), "21", "123.53", "17", "2", "1");
	}
	
	private void seventhNZOver() {
		selectNewBowler(testData.chigumbura.getName());
		
		selectRuns(2);
		selectRuns(1);
		selectRuns(1);

		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(1);

		checkCurrentBowler(testData.chigumbura.getName(), "2", "0", "0", "12", "6.00", "0", "0", "1", "0");
		
		checkNonStriker(testData.guptill.getName(), "19", "86.36", "22", "0", "1");
		checkStriker(testData.brendonMcCullum.getName(), "25", "119.05", "21", "2", "1");
	}
	
	private void eightNZOver() {
		selectNewBowler(testData.utseya.getName());
		
		selectRuns(1);
		selectRuns(1);
		clickButton(ButtonType.Dot);

		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(1);

		checkCurrentBowler(testData.utseya.getName(), "1", "0", "0", "4", "4.00", "0", "0", "0", "0");
		
		checkStriker(testData.guptill.getName(), "21", "84.00", "25", "0", "1");
		checkNonStriker(testData.brendonMcCullum.getName(), "27", "112.50", "24", "2", "1");
	}
	
	private void ninthtNZOver() {
		selectNewBowler(testData.chigumbura.getName());
		
		clickButton(ButtonType.Dot);
		selectRuns(6);
		selectRuns(4);
		selectRuns(1);
		selectRuns(1);
		selectRuns(4);

		checkCurrentBowler(testData.chigumbura.getName(), "3", "0", "0", "28", "9.33", "2", "1", "1", "0");
		
		checkNonStriker(testData.guptill.getName(), "22", "84.62", "26", "0", "1");
		checkStriker(testData.brendonMcCullum.getName(), "42", "144.83", "29", "4", "2");
	}
	
	private void tenthNZOver() {
		selectNewBowler(testData.utseya.getName());
		
		selectRuns(4);
		clickButton(ButtonType.Dot);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(1);

		checkCurrentBowler(testData.utseya.getName(), "2", "0", "0", "10", "5.00", "1", "0", "0", "0");
		
		checkStriker(testData.guptill.getName(), "27", "93.10", "29", "1", "1");
		checkNonStriker(testData.brendonMcCullum.getName(), "43", "134.38", "32", "4", "2");
	}
	
	private void eleventhNZOver() {
		selectNewBowler(testData.kyleJarvis.getName());
		
		clickButton(ButtonType.Dot);
		selectRunsWithExtra(0, BallType.WIDE);
		selectRuns(2);
		selectRuns(4);
		clickButton(ButtonType.Dot);
		selectRuns(2);
		selectRuns(6);
		
		checkCurrentBowler(testData.kyleJarvis.getName(), "3", "0", "0", "32", "10.67", "2", "2", "3", "1");
		
		checkNonStriker(testData.guptill.getName(), "27", "93.10", "29", "1", "1");
		checkStriker(testData.brendonMcCullum.getName(), "57", "150.00", "38", "5", "3");
	}
	
	private void twelthNZOver() {
		selectNewBowler(testData.chibhabha.getName());
		
		selectRuns(4);
		selectRuns(1);
		selectRuns(6);
		selectRuns(6);
		selectRuns(2);
		selectRuns(1);
		
		checkCurrentBowler(testData.chibhabha.getName(), "1", "0", "0", "20", "20.00", "1", "2", "0", "0");
		
		checkStriker(testData.guptill.getName(), "32", "103.23", "31", "2", "1");
		checkNonStriker(testData.brendonMcCullum.getName(), "72", "171.43", "42", "5", "5");
	}
	
	private void thirteenthNZOver() {
		selectNewBowler(testData.masakadza.getName());
		
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(6);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(2);
		
		checkCurrentBowler(testData.masakadza.getName(), "1", "0", "0", "10", "10.00", "0", "1", "0", "0");
		
		checkNonStriker(testData.guptill.getName(), "39", "114.71", "34", "2", "2");
		checkStriker(testData.brendonMcCullum.getName(), "75", "166.67", "45", "5", "5");
	}
	
	private void fourteenthNZOver() {
		selectNewBowler(testData.rayPrice.getName());
		
		clickButton(ButtonType.Dot);
		selectRuns(1);
		selectRuns(6);
		
		checkCurrentBowler(testData.rayPrice.getName(), "3.3", "0", "0", "26", "7.88", "1", "2", "0", "0");
		
		checkNonStriker(testData.guptill.getName(), "40", "111.11", "36", "2", "2");
		checkStriker(testData.brendonMcCullum.getName(), "81", "176.09", "46", "5", "6");
	}
	
	private void checkEndOfGame() {
		overs = (TextView) activity.findViewById(R.id.totalOvers);
		totalRuns = (TextView) activity.findViewById(R.id.totalRuns);
		runRate = (TextView) activity.findViewById(R.id.runRate);
		
		assertEquals("Overs: 13.3", overs.getText().toString());
		assertEquals("127 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 9.41", runRate.getText().toString()); //on cric info this is 9.40, but its not rounded off from 9.407
		
		Button newInningsButton = (Button) activity.findViewById(R.id.newInningsButton);
		assertFalse(newInningsButton.isEnabled());
		
		final Button endGameButton = (Button) activity.findViewById(R.id.endGameButton);
		assertEquals(Button.VISIBLE, endGameButton.getVisibility());
		assertTrue(endGameButton.isEnabled());
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				endGameButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync();

		Activity gameOverActivity = gameOverMonitor.waitForActivity();

		TextView winningTeamLabel = (TextView) gameOverActivity.findViewById(R.id.winningTeamLabel);
		assertEquals("New Zealand win by 10 wickets", winningTeamLabel.getText());
		
		clickButton(ButtonType.Done, gameOverActivity);
	}
}