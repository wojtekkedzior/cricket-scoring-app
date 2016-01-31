package cricket.ui.test.scoringScreen;

import android.widget.TextView;

import common.BallType;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;


public class OverSummaryTest extends ScoringScreenTestBase {
	
	TextView overSummary;

	public OverSummaryTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		overSummary = (TextView) activity.findViewById(R.id.overSummary);
		selectNewBowler("Mills"); 
	}
	
	public void testOverSummaryWithRuns() {
		selectRuns(1);
		selectRuns(2);
		selectRuns(3);
		selectRuns(4);
		selectRuns(5);
		selectRuns(6);
		
		assertEquals("1  2  3  4  5  6  ", overSummary.getText().toString());
	}

	public void testOverSummaryWithDots() {
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		assertEquals(".  .  .  .  .  .  ", overSummary.getText().toString());
	}
	
	public void testOverSummaryWithRunsAndDots() {
		clickButton(ButtonType.Dot);
		selectRuns(1);
		clickButton(ButtonType.Dot);
		selectRuns(6);
		clickButton(ButtonType.Dot);
		selectRuns(2);
		
		assertEquals(".  1  .  6  .  2  ", overSummary.getText().toString());
	}
	
	public void testOverSummaryWithWides() {
		selectRunsWithExtra(0, BallType.WIDE);
		selectRunsWithExtra(1, BallType.WIDE);
		
		assertEquals("WD  WD+1  ", overSummary.getText().toString());
	}
	
	public void testOverSummaryWithNoBalls() {
		selectRunsWithExtra(0, BallType.NO_BALL_EXTRA);
		selectRunsWithExtra(2, BallType.NO_BALL_EXTRA);
		selectRunsWithExtra(0, BallType.NO_BALL_RUN);
		selectRunsWithExtra(1, BallType.NO_BALL_RUN);
		
		assertEquals("NB  NB+2  NB  NB+1  ", overSummary.getText().toString());
	}
	
	public void testOverSummaryWithByeAndLegByes() {
		selectRunsWithExtra(0, BallType.BYE);
		selectRunsWithExtra(1, BallType.BYE);
		selectRunsWithExtra(4, BallType.BYE);
		selectRunsWithExtra(0, BallType.LEG_BYE);
		selectRunsWithExtra(1, BallType.LEG_BYE);
		selectRunsWithExtra(4, BallType.LEG_BYE);
		
		assertEquals("1B  1B  4B  1LB  1LB  4LB  ", overSummary.getText().toString());
	}
}