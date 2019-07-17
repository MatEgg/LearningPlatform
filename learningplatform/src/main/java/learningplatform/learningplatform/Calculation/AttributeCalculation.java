package learningplatform.learningplatform.Calculation;

import java.util.Iterator;
import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.types.OperatorHelper;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class AttributeCalculation {

	Attribute attribute;
	Boolean isPrimitive = true;
	CalculationInformation calculationInformation;
	float quantifier = -1;
	Operators operation;
	List<Attribute> attributes;
	private String calculatedDetailed;
	private String calculatedSimple;
	private Integer stepsNeeded = 0;
	private CalculationStats calculationStats;
	private float correctRoundedAnswer;
	String firstValueDescription;
	String secondValueDescription;
	private float firstValue;
	private float secondValue;
	boolean simpleCalculation = false;

	public float getCorrectRoundedAnswer() {
		return correctRoundedAnswer;
	}

	public void setCorrectRoundedAnswer(float correctRoundedAnswer) {
		this.correctRoundedAnswer = correctRoundedAnswer;
	}

	public Integer getStepsNeeded() {
		return stepsNeeded;
	}

	public void setStepsNeeded(Integer stepsNeeded) {
		this.stepsNeeded = stepsNeeded;
	}

	public AttributeCalculation(Attribute attribute) {
		this.attribute = attribute;
		calculationStats = new CalculationStats();
		calculationInformation = new CalculationInformation();
	}

	public AttributeCalculation(List<Attribute> attributes, Operators operation) {
		this.attributes = attributes;
		this.operation = operation;
		isPrimitive = false;
		calculationStats = new CalculationStats();
		calculationInformation = new CalculationInformation();

	}

	public AttributeCalculation(Attribute attribute, float quantifier, Operators operation) {
		this.attribute = attribute;
		this.quantifier = quantifier;
		this.operation = operation;
		isPrimitive = false;
		calculationStats = new CalculationStats();
		calculationInformation = new CalculationInformation();

	}

	public AttributeCalculation(float firstValue, String firstValueDescription, float secondValue,
			String secondValueDescription, Operators operation) {
		this.operation = operation;
		simpleCalculation = true;
		calculationStats = new CalculationStats();
		calculationInformation = new CalculationInformation();
		this.firstValue = firstValue;
		this.firstValueDescription = firstValueDescription;
		this.secondValue = secondValue;
		this.secondValueDescription = secondValueDescription;
	}

	public AttributeCalculation(List<Attribute> attributes, float quantifier, Operators operation) {
		this.attributes = attributes;
		this.operation = operation;
		this.quantifier = quantifier;
		isPrimitive = false;
		calculationStats = new CalculationStats();
		calculationInformation = new CalculationInformation();
	}

	public Attribute getPrimitiveAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	private void appendSimpleCalculation(StringBuilder sb) {
		sb.append(fixDecimals(firstValue));
		sb.append(" ");
		sb.append(OperatorHelper.getOperatorText(operation));
		sb.append(" ");
		sb.append(fixDecimals(secondValue));
		sb.append(" ");
		sb.append(secondValueDescription);
	}

	private String appendPrimitive(StringBuilder sb) {
		sb.append(attribute.getElement().getElementText());
		sb.append(": ");
		sb.append(attribute.getName());
		return sb.toString();
	}

	private String appendIfNextAttributeNotPrimitive(StringBuilder sb, Iterator<Attribute> i) {

		while (i.hasNext()) {
			Attribute nextAttr = i.next();
			sb.append(nextAttr.getElement().getElementText());
			sb.append(": ");
			sb.append(nextAttr.getName());
			sb.append(" ");
			if (i.hasNext()) {
				sb.append(OperatorHelper.getOperatorText(operation));
				sb.append(" ");
			}
			if (!i.hasNext() && quantifier != -1) {
				// if the quantifier is not -1, append the operator (not intuitive)
				sb.append(OperatorHelper.getOperatorText(operation));
				sb.append(" ");
				sb.append(fixDecimals(quantifier));
			}

		}
		return sb.toString();
	}

	private void appendIfNoChildren(StringBuilder sb) {
		sb.append(attribute.getElement().getElementText());
		sb.append(": ");
		sb.append(attribute.getName());
		sb.append(" ");
		sb.append(OperatorHelper.getOperatorText(operation));
		sb.append(" ");
		sb.append(fixDecimals(quantifier));
	}

	/**
	 * Construct the help text for a calculation
	 * 
	 * @return Calculation text
	 */
	private String getCalculationText() {
		StringBuilder sb = new StringBuilder();

		// if the calculation doesn't contain any attributes, just print out the one
		// calculation
		if (simpleCalculation) {
			appendSimpleCalculation(sb);

		} else {
			// if the current active attribute doesn't have any children, append the element
			// with its value
			if (isPrimitive) {
				return appendPrimitive(sb);

				// if a list of attributes is in the calculation, go through them iteratively
				// and apend them.
			} else if (attributes != null) {

				Iterator<Attribute> i = attributes.iterator();

				return appendIfNextAttributeNotPrimitive(sb, i);
			} else {
				appendIfNoChildren(sb);
			}

			return sb.toString();
		}
		return sb.toString();
	}

	/**
	 * Wrapper to get a more detailed explanation
	 * 
	 * @param optimalSolution optimal solution of the attribute or question
	 * @return detailed help text for the calculation
	 */
	public String getCalculationTextDetailedWrapper(OptimalSolution optimalSolution) {
		if (calculatedDetailed != null) {
			return calculatedDetailed;
		} else {
			calculatedDetailed = getCalculationTextDetailed(calculationStats);
			return calculatedDetailed;
		}
	}

	private String fixDecimals(float value) {
		if (value % 1 == 0) {
			return String.valueOf((int) value);
		} else {
			return String.valueOf(value);
		}
	}

	/**
	 * Wrapper to get a more detailed explanation
	 * 
	 * @return
	 */
	public String getCalculationTextSimpleWrapper() {
		if (calculatedSimple != null) {
			return calculatedSimple;
		} else {
			calculatedSimple = getCalculationText();
			return calculatedSimple;
		}
	}

	private void appendSimpleCalculationDetailed(StringBuilder sb) {
		sb.append(firstValue);
		sb.append(" ");
		sb.append(operation);
		sb.append(" ");
		sb.append(secondValue);
	}

	private String appendPrimitiveDetailed(StringBuilder sb) {
		sb.append(attribute.getElement().getElementText());
		sb.append(": ");
		sb.append(attribute.getName());
		sb.append(" ");

		return sb.toString();
	}

	private void appendIfNextAttributeNotPrimitiveDetailed(Attribute nextAttr, StringBuilder sb,
			Iterator<Attribute> i) {
		if (!nextAttr.getAttributeCalculation().isPrimitive) {
			sb.append(nextAttr.getAttributeCalculation().getCalculationTextDetailed(calculationStats));
			sb.append(" ");
		} else {
			sb.append(nextAttr.getElement().getElementText());
			sb.append(": ");
			sb.append(nextAttr.getName());
			sb.append(" ");
			if (i.hasNext() && quantifier != -1) {
				sb.append(OperatorHelper.getOperatorText(operation));
				sb.append(" ");
			}
		}
	}

	private void appendIfNoChildrenDetailed(StringBuilder sb) {
		if (!attribute.getAttributeCalculation().isPrimitive) {

			sb.append(attribute.getAttributeCalculation().getCalculationTextDetailed(calculationStats));
			sb.append(" ");
		} else {
			sb.append(attribute.getElement().getElementText());
			sb.append(": ");
			sb.append(attribute.getName());
			sb.append(" ");
		}
	}

	/**
	 * Construct the detailed help text for a calculation
	 * 
	 * @param calculationStats contains information about the calculation
	 * @return detailed calculation help
	 */
	private String getCalculationTextDetailed(CalculationStats calculationStats) {
		calculationStats.setStepsNeeded(calculationStats.getStepsNeeded() + 1);
		StringBuilder sb = new StringBuilder();

		// if the calculation doesn't contain any attributes, just print out the one
		// calculation
		if (simpleCalculation) {
			appendSimpleCalculationDetailed(sb);

		} else {
			// if the current active attribute doesn't have any children, append the element
			// with its value
			if (isPrimitive) {
				return appendPrimitiveDetailed(sb);

				// if attribute children are present, iteratively go through the children and do
				// this
			} else if (attributes != null) {

				Iterator<Attribute> i = attributes.iterator();
				while (i.hasNext()) {
					Attribute nextAttr = i.next();

					// if the current attribute in the list is not primitive and has children,
					// iteratively call this method and append the result of it
					appendIfNextAttributeNotPrimitiveDetailed(nextAttr, sb, i);
				}

				return sb.toString();
			} else {
				// If not a primitive and has no children, append just append the rest
				appendIfNoChildrenDetailed(sb);

				sb.append(OperatorHelper.getOperatorText(operation));
				sb.append(" ");
				sb.append(quantifier);
				return sb.toString();
			}
		}
		return sb.toString();

	}

	public String getCalculatedDetailed() {
		return calculatedDetailed;
	}

	public void setCalculatedDetailed(String calculatedDetailed) {
		this.calculatedDetailed = calculatedDetailed;
	}

	public String getCalculatedSimple() {
		return calculatedSimple;
	}

	public void setCalculatedSimple(String calculatedSimple) {
		this.calculatedSimple = calculatedSimple;
	}

	public CalculationStats getCalculationStats() {
		return calculationStats;
	}

	public void setCalculationStats(CalculationStats calculationStats) {
		this.calculationStats = calculationStats;
	}

	public CalculationInformation getCalculationInformation() {
		return calculationInformation;
	}

	public void setCalculationInformation(CalculationInformation calculationInformation) {
		this.calculationInformation = calculationInformation;
	}
}
