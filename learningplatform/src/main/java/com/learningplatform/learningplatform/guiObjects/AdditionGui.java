package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.models.skills.numerical.AdditionSkill;

/**
 * Overview of the addition related statistics of the student.
 *
 */
public class AdditionGui {

	private Stat decimalHandling;
	private Stat bigNumberHandling;
	private Stat numericalType;

	public AdditionGui(AdditionSkill additionSkill) {
		this.decimalHandling = new Stat("Addition - Dezimalzahlen",
				additionSkill.getNumericalSkillTypes().getDecimalHandling());
		this.bigNumberHandling = new Stat("Addition - Große Zahlen",
				additionSkill.getNumericalSkillTypes().getBigNumberHandling());
		this.numericalType = new Stat("Addition - Numerische Komplexität",
				additionSkill.getNumericalSkillTypes().getNumericalTypeHandling());
	}
	
	public Stat getDecimalHandling() {
		return decimalHandling;
	}

	public void setDecimalHandling(Stat decimalHandling) {
		this.decimalHandling = decimalHandling;
	}

	public Stat getBigNumberHandling() {
		return bigNumberHandling;
	}

	public void setBigNumberHandling(Stat bigNumberHandling) {
		this.bigNumberHandling = bigNumberHandling;
	}

	public Stat getNumericalType() {
		return numericalType;
	}

	public void setNumericalType(Stat numericalType) {
		this.numericalType = numericalType;
	}

}
