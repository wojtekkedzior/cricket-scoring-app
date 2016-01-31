package common;

import android.annotation.SuppressLint;
import game.Player;
import game.Rule;
import game.Team;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("UseSparseArrays")
public class RuleExecutor implements Serializable{
	
	private static final long serialVersionUID = 3627725989450926238L;

	private Map<Integer, Integer> ruleViolations;

	private List<Rule> rules;
	private Team battingTeam;
	private Team bowlingTeam;

	public RuleExecutor(List<Rule> rules, Team bowlingTeam, Team battingTeam) {
		this.rules = rules;
		this.bowlingTeam = bowlingTeam;
		this.battingTeam = battingTeam;
		
		ruleViolations = new HashMap<Integer, Integer>();
	}
	
	public void executeRules () {
		Map<Integer, Integer> bowlerToOvers = new HashMap<Integer, Integer>(); 
		
		Map<Integer, Player> listOfBowlers = bowlingTeam.getPlayers();
		
		for (Integer bowlerId : listOfBowlers.keySet()) {
			int numberOfOverOfBowler = battingTeam.getNumberOfOversByBowler(bowlerId);
			bowlerToOvers.put(bowlerId, numberOfOverOfBowler);
		}
		
		for (Rule rule : rules) {
			for (Integer bowlerId : bowlerToOvers.keySet()) {
				int oversBowled = bowlerToOvers.get(bowlerId);
				
				if(oversBowled >= rule.getMaxOversAllowed()) {
					Player bowler = bowlingTeam.getPlayerById(bowlerId);
					
					if(bowler.getBowlingStatus() == BowlingStatus.BowledOut){
						continue; //this bowler is already bowled out so don't update the rule violations
					}
					
					bowler.setBowlingStatus(BowlingStatus.BowledOut);
					
					if(ruleViolations.containsKey(rule.getRuleNumber())) {
						Integer integer = ruleViolations.get(rule.getRuleNumber());
						ruleViolations.put(rule.getRuleNumber(), integer + 1);
					} else {
						ruleViolations.put(rule.getRuleNumber(), 1);
					}
				}
			}
		}
	}
	
	public Map<Integer, Integer> getRuleViolations() {
		return ruleViolations;
	}

}