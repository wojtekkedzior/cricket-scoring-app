package game;

import java.io.Serializable;

public class BowlingScore implements Serializable {

	private static final long serialVersionUID = -1691860979649632717L;

	private int legByes;
	private int legByeRuns;

	private int byes;
	private int byeRuns;

	private int wides;
	private int wideRuns;

	private int noBalls;
	private int noBallRuns;

	public int getLegByeRuns() {
		return legByeRuns;
	}

	public int getByeRuns() {
		return byeRuns;
	}

	public int getWideRuns() {
		return wideRuns;
	}

	public int getNoBallRuns() {
		return noBallRuns;
	}

	public int getLegByes() {
		return legByes;
	}

	public int getByes() {
		return byes;
	}

	public int getWides() {
		return wides;
	}

	public int getNoBalls() {
		return noBalls;
	}

	public void addLegBye(int legByeRuns) {
		legByes = legByes + addOrRemove(legByeRuns);
		this.legByeRuns = this.legByeRuns + legByeRuns;
	}

	public void addBye(int byeRuns) {
		byes = byes + addOrRemove(byeRuns);
		this.byeRuns = this.byeRuns + byeRuns;
	}
	
	//Add or remove a Ball Type, positive number when scoring, negative when undoing. 
	private int addOrRemove(int runs) {
		if(runs < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	public void addWideAndExtras(int wideRuns) {
		wides = wides + 1; //actual number of wide balls a bowler has bowled
//		wides = wides + addOrRemove(wideRuns);
		
		//A wide always constitutes at least 1 run. If batsman run 2 we still add 1 for the wide itsef
//		if(wideRuns < 0) { //Undo - remove the wide mandatory wide run
//			wideRuns = wideRuns - 1;
//		} else {
			wideRuns = wideRuns + 1;
//		}
		this.wideRuns = this.wideRuns + wideRuns;
	}

	/**
	 * 	"If the batsman hits the ball he may take runs as normal. These are scored as runs by the batsman, as normal.
	 * 	Runs may also be scored without the batsman hitting the ball, but these are recorded as No ball extras rather than byes or leg byes."
	 * 
	 * 	this always increments the noBallRuns + 1
	 */
	public void addNoBallAndExtras(int noBallRuns) {
		noBalls = noBalls + 1;
		noBallRuns = noBallRuns + 1;
		this.noBallRuns = this.noBallRuns + noBallRuns;
	}
	
	public int getBowlerExtras() {
		return wideRuns + noBallRuns;
	}

	public int getFieldingExtras() {
		return legByeRuns + byeRuns;
	}

	public void undoWide(int runsFromExtras) {
		wides = wides - 1; 
		wideRuns = wideRuns - 1; // a wide always gives one run to the wides so we need to deduct it on top of any other runs taken on the wide
		//runsFromExtras is negative
		this.wideRuns = this.wideRuns + runsFromExtras;
	}
	
	public int getAllExtras() {
		return getBowlerExtras() + getFieldingExtras();
	}

	public void undoNoBall(int runsFromExtras) {
		noBalls = noBalls - 1;
		
		//firstly remove the hard-coded +1 run
		noBallRuns = noBallRuns -1;
		
		//Secondly remove any extras.  runsFromExtras is negative
		this.noBallRuns = this.noBallRuns + runsFromExtras;
	}
}
