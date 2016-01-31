package game;


import java.io.Serializable;

import android.annotation.SuppressLint;

import common.TeamBattingStatus;
import common.TeamBowlingStatus;

public class Game implements Serializable {

	public static final int TEAM_ONE = 1;
	public static final int TEAM_TWO = 2;

	private static final long serialVersionUID = 6518184082427753120L;

	private Team team1;
	private Team team2;

	private GameSettings gameSettings;
	private GameStatus gameStatus;
	
	private String overSummary;
	private int overNumber;
	private int ballsLeft;
	private boolean resuming;
	

	@SuppressLint("UseSparseArrays")
	public Game() {
		team1 = new Team(TEAM_ONE);
		team2 = new Team(TEAM_TWO);
		gameStatus = new GameStatus();
	}

	public Team getTeam(int teamIndex) {
		if (team1.getTeamIndex() == teamIndex) {
			return team1;
		} else { 
			return team2;
		}
	}
	
	public Team getTeamByName(String teamName) {
		if (team1.getTeamName() != null && team1.getTeamName().equals(teamName)) {
			return team1;
		} else if (team2.getTeamName() != null && team2.getTeamName().equals(teamName)) {
			return team2;
		} else {
			throw new IllegalArgumentException("No team found with name: " + teamName);
		}
	}

	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}
	
	public GameSettings getGameSettings() {
		return gameSettings;
	}
	
	public Team getBattingTeam() {
		return getTeam(TEAM_ONE).getTeamBattingStatus().equals(TeamBattingStatus.Batting) == true ? getTeam(TEAM_ONE) : getTeam(TEAM_TWO);
	}
	
	public Team getBowlingTeam() {
		return getTeam(TEAM_ONE).getTeamBowlingStatus().equals(TeamBowlingStatus.Bowling) == true ? getTeam(TEAM_ONE) : getTeam(TEAM_TWO);
	}
	
	public void setTeam(Team team) {
		if (team1.getTeamIndex() == team.getTeamIndex()) {
			 team1 = team;
		} else { 
			team2 = team;
		}
	}
	
	//batting status - allout or overs finished
	public void changeTeamsAround(TeamBattingStatus battingStatus) {
		Team battingTeam = getBattingTeam();
		Team bowlingTeam = getBowlingTeam();
		
		battingTeam.setTeamBattingStatus(battingStatus);
		battingTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);
	
		bowlingTeam.setTeamBattingStatus(TeamBattingStatus.Batting);
		bowlingTeam.setTeamBowlingStatus(TeamBowlingStatus.BowledOut);
		
		setTeam(battingTeam);
		setTeam(bowlingTeam);
	}
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public int getTarget() {
		return gameStatus.getTarget();
	}
	
	public String getGameDescriptnio() {
		return team1.getTeamName() + " vs'" + team2.getTeamName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameSettings == null) ? 0 : gameSettings.hashCode());
		result = prime * result + ((gameStatus == null) ? 0 : gameStatus.hashCode());
		result = prime * result + ((team1 == null) ? 0 : team1.hashCode());
		result = prime * result + ((team2 == null) ? 0 : team2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Game other = (Game) obj;
		if (gameSettings == null) {
			if (other.gameSettings != null)
				return false;
		} else if (!gameSettings.equals(other.gameSettings))
			return false;
		if (gameStatus == null) {
			if (other.gameStatus != null)
				return false;
		} else if (!gameStatus.equals(other.gameStatus))
			return false;
		if (team1 == null) {
			if (other.team1 != null)
				return false;
		} else if (!team1.equals(other.team1))
			return false;
		if (team2 == null) {
			if (other.team2 != null)
				return false;
		} else if (!team2.equals(other.team2))
			return false;
		return true;
	}

	public void setOverSummary(String overSummary) {
		this.overSummary = overSummary;
	}

	public String getOverSummary() {
		return overSummary;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

	public int getBallsLeft() {
		return ballsLeft;
	}

	public void setBallsLeft(int ballsLeft) {
		this.ballsLeft = ballsLeft;
	}

	public boolean isResuming() {
		return resuming;
	}

	public void setResuming(boolean resuming) {
		this.resuming = resuming;
	}
}