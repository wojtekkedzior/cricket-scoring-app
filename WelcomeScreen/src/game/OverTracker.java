package game;

import java.io.Serializable;

import common.BallType;

public class OverTracker implements Serializable  {

	private static final long serialVersionUID = 6794218388252155728L;
	
	private int overNumber = 0;
	private int ballsLeft = 6;
	private int maxOvers;

	public OverTracker(int maxOvers) {
		this.maxOvers = maxOvers;
	}

	public OverTracker(int maxOvers, int overNumber, int ballsLeft) {
		this.maxOvers = maxOvers;
		this.overNumber = overNumber;
		this.ballsLeft = ballsLeft;
	}
	
	public int getBallsLeft() {
		return ballsLeft;
	}

	public int getBallsBowled() {
		return 6 - ballsLeft;
	}

	public void startNewOver() {
		overNumber++;
		ballsLeft = 6;
	}

	public void startNewInnings() {
		overNumber = 0;
		ballsLeft = 6;
	}

	public int getBallNumber() {
		return 6 - ballsLeft;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void updateOver(BallType ballType) {
		if (ballsLeft == 0) {
			return;
		} else if (ballType.equals(BallType.DOT_BALL)) {
			ballsLeft = ballsLeft - 1;
		} else if (ballType.equals(BallType.SCORING)) {
			ballsLeft = ballsLeft - 1;
		} else if (ballType.equals(BallType.WICKET)) {
			ballsLeft = ballsLeft - 1;
		} else if (ballType.equals(BallType.BYE)) {
			ballsLeft = ballsLeft - 1;
		} else if (ballType.equals(BallType.LEG_BYE)) {
			ballsLeft = ballsLeft - 1;
		}
	}

	protected int getOversLeft() {
		return maxOvers - overNumber;
	}

	public boolean isFirstOver() {
		return getOversLeft() == maxOvers ? true : false;
	}

	protected void setNumberOfBallsLeft(int numberOfBallsLeft) {
		this.ballsLeft = numberOfBallsLeft;
	}

	public String getOverAsString() {
		if (getBallsLeft() == 0) {
			return overNumber + 1 + "";
		}
		return overNumber + "." + getBallNumber();
	}

	/**
	 * Used to get the number of balls left as a percentage on an over, so for example 2 balls left is 33% of the over finished. This is used to calculate the
	 * runrate because for the run rate calculation we take ALL the runs but devide by the LAST complete over, which is not accurate, especially if the current
	 * over produces lots of runs.
	 * 
	 * @return
	 */
	public String getOverAsStringPercentage() {
		return overNumber + "." + getPercentageOfOver();
	}

	// Ball number can be more than 6 if the bowler balls extras. so we need to use 'balls left in over'
	private int getPercentageOfOver() {
		Float f = Float.valueOf((Float.valueOf(6 - ballsLeft) / Float.valueOf(6)) * 100);

		if (f.intValue() == 100) {
			return 0;
		} else {
			return f.intValue();
		}
	}

	public boolean isOverFinished() {
		return getBallsLeft() == 0 ? true : false;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}
}