package backend;

import game.BattingScore;
import game.BowlingScore;
import game.Game;
import game.Player;
import game.Rule;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import beans.BattingOverBean;
import beans.BattingWicketBean;
import beans.Coordinate;

public class XMLInteraction {

	private static final String N = "\n";
	private static final String T = "\t";
	private static final String Q = "\"";
	private String output;

//	public XMLInteraction(Context context) {
//		this.context = context;
//	}
	
	public String writeXML(Game game) {
		StringBuffer xml = new StringBuffer();

		xml.append("<game>" + N);

		xml.append(T + "<gameSettings>" + N);
		xml.append(T + T + "<gameType type=" + Q + game.getGameSettings().getGameType() + Q + "/>" + N);

		xml.append(T + T + "<rules>" + N);
		for (Rule rule : game.getGameSettings().getRules()) {
			xml.append(T + T + T + "<rule ruleNumber=" + Q + rule.getRuleNumber() + Q + " numberOfBowlers=" + Q + rule.getNumberOfBowlers() + Q
					+ " maxOversAllowed=" + Q + rule.getMaxOversAllowed() + Q + "/>" + N);
		}
		xml.append(T + T + "</rules>" + N);
		xml.append(T + "</gameSettings>" + N);

		xml.append(T + "<gameStatus>" + N);
		xml.append(T + T + "<target>" + game.getTarget() + "</target>" + N);
		xml.append(T + T + "<gameSummary>" + N);
		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
		xml.append(T + T + "</gameSummary>" + N);
		xml.append(T + "</gameStatus>" + N);

		xml.append(T + "<team1>" + N);
		xml.append(T + T + "<teamName>" + game.getTeam(1).getTeamName() + "</teamName>" + N);
		xml.append(T + T + "<lastBowlerId>" + game.getTeam(1).getLastBowlerId() + "</lastBowlerId>" + N);
		for (String playerName : game.getTeam(1).getAllPlayerNames()) {
			Player player = game.getTeam(1).getPlayerByName(playerName);
			playerToXml(game, player, xml);
		}
		xml.append(T + "</team1>" + N);

		xml.append(T + "<team2>");
		xml.append(T + T + "<teamName>" + game.getTeam(2).getTeamName() + "</teamName>" + N);
		xml.append(T + T + "<lastBowlerId>" + game.getTeam(2).getLastBowlerId() + "</lastBowlerId>" + N);
		for (String playerName : game.getTeam(2).getAllPlayerNames()) {
			Player player = game.getTeam(2).getPlayerByName(playerName);
			playerToXml(game, player, xml);
		}
		xml.append(T + "</team2>" + N);
		xml.append("</game>" + N);
		
		output = xml.toString();

		return xml.toString();
	}

	private void playerToXml(Game fullGame, Player player, StringBuffer xml) {
		xml.append(T + "<player>" + N);
		xml.append(T + T + "<name>" + player.getName() + "</name>" + N);
		xml.append(T + T + "<id>" + player.getId() + "</id>" + N);
		xml.append(T + T + "<battingStatus type=" + Q + player.getBattingStatus() + Q + "/>" + N);
		xml.append(T + T + "<bowlingStatus type=" + Q + player.getBowlingStatus() + Q + "/>" + N);

		// Bowling
		BowlingScore bowlingScore = player.getBowlingScore();
		xml.append(T + T + "<bowlingScore>" + N);
		xml.append(T + T + T + "<legByes>" + bowlingScore.getLegByes() + "</legByes>" + N);
		xml.append(T + T + T + "<legByeRuns>" + bowlingScore.getLegByeRuns() + "</legByeRuns>" + N);
		xml.append(T + T + T + "<byes>" + bowlingScore.getByes() + "</byes>" + N);
		xml.append(T + T + T + "<byeRuns>" + bowlingScore.getByeRuns() + "</byeRuns>" + N);
		xml.append(T + T + T + "<wides>" + bowlingScore.getWides() + "</wides>" + N);
		xml.append(T + T + T + "<wideRuns>" + bowlingScore.getWideRuns() + "</wideRuns>" + N);
		xml.append(T + T + T + "<noBalls>" + bowlingScore.getNoBalls() + "</noBalls>" + N);
		xml.append(T + T + T + "<noBallRuns>" + bowlingScore.getNoBallRuns() + "</noBallRuns>" + N);
		xml.append(T + T + "</bowlingScore>" + N);

		// Batting
		BattingScore battingScore = player.getBattingScore();
		xml.append(T + T + "<battingScore>" + N);
		xml.append(T + T + T + "<battingSlot>" + battingScore.getBattingSlot() + "</battingSlot>" + N);
		xml.append(T + T + T + "<battingStyle type=" + Q + battingScore.getBattingStyle() + Q + "/>" + N);

		// Batting Wicket
		BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
		if (battingWicketBean != null) {
			xml.append(T + T + T + "<battingWicketBean>" + N);
			xml.append(T + T + T + T + "<bowlerId>" + battingWicketBean.getBowlerId() + "</bowlerId>" + N);
			xml.append(T + T + T + T + "<dismisalType type=" + Q + battingWicketBean.getDismisalType() + Q + "/>" + N);
			xml.append(T + T + T + T + "<overNumber>" + battingWicketBean.getOverNumber() + "</overNumber>" + N);
			xml.append(T + T + T + T + "<ballNumber>" + battingWicketBean.getBallNumber() + "</ballNumber>" + N);
			xml.append(T + T + T + T + "<fielderId>" + battingWicketBean.getFielderId() + "</fielderId>" + N);
			xml.append(T + T + T + "</battingWicketBean>" + N);
		}

		// BattingScore
		Map<Integer, BattingOverBean> battingOvers = battingScore.getBattingOvers();

		for (Entry<Integer, BattingOverBean> battingOver : battingOvers.entrySet()) {
			xml.append(T + T + T + "<battingOvers>" + N);
			xml.append(T + T + T + T + "<entry>" + N);
			xml.append(T + T + T + T + T + "<key>" + N);
			xml.append(T + T + T + T + T + T + "<overNumber>" + battingOver.getKey() + "</overNumber>" + N);
			xml.append(T + T + T + T + T + "</key>" + N);
			xml.append(T + T + T + T + T + "<value>" + N);
			xml.append(T + T + T + T + T + T + "<battingOverBean>" + N);

			BattingOverBean value = battingOver.getValue();
			xml.append(T + T + T + T + T + T + T + "<overNumber>" + value.getOverNumber() + "</overNumber>" + N);
			xml.append(T + T + T + T + T + T + T + "<bowlerId>" + value.getBowlerId() + "</bowlerId>" + N);

			Stack<Integer> runs = value.getRuns();
			xml.append(T + T + T + T + T + T + T + "<runs>" + N);
			for (Integer run : runs) {
				xml.append(T + T + T + T + T + T + T + T + "<run>" + run + "</run>" + N);
			}
			xml.append(T + T + T + T + T + T + T + "</runs>" + N);

			Stack<Coordinate> cordinatesOfAllRuns = value.getCordinatesOfAllRuns();
			xml.append(T + T + T + T + T + T + T + "<cordinatesOfRun>" + N);
			for (Coordinate coordinate : cordinatesOfAllRuns) {
				xml.append(T + T + T + T + T + T + T + T + "<cordinate>" + N);
				xml.append(T + T + T + T + T + T + T + T + T + "<rawX>" + coordinate.getRawX() + "</rawX>" + N);
				xml.append(T + T + T + T + T + T + T + T + T + "<rawY>" + coordinate.getRawY() + "</rawY>" + N);
				xml.append(T + T + T + T + T + T + T + T + T + "<run>" + coordinate.getRun() + "</run>" + N);
				xml.append(T + T + T + T + T + T + T + T + "</cordinate>" + N);
			}
			xml.append(T + T + T + T + T + T + T + "</cordinatesOfRun>" + N);
			xml.append(T + T + T + T + T + T + "</battingOverBean>" + N);
			xml.append(T + T + T + T + T + "</value>" + N);
			xml.append(T + T + T + T + "</entry>" + N);
			xml.append(T + T + T + "</battingOvers>" + N);
		}

		xml.append(T + T + "</battingScore>" + N);
		xml.append(T + "</player>" + N);
	}

	public String getOutput() {
		return output;
	}
	

	
	public void readXML(String xml) throws IOException {
//		XmlPullParserFactory pullParserFactory;
//		try {
//			pullParserFactory = XmlPullParserFactory.newInstance();
//			XmlPullParser parser = pullParserFactory.newPullParser();
//			
//			 InputStream in_s = getFileAsStream(output);
//		        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//	            parser.setInput(in_s, null);
//
//	            parseXML(parser);
//	            
//		} catch (XmlPullParserException e) {
//			e.printStackTrace();
//		}
	}
	
	
	
}