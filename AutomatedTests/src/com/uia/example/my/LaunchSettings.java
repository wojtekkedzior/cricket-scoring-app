package com.uia.example.my;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {   
	
	private UiObject enterTeam1 = new UiObject(new UiSelector().className(android.widget.TextView.class.getName()).text("team1"));
	private UiObject doneButton = new UiObject(new UiSelector().className(android.widget.Button.class.getName()).text("Done"));
	private UiObject startButton = new UiObject(new UiSelector().className(android.widget.Button.class.getName()).text("Start"));
	private UiObject newBowlerButton = new UiObject(new UiSelector().className(android.widget.Button.class.getName()).text("New Bowler"));
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		getUiDevice().pressHome();
		 
		 UiObject allAppsButton = new UiObject(new UiSelector().className("android.view.View").index(6));
			// // Simulate a click to bring up the All Apps screen.
			allAppsButton.clickAndWaitForNewWindow();

			UiObject settingsApp =  new UiObject(new UiSelector().text("Hello, Android"));
			settingsApp.clickAndWaitForNewWindow();
			
			enterTeam1.clickAndWaitForNewWindow();
			doneButton.clickAndWaitForNewWindow();
			startButton.clickAndWaitForNewWindow();
	}
	
	public void testScore() throws UiObjectNotFoundException {
		newBowlerButton.clickAndWaitForNewWindow();
		doneButton.clickAndWaitForNewWindow();
		
		//can also do this to click on the field
		getUiDevice().click(362, 1001);
		
//		UiObject field = new UiObject(new UiSelector().className(android.view.View.class.getName()).index(10));
//		field.clickAndWaitForNewWindow();
	}
	
	
	
	
	
	
	
	
	
	
	
	

//   public void testDemo() throws UiObjectNotFoundException {   
      // Simulate a short press on the HOME button.
     
      
      // We’re now in the home screen. Next, we want to simulate 
      // a user bringing up the All Apps screen.
      // If you use the uiautomatorviewer tool to capture a snapshot 
      // of the Home screen, notice that the All Apps button’s 
      // content-description property has the value “Apps”.  We can 
      // use this property to create a UiSelector to find the button. 
		
		
//      
//      // In the All Apps screen, the Settings app is located in 
//      // the Apps tab. To simulate the user bringing up the Apps tab,
//      // we create a UiSelector to find a tab with the text 
//      // label “Apps”.
//      UiObject appsTab = new UiObject(new UiSelector()
//         .text("Apps"));
//      
//      // Simulate a click to enter the Apps tab.
//      appsTab.click();
//
//      // Next, in the apps tabs, we can simulate a user swiping until
//      // they come to the Settings app icon.  Since the container view 
//      // is scrollable, we can use a UiScrollable object.
//      UiScrollable appViews = new UiScrollable(new UiSelector()
//         .scrollable(true));
//      
//      // Set the swiping mode to horizontal (the default is vertical)
//      appViews.setAsHorizontalList();
//      
//      // Create a UiSelector to find the Settings app and simulate      
//      // a user click to launch the app. 
//      UiObject settingsApp = appViews.getChildByText(new UiSelector()
//         .className(android.widget.TextView.class.getName()), 
//         "Settings");
//      settingsApp.clickAndWaitForNewWindow();
//      
//      // Validate that the package name is the expected one
//      UiObject settingsValidation = new UiObject(new UiSelector()
//         .packageName("com.android.settings"));
//      assertTrue("Unable to detect Settings", 
//         settingsValidation.exists());   
//  }   
}