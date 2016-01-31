package cricket.ui.test.popups;

import game.Game;
import game.Team;
import android.widget.Spinner;

import common.Keys;
import common.TeamBattingStatus;

import cricket.ui.R;
import cricket.ui.popups.NewInnings;
import cricket.ui.test.TestBase;

public class NewInningsTest extends TestBase<NewInnings> {

	private Spinner firstBatsmanSpinner;
	private Spinner secondBatsmanSpinner;

	public NewInningsTest() {
		super(NewInnings.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Game game = testData.getGame(false);
		game.getTeam(1).setTeamBattingStatus(TeamBattingStatus.Batting);
		
		extras.putSerializable(Keys.BATTING_TEAM, game.getBattingTeam());
		
		activity = launchActivity("cricket.ui", NewInnings.class, extras);
		
		firstBatsmanSpinner = (Spinner) activity.findViewById(R.id.firstBatsmanSpinner);
		secondBatsmanSpinner = (Spinner) activity.findViewById(R.id.secondBatsmanSpinner);
	}
	
	public void testSelectNewBatsman() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		final int firstBatsmanCount = firstBatsmanSpinner.getAdapter().getCount();
		assertEquals(11, firstBatsmanCount);
		final int secondBatsmanCount = secondBatsmanSpinner.getAdapter().getCount();
		assertEquals(11, secondBatsmanCount);
		
		
		activity.runOnUiThread(new Runnable() {
			public void run() {
				for (int i = 0; i < firstBatsmanCount; i++) {
					String item = (String) firstBatsmanSpinner.getAdapter().getItem(i);
					if(item.equals(testData.franklin.getName())) {
						firstBatsmanSpinner.setSelection(i);
						assertEquals(testData.franklin.getName(), (String) firstBatsmanSpinner.getSelectedItem());
					}
					
				}
				
				for (int i = 0; i < secondBatsmanCount; i++) {
					String item = (String) secondBatsmanSpinner.getAdapter().getItem(i);
					if(item.equals(testData.woodcock.getName())) {
						secondBatsmanSpinner.setSelection(i);
						assertEquals(testData.woodcock.getName(), (String) secondBatsmanSpinner.getSelectedItem());
					}
				}
			}
		});
		
		mInstrumentation.waitForIdleSync();
		
		//Get the game object from the activity
		Team battingTeam = (Team) getValueFromField("battingTeam", activity);
		
		assertEquals(testData.franklin.getName(), battingTeam.getStriker().getName());
		assertEquals(testData.woodcock.getName(), battingTeam.getNonStriker().getName());
	}
}
