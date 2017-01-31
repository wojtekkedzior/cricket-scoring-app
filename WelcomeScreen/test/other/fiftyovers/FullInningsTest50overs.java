package other.fiftyovers;

import static org.junit.Assert.assertEquals;
import game.Game;
import game.Team;

import org.junit.Before;
import org.junit.Test;

import common.DismisalType;

public class FullInningsTest50overs {

	// http://www.espncricinfo.com/new-zealand-v-zimbabwe-2012/engine/match/527012.html
	

	private GameData50overs gameData = new GameData50overs();
	
	private Game game;
	private Team nzTeam;
	private Team indianTeam;
	
	@Before
	public void setUp() {
		game = gameData.getGame(true);
		
		nzTeam = game.getTeam(1);
		indianTeam = game.getTeam(2);
		
		new NzInnings (gameData, game);
	}

	//Test Indian bowlers

	@Test
	public void testIndiaBowlerYadav() {
		assertEquals(2, nzTeam.getWicketsForBowler(gameData.yadav.getId()));

		int bowlingExtras = gameData.yadav.getBowlingScore().getBowlerExtras();
		assertEquals(3, bowlingExtras);
		assertEquals(0, gameData.yadav.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.yadav.getId());
		assertEquals(31, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(8, nzTeam.getNumberOfOversByBowler(gameData.yadav.getId()));
		// TODO find a way to show values to 2dp
		assertEquals(3.875f,
				(Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.yadav.getId())), 0);
		assertEquals(1, nzTeam.getMaidensForBowler(gameData.yadav.getId()));
	}

	@Test
	public void testIndiaBowlerPandya() {
		assertEquals(3, nzTeam.getWicketsForBowler(gameData.pandya.getId()));

		int bowlingExtras = gameData.pandya.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.pandya.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.pandya.getId());
		assertEquals(31, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(7, nzTeam.getNumberOfOversByBowler(gameData.pandya.getId()));
		assertEquals(4.4285712242126465f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.pandya.getId())), 0);
		assertEquals(0, nzTeam.getMaidensForBowler(gameData.pandya.getId()));
	}

	@Test
	public void testIndiaBowlerBumrah() {
		assertEquals(0, nzTeam.getWicketsForBowler(gameData.bumrah.getId()));

		int bowlingExtras = gameData.bumrah.getBowlingScore().getBowlerExtras();
		assertEquals(2, bowlingExtras);
		assertEquals(1, gameData.bumrah.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.bumrah.getId());
		assertEquals(29, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(8, nzTeam.getNumberOfOversByBowler(gameData.bumrah.getId()));
		assertEquals(3.625f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.bumrah.getId())), 0);
		assertEquals(1, nzTeam.getMaidensForBowler(gameData.bumrah.getId()));
	}

	@Test
	public void testIndiaBowlerJadhav() {
		assertEquals(2, nzTeam.getWicketsForBowler(gameData.jadhav.getId()));

		int bowlingExtras = gameData.jadhav.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.jadhav.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.jadhav.getId());
		assertEquals(6, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(3, nzTeam.getNumberOfOversByBowler(gameData.jadhav.getId()));
		assertEquals(2f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.jadhav.getId())), 0);

		assertEquals(0, nzTeam.getMaidensForBowler(gameData.jadhav.getId()));
	}

	@Test
	public void testIndiaBowlerPatel() {
		assertEquals(0, nzTeam.getWicketsForBowler(gameData.patel.getId()));

		int bowlingExtras = gameData.patel.getBowlingScore().getBowlerExtras();
		assertEquals(3, bowlingExtras);
		assertEquals(2, gameData.patel.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.patel.getId());
		assertEquals(41, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(9, nzTeam.getNumberOfOversByBowler(gameData.patel.getId()));
		assertEquals(4.55555534362793f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.patel.getId())), 0);
		assertEquals(1, nzTeam.getMaidensForBowler(gameData.patel.getId()));
	}

	@Test
	public void testIndiaBowlerMishra() {
		assertEquals(3, nzTeam.getWicketsForBowler(gameData.mishra.getId()));

		int bowlingExtras = gameData.mishra.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.mishra.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = nzTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.mishra.getId());
		assertEquals(49, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(9, nzTeam.getNumberOfOversByBowler(gameData.mishra.getId()));
		assertEquals(5.44444465637207f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / nzTeam.getNumberOfOversByBowler(gameData.mishra.getId())), 0);
		assertEquals(0, nzTeam.getMaidensForBowler(gameData.mishra.getId()));
	}

	@Test
	public void testIndiaGetCatchesForPlayer() {
		assertEquals(1, nzTeam.getCatchesForPlayer(gameData.sharma.getId()));
		assertEquals(1, nzTeam.getCatchesForPlayer(gameData.rahane.getId()));
		assertEquals(0, nzTeam.getCatchesForPlayer(gameData.kohli.getId()));
		assertEquals(1, nzTeam.getCatchesForPlayer(gameData.pandey.getId()));
		assertEquals(2, nzTeam.getCatchesForPlayer(gameData.dhoni.getId()));
		assertEquals(1, nzTeam.getCatchesForPlayer(gameData.jadhav.getId()));
		assertEquals(0, nzTeam.getCatchesForPlayer(gameData.pandya.getId()));
		assertEquals(0, nzTeam.getCatchesForPlayer(gameData.patel.getId()));
		assertEquals(1, nzTeam.getCatchesForPlayer(gameData.mishra.getId()));
		assertEquals(0, nzTeam.getCatchesForPlayer(gameData.bumrah.getId()));
		assertEquals(2, nzTeam.getCatchesForPlayer(gameData.yadav.getId()));
	}

	@Test
	public void testIndiaGetWicketsByDismissal() {
		assertEquals(9, nzTeam.getWicketsByDismissal(DismisalType.CAUGHT));
		assertEquals(1, nzTeam.getWicketsByDismissal(DismisalType.LBW));

		int runout = nzTeam.getWicketsByDismissal(DismisalType.RUNOUT) + nzTeam.getWicketsByDismissal(DismisalType.NON_STRIKER_RUNOUT);
		assertEquals(0, runout);

		assertEquals(0, nzTeam.getWicketsByDismissal(DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsByDismissal(DismisalType.STUMPED));
		assertEquals(0, nzTeam.getWicketsByDismissal(DismisalType.HIT_WICKET));
	}

	@Test
	public void testIndiaGetAllWickets() {
		assertEquals(10, nzTeam.getWicketsLost());
	}

	@Test
	public void testIndiaGetWicketsForPlayer() {
		assertEquals(2, nzTeam.getWicketsForBowler(gameData.yadav.getId()));
		assertEquals(3, nzTeam.getWicketsForBowler(gameData.pandya.getId()));
		assertEquals(0, nzTeam.getWicketsForBowler(gameData.bumrah.getId()));
		assertEquals(2, nzTeam.getWicketsForBowler(gameData.jadhav.getId()));
		assertEquals(0, nzTeam.getWicketsForBowler(gameData.patel.getId()));
		assertEquals(3, nzTeam.getWicketsForBowler(gameData.mishra.getId()));
	}

	@Test
	public void testIndiaGetWicketsForPlayerByDismisal() {
		assertEquals(2, nzTeam.getWicketsForBowlerByDismisal(gameData.yadav.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.yadav.getId(), DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.yadav.getId(), DismisalType.LBW));

		assertEquals(3, nzTeam.getWicketsForBowlerByDismisal(gameData.pandya.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.pandya.getId(), DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.pandya.getId(), DismisalType.LBW));

		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.bumrah.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.bumrah.getId(), DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.bumrah.getId(), DismisalType.LBW));

		assertEquals(2, nzTeam.getWicketsForBowlerByDismisal(gameData.jadhav.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.jadhav.getId(), DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.jadhav.getId(), DismisalType.LBW));

		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.patel.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.patel.getId(), DismisalType.BOWLED));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.patel.getId(), DismisalType.LBW));
		
		assertEquals(2, nzTeam.getWicketsForBowlerByDismisal(gameData.mishra.getId(), DismisalType.CAUGHT));
		assertEquals(0, nzTeam.getWicketsForBowlerByDismisal(gameData.mishra.getId(), DismisalType.BOWLED));
		assertEquals(1, nzTeam.getWicketsForBowlerByDismisal(gameData.mishra.getId(), DismisalType.LBW));
	}

	@Test
	public void testNZRunRate() {
		float runsScored = nzTeam.getRunsScored() + indianTeam.getAllExtras();
		float numberOfOvers = nzTeam.getNumberOfOvers(); //BUG should be 43.5 not 43.0
		numberOfOvers++; //because overs start from 0
		assertEquals(4.318181991577148f, runsScored / numberOfOvers, 0);
	}
}