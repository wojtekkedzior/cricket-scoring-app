package beans;

import java.io.Serializable;

import common.BallType;
import common.DismisalType;

import game.Player;

public class Action  implements Serializable {

	private static final long serialVersionUID = -2146979025073657161L;
	
	private Player bowler;
	private Player batsman;
	private Player nonStriker;
	private Player fielder;

	private BallType ballType; //primary ball types ie Dot, Caught, Wide
	private BallType ballType2; // secondary in the case of a wicket
	private DismisalType dismisalType;
	private int ballsLeft;
	private int overNumber;
	private int runsFromExtras;

	public Action(Player bowler, int overNumber) {
		this.bowler = bowler;
		this.overNumber = overNumber;
	}

	public Player getBowler() {
		return bowler;
	}

	public Player getBatsman() {
		return batsman;
	}

	public void setBatsman(Player batsman) {
		this.batsman = batsman;
	}

	public Player getFielder() {
		return fielder;
	}

	public void setFielder(Player fielder) {
		this.fielder = fielder;
	}

	public BallType getBallType() {
		return ballType;
	}

	public void setBallType(BallType ballType) {
		this.ballType = ballType;
	}

	public DismisalType getDismisalType() {
		return dismisalType;
	}

	public void setDismisalType(DismisalType dismisalType) {
		this.dismisalType = dismisalType;
	}

	public int getBallsLeft() {
		return ballsLeft;
	}

	public void setBallsLeft(int ballsLeft) {
		this.ballsLeft = ballsLeft;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

	public int getRunsFromExtras() {
		return Integer.valueOf("-" + runsFromExtras);
	}

	public void setRunsFromExtras(int runsFromExtras) {
		this.runsFromExtras = runsFromExtras;
	}

	public void setNonStriker(Player nonStriker) {
		this.nonStriker = nonStriker;
	}
	
	public Player getNonStriker() {
		return nonStriker;
	}

	public BallType getBallType2() {
		return ballType2;
	}

	public void setBallType2(BallType ballType2) {
		this.ballType2 = ballType2;
	}
}
