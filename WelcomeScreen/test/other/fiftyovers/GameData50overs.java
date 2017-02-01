package other.fiftyovers;

import game.Game;
import game.GameProcessor;
import game.GameSettings;
import game.Player;
import game.Team;

import common.BallType;
import common.BattingStatus;
import common.BowlingStatus;
import common.DismisalType;
import common.GameType;
import common.TeamBattingStatus;
import common.TeamBowlingStatus;

public class GameData50overs {

	private GameProcessor gameProcessor;
	private Game game = new Game();

	public final Team nzTeam = game.getTeam(1);

	public final Player guptill = new Player("Guptill", 21);
	public final Player latham = new Player("Latham", 22);
	public final Player williamson = new Player("Williamson", 23);
	public final Player taylor = new Player("Taylor", 28);
	public final Player anderson = new Player("Anderson", 24);
	public final Player ronchi = new Player("Ronchi", 25);
	public final Player neesham = new Player("Neesham", 26);
	public final Player santner = new Player("Santner", 27);
	public final Player bracewell = new Player("Bracewell", 29);
	public final Player southee = new Player("Southee", 30);
	public final Player sodhi = new Player("Sodhi", 31);

	public final Team indianTeam = game.getTeam(2);

	public final Player sharma = new Player("Sharma", 1);
	public final Player rahane = new Player("Rahane", 2);
	public final Player kohli = new Player("Kohli", 3);
	public final Player pandey = new Player("Pandey", 4);
	public final Player dhoni = new Player("Dhoni", 5);
	public final Player jadhav = new Player("Jadhav", 6);
	public final Player pandya = new Player("Pandya", 7);
	public final Player patel = new Player("Patel", 8);
	public final Player mishra = new Player("Mishra", 9);
	public final Player bumrah = new Player("Bumrah", 10);
	public final Player yadav = new Player("Yadav", 11);

	public void setGameProcessor(GameProcessor gameProcessor) {
		this.gameProcessor = gameProcessor;
	}

	public Game getGame(boolean setTeams) {
		game.setGameSettings(new GameSettings(GameType.FortyForty));

		nzTeam.setTeamName("New Zealand");
		nzTeam.addPlayer(guptill);
		nzTeam.addPlayer(latham);
		nzTeam.addPlayer(williamson);
		nzTeam.addPlayer(taylor);
		nzTeam.addPlayer(anderson);
		nzTeam.addPlayer(ronchi);
		nzTeam.addPlayer(neesham);
		nzTeam.addPlayer(santner);
		nzTeam.addPlayer(bracewell);
		nzTeam.addPlayer(southee);
		nzTeam.addPlayer(sodhi);

		indianTeam.setTeamName("India");
		indianTeam.addPlayer(sharma);
		indianTeam.addPlayer(rahane);
		indianTeam.addPlayer(kohli);
		indianTeam.addPlayer(pandey);
		indianTeam.addPlayer(dhoni);
		indianTeam.addPlayer(jadhav);
		indianTeam.addPlayer(pandya);
		indianTeam.addPlayer(patel);
		indianTeam.addPlayer(mishra);
		indianTeam.addPlayer(bumrah);
		indianTeam.addPlayer(yadav);

		if (setTeams) {
			nzTeam.setTeamBowlingStatus(TeamBowlingStatus.YetToBowl);
			nzTeam.setTeamBattingStatus(TeamBattingStatus.Batting);

			indianTeam.setTeamBattingStatus(TeamBattingStatus.YetToBat);
			indianTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);

			guptill.setBattingStatus(BattingStatus.Striker);
			latham.setBattingStatus(BattingStatus.NonStriker);

			yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);
			pandya.setBowlingStatus(BowlingStatus.OtherBowler);
		}

		return game;

	}

	@Deprecated
	// use only when full game data is needed
	public Game getFullGame() {
		getGame(true);

		game.setGameSettings(new GameSettings(GameType.TwentyTwenty));
		gameProcessor = new GameProcessor(game);

		// http://www.espncricinfo.com/india-v-new-zealand-2016-17/engine/match/1030219.html

		guptill.setBattingStatus(BattingStatus.Striker);
		latham.setBattingStatus(BattingStatus.NonStriker);

//		firstInnings();

		game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		gameProcessor.updateGame(game);

		sharma.setBattingStatus(BattingStatus.Striker);
		rahane.setBattingStatus(BattingStatus.NonStriker);

//		secondInnings();

		return game;
	}

	public void nzOver1() {
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver2() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);

		williamson.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, sharma, 0);
	}

	public void nzOver3() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver4() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	public void nzOver5() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		taylor.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, mishra, 0);
	}

	public void nzOver6() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver7() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);

		anderson.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, dhoni, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver8() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver9() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver10() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver11() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		ronchi.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, yadav, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver12() {
		gameProcessor.startNewOver();
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver13() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		neesham.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, yadav, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver14() {
		gameProcessor.startNewOver();
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver15() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		pandya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver16() {
		gameProcessor.startNewOver();
		pandya.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver17() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		jadhav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver18() {
		gameProcessor.startNewOver();
		jadhav.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.LEG_BYE, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver19() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		jadhav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		santner.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, jadhav, 0);

		bracewell.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, dhoni, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

	}

	public void nzOver20() {
		gameProcessor.startNewOver();
		jadhav.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

	}

	public void nzOver21() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		jadhav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

	}

	public void nzOver22() {
		gameProcessor.startNewOver();
		jadhav.setBowlingStatus(BowlingStatus.OtherBowler);
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

	}

	public void nzOver23() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver24() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

	}

	public void nzOver25() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver26() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver27() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver28() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver29() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver30() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver31() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver32() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		southee.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, rahane, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

	}

	public void nzOver33() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver34() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver35() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	public void nzOver36() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver37() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}

	public void nzOver38() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver39() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.BYE, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver40() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		yadav.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver41() {
		gameProcessor.startNewOver();
		yadav.setBowlingStatus(BowlingStatus.OtherBowler);
		patel.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	public void nzOver42() {
		gameProcessor.startNewOver();
		patel.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);

		sodhi.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, pandey, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void nzOver43() {
		gameProcessor.startNewOver();
		mishra.setBowlingStatus(BowlingStatus.OtherBowler);
		bumrah.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void nzOver44() {
		gameProcessor.startNewOver();
		bumrah.setBowlingStatus(BowlingStatus.OtherBowler);
		mishra.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.dismisal(DismisalType.LBW, mishra, 0);
	}

	public void indiaOver1() {
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver2() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver3() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver4() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	public void indiaOver5() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver6() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}

	public void indiaOver7() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver8() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver9() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver10() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		kohli.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.LBW, neesham, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver11() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.WIDE, 4, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.LEG_BYE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver12() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver13() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		pandey.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, ronchi, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver14() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver15() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver16() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		sodhi.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver17() {
		gameProcessor.startNewOver();
		sodhi.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver18() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		sodhi.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver19() {
		gameProcessor.startNewOver();
		sodhi.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 3, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	public void indiaOver20() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		sodhi.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		dhoni.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, williamson, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	public void indiaOver21() {
		gameProcessor.startNewOver();
		sodhi.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver22() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver23() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver24() {
		gameProcessor.startNewOver();
		santner.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}

	public void indiaOver25() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver26() {
		gameProcessor.startNewOver();
		santner.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	public void indiaOver27() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver28() {
		gameProcessor.startNewOver();
		santner.setBowlingStatus(BowlingStatus.OtherBowler);
		southee.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver29() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		jadhav.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.runOut(guptill, 0, 0, 0, BallType.DOT_BALL, DismisalType.RUNOUT);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	public void indiaOver30() {
		gameProcessor.startNewOver();
		southee.setBowlingStatus(BowlingStatus.OtherBowler);
		neesham.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver31() {
		gameProcessor.startNewOver();
		neesham.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	public void indiaOver32() {
		gameProcessor.startNewOver();
		santner.setBowlingStatus(BowlingStatus.OtherBowler);
		sodhi.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}
	
	public void indiaOver33() {
		gameProcessor.startNewOver();
		sodhi.setBowlingStatus(BowlingStatus.OtherBowler);
		santner.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}
	
	public void indiaOver34() {
		gameProcessor.startNewOver();
		santner.setBowlingStatus(BowlingStatus.OtherBowler);
		sodhi.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}
}