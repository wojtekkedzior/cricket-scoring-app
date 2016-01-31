package cricket.ui;

import android.app.Activity;
import android.view.View;

public abstract class GenericActivity extends Activity {
	
	public void onDone(View view) {
		finish();
	}
	
	public void onCancel(View view) {
		finish();
	}
	
	protected String formatFloat(Float f) {
		return String.format("%.2f", f.isInfinite() ? 0 : f);
	}

}
