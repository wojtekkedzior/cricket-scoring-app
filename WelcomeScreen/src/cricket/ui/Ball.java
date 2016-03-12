package cricket.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Ball extends View {
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private float width;
	private float height;
	private float r;
	private Canvas canvas;
	
	private IMyEventListener mEventListener;
	private MotionEvent event;
	private float rawX;
	private float rawY;

	public Ball(Context context) {
		super(context);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
	
	public interface IMyEventListener {
	    public void onEventOccured(float rawX, float rawY);
	}

	public void setEventListener(IMyEventListener mEventListener) {
	    this.mEventListener = mEventListener;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = getWidth() / 2;
		height = getHeight() / 2;
		r = width / 1.5f;
		
		this.canvas = canvas;
		paint.setColor(0xFFFF0000);
		paint.setStyle(Paint.Style.FILL); 
		canvas.drawCircle(width, height, r, paint);
		
		//FF is the alpha
		paint.setColor(0xFF34AD5E);
		paint.setStrokeWidth(10);
		paint.setStyle(Paint.Style.FILL); 
		canvas.drawRect(width-50f, height-100f, width+50f, height+100f, paint);
		
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(10);
		paint.setStyle(Paint.Style.STROKE);
		drawFieldDivider(canvas, 45, width+50f, height+100f);
		drawFieldDivider(canvas, 135, width-50f, height+100f);
		drawFieldDivider(canvas, 225, width-50f, height-100f);
		drawFieldDivider(canvas, 315, width+50f, height-100f);
	}

	private void drawFieldDivider(Canvas canvas, int degrees, float startX, float startY) {
		double radians = Math.toRadians(degrees);
		Double x1 = r * Math.cos(radians) + width;
		Double y1 = r * Math.sin(radians) + height;
		canvas.drawLine(startX, startY, x1.intValue(), y1.intValue(), paint);
	}
	
	//this doesnt quite work yet becuase the onDraw gets called multiple times
	public void drawTouch(float rawX, float rawY) {
		paint.setColor(Color.YELLOW);
		paint.setStyle(Paint.Style.FILL); 
		canvas.drawCircle(rawX, rawY, 10, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.event = event;
		return performClick();
	}
	
	@Override
	public boolean performClick() {
		super.performClick();
		
		boolean validClick = validClick(event);
		
		if(validClick) {
			mEventListener.onEventOccured(rawX, rawY);
		}
		
		return false;
	}
	
	public float getRawX() {
		return rawX;
	}

	public float getRawY() {
		return rawY;
	}
	
	private boolean validClick(MotionEvent event) {
		//get highest and lowest points on X axis
		float maxX = width + r;
		float minX = width - r;
		
		//get highest and lowest points on Y axis
		float maxY = height + r;
		float minY = height - r; 
		
		if(event.getX() >= minX && event.getX() <= maxX) {
			if(event.getY() >= minY && event.getY() <= maxY) {
				float rawX = event.getX();
				float rawY = event.getY();
				
				setRawX(rawX);
				setRawY(rawY);
				
				drawTouch(rawX, rawY);
				
				return true;
			}
		}
		
		return false;
	}

	private void setRawY(float rawY) {
		this.rawY = rawY;
	}

	private void setRawX(float rawX) {
		this.rawX = rawX;
	}
}