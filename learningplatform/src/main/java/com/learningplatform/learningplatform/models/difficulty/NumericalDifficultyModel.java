package com.learningplatform.learningplatform.models.difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learningplatform.learningplatform.models.Model;

/**
 * Model of the numerical difficulty models.
 *
 */
@Entity
@Table(name = "numerical_difficulty")
public class NumericalDifficultyModel extends Model {
	private int uid;
	private int numericalDifficulty;
	private int numbertypeDifficulty;
	private int manual;

	@OneToOne(mappedBy = "numericalDifficulty")
	private Difficulty difficulty;

	public NumericalDifficultyModel(int numericalDifficulty, int numbertypeDifficulty, int manual) {
		this.numericalDifficulty = numericalDifficulty;
		this.numbertypeDifficulty = numbertypeDifficulty;
		this.manual = manual;
	}

	public NumericalDifficultyModel() {

	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_numerical_difficulty", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "numerical_difficulty")
	public int getNumericalDifficulty() {
		return numericalDifficulty;
	}

	public void setNumericalDifficulty(int numericalDifficulty) {
		this.numericalDifficulty = numericalDifficulty;
	}

	@Column(name = "numbertype_difficulty")
	public int getNumbertypeDifficulty() {
		return numbertypeDifficulty;
	}

	public void setNumbertypeDifficulty(int numbertypeDifficulty) {
		this.numbertypeDifficulty = numbertypeDifficulty;
	}

	@Column(name = "manual")
	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}
}
