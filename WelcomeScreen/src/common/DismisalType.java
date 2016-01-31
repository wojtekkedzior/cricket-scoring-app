package common;

import java.io.Serializable;
import java.util.HashMap;

public enum DismisalType implements Serializable {
	BOWLED ("Bowled"), 
	RUNOUT ("Run out"), 
	NON_STRIKER_RUNOUT ("Non striker Run out"), 
	CAUGHT ("Caught"), 
	STUMPED ("Stumped"), 
	LBW ("LBW"), 
	HIT_WICKET ("Hit Wicket");
	
	static HashMap<String, DismisalType> reverseLookUp = new HashMap<String, DismisalType>();

	static {
		DismisalType[] values = values();
		for (DismisalType dismisalType : values) {
			reverseLookUp.put(dismisalType.getDismissalTypeName(), dismisalType);
		}
	}
	    
	private String dismissalTypeName;

	DismisalType(String dismissalTypeName) {
	        this.dismissalTypeName = dismissalTypeName;
	    }
	
	public String getDismissalTypeName() {
		return dismissalTypeName;
	}
	
	public static DismisalType get(String string) {
		return reverseLookUp.get(string);
   }
 
}
