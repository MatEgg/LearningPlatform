package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.CustomerSpending;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.templates.Template;

import learningplatform.learningplatform.Calculation.AttributeCalculation;

public class CustomerElement extends NumericalElement {

	Attribute customerSpending;
	String name = "Kunde";
	
	private float spentPerVisitor = 0;
	private float percentageOfVisitorsSpending = 0;
	private float taxes = 0;
	private float payMorePercentage = 0;
	private float payingHowMuchMore = 0;

	public CustomerElement(Template template) {
		super(template);
		customerSpending = new CustomerSpending(5, this);
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return name;
	}

	@Override
	String elementTextStart() {
		return name;
	}

	@Override
	String elementTextPlural() {
		return name;
	}

	@Override
	String replacementString() {
		return name;
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.CUSTOMER);

	}

	@Override
	void addAttributeList() {
		attributeList.add(customerSpending);
		attributeTypes.add(AttributeType.CUSTOMERSPENDING);

		attributeMap.put(AttributeType.CUSTOMERSPENDING, customerSpending);

		setAttributeTypes(attributeTypes);

	}

	@Override
	void findElement() {
		
		customerSpending.setAttributeCalculation(new AttributeCalculation(customerSpending));

		addAttributeList();

	}

	public Attribute getCustomerSpending() {
		return customerSpending;
	}

	public void setCustomerSpending(Attribute customerSpending) {
		this.customerSpending = customerSpending;
	}
	
	public float getSpentPerVisitor() {
		return spentPerVisitor;
	}

	public void setSpentPerVisitor(float spentPerVisitor) {
		this.spentPerVisitor = spentPerVisitor;
	}

	public float getPercentageOfVisitorsSpending() {
		return percentageOfVisitorsSpending;
	}

	public void setPercentageOfVisitorsSpending(float percentageOfVisitorsSpending) {
		this.percentageOfVisitorsSpending = percentageOfVisitorsSpending;
	}

	public float getTaxes() {
		return taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	public float getPayMorePercentage() {
		return payMorePercentage;
	}

	public void setPayMorePercentage(float payMorePercentage) {
		this.payMorePercentage = payMorePercentage;
	}

	public float getPayingHowMuchMore() {
		return payingHowMuchMore;
	}

	public void setPayingHowMuchMore(float payingHowMuchMore) {
		this.payingHowMuchMore = payingHowMuchMore;
	}

}
