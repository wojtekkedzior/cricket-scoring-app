package cricket.ui.popups;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import cricket.ui.GenericActivity;
import cricket.ui.R;

public class CustomSettings extends GenericActivity {
	
	private CheckBox noLimitCheckBox;
	private CheckBox noBowlingRestrictionsCheckBox;
	private EditText numberOfOversInput;
	
	private EditText rule1numberOfOversInput;
	private EditText rule2numberOfOversInput;
	private EditText rule3numberOfOversInput;
	private EditText rule4numberOfOversInput;
	
	private EditText rule1maxNumberOfOversInput;
	private EditText rule2maxNumberOfOversInput;
	private EditText rule3maxNumberOfOversInput;
	private EditText rule4maxNumberOfOversInput;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.popup_custom_settings);
		setFinishOnTouchOutside(false);
		
		noLimitCheckBox = (CheckBox) findViewById(R.id.noOversLimitCheckBox);
		noBowlingRestrictionsCheckBox = (CheckBox) findViewById(R.id.noBowlingRestrictionsCheckBox);
		
		numberOfOversInput = (EditText) findViewById(R.id.numberOfOversInput);
		
		rule1numberOfOversInput = (EditText) findViewById(R.id.rule1numberOfOversInput);
		rule2numberOfOversInput = (EditText) findViewById(R.id.rule2numberOfOversInput);
		rule3numberOfOversInput = (EditText) findViewById(R.id.rule3numberOfOversInput);
		rule4numberOfOversInput = (EditText) findViewById(R.id.rule4numberOfOversInput);
		
		rule1maxNumberOfOversInput = (EditText) findViewById(R.id.rule1maxNumberOfOversInput);
		rule2maxNumberOfOversInput = (EditText) findViewById(R.id.rule2maxNumberOfOversInput);
		rule3maxNumberOfOversInput = (EditText) findViewById(R.id.rule3maxNumberOfOversInput);
		rule4maxNumberOfOversInput = (EditText) findViewById(R.id.rule4maxNumberOfOversInput);
	}
	
	public void onNoOverLimit(View view) {
		if(noLimitCheckBox.isChecked()) {
			numberOfOversInput.setEnabled(false);
		} else {
			numberOfOversInput.setEnabled(true);
		}
	}
	
	public void onNoBowlingRestrictions(View view) {
		if(noBowlingRestrictionsCheckBox.isChecked()) {
			enableRules(false);
		} else {
			enableRules(true);
		}
	}
	
	@Override
	public void onDone(View view) {
		
		
		
		
		
	}
	
	public void onClear(View view) {
		rule1numberOfOversInput.setText("");
		rule2numberOfOversInput.setText("");
		rule3numberOfOversInput.setText("");
		rule4numberOfOversInput.setText("");
		
		rule1maxNumberOfOversInput.setText("");
		rule2maxNumberOfOversInput.setText("");
		rule3maxNumberOfOversInput.setText("");
		rule4maxNumberOfOversInput.setText("");
	}

	private void enableRules(boolean enableRules) {
		rule1numberOfOversInput.setEnabled(enableRules);
		rule2numberOfOversInput.setEnabled(enableRules);
		rule3numberOfOversInput.setEnabled(enableRules);
		rule4numberOfOversInput.setEnabled(enableRules);
		
		rule1maxNumberOfOversInput.setEnabled(enableRules);
		rule2maxNumberOfOversInput.setEnabled(enableRules);
		rule3maxNumberOfOversInput.setEnabled(enableRules);
		rule4maxNumberOfOversInput.setEnabled(enableRules);
	}
	
	
}
