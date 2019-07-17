package learningplatform.learningplatform.calculation;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class ValidCalculation {

	private Float[] attributeValues;
	private Formula formular;
	private Operators operator;
	private AttributeType relevantAttributeType;

	public ValidCalculation(Float[] attributeValues, Formula formular, Operators operator) {
		this.attributeValues = attributeValues;
		this.formular = formular;
		this.operator = operator;

	}

	public ValidCalculation(Float[] attributeValues, AttributeType relevantAttributeType, Formula formular,
			Operators operator) {
		this.attributeValues = attributeValues;
		this.formular = formular;
		this.operator = operator;
		this.relevantAttributeType = relevantAttributeType;

	}

	public Float[] getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Float[] attributeValues) {
		this.attributeValues = attributeValues;
	}

	public AttributeType getRelevantAttributeType() {
		return relevantAttributeType;
	}

	public void setRelevantAttributeType(AttributeType relevantAttributeType) {
		this.relevantAttributeType = relevantAttributeType;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public Formula getFormular() {
		return formular;
	}

	public void setFormular(Formula formular) {
		this.formular = formular;
	}

}
