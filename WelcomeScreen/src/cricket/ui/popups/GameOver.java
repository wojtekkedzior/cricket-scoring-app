package cricket.ui.popups;

import game.Game;
import game.GameStatus;

import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import backend.Storage;

import common.Keys;

import cricket.ui.GenericActivity;
import cricket.ui.R;

public class GameOver extends GenericActivity {

	private Game game;
	private GameStatus gameStatus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.popup_game_over);
		setFinishOnTouchOutside(false);

		Bundle extras = getIntent().getExtras();
		game = (Game) extras.getSerializable(Keys.GAME);
		gameStatus = game.getGameStatus();
		gameStatus.createGameSummary(game.getTeam(1), game.getTeam(2));

		// GameStatus gameStatus = new GameStatus(game.getTeam(1),
		// game.getTeam(2));

		// team 1 total
		((TextView) findViewById(R.id.team1Total)).setText(gameStatus.get(Keys.TEAM_1_SUMMARY));

		// Bataman
		((TextView) findViewById(R.id.team1TopBatsman)).setText(gameStatus.get(Keys.TEAM_1_TOP_BATSMAN));
		((TextView) findViewById(R.id.team1SecondBatsman)).setText(gameStatus.get(Keys.TEAM_1_SECOND_BATSMAN));

		// Bowlers - team bowling when the batting team (team1) was batting
		((TextView) findViewById(R.id.team2TopBowler)).setText(gameStatus.get(Keys.TEAM_2_TOP_BOWLER));
		((TextView) findViewById(R.id.team2SecondBowler)).setText(gameStatus.get(Keys.TEAM_2_SECOND_BOWLER));

		// team 2 total
		((TextView) findViewById(R.id.team2Total)).setText(gameStatus.get(Keys.TEAM_2_SUMMARY));

		// Batsman
		((TextView) findViewById(R.id.team2TopBatsman)).setText(gameStatus.get(Keys.TEAM_2_TOP_BATSMAN));
		((TextView) findViewById(R.id.team2SecondBatsman)).setText(gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN));

		// Bowlers
		((TextView) findViewById(R.id.team1TopBowler)).setText(gameStatus.get(Keys.TEAM_1_TOP_BOWLER));
		((TextView) findViewById(R.id.team1SecondBowler)).setText(gameStatus.get(Keys.TEAM_1_SECOND_BOWLER));

		// Winning team
		((TextView) findViewById(R.id.winningTeamLabel)).setText(gameStatus.get(Keys.WINNING_TEAM));
		
		//TODO By default save file to XML to storage, in a configured folder team1 vs team2 date
	}

	public void onShare(View view) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");

		// Twitter has a limit of 140 chars
		StringBuffer buf = new StringBuffer();

		buf.append(gameStatus.get(Keys.TEAM_1_SUMMARY) + "\n");
		buf.append(gameStatus.get(Keys.TEAM_1_TOP_BATSMAN) + "\n");
		// buf.append(gameStatus.get(Keys.TEAM_1_SECOND_BATSMAN) + "\n");
		buf.append(gameStatus.get(Keys.TEAM_2_TOP_BOWLER) + "\n");
		// buf.append(gameStatus.get(Keys.TEAM_2_SECOND_BOWLER) + "\n");

		buf.append(gameStatus.get(Keys.TEAM_2_SUMMARY) + "\n");
		buf.append(gameStatus.get(Keys.TEAM_2_TOP_BATSMAN) + "\n");
		// buf.append(gameStatus.get(Keys.TEAM_2_SECOND_BATSMAN) + "\n");
		buf.append(gameStatus.get(Keys.TEAM_1_TOP_BOWLER) + "\n");
		// buf.append(gameStatus.get(Keys.TEAM_1_SECOND_BOWLER) + "\n");

		shareIntent.putExtra(Intent.EXTRA_TEXT, buf.toString());

		startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));

		// String tweetUrl = "https://twitter.com/intent/tweet?text="+
		// buf.toString() +"&url=https://www.google.com";
		// Uri uri = Uri.parse(tweetUrl);
		// startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}

	public void onExit(View view) {
		//delete the temporary file - effectively start a new game
		//or have a close button to close the app. - maybe this is better. either way delete the temp file
		Storage.clear(this); 
	}

	public void onSaveToDevice(View view) throws IllegalArgumentException, IllegalStateException, IOException {
		//This step can probably be done in onCreate
		Storage.write(game, this);

		//TODO file name needs to come from a settings popup or something like that. or be hardcoded to team1 vs team 2 date
		String fileName = "myfile.xml";
		Storage.writeToXml(game, fileName, this);
	}
}