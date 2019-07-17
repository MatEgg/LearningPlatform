package com.learningplatform.learningplatform.models.difficulty.operational;

import com.learningplatform.learningplatform.models.difficulty.Difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of the operational difficulties.
 *
 */
@Entity
@Table(name = "operational_difficulty")
public class OperationalDifficultyModel {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id_operational_difficulty", unique = true)
	private int uid;

	@OneToOne
	@JoinColumn(name = "id_addition_difficulty")
	private AdditionDifficultyModel additionDifficultyModel;

	@OneToOne
	@JoinColumn(name = "id_subtraction_difficulty")
	private SubtractionDifficultyModel subtractionDifficultyModel;

	@OneToOne
	@JoinColumn(name = "id_multiplication_difficulty")
	private MultiplicationDifficultyModel multiplicationDifficultyModel;

	@OneToOne
	@JoinColumn(name = "id_division_difficulty")
	private DivisionDifficultyModel divisionDifficultyModel;

	@OneToOne(mappedBy = "operationalDifficultyModel")
	private Difficulty difficulty;

	public OperationalDifficultyModel(AdditionDifficultyModel additionDifficultyModel,
			SubtractionDifficultyModel subtractionDifficultyModel,
			MultiplicationDifficultyModel multiplicationDifficultyModel,
			DivisionDifficultyModel divisionDifficultyModel) {
		this.additionDifficultyModel = additionDifficultyModel;
		this.subtractionDifficultyModel = subtractionDifficultyModel;
		this.multiplicationDifficultyModel = multiplicationDifficultyModel;
		this.divisionDifficultyModel = divisionDifficultyModel;
	}

	public OperationalDifficultyModel() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public AdditionDifficultyModel getAdditionDifficultyModel() {
		return additionDifficultyModel;
	}

	public void setAdditionDifficultyModel(AdditionDifficultyModel additionDifficultyModel) {
		this.additionDifficultyModel = additionDifficultyModel;
	}

	public SubtractionDifficultyModel getSubtractionDifficultyModel() {
		return subtractionDifficultyModel;
	}

	public void setSubtractionDifficultyModel(SubtractionDifficultyModel subtractionDifficultyModel) {
		this.subtractionDifficultyModel = subtractionDifficultyModel;
	}

	public MultiplicationDifficultyModel getMultiplicationDifficultyModel() {
		return multiplicationDifficultyModel;
	}

	public void setMultiplicationDifficultyModel(MultiplicationDifficultyModel multiplicationDifficultyModel) {
		this.multiplicationDifficultyModel = multiplicationDifficultyModel;
	}

	public DivisionDifficultyModel getDivisionDifficultyModel() {
		return divisionDifficultyModel;
	}

	public void setDivisionDifficultyModel(DivisionDifficultyModel divisionDifficultyModel) {
		this.divisionDifficultyModel = divisionDifficultyModel;
	}
}
