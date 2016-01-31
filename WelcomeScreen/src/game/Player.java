package game;

import java.io.Serializable;

import common.BattingStatus;
import common.BowlingStatus;

public class Player implements Comparable<Player>, Serializable {
	
	private static final long serialVersionUID = 389966292834334942L;

	private String name = null;
	private int id = 0;
	
	private BattingStatus battingStatus;
	private BowlingStatus bowlingStatus;
	
	private final BattingScore battingScore;
	private final BowlingScore bowlingScore;

	public Player(String name, int id) {
		this.name = name;
		this.id = id;
		battingScore = new BattingScore();
		bowlingScore = new BowlingScore();
		
		battingStatus = BattingStatus.Available;
		bowlingStatus = BowlingStatus.Available;
	}
	
	public Player() {
		battingScore = new BattingScore();
		bowlingScore = new BowlingScore();
		
		battingStatus = BattingStatus.Available;
		bowlingStatus = BowlingStatus.Available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BattingScore getBattingScore() {
		return battingScore;
	}

	public BowlingScore getBowlingScore() {
		return bowlingScore;
	}

	public String getName() {
		return name;
	}
	
	public BattingStatus getBattingStatus() {
		return battingStatus;
	}

	public void setBattingStatus(BattingStatus battingStatus) {
		this.battingStatus = battingStatus;
	}

	public BowlingStatus getBowlingStatus() {
		return bowlingStatus;
	}

	public void setBowlingStatus(BowlingStatus bowlingStatus) {
		this.bowlingStatus = bowlingStatus;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((battingStatus == null) ? 0 : battingStatus.hashCode());
		result = prime * result + ((bowlingStatus == null) ? 0 : bowlingStatus.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (battingStatus != other.battingStatus)
			return false;
		if (bowlingStatus != other.bowlingStatus)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Player another) {
		return getBattingScore().getBattingSlot() - another.getBattingScore().getBattingSlot();
	}
	
	@Override
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
}