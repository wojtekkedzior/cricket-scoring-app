package other;

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

public class GameData {

	private GameProcessor gameProcessor;
	private Game game = new Game();

	public final Team nzTeam = game.getTeam(1);
	public final Player mills = new Player("Mills", 21);
	public final Player bracewell = new Player("Bracewell", 22);
	public final Player oram = new Player("Oram", 23);
	public final Player woodcock = new Player("Woodcock", 24);
	public final Player nathanMcCullum = new Player("Nathan McCullum", 25);
	public final Player brendonMcCullum = new Player("Brendon McCullum", 26);
	public final Player franklin = new Player("Franklin", 27);
	public final Player rossTaylor = new Player("Taylor", 28);
	public final Player guptill = new Player("Guptill", 29);
	public final Player ryder = new Player("Ryder", 30);
	public final Player williamson = new Player("Williamson", 31);

	public final Team zimbabweTeam = game.getTeam(2);
	public final Player masakadza = new Player("Masakadza", 1);
	public final Player chibhabha = new Player("Chibhabha", 2);
	public final Player brendonTaylor = new Player("Brendon Taylor", 3);
	public final Player mutizwa = new Player("Mutizwa", 4);
	public final Player waller = new Player("Waller", 5);
	public final Player coventry = new Player("Coventry", 6);
	public final Player chigumbura = new Player("Chigumbura", 7);
	public final Player chakabva = new Player("Chakabva", 8);
	public final Player utseya = new Player("Utseya", 9);
	public final Player rayPrice = new Player("Price", 10);
	public final Player kyleJarvis = new Player("Jarvis", 11);

	public Game getGame(boolean setTeams) {
		game.setGameSettings(new GameSettings(GameType.TwentyTwenty));

		nzTeam.setTeamName("New Zealand");
		nzTeam.addPlayer(mills);
		nzTeam.addPlayer(bracewell);
		nzTeam.addPlayer(oram);
		nzTeam.addPlayer(woodcock);
		nzTeam.addPlayer(nathanMcCullum);
		nzTeam.addPlayer(franklin);
		nzTeam.addPlayer(brendonMcCullum);
		nzTeam.addPlayer(rossTaylor);
		nzTeam.addPlayer(guptill);
		nzTeam.addPlayer(ryder);
		nzTeam.addPlayer(williamson);

		zimbabweTeam.setTeamName("Zimbabwe");
		zimbabweTeam.addPlayer(masakadza);
		zimbabweTeam.addPlayer(chibhabha);
		zimbabweTeam.addPlayer(brendonTaylor);
		zimbabweTeam.addPlayer(mutizwa);
		zimbabweTeam.addPlayer(waller);
		zimbabweTeam.addPlayer(coventry);
		zimbabweTeam.addPlayer(chigumbura);
		zimbabweTeam.addPlayer(chakabva);
		zimbabweTeam.addPlayer(utseya);
		zimbabweTeam.addPlayer(rayPrice);
		zimbabweTeam.addPlayer(kyleJarvis);

		if (setTeams) {
			nzTeam.setTeamBowlingStatus(TeamBowlingStatus.Bowling);
			nzTeam.setTeamBattingStatus(TeamBattingStatus.YetToBat);

			zimbabweTeam.setTeamBattingStatus(TeamBattingStatus.Batting);
			zimbabweTeam.setTeamBowlingStatus(TeamBowlingStatus.YetToBowl);

			masakadza.setBattingStatus(BattingStatus.Striker);
			chibhabha.setBattingStatus(BattingStatus.NonStriker);

			mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);
			bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		}

		return game;

	}

	public Game getFullGame() {
		getGame(true);

		game.setGameSettings(new GameSettings(GameType.TwentyTwenty));
		gameProcessor = new GameProcessor(game);

		firstOver();
		secondOver();
		thirdOver();
		fourthOver();
		fithOver();
		sixthOver();
		seventhOver();
		eigthOver();
		ninthOver();
		tenthOver();
		eleventhOver();
		twelthOver();
		thirteenthOver();
		fourteenthOver();
		fithteenthOver();
		sixteenthOver();
		seventeenthOver();
		eighteenthOver();
		nineteenthOver();
		twentiethOver();

		game.changeTeamsAround(TeamBattingStatus.Batting_Overs_Finished);
		gameProcessor.updateGame(game);

		guptill.setBattingStatus(BattingStatus.Striker);
		brendonMcCullum.setBattingStatus(BattingStatus.NonStriker);
		
		gameProcessor.startNewInnings();
		gameProcessor.updateGame(game);
		
		firstNZOver();
		secondNZOver();
		thirdNZOver();
		fourthNZOver();
		fithNZOver();
		sixthNZOver();
		seventhNZOver();
		eightNZOver();
		ninthtNZOver();
		tenthNZOver();
		eleventhNZOver();
		twelthNZOver();
		thirteenthNZOver();
		fourteenthNZOver();

		return game;
	}

	private void firstOver() {
		
		mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	private void secondOver() {
		gameProcessor.startNewOver();
		mills.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void thirdOver() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		brendonTaylor.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, nathanMcCullum, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	private void fourthOver() {
		gameProcessor.startNewOver();
		mills.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	private void fithOver() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		mutizwa.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, brendonMcCullum, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void sixthOver() {
		gameProcessor.startNewOver();
		mills.setBowlingStatus(BowlingStatus.OtherBowler);
		oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 3, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void seventhOver() {
		gameProcessor.startNewOver();
		oram.setBowlingStatus(BowlingStatus.OtherBowler);
		woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void eigthOver() {
		gameProcessor.startNewOver();
		woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void ninthOver() {
		gameProcessor.startNewOver();
		oram.setBowlingStatus(BowlingStatus.OtherBowler);
		woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		waller.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.LBW, null, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void tenthOver() {
		gameProcessor.startNewOver();
		woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void eleventhOver() {
		gameProcessor.startNewOver();
		nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 3, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void twelthOver() {
		gameProcessor.startNewOver();
		woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		coventry.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, woodcock, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void thirteenthOver() {
		gameProcessor.startNewOver();
		nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		franklin.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.NO_BALL_EXTRA, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.NO_BALL_RUN, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void fourteenthOver() {
		gameProcessor.startNewOver();
		franklin.setBowlingStatus(BowlingStatus.OtherBowler);
		mills.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void fithteenthOver() {
		gameProcessor.startNewOver();
		mills.setBowlingStatus(BowlingStatus.OtherBowler);
		woodcock.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	private void sixteenthOver() {
		gameProcessor.startNewOver();
		woodcock.setBowlingStatus(BowlingStatus.OtherBowler);
		nathanMcCullum.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		chigumbura.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, rossTaylor, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0); // batsman crossed
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void seventeenthOver() {
		gameProcessor.startNewOver();
		nathanMcCullum.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.NO_BALL_RUN, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	private void eighteenthOver() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void nineteenthOver() {
		gameProcessor.startNewOver();
		oram.setBowlingStatus(BowlingStatus.OtherBowler);
		bracewell.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		chakabva.setBattingStatus(BattingStatus.NewBatsmanOffStrike);
		gameProcessor.dismisal(DismisalType.CAUGHT, rossTaylor, 0);
	}

	private void twentiethOver() {
		gameProcessor.startNewOver();
		bracewell.setBowlingStatus(BowlingStatus.OtherBowler);
		oram.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		utseya.setBattingStatus(BattingStatus.NewBatsman);
		gameProcessor.dismisal(DismisalType.CAUGHT, brendonMcCullum, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);

		gameProcessor.runOut(oram, 0, 0, 0, BallType.SCORING, DismisalType.NON_STRIKER_RUNOUT);
	}

	private void firstNZOver() {
		kyleJarvis.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.NO_BALL_EXTRA, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	private void secondNZOver() {
		gameProcessor.startNewOver();
		kyleJarvis.setBowlingStatus(BowlingStatus.OtherBowler);
		rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	private void thirdNZOver() {
		gameProcessor.startNewOver();
		rayPrice.setBowlingStatus(BowlingStatus.OtherBowler);
		kyleJarvis.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
	}

	private void fourthNZOver() {
		gameProcessor.startNewOver();
		kyleJarvis.setBowlingStatus(BowlingStatus.OtherBowler);
		rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.LEG_BYE, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	private void fithNZOver() {
		gameProcessor.startNewOver();
		rayPrice.setBowlingStatus(BowlingStatus.OtherBowler);
		chigumbura.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	private void sixthNZOver() {
		gameProcessor.startNewOver();
		chigumbura.setBowlingStatus(BowlingStatus.OtherBowler);
		rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void seventhNZOver() {
		gameProcessor.startNewOver();
		rayPrice.setBowlingStatus(BowlingStatus.OtherBowler);
		chigumbura.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void eightNZOver() {
		gameProcessor.startNewOver();
		chigumbura.setBowlingStatus(BowlingStatus.OtherBowler);
		utseya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void ninthtNZOver() {
		gameProcessor.startNewOver();
		utseya.setBowlingStatus(BowlingStatus.OtherBowler);
		chigumbura.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
	}

	private void tenthNZOver() {
		gameProcessor.startNewOver();
		chigumbura.setBowlingStatus(BowlingStatus.OtherBowler);
		utseya.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void eleventhNZOver() {
		gameProcessor.startNewOver();
		utseya.setBowlingStatus(BowlingStatus.OtherBowler);
		kyleJarvis.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.WIDE, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}

	private void twelthNZOver() {
		gameProcessor.startNewOver();
		kyleJarvis.setBowlingStatus(BowlingStatus.OtherBowler);
		chibhabha.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 4, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
	}

	private void thirteenthNZOver() {
		gameProcessor.startNewOver();
		chibhabha.setBowlingStatus(BowlingStatus.OtherBowler);
		masakadza.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 2, 0, 0);
	}

	private void fourteenthNZOver() {
		gameProcessor.startNewOver();
		masakadza.setBowlingStatus(BowlingStatus.OtherBowler);
		rayPrice.setBowlingStatus(BowlingStatus.CurrentlyBowling);

		gameProcessor.delivery(BallType.DOT_BALL, 0, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 1, 0, 0);
		gameProcessor.delivery(BallType.SCORING, 6, 0, 0);
	}
}