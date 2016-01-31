package beans;


import java.io.Serializable;

import common.DismisalType;

public class BattingWicketBean implements Serializable {

	private static final long serialVersionUID = -7723568872275614869L;

	private Integer bowlerId;
	private DismisalType dismisalType;
	private int overNumber;
	private int ballNumber;
	private int fielderId;

	public BattingWicketBean(Integer bowlerId, DismisalType dismisalType, int overNumber, int ballNumber,  int fielderId) {
		this.bowlerId = bowlerId;
		this.dismisalType = dismisalType;
		this.overNumber = overNumber;
		this.ballNumber = ballNumber;
		this.fielderId = fielderId;
		this.ballNumber = ballNumber;
	}

	public DismisalType getDismisalType() {
		return dismisalType;
	}

	public Integer getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(Integer bowlerId) {
		this.bowlerId = bowlerId;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public int getFielderId() {
		return fielderId;
	}

	public void setFielderId(int fielderId) {
		this.fielderId = fielderId;
	}

	public void setDismisalType(DismisalType dismisalType) {
		this.dismisalType = dismisalType;
	}
}
