package cricket.ui.test.popups;


import java.io.File;

import other.GameData;
import android.app.Instrumentation.ActivityMonitor;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import backend.Storage;

import common.Keys;

import cricket.ui.R;
import cricket.ui.popups.GameOver;
import cricket.ui.test.ButtonType;
import cricket.ui.test.TestBase;

public class GameOverTest extends TestBase<GameOver> {
	
	protected ActivityMonitor newInningsMonitor;

	public GameOverTest() {
		super(GameOver.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testFullGameSummary() {
		extras.putSerializable(Keys.GAME, new GameData().getFullGame());
		activity = launchActivity("cricket.ui", GameOver.class, extras);
		
		assertEquals("New Zealand : 127/0", ((TextView) activity.findViewById(R.id.team1Total)).getText().toString()); 

		//batsman
		assertEquals("Brendon McCullum 81/46", ((TextView) activity.findViewById(R.id.team1TopBatsman)).getText().toString());
		assertEquals("Guptill 40/36", ((TextView) activity.findViewById(R.id.team1SecondBatsman)).getText().toString());
		
		//bowlers
		assertEquals("Jarvis  3-0-32-0", ((TextView) activity.findViewById(R.id.team2TopBowler)).getText().toString());
		assertEquals("Chigumbura  3-0-28-0", ((TextView) activity.findViewById(R.id.team2SecondBowler)).getText().toString());
		
		
		assertEquals("Zimbabwe : 123/8", ((TextView) activity.findViewById(R.id.team2Total)).getText().toString());
		
		//batsman
		assertEquals("Brendon Taylor 50/46", ((TextView) activity.findViewById(R.id.team2TopBatsman)).getText().toString());
		assertEquals("Mutizwa 16/16", ((TextView) activity.findViewById(R.id.team2SecondBatsman)).getText().toString());
		
		//bowlers //TODO
		assertEquals("Nathan McCullum  3-0-17-2", ((TextView) activity.findViewById(R.id.team1TopBowler)).getText().toString());
		assertEquals("Mills  4-0-15-2", ((TextView) activity.findViewById(R.id.team1SecondBowler)).getText().toString());
		
		assertEquals("New Zealand win by 10 wickets", ((TextView) activity.findViewById(R.id.winningTeamLabel)).getText().toString());
		
		
//		activity.runOnUiThread(new Runnable() {
//			public void run() {
//				teamName.setText("teamNameRenamed");
//				player6Name.setText("player6");
//				player7Name.setText("player7");
//			}
//		});
		
//		activity.runOnUiThread(new Runnable() {
//			@SuppressLint("NewApi")
//			public void run() {
//				Button shareButton = (Button) activity.findViewById(cricket.ui.R.id.shareButton);
//				shareButton.callOnClick();
//			}
//		});
//		
//		mInstrumentation.waitForIdleSync();
//		
//		System.err.println("");
//		Button shareButton = (Button) activity.findViewById(cricket.ui.R.id.saveToDeviceButton);
		
		Button shareButton = (Button) activity.findViewById(cricket.ui.R.id.saveToDeviceButton);
		shareButton.callOnClick();
		
		clickButton(ButtonType.Done);
	}

	public void testSaveToXML() throws Exception {
		extras.putSerializable(Keys.GAME, new GameData().getFullGame());
		activity = launchActivity("cricket.ui", GameOver.class, extras);
		
		Button saveToDeviceButton = (Button) activity.findViewById(cricket.ui.R.id.saveToDeviceButton);
		saveToDeviceButton.callOnClick();
		
		String filename = "myfile.xml";
		File file = new File(Environment.getExternalStorageDirectory(), filename);
		
		assertTrue(file.exists());
		
		String ret = Storage.getFileAsString("myfile.xml");  
		assertEquals(63624, ret.length());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		while(!activity.isDestroyed()) {
			//need to wait until the activity is destryoed before cleaning the cache
		}
		
		Storage.clear(activity);
	}
	
//
//	public void testOnTwitter() {
//
//	}
//
//	public void testOnEmail() {
//
//	}
//
//	public void testOnSaveToDevice() {
//
//	}
}