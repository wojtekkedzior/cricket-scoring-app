package common;

import java.io.Serializable;
import java.util.HashMap;

public enum BallType implements Serializable {

	NO_BALL_EXTRA("No Ball Extra"), 
	NO_BALL_RUN("No Ball Run"), 
	WIDE("Wide"), 
	DOT_BALL("Dot Ball"), 
	SCORING("Scoring"), 
	WICKET("Wicket"), 
	BYE("Bye"), 
	LEG_BYE("Leg BYe"), 
	DEAD_BALL("Dead Ball"); 
	
	private static HashMap<String, BallType> reverseLookUp = new HashMap<String, BallType>();
	private String ballType;

	static {
		BallType[] values = values();
		for (BallType ballType : values) {
			reverseLookUp.put(ballType.getBallTypeName(), ballType);
		}
	}

	BallType(String ballType) {
	        this.ballType = ballType;
	    }
	
	public String getBallTypeName() {
		return ballType;
	}
	
	public static BallType get(String string) {
		return reverseLookUp.get(string);
   }
																		

}
