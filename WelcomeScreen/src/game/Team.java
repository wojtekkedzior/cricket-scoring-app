package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import beans.BattingOverBean;
import beans.BattingWicketBean;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

@SuppressLint("UseSparseArrays")
public class Team implements Serializable {

	private static final long serialVersionUID = -974223279416458400L;

	private final Map<Integer, Player> players;
	private String teamName;
	private int lastBowlerId;
	
	//Todo replace with enum? HomeTeam AwayTeam or something like that, int are ugly.
	private final int teamIndex;

	private int battingSlot = 1;

	private TeamBattingStatus teamBattingStatus = TeamBattingStatus.YetToBat;
	private TeamBowlingStatus teamBowlingStatus = TeamBowlingStatus.YetToBowl;

	public Team(int teamIndex) {
		this.teamIndex = teamIndex;
		players = new LinkedHashMap<Integer, Player>(); 
	}

	// playerId is what eg in team 1 we can have player 11, 12, 13, 14....
	// the number from batsman list will be the battingscore.battingslot

	public void addPlayer(Player player) {
		if(players.containsKey(player.getId())) {
			throw new IllegalArgumentException("player with this id already exists");
		}
		players.put(player.getId(), player);
	}

	public Player getBatsmanByIndex(int batsmanNumber) {
		return players.get(batsmanNumber);
	}

	public Player getPlayerByName(String playerName) { 
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getValue().getName().equals(playerName)) {
				return entry.getValue();
			}
		}
		throw new IllegalArgumentException("Player name: " + playerName + " doesn't exist.");
	}
	
	public Player getPlayerById(Integer bowlerId) {
		return players.get(bowlerId);
	}
	
	public int getTeamIndex() {
		return teamIndex;
	}

	public Map<Integer, Player> getPlayers() {
		return players;
	}
	
	public List<Player> getPlayersAsList() {
		List<Player> playersList = new ArrayList<Player>();
		
		for (Entry<Integer, Player> entry : players.entrySet()) {
			playersList.add(entry.getValue());
		}
		
		Collections.sort(playersList);
		return playersList;
	}

	public ArrayList<String> getAllPlayerNames() {
		ArrayList<String> playerNames = new ArrayList<String>();

		for (Entry<Integer, Player> entry : players.entrySet()) {
			playerNames.add(entry.getValue().getName());
		}

		return playerNames;
	}
	
	public int getRunsScored() {
		int runsScored = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			runsScored = runsScored + entry.getValue().getBattingScore().getRuns();
		}

		return runsScored;
	}

	public int getRunsScoredForOver(int overNumber) {
		int runsScored = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			runsScored = runsScored + entry.getValue().getBattingScore().getRunsForOver(overNumber);
		}

		return runsScored;
	}

	public int getNumberOfOversByBowler(int bowlerId) {
		Map<Integer, Integer> numberOfOversFromBowler = new HashMap<Integer, Integer>();

		for (Entry<Integer, Player> entry : players.entrySet()) {
			numberOfOversFromBowler.putAll(entry.getValue().getBattingScore().getNumberOfOversFromBowler(bowlerId));
		}

		return numberOfOversFromBowler.size();
	}

	public int getWicketsLost() {
		int wickets = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();

			if (battingWicketBean != null) { {
					wickets++;
				}
			}
		}

		return wickets;
	}

	public int getCatchesForPlayer(int playerId) {
		int catches = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();
			if (battingWicketBean != null && battingWicketBean.getDismisalType() == DismisalType.CAUGHT) {
				if (battingWicketBean.getFielderId() == playerId) {
					catches++;
				}
			}
		}

		return catches;
	}

	public int getWicketsForBowler(int bowlerId) {
		int wickets = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			
			if(entry.getValue().getBattingStatus().equals(BattingStatus.Out)) {
				BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();
				if(battingWicketBean.getBowlerId() == bowlerId) {
					if(!battingWicketBean.getDismisalType().equals(DismisalType.RUNOUT) && !battingWicketBean.getDismisalType().equals(DismisalType.NON_STRIKER_RUNOUT)) {
						wickets++;
					}
				}
			}
		}

		return wickets;
	}
	
	public int getWicketsByDismissal(DismisalType type) {
		int wickets = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();

			if (battingWicketBean != null && battingWicketBean.getDismisalType().equals(type)) {
				wickets++;
			}
		}

		return wickets;
	}

	public int getWicketsForBowlerByDismisal(int bowlerId, DismisalType type) {
		int wickets = 0;
		for (Entry<Integer, Player> entry : players.entrySet()) {
			BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();

			if (battingWicketBean == null || battingWicketBean.getBowlerId() == null) {
				continue;
			}

			if (battingWicketBean.getBowlerId() == bowlerId && battingWicketBean.getDismisalType().equals(type)) {
				wickets++;
			}
		}

		return wickets;
	}

	public int getAllBowlingExtras() {
		int runsByBowlers = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			runsByBowlers = runsByBowlers + entry.getValue().getBowlingScore().getBowlerExtras();
		}

		return runsByBowlers;
	}

	public int getAllFieldingExtras() {
		int fieldRuns = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			fieldRuns = fieldRuns + entry.getValue().getBowlingScore().getFieldingExtras();
		}
		return fieldRuns;
	}
	
	public int getRunsForBowlerForBallType(BallType ballType) {
		int runs = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			
			switch(ballType) {
			case BYE:
				runs = runs + entry.getValue().getBowlingScore().getByeRuns();
				break;
			case LEG_BYE:
				runs = runs + entry.getValue().getBowlingScore().getLegByeRuns();
				break;
			case NO_BALL_EXTRA:
				runs = runs + entry.getValue().getBowlingScore().getNoBallRuns();
				break;
			case WIDE:
				runs = runs + entry.getValue().getBowlingScore().getWideRuns();
				break;
			default:
				throw new IllegalArgumentException("This method should only be called with Extra type balls.");
			}
		}
		return runs;
	}

	public int getAllExtras() {
		int extras = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			extras = extras + entry.getValue().getBowlingScore().getAllExtras();
		}
		return extras;
	}

	public int getNumberOfOvers() {
		Map<Integer, BattingOverBean> battingOvers = new HashMap<Integer, BattingOverBean>();

		for (Entry<Integer, Player> entry : players.entrySet()) {
			battingOvers.putAll(entry.getValue().getBattingScore().getBattingOvers());
		}

		int highestOver = 0;

		for (Integer overNumber : battingOvers.keySet()) {
			if (overNumber.intValue() > highestOver) {
				highestOver = overNumber.intValue();
			}
		}
		return highestOver;
	}

	public int getMaidensForBowler(int bowlerId) {
		int maidens = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			maidens = maidens + entry.getValue().getBattingScore().getMaidenForBowler(bowlerId);
		}
		return maidens;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public float getRunRate() {
		// TODO: make it more accurate by factoring is over.balls bowled. eg
		// 12.3, 12.4 ... that way we can also update the RR after every ball
		// where as now we can only do it after each over.
		int totalBallsFacedByTeam = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			totalBallsFacedByTeam = totalBallsFacedByTeam + entry.getValue().getBattingScore().getBallsFaced();
		}
		
		if(getRunsScored() == 0) {
			return 0;
		} else {
			return Float.valueOf(getRunsScored()) / Float.valueOf((totalBallsFacedByTeam / 6f));
		}
	}
	
	public int getTotalNumberOfRunsOfBowlerAgainstAllBatsman(int bowlerId, int runType) {
		int numberOfRuns = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			numberOfRuns = numberOfRuns + entry.getValue().getBattingScore().getNumberOfRunsOfBowler(bowlerId, runType);
		}

		return numberOfRuns;
	}
	
	public int getRunsGivenByBowlerAgainstAllBatsman(int bowlerId) {
		int runsGivenByBowler = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			runsGivenByBowler = runsGivenByBowler + entry.getValue().getBattingScore().getRunsOfBowler(bowlerId);
		}

		return runsGivenByBowler;
	}

	//this needs to be called when selecting the first batsman. after that we can use setStatsuForBatsman
	public void addOpeningBatsman(String batsmanName, BattingStatus status) {
		//clear the status, if set, ie u the opening batsman is changed. we need to clear out the status of the previously selected one.
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getValue().getBattingStatus().equals(status)) {
				entry.getValue().setBattingStatus(BattingStatus.Available);
				
				//have to explicitly set the batting slot back to -1
				entry.getValue().getBattingScore().setBattingSlot(BattingScore.DEFAULT_BATTING_SLOT);
				battingSlot = battingSlot - 1;
			}
		}
		
		setBattingStatusForBatsman(batsmanName, status);
	}
	
	public void setBattingStatusForBatsman(String batsmanName, BattingStatus status) {
		Player batsman = getPlayerByName(batsmanName);
		
		if(batsman.getBattingScore().getBattingSlot() == BattingScore.DEFAULT_BATTING_SLOT) {
			batsman.getBattingScore().setBattingSlot(battingSlot);
			battingSlot = battingSlot + 1;
		}
		
		batsman.setBattingStatus(status);
	}

	public Player getStriker() {
		return getBatsmanByStatus(BattingStatus.Striker);
	}
	
	public Player getNonStriker() {
		return getBatsmanByStatus(BattingStatus.NonStriker);
	}
	
	public Player getBatsmanByStatus(BattingStatus status) {
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getValue().getBattingStatus().equals(status)) {
				return (entry.getValue());
			}
		}
		
		return null;
	}
	
	public ArrayList<String> getAvailableBatsmanNames() {
		ArrayList<String> playerNames = new ArrayList<String>();
		
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getValue().getBattingStatus().equals(BattingStatus.Available)) {
					playerNames.add(entry.getValue().getName());
			}
		}

		return playerNames;
	}
	
	public void alternateStriker() {
		Player striker = getStriker();
		Player nonStriker = getNonStriker();
		
		setBattingStatusForBatsman(striker.getName(), BattingStatus.NonStriker);
		
		if(nonStriker != null) { //this will go once we sort out the ending of a game
			setBattingStatusForBatsman(nonStriker.getName(), BattingStatus.Striker);
		}
	}
	
	//------------------- get Bowlers
	
	public List<Player> getBowlersByStatus(BowlingStatus status) {
		List<Player> playersByStatus = new ArrayList<Player>();
		
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (entry.getValue().getBowlingStatus().equals(status)) {
				playersByStatus.add(entry.getValue());
			}
		}
		
		return playersByStatus;
	}
	
	public Player getCurrentBowler() {
		Player bowler = getBowlersByStatus(BowlingStatus.CurrentlyBowling).size() != 0 ? getBowlersByStatus(BowlingStatus.CurrentlyBowling).get(0) : null;
		
		if(bowler != null) {
			setLastBowlerId(bowler.getId());
		}
		
		return bowler;
	}
	
	public Player getOtherBowler() {
		return getBowlersByStatus(BowlingStatus.OtherBowler).size() != 0 ? getBowlersByStatus(BowlingStatus.OtherBowler).get(0) : null;
	}
	
	public Player getBowlerByStatus(BowlingStatus status) {
		return getBowlersByStatus(status).size() != 0 ? getBowlersByStatus(status).get(0) : null;
	}
	
	public void setBowlingStatusForBowler(String bowlerName, BowlingStatus status) {
		getPlayerByName(bowlerName).setBowlingStatus(status);
	}
	
	public List<Player> getBowlerNamesWhoCanBowl() {
		List<Player> playersByStatus = new ArrayList<Player>();
		
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if (!entry.getValue().getBowlingStatus().equals(BowlingStatus.BowledOut) &&
				!entry.getValue().getBowlingStatus().equals(BowlingStatus.CurrentlyBowling) &&
				entry.getKey() != lastBowlerId) {
				playersByStatus.add(entry.getValue());
			}
		}
		
		return playersByStatus;
	}
	
	public int getTotalForBallType(BallType ballType) {
		int totalForBallType = 0;

		for (Integer playerId : players.keySet()) {
			Player player = players.get(playerId);
			
			switch (ballType) {
			case BYE:
				totalForBallType = totalForBallType + player.getBowlingScore().getByes();
				break;
			case DOT_BALL:
				//TODO - this one is usefull
				break;
			case LEG_BYE:
				totalForBallType = totalForBallType + player.getBowlingScore().getLegByes();
				break;
			case NO_BALL_EXTRA:
				totalForBallType = totalForBallType + player.getBowlingScore().getNoBalls();
				break;
			case WIDE:
				totalForBallType = totalForBallType + player.getBowlingScore().getWides();
				break;
			default:
				throw new IllegalArgumentException("This method should only be called with Extra type balls.");
			}
		}
		
		return totalForBallType;
	}
	
	public int getNumberOfRuns(int run) {
		int totalNumberOfGivenRun = 0;

		for (Entry<Integer, Player> entry : players.entrySet()) {
			for (Entry<Integer, BattingOverBean> battingOverentry : entry.getValue().getBattingScore().getBattingOvers().entrySet()) {
				totalNumberOfGivenRun = totalNumberOfGivenRun + battingOverentry.getValue().getNumberOfRuns(run);
			}
		}
		
		return totalNumberOfGivenRun;
	}

	public void setLastBowlerId(int lastBowlerId) {
		this.lastBowlerId = lastBowlerId;
	}
	
	public int getLastBowlerId() {
		return lastBowlerId;
	}
	
	public int getTotalRunsFromBowler(Player bowler) {
		int runsGivenByBowler = getRunsGivenByBowlerAgainstAllBatsman(bowler.getId());
		int runFromExtras = bowler.getBowlingScore().getBowlerExtras();
		int totalRunFromBowler = runsGivenByBowler + runFromExtras;
		return totalRunFromBowler;
	}

	public void setTeamBattingStatus(TeamBattingStatus teamBattingStatus) {
		this.teamBattingStatus = teamBattingStatus;
	}
	
	public void setTeamBowlingStatus(TeamBowlingStatus teamBowlingStatus) {
		this.teamBowlingStatus = teamBowlingStatus;
	}

	public TeamBattingStatus getTeamBattingStatus() {
		return teamBattingStatus;
	}
	
	public TeamBowlingStatus getTeamBowlingStatus() {
		return teamBowlingStatus;
	}

	public Player getTopBatsman(int idToIgnore) {
		int highestScore = 0;
		Player highestScorer = null;
		
		for (Entry<Integer, Player> entry : players.entrySet()) {
			if(entry.getKey() == idToIgnore) {
				continue;
			}
			
			if (entry.getValue().getBattingScore().getRuns() > highestScore) {
				highestScore = entry.getValue().getBattingScore().getRuns();
				highestScorer = entry.getValue();
			}
		}

		return highestScorer;
	}

	public Integer getTopBowler(int idToIgnore) {
		int highestWickets = 0;
		int highestScorerId = 0;
		
		Map<Integer, Integer> bowlersToWickets = new HashMap<Integer, Integer>();

		for (Entry<Integer, Player> entry : players.entrySet()) {
			if(entry.getValue().getBattingStatus().equals(BattingStatus.Out)) {
				BattingWicketBean battingWicketBean = entry.getValue().getBattingScore().getBattingWicketBean();
				if(battingWicketBean.getBowlerId() == idToIgnore) {
					continue;
				}
				
				if(!battingWicketBean.getDismisalType().equals(DismisalType.RUNOUT) && !battingWicketBean.getDismisalType().equals(DismisalType.NON_STRIKER_RUNOUT)) {
					if(bowlersToWickets.containsKey(battingWicketBean.getBowlerId())) {
						Integer wicketsForBowler = bowlersToWickets.get(battingWicketBean.getBowlerId());
						bowlersToWickets.put(battingWicketBean.getBowlerId(), ++wicketsForBowler);
					} else {
						bowlersToWickets.put(battingWicketBean.getBowlerId(), 1);
					}
				}
			}
		}
		
		//No wickekts taken so need to find the bowler with the lowest runs.
		if(highestWickets == 0) {
			//need to return here and go to the other team and get the best bowler and that come back here and add the extras for that bowler. 
			//or create a util class that takes both teams and returns this info.
			//or throw error and do this check in Game.  - it should be ok there.
		}
		
		for (Entry<Integer, Integer> entry : bowlersToWickets.entrySet()) {
			if(entry.getValue() > highestWickets) {
				highestWickets = entry.getValue();
				highestScorerId = entry.getKey();
			} 
		}
		
		return highestScorerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + battingSlot;
		result = prime * result + lastBowlerId;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((teamBattingStatus == null) ? 0 : teamBattingStatus.hashCode());
		result = prime * result + ((teamBowlingStatus == null) ? 0 : teamBowlingStatus.hashCode());
		result = prime * result + teamIndex;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (battingSlot != other.battingSlot)
			return false;
		if (lastBowlerId != other.lastBowlerId)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (teamBattingStatus != other.teamBattingStatus)
			return false;
		if (teamBowlingStatus != other.teamBowlingStatus)
			return false;
		if (teamIndex != other.teamIndex)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}
}