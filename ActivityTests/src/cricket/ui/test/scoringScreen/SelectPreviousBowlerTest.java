package cricket.ui.test.scoringScreen;

import android.annotation.SuppressLint;
import android.widget.Button;
import cricket.ui.R;
import cricket.ui.ScoringScreen;
import cricket.ui.test.ButtonType;

public class SelectPreviousBowlerTest extends ScoringScreenTestBase {

	private Button previousBowlerButton;
	private Button newBowlerButton;
	
	public SelectPreviousBowlerTest() {
		super(ScoringScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		previousBowlerButton = (Button) activity.findViewById(R.id.previousBowlerButton);
		newBowlerButton = (Button) activity.findViewById(R.id.newBowlerButton);
	}
	
	public void testPreviousButtonDisabled() {
		assertFalse(previousBowlerButton.isEnabled());
		
		selectNewBowler("Mills");
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		//still disabled
		assertFalse(previousBowlerButton.isEnabled());
		
		selectNewBowler("Bracewell");
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		assertTrue(previousBowlerButton.isEnabled());
	}
	
	public void testPreviousButton() {
		assertFalse(previousBowlerButton.isEnabled());
		
		selectNewBowler("Mills");
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		selectNewBowler("Bracewell");
		assertTrue(newBowlerButton.isEnabled());
		
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		clickButton(ButtonType.Dot);
		
		assertTrue(previousBowlerButton.isEnabled());
		
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				previousBowlerButton.callOnClick();
			}
		});
		mInstrumentation.waitForIdleSync(); 
		
		//we can also select a new bowler if we want to
		assertTrue(newBowlerButton.isEnabled());
		checkCurrentBowler("Mills", "1", "1", "0", "0", "0.00", "0", "0", "0", "0");
		
		//after the first ball is bowled than we can't change the bowler any more
		clickButton(ButtonType.Dot);
		
		assertFalse(newBowlerButton.isEnabled());
		assertFalse(previousBowlerButton.isEnabled());
	}
}