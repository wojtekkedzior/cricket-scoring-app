package game;

import java.io.Serializable;

public class Rule implements Comparable<Rule>, Serializable {
	
	private static final long serialVersionUID = -7933221197686315858L;
	
	private Integer ruleNumber;
	private Integer numberOfBowlers;
	private Integer maxOversAllowed;
	
	public Rule(Integer ruleNumber, Integer numberOfBowlers, Integer maxOversAllowed) {
		this.ruleNumber = ruleNumber;
		this.numberOfBowlers = numberOfBowlers;
		this.maxOversAllowed = maxOversAllowed;
	}
	
	public Integer getRuleNumber() {
		return ruleNumber;
	}
	public void setRuleNumber(Integer ruleNumber) {
		this.ruleNumber = ruleNumber;
	}
	public Integer getNumberOfBowlers() {
		return numberOfBowlers;
	}
	public void setNumberOfBowlers(Integer numberOfBowlers) {
		this.numberOfBowlers = numberOfBowlers;
	}
	public Integer getMaxOversAllowed() {
		return maxOversAllowed;
	}
	public void setMaxOversAllowed(Integer maxOversAllowed) {
		this.maxOversAllowed = maxOversAllowed;
	}

	@Override
	public int compareTo(Rule another) {
		//Need the biggest maxOversAllowed first. 
		return another.getMaxOversAllowed() - maxOversAllowed;
	}
}
