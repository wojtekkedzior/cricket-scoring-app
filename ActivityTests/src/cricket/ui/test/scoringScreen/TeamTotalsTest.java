package cricket.ui.test.scoringScreen;

import android.widget.TextView;
import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class TeamTotalsTest extends ScoringScreenTestBase {

	private TextView overs;
	private TextView totalRuns;
	private TextView runRate;
	
	public TeamTotalsTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		selectNewBowler("Mills");
		
		overs = (TextView) activity.findViewById(R.id.totalOvers);
		totalRuns = (TextView) activity.findViewById(R.id.totalRuns);
		runRate = (TextView) activity.findViewById(R.id.runRate);
	}
	
	public void testTeamTotalsDuringOver() {
		assertEquals("Overs: 0.0", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		clickButton(ButtonType.Dot);
		assertEquals("Overs: 0.1", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		selectRuns(1);
		assertEquals("Overs: 0.2", overs.getText().toString());
		assertEquals("1 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 3.03", runRate.getText().toString());
		
		selectRuns(3);
		assertEquals("Overs: 0.3", overs.getText().toString());
		assertEquals("4 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 8.00", runRate.getText().toString());
		
		selectRuns(4);
		assertEquals("Overs: 0.4", overs.getText().toString());
		assertEquals("8 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 12.12", runRate.getText().toString());
		
		selectWicket(true);
		assertEquals("Overs: 0.5", overs.getText().toString());
		assertEquals("8 for 1", totalRuns.getText().toString());
		assertEquals("R/R: 9.64", runRate.getText().toString());
		
		selectRuns(6);
		assertEquals("Overs: 1", overs.getText().toString());
		assertEquals("14 for 1", totalRuns.getText().toString());
		assertEquals("R/R: 14.00", runRate.getText().toString());
		
		
		//New over by new bowler
		selectNewBowler("Bracewell");
		clickButton(ButtonType.Dot);
		assertEquals("Overs: 1.1", overs.getText().toString());
		assertEquals("14 for 1", totalRuns.getText().toString());
		assertEquals("R/R: 12.07", runRate.getText().toString());
	}
	
	
	public void testTotalsWithUndo() {
		selectRuns(4);
		
		assertEquals("Overs: 0.1", overs.getText().toString());
		assertEquals("4 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 25.00", runRate.getText().toString());
		
		clickButton(ButtonType.Undo);
		
		assertEquals("Overs: 0.0", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		selectWicket(true);
		
		assertEquals("Overs: 0.1", overs.getText().toString());
		assertEquals("0 for 1", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		clickButton(ButtonType.Undo);
		
		assertEquals("Overs: 0.0", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
	}
	
	public void testTotalsWithUndoLastBallOfOver() {
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		selectRuns(4);
		
		assertEquals("Overs: 1", overs.getText().toString());
		assertEquals("4 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 4.00", runRate.getText().toString());
		
		clickButton(ButtonType.Undo);
		
		assertEquals("Overs: 0.5", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		selectWicket(true);
		
		assertEquals("Overs: 1", overs.getText().toString());
		assertEquals("0 for 1", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
		
		clickButton(ButtonType.Undo);
		
		assertEquals("Overs: 0.5", overs.getText().toString());
		assertEquals("0 for 0", totalRuns.getText().toString());
		assertEquals("R/R: 0.00", runRate.getText().toString());
	}
}
