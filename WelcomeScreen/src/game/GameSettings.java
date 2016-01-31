package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.GameType;

public class GameSettings implements Serializable {
	
	private static final long serialVersionUID = -4763551011639144443L;
	
	private GameType gameType;
	private List<Rule> rules;
	
	public GameSettings (GameType gameType) {
		rules = new ArrayList<Rule>();
		this.gameType = gameType;
		
		switch(gameType) {
		case Custom:
			break;
		case FortyForty:
			rules.add(new Rule(1, 11, 10));
			break;
		case TwentyTwenty:
			rules.add(new Rule(1, 11, 4));
			break;
		default:
			break;
		}
	}
	
	public GameType getGameType() {
		return gameType;
	}
	
	public List<Rule> getRules() {
		Collections.sort(rules);
		return rules;
	}

	public void addToRules(Rule rule) {
		for (Rule definedRule : rules) {
			if(definedRule.getRuleNumber() == rule.getRuleNumber()) {
				throw new IllegalArgumentException("you cant have the same rule number for two rules");
			}
		}
		
		rules.add(rule);
	}

	public int getMaxOvers() {
		switch(gameType) {
		case Custom:
			return 1; //Todo
		case FortyForty:
			return 40;
		case TwentyTwenty:
			return 20;
		default:
			return 0;
		} 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
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
		GameSettings other = (GameSettings) obj;
		if (gameType != other.gameType)
			return false;
		if (rules == null) {
			if (other.rules != null)
				return false;
		} else if (!rules.equals(other.rules))
			return false;
		return true;
	}
	
}
