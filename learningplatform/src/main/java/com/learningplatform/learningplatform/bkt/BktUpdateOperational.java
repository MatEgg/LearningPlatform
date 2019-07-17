package com.learningplatform.learningplatform.bkt;

public class BktUpdateOperational {

	private BktTypeOperational bktTypeOperational;
	private boolean decimal;
	private boolean update;

	private boolean bigNumber;
	private boolean numericalType;
	private boolean result;

	public BktUpdateOperational(BktTypeOperational bktTypeOperational, boolean decimal, boolean bigNumber,
			boolean numericalType, boolean result, boolean update) {
		this.bktTypeOperational = bktTypeOperational;
		this.decimal = decimal;
		this.bigNumber = bigNumber;
		this.numericalType = numericalType;
		this.result = result;
		this.update = update;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public BktTypeOperational getBktTypeOperational() {
		return bktTypeOperational;
	}

	public void setBktTypeOperational(BktTypeOperational bktTypeOperational) {
		this.bktTypeOperational = bktTypeOperational;
	}

	public void setDecimal(boolean decimal) {
		this.decimal = decimal;
	}

	public boolean isBigNumber() {
		return bigNumber;
	}

	public void setBigNumber(boolean bigNumber) {
		this.bigNumber = bigNumber;
	}

	public boolean isNumericalType() {
		return numericalType;
	}

	public void setNumericalType(boolean numericalType) {
		this.numericalType = numericalType;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDecimal() {
		return decimal;
	}

}
