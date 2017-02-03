package game;

import java.io.Serializable;

import beans.BattingOverBean;
import beans.BattingWicketBean;

import common.BallType;
import common.BattingStatus;
import common.DismisalType;
import common.TeamBattingStatus;

public class GameProcessor implements Serializable {
	private static final long serialVersionUID = -7324540716052154006L;

	private transient Team battingTeam;
	private OverTracker overTracker;
	private UndoManager undoManager;
	
	private Game game; 
	
	public GameProcessor(Game game) {
		this.game = game;
		battingTeam = game.getBattingTeam();
		if(game.isResuming()) {
			overTracker = new OverTracker(game.getGameSettings().getMaxOvers(), game.getOverNumber(), game.getBallsLeft());
		} else {
			overTracker = new OverTracker(game.getGameSettings().getMaxOvers());
		}
		undoManager = new UndoManager(game, overTracker);
	}
	
	public void inningsChange(TeamBattingStatus battingStatus) { // or rename this to Innings processor and have one PER innings - seems like a better idea
		//record a summary of current innings in a new class
		
		//then change the teams over
		game.changeTeamsAround(battingStatus);
		
		battingTeam = game.getBattingTeam();
	}
	
	//this needs to be called EVERYTIME the game object changes otherwise we are looking at an old game object
	public void updateGame(Game game) {
		this.game = game;
		battingTeam = game.getBattingTeam();
	}
	
	public void delivery(BallType ballType, int runs, float rawX, float rawY) {
		undoManager.createAction(ballType, runs);
		Player bowler =  game.getBowlingTeam().getCurrentBowler();
		BattingOverBean battingOverBean = battingTeam.getStriker().getBattingScore().getBattingOverBean(overTracker.getOverNumber(), bowler.getId());
		
		switch (ballType) {
		case WIDE:
			bowler.getBowlingScore().addWideAndExtras(runs); //any runs ran of a wide count a wideRuns.
			break;
		case NO_BALL_EXTRA:
			bowler.getBowlingScore().addNoBallAndExtras(runs);
			// 0 runs to the batsman as it counts as a ball faced
			battingOverBean.addRun(0, rawX, rawY);
			//any run that the batsman run are added to NoBallRuns
			break;
		case NO_BALL_RUN:
			//runs will default to 0 in the scoring screen so if u call select NO_BALL_RUN with 0 runs its the same as calling NO_BALL_EXTRA with 0 runs.
			//Here a no ball was hit for runs of the bat. so batsman gets thoese and bowler gets 1 no ball run
			bowler.getBowlingScore().addNoBallAndExtras(0); 
			battingOverBean.addRun(runs, rawX, rawY);
			break;
		case BYE:
			bowler.getBowlingScore().addBye(runs);
			battingOverBean.addRun(0, rawX, rawY);
			break;
		case LEG_BYE:
			bowler.getBowlingScore().addLegBye(runs);
			battingOverBean.addRun(0, rawX, rawY);
			break;
		case DEAD_BALL:
			break;
		default: //DEAD_BALL, DOT_BALL, SCORING, WICKET
			battingOverBean.addRun(runs, rawX, rawY);
			break;
		}

		updateBatsmanPositions(runs);
		completeScore(ballType);
	}
	
	private void updateBatsmanPositions(int runs) {
		if (runs == 1 || runs == 3 || runs == 5) {
			battingTeam.alternateStriker();
		}
	}
	
	private void completeScore(BallType ballType) {
		overTracker.updateOver(ballType);
	}

	public void dismisal(DismisalType dismisalType, Player fielder, int runs) {
		Player bowler =  game.getBowlingTeam().getCurrentBowler();
		undoManager.createWicketAction(dismisalType, 0);
		
		if (dismisalType.equals(DismisalType.RUNOUT) || dismisalType.equals(DismisalType.NON_STRIKER_RUNOUT)) {
			throw new IllegalArgumentException("Dont use this method for run outs");
		}

		BattingWicketBean battingWicketBean = new BattingWicketBean(bowler.getId(), dismisalType, overTracker.getOverNumber(), overTracker.getBallNumber(), fielder == null ? 0 : fielder.getId());
		BattingOverBean battingOverBean = battingTeam.getStriker().getBattingScore().getBattingOverBean(overTracker.getOverNumber(), bowler.getId());
		battingOverBean.addRun(0, 0, 0); // rule say any runs from a catch are voided, hence 0 here

		if (fielder != null) { //if a fielder is required for a dismissal type, the UI catches this
			battingWicketBean.setFielderId(fielder.getId());
		}

		battingTeam.getStriker().getBattingScore().setBattingWicketBean(battingWicketBean);
		
		Player newBatsman = battingTeam.getBatsmanByStatus(BattingStatus.NewBatsman);
		if(newBatsman == null) {
			 newBatsman = battingTeam.getBatsmanByStatus(BattingStatus.NewBatsmanOffStrike);
			 if(newBatsman == null) {
				 battingTeam.getStriker().setBattingStatus(BattingStatus.Out);	
				 //all out
				 completeScore(BallType.WICKET);
				 return;
			 }
			 battingTeam.getStriker().setBattingStatus(BattingStatus.Out);	
			 battingTeam.getNonStriker().setBattingStatus(BattingStatus.Striker);
			 newBatsman.setBattingStatus(BattingStatus.NonStriker); 
		} else {
			battingTeam.getStriker().setBattingStatus(BattingStatus.Out);
			newBatsman.setBattingStatus(BattingStatus.Striker); 
		}
		
		completeScore(BallType.WICKET);
	}

	public void stumpedOfWide(Player fielder, int ballNumber, int runs) { //hard code this to 1 run. u can't get more than 1 wide when stumped of a wide
		Player bowler =  game.getBowlingTeam().getCurrentBowler();
		undoManager.createStumpedOfWideAction(runs);

		BattingWicketBean battingWicketBean = new BattingWicketBean(bowler.getId(), DismisalType.STUMPED, overTracker.getOverNumber(), ballNumber, fielder.getId());
		BattingOverBean battingOverBean = battingTeam.getStriker().getBattingScore().getBattingOverBean(overTracker.getOverNumber(), bowler.getId());
		battingOverBean.addRun(0, 0, 0); // rule say any runs from a catch are voided, hence 0 here

		if (fielder != null) { //if a fielder is required for a dismissal type, the UI catches this
			battingWicketBean.setFielderId(fielder.getId());
		}

		battingTeam.getStriker().getBattingScore().setBattingWicketBean(battingWicketBean);
		
		bowler.getBowlingScore().addWideAndExtras(runs); //any runs ran of a wide count a wideRuns.
		completeScore(BallType.WIDE);
	}
	
	public void runOut(Player fielder, int runs, float rawX, float rawY, BallType ballType, DismisalType dismisalType) {
		Player bowler =  game.getBowlingTeam().getCurrentBowler();
		undoManager.createRunOutAction(dismisalType, runs, ballType);
		
		BattingOverBean battingOverBean = battingTeam.getStriker().getBattingScore().getBattingOverBean(overTracker.getOverNumber(), bowler.getId());
		//battingOverBean - is set later on. during wides there is no battingOverBean;
		
		switch (ballType) {
		case WIDE:
			bowler.getBowlingScore().addWideAndExtras(runs); // any runs ran of a wide count a wideRuns.
			break;
		case NO_BALL_EXTRA:
			// any runs that are NOT of the bat are added to NoBallRuns
			bowler.getBowlingScore().addNoBallAndExtras(runs);
			// 0 runs to the batsman as it counts as a ball faced
			battingOverBean.addRun(0, rawX, rawY);
			break;
		case NO_BALL_RUN:
			//runs will default to 0 in the scoring screen so if u call select NO_BALL_RUN with 0 runs its the same as calling NO_BALL_EXTRA with 0 runs.
			//Here a no ball was hit for runs of the bat. so batsman gets thoese and bowler gets 1 no ball run
			bowler.getBowlingScore().addNoBallAndExtras(0); 
			battingOverBean.addRun(runs, rawX, rawY);
			break;
		case BYE:
			bowler.getBowlingScore().addBye(runs);
			battingOverBean.addRun(0, 0, 0);
			break;
		case LEG_BYE:
			bowler.getBowlingScore().addLegBye(runs);
			battingOverBean.addRun(0, 0, 0);
			break;
		case SCORING:
			battingOverBean.addRun(runs, rawX, rawY);
			break;
		default:
			throw new IllegalArgumentException("Unsupported Ball Type: " + ballType.getBallTypeName());
		}

		BattingWicketBean battingWicketBean = new BattingWicketBean(bowler.getId(), dismisalType, overTracker.getOverNumber(), overTracker.getBallNumber(), fielder.getId());

		Player newBatsman = battingTeam.getBatsmanByStatus(BattingStatus.NewBatsman);

		switch (dismisalType) {
		case NON_STRIKER_RUNOUT:
			battingTeam.getNonStriker().getBattingScore().setBattingWicketBean(battingWicketBean);
			battingTeam.getNonStriker().setBattingStatus(BattingStatus.Out);
			
			if(newBatsman == null) {
				 newBatsman = battingTeam.getBatsmanByStatus(BattingStatus.NewBatsmanOffStrike);
				 if(newBatsman == null) {
					 //all out
					 if(ballType == BallType.WIDE) {
						 completeScore(BallType.WIDE);
					 } else if (ballType == BallType.NO_BALL_EXTRA)  {
						 completeScore(BallType.NO_BALL_EXTRA);
					 } else if (ballType == BallType.NO_BALL_RUN)  {
						 completeScore(BallType.NO_BALL_RUN);
					 } else {
						 completeScore(BallType.WICKET);
					 }
					 return;
				 }
				 newBatsman.setBattingStatus(BattingStatus.NonStriker); 
			} else {
				battingTeam.getStriker().setBattingStatus(BattingStatus.NonStriker);
				newBatsman.setBattingStatus(BattingStatus.Striker); 
			}
			
			break;
		case RUNOUT:
			battingTeam.getStriker().getBattingScore().setBattingWicketBean(battingWicketBean);
			battingTeam.getStriker().setBattingStatus(BattingStatus.Out);
			
			if(newBatsman == null) {
				 newBatsman = battingTeam.getBatsmanByStatus(BattingStatus.NewBatsmanOffStrike);
				 if(newBatsman == null) {
					 //all out
					 if(ballType == BallType.WIDE) {
						 completeScore(BallType.WIDE);
					 } else if (ballType == BallType.NO_BALL_EXTRA)  {
						 completeScore(BallType.NO_BALL_EXTRA);
					 } else if (ballType == BallType.NO_BALL_RUN)  {
						 completeScore(BallType.NO_BALL_RUN);
					 } else {
						 completeScore(BallType.WICKET);
					 }
					 return;
				 }
				 battingTeam.getNonStriker().setBattingStatus(BattingStatus.Striker);
				 newBatsman.setBattingStatus(BattingStatus.NonStriker); 
			} else {
				newBatsman.setBattingStatus(BattingStatus.Striker); 
			}
			
			break;
		default:
			throw new IllegalArgumentException("This method is used only for runouts");
		}
		
		completeScore(ballType);
	}
	
	protected OverTracker getOverTracker() {
		return overTracker;
	}
	
	protected void setOverTracker(OverTracker overTracker) {
		this.overTracker = overTracker;
	}

	/**
	 * Bowling score does not need a stack because we can just pass it a negative number of runs to Undo batting score uses a stack so thats
	 * why we need to pop it
	 */
	public void undo() {
		undoManager.undo();
	}

	public boolean isOverFinished() {
		return overTracker.isOverFinished();
	}

	public void startNewOver() {
		battingTeam.alternateStriker();
		overTracker.startNewOver();
	}

	public void startNewInnings() {
		overTracker.startNewInnings();		
	}

	public int getBallsLeft() {
		return overTracker.getBallsLeft();
	}

	public boolean isFirstOver() {
		return overTracker.isFirstOver();
	}

	public int getBallsBowled() {
		return overTracker.getBallsBowled();
	}

	public int getOverNumber() {
		return overTracker.getOverNumber();
	}

	public String getOverAsString() {
		return overTracker.getOverAsString();
	}

	public String getOverAsStringPercentage() {
		return overTracker.getOverAsStringPercentage();
	}
	
}