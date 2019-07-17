package com.learningplatform.learningplatform.models.difficulty.operational;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of the operational detailed difficulties.
 *
 */
@Entity
@Table(name = "numerical_skill_types_difficulty")
public class NumericalSkillTypesDifficultyModel {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id_numerical_skill_types_difficulty", unique = true)
	private int uid;

	@Column(name = "decimal_difficulty")
	private int decimalDifficulty;

	@Column(name = "magnitude_difficulty")
	private int magnitudeDifficulty;

	@Column(name = "numerical_difficulty")
	private int numericalDifficulty;

	public NumericalSkillTypesDifficultyModel(int decimalDifficulty, int magnitudeDifficulty,
			int numericalDifficulty) {
		this.decimalDifficulty = decimalDifficulty;
		this.magnitudeDifficulty = magnitudeDifficulty;
		this.numericalDifficulty = numericalDifficulty;
	}

	public NumericalSkillTypesDifficultyModel() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDecimalDifficulty() {
		return decimalDifficulty;
	}

	public void setDecimalDifficulty(int decimalDifficulty) {
		this.decimalDifficulty = decimalDifficulty;
	}

	public int getMagnitudeDifficulty() {
		return magnitudeDifficulty;
	}

	public void setMagnitudeDifficulty(int magnitudeDifficulty) {
		this.magnitudeDifficulty = magnitudeDifficulty;
	}

	public int getNumericalDifficulty() {
		return numericalDifficulty;
	}

	public void setNumericalDifficulty(int numericalDifficulty) {
		this.numericalDifficulty = numericalDifficulty;
	}

}
