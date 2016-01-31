package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import beans.BattingOverBean;
import beans.BattingWicketBean;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.Keys;

public class TeamTest {

	private final Team team = new Team(1);
	private final Player masakadza = new Player("Masakadza", 1);
	private final Player chibhabha = new Player("Chibhabha", 2);

	@Before
	public void setUp() {
		team.addPlayer(masakadza);
		team.addPlayer(chibhabha);

		BattingOverBean bean1 = new BattingOverBean(1, 1);
		bean1.addRun(1, 0, 0);
		bean1.addRun(2, 0, 0);

		BattingOverBean bean2 = new BattingOverBean(2, 2);
		bean2.addRun(1, 0, 0);

		masakadza.getBattingScore().addBattingOver(bean1);
		chibhabha.getBattingScore().addBattingOver(bean2);
	}

	@Test
	public void testTeam() {
		Team team = new Team(1);
		assertEquals(0, team.getPlayers().size());
	}

	@Test
	public void testGetPlayers() {
		assertEquals(2, team.getPlayers().size());
	}

	@Test
	public void testAddBatsman() {
		Player batsman = new Player("batsman", 3);
		team.addPlayer(batsman);
		assertEquals(batsman, team.getBatsmanByIndex(3));
	}

	@Test
	public void testGetBatsmanByIndex() {
		assertEquals(masakadza, team.getBatsmanByIndex(1));
	}

	@Test
	public void testGetRunsScored() {
		assertEquals(4, team.getRunsScored());
	}

	@Test
	public void testGetRunsScoredForOver() {
		assertEquals(3, team.getRunsScoredForOver(1));
		assertEquals(1, team.getRunsScoredForOver(2));
	}

	@Test
	public void testGetWicketsForBowler() {
		Player batsman3 = new Player("batsman3", 3);
		Player batsman4 = new Player("batsman4", 4);
		Player batsman5 = new Player("batsman5", 5);
		Player batsman6 = new Player("batsman6", 6);
		
		masakadza.setBattingStatus(BattingStatus.Out);
		batsman3.setBattingStatus(BattingStatus.Out);
		batsman4.setBattingStatus(BattingStatus.Out);
		batsman5.setBattingStatus(BattingStatus.Out);
		batsman6.setBattingStatus(BattingStatus.Out);

		team.addPlayer(batsman3);
		team.addPlayer(batsman4);
		team.addPlayer(batsman5);
		team.addPlayer(batsman6);

		masakadza.getBattingScore().setBattingWicketBean(new BattingWicketBean(1, DismisalType.CAUGHT, 1, 1, 9));
		batsman3.getBattingScore().setBattingWicketBean(new BattingWicketBean(1, DismisalType.BOWLED, 2, 1, 9));
		batsman4.getBattingScore().setBattingWicketBean(new BattingWicketBean(1, DismisalType.LBW, 3, 1, 1));
		batsman5.getBattingScore().setBattingWicketBean(new BattingWicketBean(2, DismisalType.RUNOUT, 4, 1, 9));
		batsman6.getBattingScore().setBattingWicketBean(new BattingWicketBean(2, DismisalType.NON_STRIKER_RUNOUT, 5, 1, 9));

		assertEquals(3, team.getWicketsForBowler(1));
		assertEquals(0, team.getWicketsForBowler(2));
	}

	@Test
	public void testGetRunsGivenByBowlerAgainstAllBatsman() {
		BattingOverBean bean = new BattingOverBean(3, 2);
		bean.addRun(1, 0, 0);
		bean.addRun(5, 0, 0);

		masakadza.getBattingScore().addBattingOver(bean);

		assertEquals(7, team.getRunsGivenByBowlerAgainstAllBatsman(2));
		assertEquals(3, team.getRunsGivenByBowlerAgainstAllBatsman(1));
	}

	@Test
	public void testGetNumberOfOversByBowler() {
		BattingOverBean bean = new BattingOverBean(3, 2);
		bean.addRun(1, 0, 0);
		bean.addRun(5, 0, 0);

		masakadza.getBattingScore().addBattingOver(bean);
		assertEquals(2, team.getNumberOfOversByBowler(2));
		assertEquals(1, team.getNumberOfOversByBowler(1));
	}

	@Test
	public void testGetAllExtras() {
		Team team = new Team(1);
		Player bowler = new Player("Bowler", 1);
		team.addPlayer(bowler);

		assertEquals(0, team.getAllBowlingExtras());

		bowler.getBowlingScore().addNoBallAndExtras(1);
		bowler.getBowlingScore().addWideAndExtras(1);
		bowler.getBowlingScore().addLegBye(1);
		bowler.getBowlingScore().addBye(1);

		assertEquals(4, team.getAllBowlingExtras());
		assertEquals(2, team.getAllFieldingExtras());

		assertEquals(6, team.getAllExtras());
	}

	@Test
	public void testGetMaidensForBowler() {
		BattingOverBean maidenOver = createMaiden();

		masakadza.getBattingScore().addBattingOver(maidenOver);

		assertEquals(1, team.getMaidensForBowler(5));
	}
	
	@Test
	public void testGetPlayerNames() {
		team.addPlayer(new Player("player1", 10));
		team.addPlayer(new Player("player2", 11));
		team.addPlayer(new Player("player3", 12));
		team.addPlayer(new Player("player4", 13));
		
		ArrayList<String> playerNames = team.getAllPlayerNames();
		assertTrue(playerNames.size() == 6);
	}
	
	@Test
	public void testGetRemainingBatsman() {
		Player player1 = new Player("player1", 10);
		Player player2 = new Player("player2", 11);
		Player player3 = new Player("player3", 12);
		
		team.addPlayer(player1);
		team.addPlayer(player2);
		team.addPlayer(player3);
		team.addPlayer(new Player("player4", 13));
		
		ArrayList<String> remainingBatsman = team.getAvailableBatsmanNames();
		assertTrue(remainingBatsman.size() == 6);

		player1.setBattingStatus(BattingStatus.Out);
		player2.setBattingStatus(BattingStatus.Striker);
		player3.setBattingStatus(BattingStatus.NonStriker);
		
		remainingBatsman = team.getAvailableBatsmanNames();
		
		assertEquals(3, remainingBatsman.size());
		assertFalse(remainingBatsman.contains("player1"));
		assertFalse(remainingBatsman.contains("player2"));
		assertFalse(remainingBatsman.contains("player3"));
	}
	
	@Test
	public void testGetPlayerByStatus() {
		masakadza.setBattingStatus(BattingStatus.Striker);
		assertEquals(masakadza, team.getBatsmanByStatus(BattingStatus.Striker));
	}
	
	@Test
	public void testAddOpeningBatsman() {
		team.addOpeningBatsman(masakadza.getName(), BattingStatus.Striker);
		assertTrue(masakadza.getBattingStatus().equals(BattingStatus.Striker));
		assertTrue(chibhabha.getBattingStatus().equals(BattingStatus.Available));
		
		team.addOpeningBatsman(chibhabha.getName(), BattingStatus.Striker);
		assertTrue(chibhabha.getBattingStatus().equals(BattingStatus.Striker));
		assertTrue(masakadza.getBattingStatus().equals(BattingStatus.Available));
		
		team.addOpeningBatsman(masakadza.getName(), BattingStatus.NonStriker);
		assertTrue(chibhabha.getBattingStatus().equals(BattingStatus.Striker));
		assertTrue(masakadza.getBattingStatus().equals(BattingStatus.NonStriker));
	}
	
	@Test
	public void testGetSixesOfBowlerAgainstAllBatsman() {
		assertEquals(0, team.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(3, Keys.SIX));
		
		BattingOverBean firstSix = new BattingOverBean(3, 3);
		firstSix.addRun(6);
		
		BattingOverBean secondSix = new BattingOverBean(4, 3);
		secondSix.addRun(6);
		
		masakadza.getBattingScore().addBattingOver(firstSix);
		chibhabha.getBattingScore().addBattingOver(secondSix);
		
		assertEquals(2, team.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(3, Keys.SIX));
	}
	
	@Test
	public void testGetFoursOfBowlerAgainstAllBatsman() {
		assertEquals(0, team.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(3, Keys.FOUR));
		
		BattingOverBean firstFour= new BattingOverBean(3, 3);
		firstFour.addRun(4);
		
		BattingOverBean secondFour = new BattingOverBean(4, 3);
		secondFour.addRun(4);
		
		masakadza.getBattingScore().addBattingOver(firstFour);
		chibhabha.getBattingScore().addBattingOver(secondFour);
		
		assertEquals(2, team.getTotalNumberOfRunsOfBowlerAgainstAllBatsman(3, Keys.FOUR));
	}
	
	@Test
	public void testGetTeamName() {
		team.setTeamName("team");
		assertEquals("team", team.getTeamName());
	}
	
	@Test
	public void testGetRunRate() {
		assertEquals(8f, team.getRunRate(), 8);
		masakadza.getBattingScore().addBattingOver(createMaiden());
		assertEquals(2.6666667f, team.getRunRate(), 8);
	}
	
	@Test
	public void testGetRunRateAtStartOfInnings() {
		Team team = new Team(3);
		team.addPlayer(new Player("player", 2));
		assertEquals(0f, team.getRunRate(), 8);
	}
	
	@Test
	public void testGetTeamIndex() {
		assertEquals(1, team.getTeamIndex());
	}
	
	@Test
	public void testGetBowlersByStatus() {
		assertTrue(team.getBowlersByStatus(BowlingStatus.BowledOut).isEmpty());
		assertTrue(team.getBowlersByStatus(BowlingStatus.CurrentlyBowling).isEmpty());
		assertTrue(team.getBowlersByStatus(BowlingStatus.OtherBowler).isEmpty());
		assertEquals(2, team.getBowlersByStatus(BowlingStatus.Available).size());

		masakadza.setBowlingStatus(BowlingStatus.BowledOut);
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.BowledOut).size());
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.Available).size());
		
		masakadza.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		assertTrue(team.getBowlersByStatus(BowlingStatus.BowledOut).isEmpty());
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.CurrentlyBowling).size());
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.Available).size());
		
		masakadza.setBowlingStatus(BowlingStatus.OtherBowler);
		assertTrue(team.getBowlersByStatus(BowlingStatus.BowledOut).isEmpty());
		assertTrue(team.getBowlersByStatus(BowlingStatus.CurrentlyBowling).isEmpty());
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.Available).size());
	}
	
	@Test
	public void testGetCurrentBowler() {
		assertNull(team.getCurrentBowler());
		masakadza.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		
		assertEquals(masakadza, team.getCurrentBowler());
	}
	
	@Test
	public void testGetOtherBowler() {
		assertNull(team.getOtherBowler());
		masakadza.setBowlingStatus(BowlingStatus.OtherBowler);
		
		assertEquals(masakadza, team.getOtherBowler());
	}
	
	@Test
	public void testSetBowlingStatusForBowler() {
		assertTrue(team.getBowlersByStatus(BowlingStatus.BowledOut).isEmpty());
		team.setBowlingStatusForBowler(masakadza.getName(), BowlingStatus.BowledOut);
		assertEquals(1, team.getBowlersByStatus(BowlingStatus.BowledOut).size());
	}
	
	@Test
	public void testGetBowlerNamesWhoCanBowl() {
		assertEquals(2, team.getBowlerNamesWhoCanBowl().size());
		
		masakadza.setBowlingStatus(BowlingStatus.BowledOut);
		assertEquals(1, team.getBowlerNamesWhoCanBowl().size());
		
		chibhabha.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		assertTrue(team.getBowlerNamesWhoCanBowl().isEmpty());
	}
	
	@Test
	public void testGetTotalForBallType() {
		assertEquals(0, team.getTotalForBallType(BallType.NO_BALL_EXTRA));
		assertEquals(0, team.getTotalForBallType(BallType.LEG_BYE));
		assertEquals(0, team.getTotalForBallType(BallType.BYE));
		assertEquals(0, team.getTotalForBallType(BallType.WIDE));
		assertEquals(0, team.getTotalForBallType(BallType.DOT_BALL));
		
		masakadza.getBowlingScore().addBye(1);
		masakadza.getBowlingScore().addLegBye(1);
		masakadza.getBowlingScore().addWideAndExtras(1);
		masakadza.getBowlingScore().addNoBallAndExtras(1);
		
		chibhabha.getBowlingScore().addBye(1);
		chibhabha.getBowlingScore().addLegBye(1);
		chibhabha.getBowlingScore().addWideAndExtras(1);
		chibhabha.getBowlingScore().addNoBallAndExtras(1);
		
		assertEquals(2, team.getTotalForBallType(BallType.NO_BALL_EXTRA));
		assertEquals(2, team.getTotalForBallType(BallType.LEG_BYE));
		assertEquals(2, team.getTotalForBallType(BallType.BYE));
		assertEquals(2, team.getTotalForBallType(BallType.WIDE));
		assertEquals(0, team.getTotalForBallType(BallType.DOT_BALL));
	}
	
	@Test
	public void testGetTotalForBallTypeThrowsException() {
		try {
			team.getTotalForBallType(BallType.WICKET);
			fail();
		} catch (Exception e) {
			assertEquals("This method should only be called with Extra type balls.", e.getMessage());
		}
	}
	
	@Test
	public void testRunsForBowlerForBallType() {
		assertEquals(0, team.getRunsForBowlerForBallType(BallType.NO_BALL_EXTRA));
		assertEquals(0, team.getRunsForBowlerForBallType(BallType.LEG_BYE));
		assertEquals(0, team.getRunsForBowlerForBallType(BallType.BYE));
		assertEquals(0, team.getRunsForBowlerForBallType(BallType.WIDE));
		
		masakadza.getBowlingScore().addBye(1);
		masakadza.getBowlingScore().addLegBye(1);
		masakadza.getBowlingScore().addWideAndExtras(1);
		masakadza.getBowlingScore().addNoBallAndExtras(1);
		
		chibhabha.getBowlingScore().addBye(1);
		chibhabha.getBowlingScore().addLegBye(1);
		chibhabha.getBowlingScore().addWideAndExtras(1);
		chibhabha.getBowlingScore().addNoBallAndExtras(1);
		
		assertEquals(4, team.getRunsForBowlerForBallType(BallType.NO_BALL_EXTRA));
		assertEquals(2, team.getRunsForBowlerForBallType(BallType.LEG_BYE));
		assertEquals(2, team.getRunsForBowlerForBallType(BallType.BYE));
		assertEquals(4, team.getRunsForBowlerForBallType(BallType.WIDE));
	}
	
	@Test
	public void testRunsForBowlerForBallTypeThrowsException() {
		try {
			team.getRunsForBowlerForBallType(BallType.WICKET);
			fail();
		} catch (Exception e) {
			assertEquals("This method should only be called with Extra type balls.", e.getMessage());
		}
	}
	
	@Test
	public void testGetNumberOfRuns() {
		assertEquals(2, team.getNumberOfRuns(1));
		assertEquals(1, team.getNumberOfRuns(2));
		assertEquals(0, team.getNumberOfRuns(3));
	}
	
	@Test
	public void testGetBatsmanByStatus() {
		assertNull(team.getBatsmanByStatus(BattingStatus.Striker));
		
		masakadza.setBattingStatus(BattingStatus.Striker);
		assertEquals(masakadza, team.getBatsmanByStatus(BattingStatus.Striker));
		
		masakadza.setBattingStatus(BattingStatus.NonStriker);
		assertEquals(masakadza, team.getBatsmanByStatus(BattingStatus.NonStriker));
		
		masakadza.setBattingStatus(BattingStatus.Out);
		assertEquals(masakadza, team.getBatsmanByStatus(BattingStatus.Out));
	}
	
	@Test
	public void testGetPlayersAsList() {
		assertTrue(team.getPlayersAsList().size() == 2);
		assertTrue(team.getPlayersAsList().contains(masakadza));
		assertTrue(team.getPlayersAsList().contains(chibhabha));
	}
	
	@Test
	public void testGetPlayerByName() {
		assertEquals(masakadza, team.getPlayerByName(masakadza.getName()));
	}
	
	@Test
	public void testGetPlayerByNameThrowsException() {
		try {
			team.getPlayerByName("Bob");
			fail();
		} catch (Exception e) {
			assertEquals("Player name: Bob doesn't exist.", e.getMessage());
		}
	}
	
	@Test
	public void testGetTopAndSecondTopScorers() {
		assertEquals(masakadza, team.getTopBatsman(-1));
		assertEquals(chibhabha, team.getTopBatsman(masakadza.getId()));
	}
	
	@Test
	public void testGetTopAndSecondTopScorersWithSameScore() {
		BattingOverBean bean3 = new BattingOverBean(2, 2);
		bean3.addRun(2, 0, 0);
		
		chibhabha.getBattingScore().addBattingOver(bean3);
		
		assertEquals(masakadza, team.getTopBatsman(-1));
		assertEquals(chibhabha, team.getTopBatsman(masakadza.getId()));
	}

	@Test
	public void testGetTopWithBestBatsmanIsNotTheNumerOneBatsman() {
		BattingOverBean bean3 = new BattingOverBean(3, 2);
		bean3.addRun(4, 0, 0);
		
		chibhabha.getBattingScore().addBattingOver(bean3);
		
		assertEquals(chibhabha, team.getTopBatsman(-1));
		assertEquals(masakadza, team.getTopBatsman(chibhabha.getId()));
	}
	
	@Test
	public void testGetTopBowlers() {
		Player brendonTalyer = new Player("Brendon Tayler", 203);
		Player mutizwa = new Player("Mutizwa", 204);
		Player waller = new Player("Waller", 205);
		Player coventry = new Player("Coventry", 206);
		Player chigumbura = new Player("Chigumbura", 207);
		Player chakabva = new Player("Chakabva", 208);
		Player utseya = new Player("Utseya", 209);

		team.addPlayer(brendonTalyer);
		team.addPlayer(mutizwa);
		team.addPlayer(waller);
		team.addPlayer(coventry);
		team.addPlayer(chigumbura);
		team.addPlayer(chakabva);
		team.addPlayer(utseya);
		
		//First bowler gets 2 wickets
		BattingWicketBean battingWicketBean = new BattingWicketBean(1, DismisalType.BOWLED, 1, 1, 1);
		chibhabha.getBattingScore().setBattingWicketBean(battingWicketBean);
		chibhabha.setBattingStatus(BattingStatus.Out);
		
		BattingWicketBean battingWicketBean1 = new BattingWicketBean(1, DismisalType.BOWLED, 1, 1, 1);
		masakadza.getBattingScore().setBattingWicketBean(battingWicketBean1);
		masakadza.setBattingStatus(BattingStatus.Out);
		
		//Second bowler gets 3 wickets
		BattingWicketBean battingWicketBean2 = new BattingWicketBean(2, DismisalType.BOWLED, 1, 1, 1);
		brendonTalyer.getBattingScore().setBattingWicketBean(battingWicketBean2);
		brendonTalyer.setBattingStatus(BattingStatus.Out);
		
		BattingWicketBean battingWicketBean3 = new BattingWicketBean(2, DismisalType.BOWLED, 1, 1, 1);
		mutizwa.getBattingScore().setBattingWicketBean(battingWicketBean3);
		mutizwa.setBattingStatus(BattingStatus.Out);
		
		BattingWicketBean battingWicketBean4 = new BattingWicketBean(2, DismisalType.BOWLED, 1, 1, 1);
		waller.getBattingScore().setBattingWicketBean(battingWicketBean4);
		waller.setBattingStatus(BattingStatus.Out);
		
		//Second bowler gets 1 wicket
		BattingWicketBean battingWicketBean5 = new BattingWicketBean(3, DismisalType.BOWLED, 1, 1, 1);
		coventry.getBattingScore().setBattingWicketBean(battingWicketBean5);
		coventry.setBattingStatus(BattingStatus.Out);
		
		assertEquals(Integer.valueOf(2), team.getTopBowler(-1));
	}
	
	@Test
	public void testGetTopBowlersNoWicketsTaken() {
		assertEquals(Integer.valueOf(0), team.getTopBowler(-1));
	}
	
	@Test
	public void testAddPlayer() {
		Player player = new Player("playerName", 1);
		
		try {
			team.addPlayer(player);
			fail();
		} catch (Exception e) {
			assertEquals("player with this id already exists", e.getMessage());
		}
	}
	
	@Test
	public void testGetTotalRunsFromBowler() {
		BattingOverBean bean = new BattingOverBean(1, masakadza.getId());
		bean.addRun(1);
		bean.addRun(1);
		bean.addRun(1);
		bean.addRun(1);
		bean.addRun(1);
		bean.addRun(1);
		masakadza.getBattingScore().addBattingOver(bean);
		
		assertEquals(6, team.getTotalRunsFromBowler(masakadza));
	}
	
	@Test
	public void testGetBowlerByStatus() {
		assertNull(team.getBowlerByStatus(BowlingStatus.CurrentlyBowling));
		
		masakadza.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		assertEquals(masakadza, team.getBowlerByStatus(BowlingStatus.CurrentlyBowling));
	}
	
	@Test
	public void testGetWicketsForBowlerByDismisal() {
		assertEquals(0, team.getWicketsForBowlerByDismisal(1,DismisalType.BOWLED));

		BattingWicketBean battingWicketBean = new BattingWicketBean(null, DismisalType.BOWLED, 1, 1, 3);
		masakadza.getBattingScore().setBattingWicketBean(battingWicketBean);
		assertEquals(0, team.getWicketsForBowlerByDismisal(1,DismisalType.BOWLED));
	}
	
	private BattingOverBean createMaiden() {
		BattingOverBean maidenOver = new BattingOverBean(3, 5);

		maidenOver.addRun(0, 0, 0);
		maidenOver.addRun(0, 0, 0);
		maidenOver.addRun(0, 0, 0);
		maidenOver.addRun(0, 0, 0);
		maidenOver.addRun(0, 0, 0);
		maidenOver.addRun(0, 0, 0);
		return maidenOver;
	}
}