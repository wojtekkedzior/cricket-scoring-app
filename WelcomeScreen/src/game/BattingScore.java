package game;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import common.BattingStyle;

import beans.BattingOverBean;
import beans.BattingWicketBean;

public class BattingScore implements Serializable {

	public static final int DEFAULT_BATTING_SLOT = 99;
	
	private static final long serialVersionUID = -8977010159756399958L;
	
	private final Map<Integer, BattingOverBean> battingOvers; // over number - // bean
	private BattingWicketBean battingWicketBean;
	private int battingSlot = DEFAULT_BATTING_SLOT;

	private BattingStyle battingStyle;
	
	public BattingScore() {
		battingOvers = new HashMap<Integer, BattingOverBean>();
		battingStyle = BattingStyle.Right;
	}

	public Map<Integer, BattingOverBean> getBattingOvers() {
		return battingOvers;
	}

	public void addBattingOver(BattingOverBean bean) {
		battingOvers.put(bean.getOverNumber(), bean);
	}

	public int getRuns() {
		int runs = 0;
		Set<Integer> keySet = battingOvers.keySet();
		for (Integer overNumber : keySet) {
			BattingOverBean battingOverBean = battingOvers.get(overNumber);
			runs = runs + battingOverBean.getRunsForOver();
		}

		return runs;
	}

	public BattingOverBean getBattingOverBean(int overNumber, int bowlerId) {
		if (battingOvers.containsKey(overNumber)) {
			return battingOvers.get(overNumber);
		} else {
			BattingOverBean bean = new BattingOverBean(overNumber, bowlerId);
			battingOvers.put(overNumber, bean);
			return bean;
		}
	}

	public int getRunsOfBowler(int bowlerId) {
		int runsOfBowler = 0;
		Collection<BattingOverBean> battingOverBeans = battingOvers.values();

		for (BattingOverBean battingOverBean : battingOverBeans) {
			if (battingOverBean.getBowlerId() == bowlerId) {
				runsOfBowler = runsOfBowler + battingOverBean.getRunsForOver();
			}
		}

		return runsOfBowler;
	}

	public int getRunsForOver(int overNumber) {
		if (battingOvers.containsKey(overNumber)) {
			BattingOverBean battingOver = battingOvers.get(overNumber);
			return battingOver.getRunsForOver();
		} else {
			return 0;
		}
	}

	public int getBallsFaced() {
		int totalBallsFaced = 0;
		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			totalBallsFaced = totalBallsFaced + overNumber.getValue().getBallsFaced();
		}

		return totalBallsFaced;
	}

	public Map<Integer, Integer> getNumberOfOversFromBowler(int bowlerId) {
		Map<Integer, Integer> overByBowler = new HashMap<Integer, Integer>();

		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			if (overNumber.getValue().getBowlerId() == bowlerId) {
				overByBowler.put(overNumber.getKey(), bowlerId);
			}
		}

//		if(!overByBowler.isEmpty())
//			System.err.println(overByBowler + " size: " + overByBowler.size());
		return overByBowler;
	}

	public BattingWicketBean getBattingWicketBean() {
		return battingWicketBean;
	}

	public void setBattingWicketBean(BattingWicketBean battingWicketBean) {
		this.battingWicketBean = battingWicketBean;
	}

	public int getAllMaidens() {
		int maidens = 0;
		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			if (overNumber.getValue().getBallsFaced() == 6 && overNumber.getValue().getRunsForOver() == 0) {
				maidens++;
			}
		}

		return maidens;
	}

	public int getMaidenForBowler(int bowlerId) {
		int maidens = 0;
		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			if (overNumber.getValue().getBowlerId() == bowlerId) {
				if (overNumber.getValue().getBallsFaced() == 6 && overNumber.getValue().getRunsForOver() == 0) {
					maidens++;
				}
			}
		}

		return maidens;
	}
	
	public int getScoringShots() {
		//not gonna work for now coz we dont track which ball scored a run. Maybe add later
		return 0;
	}
	
	public float getStrikeRate() {
		if(getBallsFaced() == 0 || getRuns() == 0) {
			return 0;
		}
		return Float.valueOf(getRuns()) / Float.valueOf(getBallsFaced()) * 100f;
	}
	
	public int getNumberOfRuns(int run) {
		int numOfRun = 0;
		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			numOfRun = numOfRun + overNumber.getValue().getNumberOfRuns(run);
		}

		return numOfRun;
	}
	
	public int getNumberOfRunsOfBowler(int bowlerId, int run) {
		int numOfRun = 0;
		for (Entry<Integer, BattingOverBean> overNumber : battingOvers.entrySet()) {
			if (overNumber.getValue().getBowlerId() == bowlerId) {
				numOfRun = numOfRun + overNumber.getValue().getNumberOfRuns(run);
			}
		}

		return numOfRun;
	}

	public void setBattingStyle(BattingStyle battingStyle) {
		this.battingStyle = battingStyle;
	}
	
	public BattingStyle getBattingStyle() {
		return battingStyle;
	}

	public int getBattingSlot() {
		return battingSlot;
	}
	
	public void setBattingSlot(int battingSlot) {
		this.battingSlot = battingSlot;
	}
}
