package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.models.skills.numerical.SubtractionSkill;

/**
 * Overview of the subtraction related statistics of the student.
 *
 */
public class SubtractionGui {

	private Stat decimalHandling;
	private Stat bigNumberHandling;
	private Stat numericalType;

	public SubtractionGui(SubtractionSkill subtractionSkill) {
		this.decimalHandling = new Stat("Subtraktion - Dezimalzahlen",
				subtractionSkill.getNumericalSkillTypes().getDecimalHandling());
		this.bigNumberHandling = new Stat("Subtraktion - Große Zahlen",
				subtractionSkill.getNumericalSkillTypes().getBigNumberHandling());
		this.numericalType = new Stat("Subtraktion - Numerische Komplexität",
				subtractionSkill.getNumericalSkillTypes().getNumericalTypeHandling());
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
