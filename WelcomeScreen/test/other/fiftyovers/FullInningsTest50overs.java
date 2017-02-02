package other.fiftyovers;

import static org.junit.Assert.assertEquals;
import game.Game;
import game.GameProcessor;
import game.Team;

import org.junit.Before;
import org.junit.Test;

import common.BattingStatus;
import common.DismisalType;
import common.TeamBattingStatus;

public class FullInningsTest50overs {

	// http://www.espncricinfo.com/new-zealand-v-zimbabwe-2012/engine/match/527012.html

	private GameData50overs gameData = new GameData50overs();
	
	private Game game;
	private Team nzTeam;
	private Team indianTeam;
	
	private GameProcessor gameProcessor;
	
	@Before
	public void setUp() {
		game = gameData.getGame(true);
		
		gameProcessor = new GameProcessor(game);
		gameData.setGameProcessor(gameProcessor);
		
		nzTeam = game.getTeam(1);
		indianTeam = game.getTeam(2);
		
		new NzInnings (gameData, game);
		
		game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		gameProcessor.startNewInnings();
		gameProcessor.updateGame(game);

		gameData.sharma.setBattingStatus(BattingStatus.Striker);
		gameData.rahane.setBattingStatus(BattingStatus.NonStriker);
		
		new IndianInnings(gameData, game);
	}

	//----------------------------------------------------------------------------------------------------
	//Test Indian bowlers
	//----------------------------------------------------------------------------------------------------
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

	//----------------------------------------------------------------------------------------------------
	//Test NZ bowlers
	//----------------------------------------------------------------------------------------------------
	@Test
	public void testIndiaBowlerSouthee() {
		assertEquals(0, indianTeam.getWicketsForBowler(gameData.southee.getId()));

		int bowlingExtras = gameData.southee.getBowlingScore().getBowlerExtras();
		assertEquals(2, bowlingExtras);
		assertEquals(0, gameData.southee.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = indianTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.southee.getId());
		assertEquals(57, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(9, indianTeam.getNumberOfOversByBowler(gameData.southee.getId()));
		// TODO find a way to show values to 2dp
		assertEquals(6.333333492279053f,
				(Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / indianTeam.getNumberOfOversByBowler(gameData.southee.getId())), 0);
		assertEquals(0, indianTeam.getMaidensForBowler(gameData.southee.getId()));
	}

	@Test
	public void testIndiaBowlerBracewell() {
		assertEquals(1, indianTeam.getWicketsForBowler(gameData.bracewell.getId()));

		int bowlingExtras = gameData.bracewell.getBowlingScore().getBowlerExtras();
		assertEquals(1, bowlingExtras);
		assertEquals(0, gameData.bracewell.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = indianTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.bracewell.getId());
		assertEquals(44, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(8, indianTeam.getNumberOfOversByBowler(gameData.bracewell.getId()));
		assertEquals(5.5f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / indianTeam.getNumberOfOversByBowler(gameData.bracewell.getId())), 0);
		assertEquals(1, indianTeam.getMaidensForBowler(gameData.bracewell.getId())); //BUG should be two maidens
	}

	@Test
	public void testIndiaBowlerNeesham() {
		assertEquals(1, indianTeam.getWicketsForBowler(gameData.neesham.getId()));

		int bowlingExtras = gameData.neesham.getBowlingScore().getBowlerExtras();
		assertEquals(10, bowlingExtras);
		assertEquals(1, gameData.neesham.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = indianTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.neesham.getId());
		assertEquals(40, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));
		assertEquals(6, indianTeam.getNumberOfOversByBowler(gameData.neesham.getId()));
		assertEquals(6.666666507720947f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / indianTeam.getNumberOfOversByBowler(gameData.neesham.getId())), 0);
		assertEquals(0, indianTeam.getMaidensForBowler(gameData.neesham.getId()));
	}

	@Test
	public void testIndiaBowlerSodhi() {
		assertEquals(1, indianTeam.getWicketsForBowler(gameData.sodhi.getId()));

		int bowlingExtras = gameData.sodhi.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.sodhi.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = indianTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.sodhi.getId());
		assertEquals(34, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(5, indianTeam.getNumberOfOversByBowler(gameData.sodhi.getId()));
		assertEquals(6.800000190734863f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / indianTeam.getNumberOfOversByBowler(gameData.sodhi.getId())), 0);
		//BUG economy should be 8.16
		assertEquals(0, indianTeam.getMaidensForBowler(gameData.sodhi.getId()));
	}
	
	@Test
	public void testIndiaBowlerSantner() {
		assertEquals(0, indianTeam.getWicketsForBowler(gameData.santner.getId()));

		int bowlingExtras = gameData.santner.getBowlingScore().getBowlerExtras();
		assertEquals(0, bowlingExtras);
		assertEquals(0, gameData.santner.getBowlingScore().getFieldingExtras());

		int runsGivenByBowlerAgainstAllBatsman = indianTeam.getRunsGivenByBowlerAgainstAllBatsman(gameData.santner.getId());
		assertEquals(18, (runsGivenByBowlerAgainstAllBatsman + bowlingExtras));

		assertEquals(6, indianTeam.getNumberOfOversByBowler(gameData.santner.getId()));
		assertEquals(3.0f, (Float.valueOf(runsGivenByBowlerAgainstAllBatsman + bowlingExtras) / indianTeam.getNumberOfOversByBowler(gameData.santner.getId())), 0);
		assertEquals(0, indianTeam.getMaidensForBowler(gameData.santner.getId()));
	}

	
	//----------------------------------------------------------------------------------------------------
	//Test India wickets
	//----------------------------------------------------------------------------------------------------
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
	
	//----------------------------------------------------------------------------------------------------
	//Test NZ wickets
	//----------------------------------------------------------------------------------------------------
	@Test
	public void testNZGetCatchesForPlayer() {
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.guptill.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.latham.getId()));
		assertEquals(1, indianTeam.getCatchesForPlayer(gameData.williamson.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.taylor.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.anderson.getId()));
		assertEquals(1, indianTeam.getCatchesForPlayer(gameData.ronchi.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.neesham.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.santner.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.bracewell.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.southee.getId()));
		assertEquals(0, indianTeam.getCatchesForPlayer(gameData.sodhi.getId()));
	}

	@Test
	public void testNZGetWicketsByDismissal() {
		assertEquals(2, indianTeam.getWicketsByDismissal(DismisalType.CAUGHT));
		assertEquals(1, indianTeam.getWicketsByDismissal(DismisalType.LBW));

		int runout = indianTeam.getWicketsByDismissal(DismisalType.RUNOUT) + indianTeam.getWicketsByDismissal(DismisalType.NON_STRIKER_RUNOUT);
		assertEquals(1, runout);

		assertEquals(0, indianTeam.getWicketsByDismissal(DismisalType.BOWLED));
		assertEquals(0, indianTeam.getWicketsByDismissal(DismisalType.STUMPED));
		assertEquals(0, indianTeam.getWicketsByDismissal(DismisalType.HIT_WICKET));
	}

	@Test
	public void testNZGetAllWickets() {
		assertEquals(4, indianTeam.getWicketsLost());
	}

	@Test
	public void testNZGetWicketsForPlayerByDismisal() {
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.southee.getId(), DismisalType.CAUGHT));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.southee.getId(), DismisalType.BOWLED));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.southee.getId(), DismisalType.LBW));

		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.CAUGHT));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.BOWLED));
		assertEquals(1, indianTeam.getWicketsForBowlerByDismisal(gameData.bracewell.getId(), DismisalType.LBW));

		assertEquals(1, indianTeam.getWicketsForBowlerByDismisal(gameData.neesham.getId(), DismisalType.CAUGHT));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.neesham.getId(), DismisalType.BOWLED));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.neesham.getId(), DismisalType.LBW));

		assertEquals(1, indianTeam.getWicketsForBowlerByDismisal(gameData.sodhi.getId(), DismisalType.CAUGHT));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.sodhi.getId(), DismisalType.BOWLED));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.sodhi.getId(), DismisalType.LBW));

		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.santner.getId(), DismisalType.CAUGHT));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.santner.getId(), DismisalType.BOWLED));
		assertEquals(0, indianTeam.getWicketsForBowlerByDismisal(gameData.santner.getId(), DismisalType.LBW));
	}
	
	//----------------------------------------------------------------------------------------------------
	//Test India batsman
	//----------------------------------------------------------------------------------------------------
	@Test public void testBatsmanSharma() {
		
	}
	@Test public void testBatsmanRahane() {
		assertEquals(33, gameData.rahane.getBattingScore().getRuns());
		assertEquals(34, gameData.rahane.getBattingScore().getBallsFaced());
		assertEquals(97.05882263183594f, gameData.rahane.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(24, gameData.rahane.getBattingScore().getNumberOfRuns(0));
		assertEquals(3, gameData.rahane.getBattingScore().getNumberOfRuns(1));
		assertEquals(1, gameData.rahane.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.rahane.getBattingScore().getNumberOfRuns(3));

		assertEquals(4, gameData.rahane.getBattingScore().getNumberOfRuns(4));
		assertEquals(2, gameData.rahane.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.rahane.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.rahane.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanKohli() {
		assertEquals(85, gameData.kohli.getBattingScore().getRuns());
		assertEquals(81, gameData.kohli.getBattingScore().getBallsFaced());
		assertEquals(104.93827056884766f, gameData.kohli.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(39, gameData.kohli.getBattingScore().getNumberOfRuns(0));
		assertEquals(21, gameData.kohli.getBattingScore().getNumberOfRuns(1));
		assertEquals(11, gameData.kohli.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.kohli.getBattingScore().getNumberOfRuns(3));

		assertEquals(9, gameData.kohli.getBattingScore().getNumberOfRuns(4));
		assertEquals(1, gameData.kohli.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.kohli.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.kohli.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanPandey() {
		assertEquals(17, gameData.pandey.getBattingScore().getRuns());
		assertEquals(22, gameData.pandey.getBattingScore().getBallsFaced());
		assertEquals(77.2727279663086f, gameData.pandey.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(11, gameData.pandey.getBattingScore().getNumberOfRuns(0));
		assertEquals(8, gameData.pandey.getBattingScore().getNumberOfRuns(1));
		assertEquals(1, gameData.pandey.getBattingScore().getNumberOfRuns(2));
		assertEquals(1, gameData.pandey.getBattingScore().getNumberOfRuns(3));

		assertEquals(1, gameData.pandey.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.pandey.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.pandey.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.pandey.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanDhoni() {
		assertEquals(21, gameData.dhoni.getBattingScore().getRuns());
		assertEquals(24, gameData.dhoni.getBattingScore().getBallsFaced());
		assertEquals(87.5, gameData.dhoni.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(12, gameData.dhoni.getBattingScore().getNumberOfRuns(0));
		assertEquals(9, gameData.dhoni.getBattingScore().getNumberOfRuns(1));
		assertEquals(1, gameData.dhoni.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.dhoni.getBattingScore().getNumberOfRuns(3));

		assertEquals(1, gameData.dhoni.getBattingScore().getNumberOfRuns(4));
		assertEquals(1, gameData.dhoni.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.dhoni.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.dhoni.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanJadhav() {
		assertEquals(10, gameData.jadhav.getBattingScore().getRuns());
		assertEquals(12, gameData.jadhav.getBattingScore().getBallsFaced());
		assertEquals(83.33332824707031f, gameData.jadhav.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(8, gameData.jadhav.getBattingScore().getNumberOfRuns(0));
		assertEquals(2, gameData.jadhav.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.jadhav.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.jadhav.getBattingScore().getNumberOfRuns(3));

		assertEquals(2, gameData.jadhav.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.jadhav.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.jadhav.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.jadhav.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanPandya() {
		assertEquals(0, gameData.pandya.getBattingScore().getRuns());
		assertEquals(0, gameData.pandya.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.pandya.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(3));

		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.pandya.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanPatel() {
		assertEquals(0, gameData.patel.getBattingScore().getRuns());
		assertEquals(0, gameData.patel.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.patel.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(3));

		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.patel.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanMishra() {
		assertEquals(0, gameData.mishra.getBattingScore().getRuns());
		assertEquals(0, gameData.mishra.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.mishra.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(3));

		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.mishra.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanBumrah() {
		assertEquals(0, gameData.bumrah.getBattingScore().getRuns());
		assertEquals(0, gameData.bumrah.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.bumrah.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(3));

		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.bumrah.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanYadav() {
		assertEquals(0, gameData.yadav.getBattingScore().getRuns());
		assertEquals(0, gameData.yadav.getBattingScore().getBallsFaced());
		assertEquals(0, gameData.yadav.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(3));

		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.yadav.getBattingScore().getNumberOfRuns(7));
	}
	
	//----------------------------------------------------------------------------------------------------
	//Test NZ batsman
	//----------------------------------------------------------------------------------------------------
	@Test public void testBatsmanGuptil() {
		assertEquals(12, gameData.guptill.getBattingScore().getRuns());
		assertEquals(11, gameData.guptill.getBattingScore().getBallsFaced());
		assertEquals(109.09091186523438f, gameData.guptill.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(8, gameData.guptill.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(3));

		assertEquals(3, gameData.guptill.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(6));
		
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.guptill.getBattingScore().getNumberOfRuns(7));
	}
	
	@Test public void testBatsmanLatham() {
		assertEquals(79, gameData.latham.getBattingScore().getRuns());
		assertEquals(98, gameData.latham.getBattingScore().getBallsFaced());
		assertEquals(80.61224365234375f, gameData.latham.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(48, gameData.latham.getBattingScore().getNumberOfRuns(0));
		assertEquals(39, gameData.latham.getBattingScore().getNumberOfRuns(1));
		assertEquals(3, gameData.latham.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.latham.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(7, gameData.latham.getBattingScore().getNumberOfRuns(4));
		assertEquals(1, gameData.latham.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.latham.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.latham.getBattingScore().getNumberOfRuns(7));
	}
	
	@Test public void testBatsmanWilliamson() {
		assertEquals(3, gameData.williamson.getBattingScore().getRuns());
		assertEquals(9, gameData.williamson.getBattingScore().getBallsFaced());
		assertEquals(33.333335876464844f, gameData.williamson.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(6, gameData.williamson.getBattingScore().getNumberOfRuns(0));
		assertEquals(3, gameData.williamson.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.williamson.getBattingScore().getNumberOfRuns(7));
	}
	
	@Test public void testBatsmanTaylor() {
		assertEquals(0, gameData.taylor.getBattingScore().getRuns());
		assertEquals(1, gameData.taylor.getBattingScore().getBallsFaced());
		assertEquals(0.0f, gameData.taylor.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(1, gameData.taylor.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.taylor.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanAnderson() {
		assertEquals(4, gameData.anderson.getBattingScore().getRuns());
		assertEquals(14, gameData.anderson.getBattingScore().getBallsFaced());
		assertEquals(28.571430206298828f, gameData.anderson.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(13, gameData.anderson.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(1, gameData.anderson.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.anderson.getBattingScore().getNumberOfRuns(7));
	}
	
	@Test public void testBatsmanRonchi() {
		assertEquals(0, gameData.ronchi.getBattingScore().getRuns());
		assertEquals(3, gameData.ronchi.getBattingScore().getBallsFaced());
		assertEquals(0.0f, gameData.ronchi.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(3, gameData.ronchi.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.ronchi.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanNeesham() {
		assertEquals(10, gameData.neesham.getBattingScore().getRuns());
		assertEquals(25, gameData.neesham.getBattingScore().getBallsFaced());
		assertEquals(40.0f, gameData.neesham.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(18, gameData.neesham.getBattingScore().getNumberOfRuns(0));
		assertEquals(6, gameData.neesham.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.neesham.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.neesham.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(1, gameData.neesham.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.neesham.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.neesham.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.neesham.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanSantner() {
		assertEquals(0, gameData.santner.getBattingScore().getRuns());
		assertEquals(1, gameData.santner.getBattingScore().getBallsFaced());
		assertEquals(0.0f, gameData.santner.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(1, gameData.santner.getBattingScore().getNumberOfRuns(0));
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.santner.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanBracewell() {
		assertEquals(15, gameData.bracewell.getBattingScore().getRuns());
		assertEquals(46, gameData.bracewell.getBattingScore().getBallsFaced());
		assertEquals(32.60869598388672f, gameData.bracewell.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(31, gameData.bracewell.getBattingScore().getNumberOfRuns(0));
		assertEquals(15, gameData.bracewell.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.bracewell.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanSouthee() {
		assertEquals(55, gameData.southee.getBattingScore().getRuns());
		assertEquals(45, gameData.southee.getBattingScore().getBallsFaced());
		assertEquals(122.22222137451172f, gameData.southee.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(24, gameData.southee.getBattingScore().getNumberOfRuns(0));
		assertEquals(11, gameData.southee.getBattingScore().getNumberOfRuns(1));
		assertEquals(1, gameData.southee.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.southee.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(6, gameData.southee.getBattingScore().getNumberOfRuns(4));
		assertEquals(3, gameData.southee.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.southee.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.southee.getBattingScore().getNumberOfRuns(7));
	}
	@Test public void testBatsmanSodhi() {
		assertEquals(1, gameData.sodhi.getBattingScore().getRuns());
		assertEquals(10, gameData.sodhi.getBattingScore().getBallsFaced());
		assertEquals(10.0f, gameData.sodhi.getBattingScore().getStrikeRate(), 0);
		
		assertEquals(9, gameData.sodhi.getBattingScore().getNumberOfRuns(0));
		assertEquals(1, gameData.sodhi.getBattingScore().getNumberOfRuns(1));
		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(2));
		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(3));
		
		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(4));
		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(6));

		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(5));
		assertEquals(0, gameData.sodhi.getBattingScore().getNumberOfRuns(7));
	}
	
	
	
	
	
	
	
	
	

	@Test
	public void testNZRunRate() {
		float runsScored = nzTeam.getRunsScored() + indianTeam.getAllExtras();
		float numberOfOvers = nzTeam.getNumberOfOvers(); //BUG should be 43.5 not 43.0
		numberOfOvers++; //because overs start from 0
		assertEquals(4.318181991577148f, runsScored / numberOfOvers, 0);
	}
	
	@Test
	public void testIndiaRunRate() {
		float runsScored = indianTeam.getRunsScored() + nzTeam.getAllExtras();
		float numberOfOvers = indianTeam.getNumberOfOvers(); //BUG should be 33.1 not 34
		numberOfOvers++; //because overs start from 0
		assertEquals(5.705882549285889f, runsScored / numberOfOvers, 0);
	}
	
	@Test
	public void testWinningTeam() {
	}
	
	
	
	
}