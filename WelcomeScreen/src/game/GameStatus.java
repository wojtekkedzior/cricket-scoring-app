package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Keys;

public class GameStatus implements Serializable{
	private static final long serialVersionUID = 325487136746313984L;
	
	private Map<Integer, String> gameSummary;
	private int target = 0;
	
	public GameStatus() {
		gameSummary = new HashMap<Integer, String>();
	}

	private void getTotals(Team team1, Team team2, Integer teamIndexSummary, Integer topBatsman, Integer secondBatsman, Integer topBowler, Integer secondBowler) {
		String teamSummary = team1.getTeamName() + " : " + (team1.getRunsScored() + team2.getAllExtras()) + "/" + team1.getWicketsLost();
		
		gameSummary.put(teamIndexSummary, teamSummary);
		
		Player topScorer1 = team1.getTopBatsman(-1); 
		if(topScorer1 != null) {
			String topScore1String = topScorer1.getName() + " " + topScorer1.getBattingScore().getRuns() + "/" + topScorer1.getBattingScore().getBallsFaced();
			gameSummary.put(topBatsman, topScore1String);
		}
		
		Player topScorer2 = team1.getTopBatsman(topScorer1 == null ? -1 : topScorer1.getId()); 
		if(topScorer2 != null) {
			String topScore2String = topScorer2.getName() + " " + topScorer2.getBattingScore().getRuns() + "/" + topScorer2.getBattingScore().getBallsFaced();
			gameSummary.put(secondBatsman, topScore2String);
		}
		
		//The reason for a clone is that we remove players, so that will effect any totals calculated later on.
		int bestBowler = findBestBowler(team1, team2, topBowler, -1);
		findBestBowler(team1, team2, secondBowler, bestBowler);
	}
	
	private int findBestBowler(Team team1, Team team2, Integer key, int idToIgnore) {
		//try to get the best bowler by wickets first
		int bestBowerId = team1.getTopBowler(idToIgnore); 

		if(bestBowerId == 0) { //No bowler took a wicket. need to find the best bowler by economy rate
			int highestRunsGiven = 0;
			
			ArrayList<Player> allBowlers = new ArrayList<Player>();
			
			for (Player player : team2.getPlayersAsList()) {
				if(player.getId() != idToIgnore) { //remove the previous best bowler, if any
					allBowlers.add(player);
				}
			}
			
			for (Player bowler : allBowlers) {
				int runsGiven = team1.getRunsGivenByBowlerAgainstAllBatsman(bowler.getId());
				int allExtras = bowler.getBowlingScore().getAllExtras();
				if((runsGiven + allExtras) >= highestRunsGiven) {
					highestRunsGiven = runsGiven + allExtras;
					bestBowerId = bowler.getId();
				}
			}
		} 
		
		Player topbowler1 = team2.getPlayerById(bestBowerId);
		
		if(topbowler1 != null) {
			StringBuffer strBuff = new StringBuffer();
			strBuff.append(topbowler1.getName() + "  "); 
			strBuff.append(team1.getNumberOfOversByBowler(bestBowerId) + "-");
			strBuff.append(team1.getMaidensForBowler(bestBowerId) + "-");
			strBuff.append(team1.getRunsGivenByBowlerAgainstAllBatsman(bestBowerId) + topbowler1.getBowlingScore().getAllExtras() + "-");
			strBuff.append(team1.getWicketsForBowler(bestBowerId));
			gameSummary.put(key, strBuff.toString());
			return topbowler1.getId();
		}

		return -1;
	}

	public String get(Integer key) {
		return gameSummary.get(key);
	}
	
	public void add(Integer key, String value) {
		//check if key exists already and then throw and error?
		gameSummary.put(key, value);
	}
	
	public void createGameSummary(Team team1, Team team2) {
		getTotals(team1, team2, Keys.TEAM_1_SUMMARY, Keys.TEAM_1_TOP_BATSMAN, Keys.TEAM_1_SECOND_BATSMAN, Keys.TEAM_2_TOP_BOWLER, Keys.TEAM_2_SECOND_BOWLER);
		getTotals(team2, team1, Keys.TEAM_2_SUMMARY, Keys.TEAM_2_TOP_BATSMAN, Keys.TEAM_2_SECOND_BATSMAN, Keys.TEAM_1_TOP_BOWLER, Keys.TEAM_1_SECOND_BOWLER);
		
		getWinningTeam(team1, team2);
	}

	private void getWinningTeam(Team team1, Team team2) {
		int team1Runs = team1.getRunsScored() + team2.getAllExtras();
		int team1WicketFallen = team1.getWicketsLost();
		
		int team2Runs = team2.getRunsScored() + team1.getAllExtras();
//		int team2WicketFallen = team2.getWicketsLost();
		
		if(team1Runs > team2Runs) { //Team1 wins
			gameSummary.put(Keys.WINNING_TEAM, team1.getTeamName() + " win by " + (10 - team1WicketFallen) + " wickets");
		} else if(team1Runs == team2Runs) { //Draw
			gameSummary.put(Keys.WINNING_TEAM, "Draw: " + team1Runs + " runs");
		} else if(team1Runs < team2Runs) { //team2 wins
			gameSummary.put(Keys.WINNING_TEAM, team2.getTeamName() + " win by " + (team2Runs - team1Runs) + " runs");
		}
	}

	public boolean isGameOver() {
		return false;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameSummary == null) ? 0 : gameSummary.hashCode());
		result = prime * result + target;
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
		GameStatus other = (GameStatus) obj;
		if (gameSummary == null) {
			if (other.gameSummary != null)
				return false;
		} else if (!gameSummary.equals(other.gameSummary))
			return false;
		if (target != other.target)
			return false;
		return true;
	}

//		 Team team2Clone = null;
//		
//		   try {
//			final ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
//			    final ObjectOutputStream oos = new ObjectOutputStream(baos);
//			    oos.writeObject(team2);
//			    oos.close();
//
//			    final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
//			    team2Clone = (Team) ois.readObject();
//		} catch (StreamCorruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OptionalDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	
	
}
