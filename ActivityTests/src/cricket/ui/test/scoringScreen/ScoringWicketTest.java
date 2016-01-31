package cricket.ui.test.scoringScreen;

import android.widget.TextView;

import common.BallType;
import common.DismisalType;

import cricket.ui.R;
import cricket.ui.ScoringScreen;

public class ScoringWicketTest extends ScoringScreenTestBase {

	private TextView overSummary;
	
	private TextView strikerName;
	private TextView nonStrikerName;
	
	public ScoringWicketTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		overSummary = (TextView) activity.findViewById(R.id.overSummary);
		selectNewBowler("Mills");
		
		strikerName = (TextView) activity.findViewById(R.id.batsman1name);
		nonStrikerName = (TextView) activity.findViewById(R.id.batsman2name);
		
		//At the start we have: 
		// 1. Masakadza
		// 2. chibhabha
	}
	
	public void testBowled() {
		selectWicket(true, DismisalType.BOWLED);
		checkCurrentBowler("Mills", "0.1", "0", "1", "0", "0.00", "0", "0", "0", "0");
		
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
	}
	
	public void testLBW() {
		selectWicket(true, DismisalType.LBW);
		checkCurrentBowler("Mills", "0.1", "0", "1", "0", "0.00", "0", "0", "0", "0");
		
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
	}
	
	public void testHitWicket() {
		selectWicket(true, DismisalType.HIT_WICKET);
		checkCurrentBowler("Mills", "0.1", "0", "1", "0", "0.00", "0", "0", "0", "0");
		
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
	}
	
	public void testCaughtNewBatsmanOnStrike() {
		selectWicket(true, DismisalType.CAUGHT);
		checkCurrentBowler("Mills", "0.1", "0", "1", "0", "0.00", "0", "0", "0", "0");
		
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
	}
	
	public void testCaughtNewBatsmanNotOnStrike() {
		selectWicket(false, DismisalType.CAUGHT);
		checkCurrentBowler("Mills", "0.1", "0", "1", "0", "0.00", "0", "0", "0", "0");
		
		assertEquals("Chibhabha",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
	}
	
	public void testRunOutWithWideNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.WIDE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "1", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());

		//With Runs
		selectWicketWithExtra(true, BallType.WIDE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "2", "0");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
		
		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithWideNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.WIDE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "1", "0");
		assertEquals("Chibhabha",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());

		//With Runs
		selectWicketWithExtra(false, BallType.WIDE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "2", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());
		
		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithNoBallNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "0", "2");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithNoBallNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		assertEquals("Chibhabha",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "0", "2");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testNonStrikerRunOutWithNoBallBatsmanGetsRuns() {
		selectWicketWithExtra(true, BallType.NO_BALL_RUN, 0, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		checkStriker("Brendon Taylor", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "0", "0.00", "1", "0", "0");
		
		//With some runs
		selectWicketWithExtra(true, BallType.NO_BALL_RUN, 2, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "0", "2");
		checkStriker("Mutizwa", "0", "0.00", "0", "0", "0");
		checkNonStriker("Brendon Taylor", "2", "200.00", "1", "0", "0");
		
		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithByeNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.BYE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(true, BallType.BYE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.2", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
		
	public void testRunOutWithLegNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.LEG_BYE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(true, BallType.LEG_BYE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.2", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Chibhabha",  nonStrikerName.getText().toString());
		
		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithByeNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.BYE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Chibhabha",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(false, BallType.BYE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.2", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testRunOutWithLegByeNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.LEG_BYE, 0, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Chibhabha",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
		
		//With Runs
		selectWicketWithExtra(false, BallType.LEG_BYE, 2, DismisalType.RUNOUT);
		checkCurrentBowler("Mills", "0.2", "0", "0", "0", "0.00", "0", "0", "0", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testNonStrikerRunOutWithWideNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.WIDE, 0, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "1", "0");
		assertEquals("Masakadza",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
		
		//With some runs
		selectWicketWithExtra(false, BallType.WIDE, 2, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "2", "0");
		assertEquals("Masakadza",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testNonStrikerRunOutWithWideNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.WIDE, 0, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "1", "0");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Masakadza",  nonStrikerName.getText().toString());
		
		//With some runs
		selectWicketWithExtra(true, BallType.WIDE, 2, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "2", "0");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testNonStrikerRunOutWithNoBallNewBatsmanNotOnStrike() {
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 0, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		assertEquals("Masakadza",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());
		
		//With some runs
		selectWicketWithExtra(false, BallType.NO_BALL_EXTRA, 2, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "0", "2");
		assertEquals("Masakadza",  strikerName.getText().toString());
		assertEquals("Mutizwa",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
	
	public void testNonStrikerRunOutWithNoBallNewBatsmanOnStrike() {
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 0, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		assertEquals("Brendon Taylor",  strikerName.getText().toString());
		assertEquals("Masakadza",  nonStrikerName.getText().toString());
		
		//With some runs
		selectWicketWithExtra(true, BallType.NO_BALL_EXTRA, 2, DismisalType.NON_STRIKER_RUNOUT);
		checkCurrentBowler("Mills", "0.0", "0", "0", "4", "0.00", "0", "0", "0", "2");
		assertEquals("Mutizwa",  strikerName.getText().toString());
		assertEquals("Brendon Taylor",  nonStrikerName.getText().toString());

		assertEquals("W  W  ", overSummary.getText().toString());
	}
}