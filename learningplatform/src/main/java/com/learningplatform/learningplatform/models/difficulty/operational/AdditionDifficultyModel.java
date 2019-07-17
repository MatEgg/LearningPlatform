package com.learningplatform.learningplatform.models.difficulty.operational;

import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of the addition difficulty.
 *
 */
@Entity
@Table(name = "addition_difficulty")
public class AdditionDifficultyModel extends Model {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id_addition_difficulty", unique = true)
	private int uid;

	@Column(name = "overall_difficulty")
	private int overallDifficulty;

	@OneToOne
	@JoinColumn(name = "id_numerical_skill_types_difficulty_addition")
	private NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModel;

	@Column(name = "manual")
	private int manual;

	@OneToOne(mappedBy = "additionDifficultyModel")
	private OperationalDifficultyModel operationalDifficultyModel;

	public AdditionDifficultyModel(int overalDifficulty,
			NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModel, int manual) {
		this.overallDifficulty = overalDifficulty;
		this.numericalSkillTypesDifficultyModel = numericalSkillTypesDifficultyModel;
		this.manual = manual;
	}

	public AdditionDifficultyModel() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOverallDifficulty() {
		return overallDifficulty;
	}

	public void setOverallDifficulty(int overallDifficulty) {
		this.overallDifficulty = overallDifficulty;
	}

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}

	public OperationalDifficultyModel getOperationalDifficultyModel() {
		return operationalDifficultyModel;
	}

	public void setOperationalDifficultyModel(OperationalDifficultyModel operationalDifficultyModel) {
		this.operationalDifficultyModel = operationalDifficultyModel;
	}

	public NumericalSkillTypesDifficultyModel getNumericalSkillTypesDifficultyModel() {
		return numericalSkillTypesDifficultyModel;
	}

	public void setNumericalSkillTypesDifficultyModel(
			NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModel) {
		this.numericalSkillTypesDifficultyModel = numericalSkillTypesDifficultyModel;
	}

}
