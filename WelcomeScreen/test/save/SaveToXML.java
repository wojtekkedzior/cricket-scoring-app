package save;

import static org.junit.Assert.assertEquals;
import game.Game;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import other.GameData;
import backend.XMLInteraction;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;

//import static org.mockito.Mockito.*;

public class SaveToXML {

//	private static final String N = "\n";
//	private static final String T = "\t";
//	private static final String Q = "\"";
	
	private XMLInteraction xml;
//	@Mock
//	private Context context;
	
	
	@Before
	public void setUp() {
//		MockitoAnnotations.initMocks(this);
		xml = new XMLInteraction();
	}
	
	@Test
	public void testWriteXML() {
		GameData gameData = new GameData();
		Game fullGame = gameData.getFullGame();
		xml.writeXML(fullGame);
		
		String output = xml.getOutput();
//		System.err.println(output.length());
		assertEquals(63583, output.length());
	}
	
	@Test
	public void testReadXML() throws IOException {
		GameData gameData = new GameData();
		Game fullGame = gameData.getFullGame();
		String output = xml.writeXML(fullGame);
		
		//StandardCharsets.UTF_8
//		System.err.println(output);
		
//		when(context.getAssets().open("")).thenReturn(stream);
//		xml.readXML();
		
//		System.err.println(output.length());
//		System.err.println(output);
		assertEquals(63583, output.length());
	}
	
	
	
	
	
	

//	@Test
//	public void test() {
//		StringBuffer xml = new StringBuffer();
//		String gameType = "asdas"; 
//		
//		xml.append("<game>" + N);
//		
//		xml.append(T + "<gameSettings>" + N);
//		xml.append("<gameType type=" + Q + gameType + Q + "/>" + N);
//		xml.append("<rules>" + N);
//		xml.append(T + T + T + "<rule ruleNumber=" + Q + 1 + Q + " numberOfBowlers=" + Q + 1 + Q + " maxOversAllowed=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + T + "<rule ruleNumber=" + Q + 1 + Q + " numberOfBowlers=" + Q + 1 + Q + " maxOversAllowed=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + "<rules/>" + N);
//		xml.append(T + "</gameSettings>" + N);
//			
//		xml.append(T + "<gameStatus>" + N);
//		xml.append(T + T + "<target=" + Q + 1 + Q + "</target>" + N);
//		xml.append(T + T + "<gameSummary>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + "</gameSummary>" + N);
//		xml.append(T + "</gameStatus>"+ N);	
//			
//		xml.append(T + "<team1>");
//		xml.append(T + T + "<teamName>" + Q + 1 + Q + "</teamName>"+N);
//		xml.append(T + T + "<lastBowlerId>" + Q + 1 + Q + "</lastBowlerId>"+N);
//
//		xml.append(T + T + "<players>" +N);
//		xml.append(T + T + T+ "<name=" + Q + 1 + Q + "</name>" + N);
//		xml.append(T + T + T+ "<id=" + Q + 1 + Q + "</id>" + N);
//		
//		xml.append(T + T + "</players>" +N);
//		
//		xml.append(T + "</team1>");
//			
//			xml.append("<team2>");
//			xml.append("");
//			xml.append("</team2>");
//			
//		xml.append("</game>");
//		
//		System.err.println(xml.toString());
//	}

	
	
//	@Test
//	@Ignore
//	public void testasdaML() {
//		GameData gameData = new GameData();
//		Game fullGame = gameData.getFullGame();
//		
//		StringBuffer xml = new StringBuffer();
//		
//		xml.append("<game>" + N);
//		
//		xml.append(T + "<gameSettings>" + N);
//		xml.append(T + T + "<gameType type=" + Q + fullGame.getGameSettings().getGameType() + Q + "/>" + N);
//		
//		xml.append(T + T + "<rules>" + N);
//		for (Rule rule : fullGame.getGameSettings().getRules()) {
//			xml.append(T + T + T + "<rule ruleNumber=" + Q + rule.getRuleNumber() + Q + 
//					" numberOfBowlers=" + Q + rule.getNumberOfBowlers() + Q + 
//					" maxOversAllowed=" + Q + rule.getMaxOversAllowed() + Q + "/>" + N);
//		}
//		xml.append(T + T + "<rules/>" + N);
//		xml.append(T + "</gameSettings>" + N);
//			
//		xml.append(T + "<gameStatus>" + N);
//		xml.append(T + T + "<target=" + fullGame.getTarget() + "</target>" + N);
//		xml.append(T + T + "<gameSummary>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + T + "<entry key=" + Q + 1 + Q + " value=" + Q + 1 + Q + "/>" + N);
//		xml.append(T + T + "</gameSummary>" + N);
//		xml.append(T + "</gameStatus>"+ N);	
//		
//		xml.append(T + "<team1>" + N);
//		xml.append(T + T + "<teamName>" + Q + fullGame.getTeam(1).getTeamName() + Q + "</teamName>"+N);
//		xml.append(T + T + "<lastBowlerId>" + Q + fullGame.getTeam(1).getLastBowlerId() + Q + "</lastBowlerId>"+N);
//		for (String playerName : fullGame.getTeam(1).getAllPlayerNames()) {
//			Player player = fullGame.getTeam(1).getPlayerByName(playerName);
//			playerToXml(fullGame, player, xml);
//		}
//		xml.append(T + "</team1>" + N);
//		
//		xml.append(T + "<team2>");
//		xml.append(T + T + "<teamName>" + Q + fullGame.getTeam(2).getTeamName() + Q + "</teamName>"+N);
//		xml.append(T + T + "<lastBowlerId>" + Q + fullGame.getTeam(2).getLastBowlerId() + Q + "</lastBowlerId>"+N);
//		for (String playerName : fullGame.getTeam(2).getAllPlayerNames()) {
//			Player player = fullGame.getTeam(2).getPlayerByName(playerName);
//			playerToXml(fullGame, player, xml);
//		}
//		xml.append(T + "</team2>" + N);
//
//		xml.append("</game>" + N);
//		
//		System.err.println(xml.toString());
//	}
//
//
//	private void playerToXml(Game fullGame, Player player, StringBuffer xml) {
//			xml.append(T + "<player>" + N);
//			xml.append(T + T + "<name>" + player.getName() + "</name>" + N);
//			xml.append(T + T + "<id>"+ player.getId() + "</id>" + N);
//			xml.append(T + T + "<battingStatus type=" + Q + player.getBattingStatus() + Q + "/>" + N);
//			xml.append(T + T + "<bowlingStatus type=" + Q + player.getBowlingStatus() + Q + "/>" + N);
//			
//			//Bowling
//			BowlingScore bowlingScore = player.getBowlingScore();
//			xml.append(T + T + "<bowlingScore>" + N);
//			xml.append(T + T + T + "<legByes>" + bowlingScore.getLegByes() + "</legByes>" + N);
//			xml.append(T + T + T + "<legByeRuns>" + bowlingScore.getLegByeRuns() + "</legByeRuns>" + N);
//			xml.append(T + T + T + "<byes>" + bowlingScore.getByes() + "</byes>" + N);
//			xml.append(T + T + T + "<byeRuns>"+ bowlingScore.getByeRuns() + "</byeRuns>" + N);
//			xml.append(T + T + T + "<wides>" + bowlingScore.getWides() + "</wides>" + N);
//			xml.append(T + T + T + "<wideRuns>" + bowlingScore.getWideRuns() + "</wideRuns>" + N);
//			xml.append(T + T + T + "<noBalls>" + bowlingScore.getNoBalls() + "</noBalls>" + N);
//			xml.append(T + T + T + "<noBallRuns>" + bowlingScore.getNoBallRuns() + "</noBallRuns>" + N);
//			xml.append(T + T + "</bowlingScore>" + N);
//			
//			//Batting
//			BattingScore battingScore = player.getBattingScore();
//			xml.append(T + T + "<battingScore>" + N);
//			xml.append(T + T + T + "<battingSlot>" + battingScore.getBattingSlot() + "</battingSlot>" + N);
//			xml.append(T + T + T + "<battingStyle type=" + Q + battingScore.getBattingStyle() + Q + "/>" + N);
//			
//			//Batting Wicket
//			BattingWicketBean battingWicketBean = battingScore.getBattingWicketBean();
//			if(battingWicketBean != null) {
//				xml.append(T + T + T + "<battingWicketBean>" + N);
//				xml.append(T + T + T + T + "<bowlerId>" +  battingWicketBean.getBowlerId() + "</bowlerId>" + N);
//				xml.append(T + T + T + T + "<dismisalType type=" + Q + battingWicketBean.getDismisalType() + Q + "/>" + N);
//				xml.append(T + T + T + T + "<overNumber>" + battingWicketBean.getOverNumber() + "</overNumber>" + N);
//				xml.append(T + T + T + T + "<ballNumber>" + battingWicketBean.getBallNumber() + "</ballNumber>" + N);
//				xml.append(T + T + T + T + "<fielderId>" + battingWicketBean.getFielderId() + "</fielderId>" + N);
//				xml.append(T + T + T + "</battingWicketBean>" + N);
//			}
//			
//			//BattingScore
//			Map<Integer, BattingOverBean> battingOvers = battingScore.getBattingOvers();
//			
//			for (Entry<Integer, BattingOverBean> battingOver : battingOvers.entrySet()) {
//				xml.append(T + T + T + "<battingOvers>" + N);
//				xml.append(T + T + T + T + "<entry>" + N);
//				xml.append(T + T + T + T + T + "<key>" + N);
//				xml.append(T + T + T + T + T + T +"<overNumber>" + battingOver.getKey() + "</overNumber>" + N);
//				xml.append(T + T + T + T + T + "</key>" + N);
//				xml.append(T + T + T + T + T + "</value>" + N);
//				xml.append(T + T + T + T + T + T + "<battingOverBean>" + N);
//				
//				BattingOverBean value = battingOver.getValue();
//				xml.append(T + T + T + T + T + T + T + "<overNumber>" + value.getOverNumber() + "</overNumber>" + N);
//				xml.append(T + T + T + T + T + T + T + "<bowlerId>" + value.getBowlerId() + "</bowlerId>" + N);
//				
//				Stack<Integer> runs = value.getRuns();
//				xml.append(T + T + T + T + T + T + T + "<runs>" + N);
//				for (Integer run : runs) {
//					xml.append(T + T + T + T + T + T + T + T + "<run>" + run + "</run>" + N);
//				}
//				xml.append(T + T + T + T + T + T + T + "</runs>" + N);
//				
//				Stack<Coordinate> cordinatesOfAllRuns = value.getCordinatesOfAllRuns();
//				xml.append(T + T + T + T + T + T + T + "<cordinatesOfRun>" + N);
//				for (Coordinate coordinate : cordinatesOfAllRuns) {
//					xml.append(T + T + T + T + T + T + T + T + "<cordinate>" + N);
//					xml.append(T + T + T + T + T + T + T + T + T + "<rawX>" + coordinate.getRawX() + "</rawX>" + N);
//					xml.append(T + T + T + T + T + T + T + T + T + "<rawY>" + coordinate.getRawY() + "</rawY>" + N);
//					xml.append(T + T + T + T + T + T + T + T + T + "<run>" + coordinate.getRun() + "</run>" + N);
//					xml.append(T + T + T + T + T + T + T + T + "</cordinate>" + N);
//				}
//				xml.append(T + T + T + T + T + T + T + "</cordinatesOfRun>" + N);
//				xml.append(T + T + T + T + T + T + "</battingOverBean>" + N);
//				xml.append(T + T + T + T + T + "</value>" + N);
//				xml.append(T + T + T + T + "</entry>" + N);
//				xml.append(T + T + T + "</battingOvers/>" + N);
//			}
//			
//			xml.append(T + T + "</battingScore/>" + N);
//			xml.append(T + "</player>" + N);
//	}
//	
	
	
//	xml.append(T + T + T + "<battingWicketBean bowlerId=" + Q + battingWicketBean.getBowlerId() +  Q +
//	" dismisalType=" + Q +battingWicketBean.getDismisalType() + Q +
//	" overNumber=" +  Q +battingWicketBean.getOverNumber() +  Q +
//	" ballNumber=" +  Q +battingWicketBean.getBallNumber() +  Q +
//	" fielderId=" +  Q +battingWicketBean.getFielderId() +  Q +"/>" + N);
	

}
