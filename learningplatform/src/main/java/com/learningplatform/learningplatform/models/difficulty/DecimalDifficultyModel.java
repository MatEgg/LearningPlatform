package com.learningplatform.learningplatform.models.difficulty;

import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of decimal difficulty.
 *
 */
@Entity
@Table(name = "decimal_difficulty")
public class DecimalDifficultyModel extends Model {

	private int uid;
	private int decimalDifficulty;
	private int decimalcountDifficulty;
	private int decimalenableDifficulty;

	private int manual;

	@OneToOne(mappedBy = "decimal_difficulty")
	private Difficulty difficulty;

	public DecimalDifficultyModel(int decimalDifficulty, int decimalcountDifficulty, int decimalenableDifficulty,
			int manual) {
		this.decimalDifficulty = decimalDifficulty;
		this.decimalcountDifficulty = decimalcountDifficulty;
		this.decimalenableDifficulty = decimalenableDifficulty;
		this.manual = manual;
	}

	public DecimalDifficultyModel() {

	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_decimal_difficulty", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "decimal_difficulty")
	public int getDecimalDifficulty() {
		return decimalDifficulty;
	}

	public void setDecimalDifficulty(int decimalDifficulty) {
		this.decimalDifficulty = decimalDifficulty;
	}

	@Column(name = "decimalcount_difficulty")
	public int getDecimalcountDifficulty() {
		return decimalcountDifficulty;
	}

	public void setDecimalcountDifficulty(int decimalcountDifficulty) {

		this.decimalcountDifficulty = decimalcountDifficulty;
	}

	@Column(name = "decimalenable_difficulty")
	public int getDecimalenableDifficulty() {
		return decimalenableDifficulty;
	}

	public void setDecimalenableDifficulty(int decimalenableDifficulty) {
		this.decimalenableDifficulty = decimalenableDifficulty;
	}

	@Column(name = "manual")
	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}
}
