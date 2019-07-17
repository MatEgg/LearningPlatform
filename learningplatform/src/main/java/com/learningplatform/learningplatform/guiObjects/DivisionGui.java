package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.models.skills.numerical.DivisionSkill;

/**
 * Overview of the division related statistics of the student.
 *
 */
public class DivisionGui {


	private Stat decimalHandling;
	private Stat bigNumberHandling;
	private Stat numericalType;

	public DivisionGui(DivisionSkill divisionSkill) {
		this.decimalHandling = new Stat("Division - Dezimalzahlen",
				divisionSkill.getNumericalSkillTypes().getDecimalHandling());
		this.bigNumberHandling = new Stat("Division - Große Zahlen",
				divisionSkill.getNumericalSkillTypes().getBigNumberHandling());
		this.numericalType = new Stat("Division - Numerische Komplexität",
				divisionSkill.getNumericalSkillTypes().getNumericalTypeHandling());
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
