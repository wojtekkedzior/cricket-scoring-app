package cricket.ui.test.scoringScreen;

import android.widget.TextView;

import common.BallType;
import common.DismisalType;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class UndoTest extends ScoringScreenTestBase {

	private TextView overSummary;
	
	public UndoTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		overSummary = (TextView) activity.findViewById(R.id.overSummary);
		selectNewBowler("Mills");
		
		//At the start we have: 
		// 1. Masakadza
		// 2. chibhabha
	}
	
	public void testUndoWithRuns() {
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Undo);
		verifyAllFieldAreClear();
		
		selectRuns(1);
		clickButton(ButtonType.Undo);
		verifyAllFieldAreClear();
		
		selectRuns(2);
		clickButton(ButtonType.Undo);
		verifyAllFieldAreClear();
		
		selectRuns(4);
		clickButton(ButtonType.Undo);
		verifyAllFieldAreClear();
		
		selectRuns(6);
		clickButton(ButtonType.Undo);
		verifyAllFieldAreClear();
	}
	
	public void testUndoWithNoBalls() {
		selectRunsWithExtra(0, BallType.NO_BALL_EXTRA);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(1, BallType.NO_BALL_EXTRA);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(0, BallType.NO_BALL_RUN);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(1, BallType.NO_BALL_RUN);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}

	public void testUndoWithWides() {
		selectRunsWithExtra(0, BallType.WIDE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(1, BallType.WIDE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWithLegByeAndBye() {
		selectRunsWithExtra(0, BallType.LEG_BYE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(1, BallType.LEG_BYE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(0, BallType.BYE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectRunsWithExtra(1, BallType.BYE);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWithWicket() {
		selectWicket(true);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicket(false);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWicketAndBye() {
		selectWicketWithExtra(true, BallType.BYE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.BYE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(true, BallType.BYE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.BYE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWicketAndLegBye() {
		selectWicketWithExtra(true, BallType.LEG_BYE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.LEG_BYE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(true, BallType.LEG_BYE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.LEG_BYE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWicketAndWide() {
		selectWicketWithExtra(true, BallType.WIDE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.WIDE, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(true, BallType.WIDE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.WIDE, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWicketAndNoBallExtra() {
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void testUndoWicketAndNoBallRun() {
		selectWicketWithExtra(true, BallType.NO_BALL_RUN, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.NO_BALL_RUN, 2, DismisalType.NON_STRIKER_RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(true, BallType.NO_BALL_RUN, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.NO_BALL_RUN, 2, DismisalType.RUNOUT);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}
	
	public void selectWicketWithRun() {
		selectWicketWithExtra(true, BallType.SCORING, 2, DismisalType.BOWLED);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
		
		selectWicketWithExtra(false, BallType.SCORING, 2, DismisalType.BOWLED);
		clickButton(ButtonType.Undo); 
		verifyAllFieldAreClear();
	}

	private void verifyAllFieldAreClear() {
		checkCurrentBowler("Mills", "0.0", "0", "0", "0", "NaN", "0", "0", "0", "0");
		checkStriker("Masakadza", "0", "0.00", "0", "0", "0");
		checkNonStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		assertEquals("", overSummary.getText().toString());
	}
}
