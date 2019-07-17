package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.models.skills.numerical.MultiplicationSkill;

/**
 * Overview of the multiplication related statistics of the student.
 *
 */
public class MultiplicationGui {

	private Stat decimalHandling;
	private Stat bigNumberHandling;
	private Stat numericalType;

	public MultiplicationGui(MultiplicationSkill multiplicationSkill) {
		this.decimalHandling = new Stat("Multiplikation - Dezimalzahlen",
				multiplicationSkill.getNumericalSkillTypes().getDecimalHandling());
		this.bigNumberHandling = new Stat("Multiplikation - Große Zahlen",
				multiplicationSkill.getNumericalSkillTypes().getBigNumberHandling());
		this.numericalType = new Stat("Multiplikation - Numerische Komplexität",
				multiplicationSkill.getNumericalSkillTypes().getNumericalTypeHandling());
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
