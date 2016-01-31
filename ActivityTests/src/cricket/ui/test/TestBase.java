package cricket.ui.test;

import java.lang.reflect.Field;

import other.GameData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public abstract class TestBase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {
	
	protected T activity;
	protected Instrumentation mInstrumentation;
	protected Bundle extras;
	protected GameData testData = new GameData();

	public TestBase(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
		extras = new Bundle();
		mInstrumentation = getInstrumentation();
	}

	@Override
	protected void tearDown() throws Exception {
		activity.finish();
		super.tearDown();
	}
	
	protected void clickButton(final ButtonType type) {
		activity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				getButton(type, activity).callOnClick();
			}
		});
		
		mInstrumentation.waitForIdleSync();
	}
	
	protected void clickButton(final ButtonType type, final Activity otherActivity) {
		otherActivity.runOnUiThread(new Runnable() {
			@SuppressLint("NewApi")
			public void run() {
				getButton(type, otherActivity).callOnClick();
			}
		});
		
		mInstrumentation.waitForIdleSync();
	}
	
	protected Button getButton(ButtonType buttonType, Activity activity) {
		Button button = null;
		
		switch (buttonType) {
		case Cancel:
			button = (Button) activity.findViewById(cricket.ui.R.id.cancelButton);
			break;
		case Clear:
			button = (Button) activity.findViewById(cricket.ui.R.id.clearButton);
			break;
		case Done:
			button = (Button) activity.findViewById(cricket.ui.R.id.doneButton);
			break;
		case Undo:
			button = (Button) activity.findViewById(cricket.ui.R.id.undoButton);
			break;
		case Dot:
			button = (Button) activity.findViewById(cricket.ui.R.id.dotBallButton);
			break;
		default:
			fail();
		}
		
		assertNotNull(button);
		return button;
	}
	
	protected Intent getIntentWithResultCode(int resultCode) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		assertTrue(activity.isFinishing());
		Field f = Activity.class.getDeclaredField("mResultCode");
		f.setAccessible(true);
		int actualResultCode = (Integer) f.get(activity);
		assertEquals(resultCode, actualResultCode);
		f = Activity.class.getDeclaredField("mResultData");
		f.setAccessible(true);
		return (Intent) f.get(activity);
	}
	
	//Every Activity has a mResultData
	protected Intent getIntentFromActivity(Activity activity) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		Field f = Activity.class.getDeclaredField("mResultData");
		f.setAccessible(true);
		return (Intent) f.get(activity);
	}
	
	protected Object getValueFromField(String fieldName, Activity activity) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		Field f = activity.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		return f.get(activity);
	}
}