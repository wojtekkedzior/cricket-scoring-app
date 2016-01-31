package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import common.GameType;

public class GameTest {
	
	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void testGame() {
		game = new Game();
		
		assertNotNull(game.getTeam(Game.TEAM_ONE));
		assertNotNull(game.getTeam(Game.TEAM_TWO));
	}

	@Test
	public void testGetTeam() {
		assertEquals(Game.TEAM_ONE, game.getTeam(Game.TEAM_ONE).getTeamIndex());
		assertEquals(Game.TEAM_TWO, game.getTeam(Game.TEAM_TWO).getTeamIndex());
	}

	@Test
	public void testGetTeamByName() {
		game.getTeam(Game.TEAM_ONE).setTeamName("team1");
		game.getTeam(Game.TEAM_TWO).setTeamName("team2");
		
		assertEquals(Game.TEAM_ONE, game.getTeamByName("team1").getTeamIndex());
		assertEquals(Game.TEAM_TWO, game.getTeamByName("team2").getTeamIndex());
	}
	
	@Test
	public void testGetTeamByWrongName() {
		try {
			game.getTeamByName("team name does't exist");
			fail();
		} catch (Exception e) {
			assertEquals("No team found with name: team name does't exist", e.getMessage());
		}
		
		//add a name to the first team to test the second team name when null
		game.getTeam(Game.TEAM_ONE).setTeamName("team1");
		
		try {
			game.getTeamByName("team name does't exist");
			fail();
		} catch (Exception e) {
			assertEquals("No team found with name: team name does't exist", e.getMessage());
		}
		
		//test not matching the second team's name
		game.getTeam(Game.TEAM_TWO).setTeamName("team2");
		
		try {
			game.getTeamByName("team name does't exist");
			fail();
		} catch (Exception e) {
			assertEquals("No team found with name: team name does't exist", e.getMessage());
		}
	}

	@Test
	public void testSetGameSettings() {
		assertNull(game.getGameSettings());
	}

	@Test
	public void testGetGameSettings() {
		GameSettings gameSettings = new GameSettings(GameType.TwentyTwenty);
		game.setGameSettings(gameSettings);
		assertEquals(gameSettings, game.getGameSettings());
	}
	
	@Test
	public void testGetGameStatus() {
		assertNotNull(game.getGameStatus());
	}
	
	@Test
	public void testGetTarget() {
		int target = 100;
		game.getGameStatus().setTarget(target);
		assertEquals(target,  game.getTarget());
	}
}