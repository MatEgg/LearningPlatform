package learningplatform.learningplatform.calculation;

import org.hibernate.cfg.beanvalidation.GroupsPerOperation.Operation;

import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class SingleCalculation {

	private float firstValue;
	private float secondValue;
	private Operators operator;

	public SingleCalculation(float firstValue, float secondValue, String operator) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.operator = getOperatorByString(operator);
	}

	public float getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(float firstValue) {
		this.firstValue = firstValue;
	}

	public float getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(float secondValue) {
		this.secondValue = secondValue;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	private Operators getOperatorByString(String operator) {
		switch (operator) {
		case "+":
			return Operators.ADDITION;
		case "-":
			return Operators.SUBSTRACTION;
		case "*":
			return Operators.MULTIPLICATION;
		case "/":
			return Operators.DIVISION;
		default:
			return null;
		}
	}
}
