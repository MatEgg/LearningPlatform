package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Cost;
import com.learningplatform.learningplatform.models.Product;
import com.learningplatform.learningplatform.models.service.ProductService;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.templates.Template;

public class ProductElement extends NumericalElement {

	Product product;
	ProductService productService;
	String name;
	Attribute cost;

	public ProductElement(Template template) {
		super(template);
		productService = wordProblem.getDaoFactory().getProductService();
		product = productService.getRandomModel();
		findElement();
		finishElementConstruction();
	}

	public ProductElement(Product product, Template template) {
		super(template);
		productService = wordProblem.getDaoFactory().getProductService();
		this.product = product;
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return product.getName();
	}

	@Override
	String elementTextStart() {
		return product.getName();
	}

	@Override
	String elementTextPlural() {
		return product.getName();
	}

	@Override
	String replacementString() {
		return product.getName();
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.ANIMALFOOD);
	}

	@Override
	void findElement() {
		name = product.getName();
		int costVal = product.getCost();

		cost = new Cost(costVal, this);

		addAttributeList();
	}

	@Override
	public void addAttributeList() {
		attributeList.add(cost);
		attributeTypes.add(AttributeType.PRICE);

		attributeMap.put(AttributeType.PRICE, cost);

		setAttributeTypes(attributeTypes);
	}

	public Attribute getCost() {
		return cost;
	}

	public void setCost(Attribute cost) {
		this.cost = cost;
	}

}
