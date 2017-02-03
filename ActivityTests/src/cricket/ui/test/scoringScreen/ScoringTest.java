package cricket.ui.test.scoringScreen;

import android.widget.TextView;
import common.BallType;

import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class ScoringTest extends ScoringScreenTestBase {
	
	private TextView overSummary;
	
	public ScoringTest() {
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
	
	public void testRuns() {
		selectRuns(1);
		checkCurrentBowler("Mills", "0.1", "0", "0", "1", "10.00", "0", "0", "0", "0");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "1", "100.00", "1", "0", "0");
		assertEquals("1  ", overSummary.getText().toString());
		
		selectRuns(2);
		checkCurrentBowler("Mills", "0.2", "0", "0", "3", "15.00", "0", "0", "0", "0");
		checkStriker("Chibhabha", "2", "200.00", "1", "0", "0");
		checkNonStriker("Masakadza", "1", "100.00", "1", "0", "0");
		assertEquals("1  2  ", overSummary.getText().toString());
		
		selectRuns(3);
		checkCurrentBowler("Mills", "0.3", "0", "0", "6", "20.00", "0", "0", "0", "0");
		checkStriker("Masakadza", "1", "100.00", "1", "0", "0");
		checkNonStriker("Chibhabha", "5", "250.00", "2", "0", "0");
		assertEquals("1  2  3  ", overSummary.getText().toString());
		
		selectRuns(4);
		checkCurrentBowler("Mills", "0.4", "0", "0", "10", "25.00", "1", "0", "0", "0");
		checkStriker("Masakadza", "5", "250.00", "2", "1", "0");
		checkNonStriker("Chibhabha", "5", "250.00", "2", "0", "0");
		assertEquals("1  2  3  4  ", overSummary.getText().toString());
		
		selectRuns(5);
		checkCurrentBowler("Mills", "0.5", "0", "0", "15", "30.00", "1", "0", "0", "0");
		checkStriker("Chibhabha", "5", "250.00", "2", "0", "0");
		checkNonStriker("Masakadza", "10", "333.33", "3", "1", "0");
		assertEquals("1  2  3  4  5  ", overSummary.getText().toString());
		
		selectRuns(6);
		checkCurrentBowler("Mills", "1", "0", "0", "21", "21.00", "1", "1", "0", "0");
		checkStriker("Chibhabha", "11", "366.67", "3", "0", "1");
		checkNonStriker("Masakadza", "10", "333.33", "3", "1", "0");
		assertEquals("1  2  3  4  5  6  ", overSummary.getText().toString());
	}
	
	public void testDotBall() {
		clickButton(ButtonType.Dot);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		checkStriker("Masakadza", "0", "0.00", "1", "0", "0");
		checkNonStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		assertEquals(".  ", overSummary.getText().toString());
	}
	
	public void testNoBallRun() {
		selectRunsWithExtra(0, BallType.NO_BALL_RUN);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		checkStriker("Masakadza", "0", "0.00", "1", "0", "0");
		checkNonStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		assertEquals("NB  ", overSummary.getText().toString());
		
		selectRunsWithExtra(1, BallType.NO_BALL_RUN);
		checkCurrentBowler("Mills", "0.0", "0", "0", "3", "0.00", "0", "0", "0", "2");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "1", "50.00", "2", "0", "0");
		assertEquals("NB  NB+1  ", overSummary.getText().toString());
	}
	
	public void testNoBallExtra() {
		selectRunsWithExtra(0, BallType.NO_BALL_EXTRA);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "0", "1");
		checkStriker("Masakadza", "0", "0.00", "1", "0", "0");
		checkNonStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		assertEquals("NB  ", overSummary.getText().toString());
		
		selectRunsWithExtra(1, BallType.NO_BALL_EXTRA);
		checkCurrentBowler("Mills", "0.0", "0", "0", "3", "0.00", "0", "0", "0", "2");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "0", "0.00", "2", "0", "0");
		assertEquals("NB  NB+1  ", overSummary.getText().toString());
	}
	
	public void testWidesWithRuns() {
		selectRunsWithExtra(0, BallType.WIDE);
		checkCurrentBowler("Mills", "0.0", "0", "0", "1", "0.00", "0", "0", "1", "0");
		checkStriker("Masakadza", "0", "0.00", "0", "0", "0");
		checkNonStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		assertEquals("WD  ", overSummary.getText().toString());
		
		selectRunsWithExtra(1, BallType.WIDE);
		checkCurrentBowler("Mills", "0.0", "0", "0", "3", "0.00", "0", "0", "2", "0");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "0", "0.00", "0", "0", "0");
		assertEquals("WD  WD+1  ", overSummary.getText().toString());
	}
	
	public void testWidesWithLegBye() {
		selectRunsWithExtra(1, BallType.LEG_BYE);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "0", "0.00", "1", "0", "0");
		assertEquals("1LB  ", overSummary.getText().toString());
	}
	
	public void testWidesWithBye() {
		selectRunsWithExtra(1, BallType.BYE);
		checkCurrentBowler("Mills", "0.1", "0", "0", "0", "0.00", "0", "0", "0", "0");
		checkStriker("Chibhabha", "0", "0.00", "0", "0", "0");
		checkNonStriker("Masakadza", "0", "0.00", "1", "0", "0");
		assertEquals("1B  ", overSummary.getText().toString());
	}
}
