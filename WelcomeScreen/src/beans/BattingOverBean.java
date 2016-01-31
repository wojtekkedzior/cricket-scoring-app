package beans;

import java.io.Serializable;
import java.util.Stack;

public class BattingOverBean implements Serializable {

	private static final long serialVersionUID = -1661541901455589857L;

	private final int overNumber;
	private final int bowlerId;
	private final Stack<Integer> runs;
	
	private Stack<Coordinate> cordinatesOfRun;

	public BattingOverBean(Integer overNumber, Integer bowlerId) {
		this.overNumber = overNumber;
		this.bowlerId = bowlerId;
		runs = new Stack<Integer>();
		cordinatesOfRun = new Stack<Coordinate>();
	}

	public int getRunsForOver() {
		int sum = 0;

		for (Integer run : runs) {
			if (run > 0) {
				sum = sum + run;
			}
		}

		return sum;
	}

	public void addRun(Integer run, float rawX, float rawY) {
		runs.add(run);
		cordinatesOfRun.add(new Coordinate(rawX, rawY, run));
	}

	public void addRun(Integer run) {
		addRun(run, 0, 0);
	}

	public int getNumberOfRuns(int runNum) {
		int numberOfRuns = 0;
		for (Integer run : runs) {
			if (run == runNum) {
				numberOfRuns++;
			}
		}
		return numberOfRuns;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public int getBowlerId() {
		return bowlerId;
	}

	public Integer getBallsFaced() {
		return runs.size();
	}

	public Stack<Coordinate> getCordinatesOfAllRuns() {
		return cordinatesOfRun;
	}
	
	public Stack<Integer> getRuns() {
		return runs;
	}

	//When undoing a Wide the stacks will be empty because there is not bean for a wide delivery, hence the check ifEmpty
	public void undo() {
		if(!runs.isEmpty()) {
			runs.pop();
		} 
		if(!cordinatesOfRun.isEmpty()) {
			cordinatesOfRun.pop();
		}
	}
}
