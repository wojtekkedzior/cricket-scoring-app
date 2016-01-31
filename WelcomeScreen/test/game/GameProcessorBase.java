package game;

import org.junit.Before;

import other.GameData;

public class GameProcessorBase {

	protected Game game;
	protected GameProcessor gameProcessor;
	protected GameData testData;
	protected BattingScore battingScore;
	protected BowlingScore bowlingScore;
	
	protected Player fielder;
	protected Player bowler;
	protected Player nonStriker;
	
	@Before
	public void setUp() {
		testData = new GameData();
		game = testData.getGame(true);

		battingScore = game.getBattingTeam().getStriker().getBattingScore();
		bowlingScore = game.getBowlingTeam().getCurrentBowler().getBowlingScore();
		
		String somePlayerName = game.getTeam(1).getAllPlayerNames().get(0);
		fielder = game.getTeam(1).getPlayerByName(somePlayerName);
		
		bowler = game.getBowlingTeam().getCurrentBowler();
		nonStriker = game.getBattingTeam().getNonStriker();

		gameProcessor = new GameProcessor(game);
	}
}
