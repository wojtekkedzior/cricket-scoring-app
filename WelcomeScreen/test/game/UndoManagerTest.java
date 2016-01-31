package game;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import other.GameData;
import beans.Action;

import common.BallType;
import common.DismisalType;

public class UndoManagerTest  {
	
	protected UndoManager undoManager;
	protected OverTracker overTracker = new OverTracker(20);
	protected Game game;

	@Before
	public void setUp() {
		GameData testData = new GameData();
		game = testData.getGame(true);
		undoManager = new UndoManager(game, overTracker);
	}
	
	@Test
	public void testGetLatestGameAction() {
		undoManager.createAction(BallType.DOT_BALL, 0);
		assertNotNull(undoManager.getLatestGameAction());
	}
	
	@Test
	public void testUpdateStack() {
		undoManager.createAction(BallType.DOT_BALL, 0);
		overTracker.updateOver(BallType.DOT_BALL);
		assertNotNull(undoManager.getLatestGameAction());
		
		undoManager.createAction(BallType.DOT_BALL, 0);
		overTracker.updateOver(BallType.DOT_BALL);
		
		Action action = undoManager.getLatestGameAction();
		assertEquals(5, action.getBallsLeft());
		assertEquals(BallType.DOT_BALL, action.getBallType());
		assertEquals(game.getBattingTeam().getStriker(), action.getBatsman());
		assertEquals(null, action.getDismisalType());
		assertEquals(null, action.getFielder());
		assertEquals(0, action.getOverNumber());
	}
	
	@Test
	public void testUpdateStackWithTwoActions() {
		undoManager.createAction(BallType.DOT_BALL, 0);
		overTracker.updateOver(BallType.DOT_BALL);
		
		undoManager.createAction(BallType.SCORING, 1);
		overTracker.updateOver(BallType.SCORING);
		
		Action action = undoManager.getLatestGameAction();
		assertEquals(5, action.getBallsLeft());
		assertEquals(BallType.SCORING, action.getBallType());
		assertEquals(game.getBattingTeam().getStriker(), action.getBatsman());
		assertEquals(null, action.getDismisalType());
		assertEquals(null, action.getFielder());
		assertEquals(0, action.getOverNumber());
	}
	
	@Test
	public void testProcessBallStoreCorrectBallType() {
		undoManager.createAction(BallType.WIDE, 0);
		overTracker.updateOver(BallType.WIDE);
		assertEquals(BallType.WIDE, undoManager.getLatestGameAction().getBallType());
		
		undoManager.createAction(BallType.NO_BALL_EXTRA, 1);
		overTracker.updateOver(BallType.NO_BALL_EXTRA);
		assertEquals(BallType.NO_BALL_EXTRA, undoManager.getLatestGameAction().getBallType());
		
		undoManager.createAction(BallType.NO_BALL_RUN, 1);
		overTracker.updateOver(BallType.NO_BALL_RUN);
		assertEquals(BallType.NO_BALL_RUN, undoManager.getLatestGameAction().getBallType());
		
		undoManager.createAction(BallType.BYE, 1);
		overTracker.updateOver(BallType.BYE);
		assertEquals(BallType.BYE, undoManager.getLatestGameAction().getBallType());
		
		undoManager.createAction(BallType.LEG_BYE, 1);
		overTracker.updateOver(BallType.LEG_BYE);
		assertEquals(BallType.LEG_BYE, undoManager.getLatestGameAction().getBallType());
	}

	@Test
	public void testProcessDismisalStoreCorrectDismisalType() {
		undoManager.createRunOutAction(DismisalType.RUNOUT, 0, BallType.SCORING);
		overTracker.updateOver(BallType.WICKET);
		Action gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.RUNOUT, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		
		undoManager.createWicketAction(DismisalType.STUMPED, 0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.STUMPED, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());

		undoManager.createWicketAction(DismisalType.BOWLED, 0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.BOWLED, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		
		undoManager.createWicketAction(DismisalType.CAUGHT, 0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.CAUGHT, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		
		undoManager.createWicketAction(DismisalType.LBW, 0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.LBW, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		
		undoManager.createWicketAction(DismisalType.HIT_WICKET, 0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.HIT_WICKET, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		
		undoManager.createStumpedOfWideAction(0);
		overTracker.updateOver(BallType.WICKET);
		gameAction = undoManager.getLatestGameAction();
		assertEquals(DismisalType.STUMPED, gameAction.getDismisalType());
		assertEquals(BallType.WICKET, gameAction.getBallType());
		assertEquals(BallType.WIDE, gameAction.getBallType2());
	}
	
	@Test
	public void testCanUndo() {
		assertFalse(undoManager.canUndo());
		undoManager.createAction(BallType.DOT_BALL, 0);
		assertTrue(undoManager.canUndo());
	}
}