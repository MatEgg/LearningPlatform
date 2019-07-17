package learningplatform.learningplatform.calculation;

import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class MostDifficultyCalculation {

	private int decimalLevel;
	private int bigDigitLevel;
	private int numericalLevel;
	private Operators operator;

	public MostDifficultyCalculation(int decimalLevel, int bigDigitLevel, int numericalLevel, Operators operator) {
		this.decimalLevel = decimalLevel;
		this.bigDigitLevel = bigDigitLevel;
		this.numericalLevel = numericalLevel;
		this.operator = operator;
	}

	public int getDecimalLevel() {
		return decimalLevel;
	}

	public void setDecimalLevel(int decimalLevel) {
		this.decimalLevel = decimalLevel;
	}

	public int getBigDigitLevel() {
		return bigDigitLevel;
	}

	public void setBigDigitLevel(int bigDigitLevel) {
		this.bigDigitLevel = bigDigitLevel;
	}

	public int getNumericalLevel() {
		return numericalLevel;
	}

	public void setNumericalLevel(int numericalLevel) {
		this.numericalLevel = numericalLevel;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}
}
