package game;

import java.io.Serializable;
import java.util.Stack;

import beans.Action;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;

public class UndoManager implements Serializable  {

	private static final long serialVersionUID = 2495506427315765827L;

	private Stack<Action> latestGameAction;
	
	private Game game;
	private OverTracker overTracker;
	
	public UndoManager(Game game, OverTracker overTracker) {
		this.game = game;
		this.overTracker = overTracker;
		latestGameAction = new Stack<Action>();
	}
	
	public void createAction(BallType ballType, int runs) {
		Action action = new Action(game.getBowlingTeam().getCurrentBowler(), overTracker.getOverNumber());
		action.setBatsman(game.getBattingTeam().getStriker());
		action.setBallType(ballType);
		action.setNonStriker(game.getBattingTeam().getNonStriker());
		action.setRunsFromExtras(runs); 
		action.setBallsLeft(overTracker.getBallsLeft());
		action.setOverNumber(overTracker.getOverNumber());
		updateStack(action);
	}
	
	public void createWicketAction(DismisalType dismisalType, int runs) {
		Action action = new Action(game.getBowlingTeam().getCurrentBowler(), overTracker.getOverNumber());
		action.setBallType(BallType.WICKET);

		action.setDismisalType(dismisalType);
		action.setBatsman(game.getBattingTeam().getStriker());
		action.setNonStriker(game.getBattingTeam().getNonStriker());
		action.setRunsFromExtras(runs); 
		action.setBallsLeft(overTracker.getBallsLeft());
		action.setOverNumber(overTracker.getOverNumber());
		updateStack(action);
	}
	
	public void createStumpedOfWideAction(int runs) {
		Action action = new Action(game.getBowlingTeam().getCurrentBowler(), overTracker.getOverNumber());
		action.setBallType(BallType.WICKET);
		
		action.setBatsman(game.getBattingTeam().getStriker());
		action.setDismisalType(DismisalType.STUMPED);
		action.setBallType2(BallType.WIDE);
		action.setBallType(BallType.WICKET);
		action.setNonStriker(game.getBattingTeam().getNonStriker());
		action.setRunsFromExtras(runs);
		action.setBallsLeft(overTracker.getBallsLeft());
		action.setOverNumber(overTracker.getOverNumber());
		updateStack(action);
	}
	
	public void createRunOutAction(DismisalType dismisalType, int runs, BallType ballType) {
		Action action = new Action(game.getBowlingTeam().getCurrentBowler(), overTracker.getOverNumber());
		action.setBallType(BallType.WICKET);
		
		action.setBatsman(game.getBattingTeam().getStriker());
		action.setDismisalType(dismisalType);
		action.setNonStriker(game.getBattingTeam().getNonStriker());
		action.setBallType2(ballType);
		action.setRunsFromExtras(runs);
		action.setBallsLeft(overTracker.getBallsLeft());
		action.setOverNumber(overTracker.getOverNumber());
		
		updateStack(action);
	}

	private void updateStack(Action action) {
		if (!latestGameAction.isEmpty()) {
			latestGameAction.pop();
		}

		latestGameAction.push(action);
	}
	
	protected boolean canUndo() {
		return !latestGameAction.isEmpty();
	}
	
	protected Action getLatestGameAction() {
		return latestGameAction.pop();
	}

	public void undo() {
		if(!canUndo()) {
			return;
		}
		
		Action action = getLatestGameAction();

		overTracker.setNumberOfBallsLeft(action.getBallsLeft());

		BattingScore battingScore = action.getBatsman().getBattingScore();
		BowlingScore bowlingScore = action.getBowler().getBowlingScore();
			int overNumber = action.getOverNumber();
		overTracker.setOverNumber(overNumber);
		int bowlerId = action.getBowler().getId();

		switch (action.getBallType()) {
		case BYE:
			bowlingScore.addBye(action.getRunsFromExtras()); // runs passed are negative
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			break;
		case DEAD_BALL:
			// TODO: not used for now - no UI - will add it later 
			break;
		case DOT_BALL:
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			break;
		case LEG_BYE:
			bowlingScore.addLegBye(action.getRunsFromExtras());
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			break;
		case NO_BALL_EXTRA:
			//undo the dot ball the batsman gets
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			//remove all runs against the bowler (1 + any extras)
			bowlingScore.undoNoBall(action.getRunsFromExtras());
			break;
		case NO_BALL_RUN:
			//undo any runs the batsman got 
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			//remove the 1 run against the bowler
			bowlingScore.undoNoBall(0);
			break;
		case SCORING:
			battingScore.getBattingOverBean(overNumber, bowlerId).undo();
			break;
		case WICKET:
			switch (action.getDismisalType()) {
			case NON_STRIKER_RUNOUT:
				BattingScore nonStrikerBattingScore = action.getNonStriker().getBattingScore();
				nonStrikerBattingScore.setBattingWicketBean(null);
				battingScore.getBattingOverBean(overNumber, bowlerId).undo();
				break;
			default:
				//All dismissal types will do this:
				battingScore.setBattingWicketBean(null);
				battingScore.getBattingOverBean(overNumber, bowlerId).undo();
				break;
			}
			
			//Its a wicket, lets check if it occurred from an extra
			if(action.getBallType2() != null) {
				switch(action.getBallType2()) {
				case BYE:
					bowlingScore.addBye(action.getRunsFromExtras()); // runs passed are negative
					break;
				case LEG_BYE:
					bowlingScore.addLegBye(action.getRunsFromExtras());
					break;
				case NO_BALL_EXTRA:
					//the batting over bean has already been done above
					bowlingScore.undoNoBall(action.getRunsFromExtras());
					break;
				case NO_BALL_RUN:
					//the batting over bean has already been done above
					bowlingScore.undoNoBall(0);
					break;
				case WIDE:
					bowlingScore.undoWide(action.getRunsFromExtras()); // specialm case for wides
					break;
				case SCORING:
					//Allowed
					break;
				default:
					throw new IllegalArgumentException("A wicket cannot fall on this ball type: " + action.getBallType2());
				}
				
				//reset this field because the action has a life span of an over. 
				action.setBallType2(null);
			}
			
			break;
		case WIDE:
			bowlingScore.undoWide(action.getRunsFromExtras()); // specialm case for wides
			break;
		default:
			break;
		}
		
		if(game.getBattingTeam().getStriker() != null) { //can only happen when last batsman is dismissed
			game.getBattingTeam().getStriker().setBattingStatus(BattingStatus.Available); 
		}
		
		if(game.getBattingTeam().getNonStriker() != null) {//can only happen when last batsman is dismissed
			game.getBattingTeam().getNonStriker().setBattingStatus(BattingStatus.Available);
		}
		
		game.getBattingTeam().setBattingStatusForBatsman(action.getBatsman().getName(), BattingStatus.Striker);
		game.getBattingTeam().setBattingStatusForBatsman(action.getNonStriker().getName(), BattingStatus.NonStriker);
	}
}