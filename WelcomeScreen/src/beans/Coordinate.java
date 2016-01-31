package beans;

import java.io.Serializable;

public class Coordinate implements Serializable {

	private static final long serialVersionUID = -7615123396148105429L;
	
	private float rawX;
	private float rawY;
	private int run;

	public Coordinate(float rawX, float rawY, int run) {
		this.rawX = rawX;
		this.rawY = rawY;
		this.run = run;
	}

	public float getRawX() {
		return rawX;
	}

	public float getRawY() {
		return rawY;
	}

	public int getRun() {
		return run;
	}
}
