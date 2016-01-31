package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import other.GameData;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.Keys;
import common.TeamBattingStatus;

public class GameStatusTest {
	
	private GameStatus gameStatus;
	private Game game;
	private GameData testData = new GameData();

	@Before
	public void setUp() throws Exception {
		gameStatus = new GameStatus();
		game = testData.getGame(true);
	}

	@Test
	public void testGameStatus() {
		gameStatus = new GameStatus();
		assertNull(gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertNull(gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertNull(gameStatus.get(Keys.TEAM_1_TOP_BATSMAN));
		assertNull(gameStatus.get(Keys.TEAM_1_SECOND_BATSMAN));
		
		assertNull(gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertNull(gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertNull(gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		assertNull(gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));
		
		assertNull(gameStatus.get(Keys.TEAM_2_TOP_BOWLER));
		assertNull(gameStatus.get(Keys.TEAM_2_SECOND_BOWLER));
	}

	@Test
	public void testGet() {
		gameStatus = new GameStatus();
		assertNull(gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertNull(gameStatus.get(Keys.TEAM_2_SUMMARY));
	}

	@Test
	public void testIsGameOver() {
		assertFalse(gameStatus.isGameOver());
	}

	@Test
	public void testSetTarget() {
		assertEquals(0, gameStatus.getTarget());
		gameStatus.setTarget(100);
		assertEquals(100, gameStatus.getTarget());
	}

	@Test
	public void testGetTarget() {
		assertEquals(0, gameStatus.getTarget());
	}
	
	@Test
	public void testCreateGameSummary() {
		GameData testData2 = new GameData();
		Game game2 = testData2.getFullGame();
		gameStatus.createGameSummary(game2.getTeam(1), game2.getTeam(2));
		
		assertEquals("New Zealand : 127/0", gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertEquals("Zimbabwe : 123/8", gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertEquals("Brendon McCullum 81/46", gameStatus.get(Keys.TEAM_1_TOP_BATSMAN));
		assertEquals("Guptill 40/36", gameStatus.get(Keys.TEAM_1_SECOND_BATSMAN));
		
		assertEquals("Brendon Taylor 50/46", gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertEquals("Mutizwa 16/16", gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertEquals("Mills  4-0-15-2", gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		assertEquals("Nathan McCullum  3-0-17-2", gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));
		
		assertEquals("Jarvis  3-0-32-0", gameStatus.get(Keys.TEAM_2_TOP_BOWLER));
		assertEquals("Chigumbura  3-0-28-0", gameStatus.get(Keys.TEAM_2_SECOND_BOWLER));
	}
	
	@Test
	public void testTwoBatsmanHaveSameScore() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		testData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertEquals("New Zealand : 0/0", gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertEquals("Zimbabwe : 2/0", gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertEquals("Masakadza 1/5", gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertEquals("Chibhabha 1/7", gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertEquals("Mills  1-0-2-0", gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		//this doesn't make sense, in a game there will always be a second best bowler (only if the game is abandoned after a few overs)
//		assertEquals("Kane Williamson  0-0-0-0", gameStatus.get(Keys.TEAM_1_SECOND_BOWLER)); 
	}
	
	@Test
	public void testOnlyOneWicketFalls() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		testData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		testData.waller.setBattingStatus(BattingStatus.NewBatsman);
		
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertEquals("Masakadza 1/5", gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertEquals("Chibhabha 1/2", gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertEquals("Bracewell  1-0-0-1", gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		assertEquals("Mills  1-0-2-0", gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));
	}
	
	@Test
	public void testAllOut() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		testData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		testData.brendonTaylor.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0); //masakadza gone
		
		testData.mutizwa.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0); //brendonTalyer gone ...
		
		testData.waller.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);

		testData.coventry.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.chigumbura.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.chakabva.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		testData.utseya.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.rayPrice.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.kyleJarvis.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		//last wicket to fall - no new batsman available
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertNull(gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertNull(gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertEquals("Mills  1-0-0-6", gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		assertEquals("Bracewell  1-0-0-4", gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));
	}
	
	@Test
	public void testLastWicketIsANonStriker() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		testData.mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		testData.brendonTaylor.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0); //masakadza gone
		
		testData.mutizwa.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0); //brendonTalyer gone ...
		
		testData.waller.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);

		testData.coventry.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.chigumbura.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.chakabva.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		testData.utseya.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.rayPrice.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		testData.kyleJarvis.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.BOWLED, null, 0);
		
		//last wicket to fall - no new batsman available
		gameProcessor.runOut(testData.brendonMcCullum, 0, 0,0, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		 
		//TODO wrong. should be someone at least
		assertNull(gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		assertNull(gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));
		
		assertEquals("Zimbabwe : 0/10", gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertEquals("Mills  1-0-0-6", gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		//last wicket is a runout, so this bowler only gets three wickets
		assertEquals("Bracewell  1-0-0-3", gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));
	}
	
	@Test
	public void testGetWinningTeam() {
		GameData data = new GameData();
		Game game = data.getFullGame();
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertEquals("New Zealand win by 10 wickets", gameStatus.get(Keys.WINNING_TEAM));
	}
	
	@Test
	public void testGetDrawResult() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		gameProcessor.updateGame(game);

		testData.guptill.setBattingStatus(BattingStatus.Striker);
		testData.brendonMcCullum.setBattingStatus(BattingStatus.NonStriker);

		gameProcessor.startNewOver();
		testData.kyleJarvis.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameProcessor.startNewOver();
		testData.kyleJarvis.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertEquals("New Zealand : 2/0", gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertEquals("Zimbabwe : 2/0", gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertEquals("Draw: 2 runs", gameStatus.get(Keys.WINNING_TEAM));
	}
	
	@Test
	public void testGetSecondTeamWins() {
		GameProcessor gameProcessor = new GameProcessor(game);
		
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.startNewOver();
		testData.mills.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		gameProcessor.updateGame(game);

		testData.guptill.setBattingStatus(BattingStatus.Striker);
		testData.brendonMcCullum.setBattingStatus(BattingStatus.NonStriker);

		gameProcessor.startNewOver();
		testData.kyleJarvis.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameProcessor.startNewOver();
		testData.kyleJarvis.setBowlingStatus(BowlingStatus.OtherBowler);
		testData.rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));
		
		assertEquals("New Zealand : 2/0", gameStatus.get(Keys.TEAM_1_SUMMARY));
		assertEquals("Zimbabwe : 3/0", gameStatus.get(Keys.TEAM_2_SUMMARY));
		
		assertEquals("Zimbabwe win by 1 runs", gameStatus.get(Keys.WINNING_TEAM));
	}
}