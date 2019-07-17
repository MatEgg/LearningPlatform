package com.learningplatform.learningplatform.models.difficulty.concept;

import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of the concept difficulty.
 *
 */
@Entity
@Table(name = "concept_difficulty")
public class ConceptDifficultyModel extends Model {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id_concept_difficulty", unique = true)
	private int uid;

	@Column(name = "computational_rules_difficulty")
	private int computationalRulesDifficulty;

	@Column(name = "measurement_difficulty")
	private int measurementDifficulty;

	@Column(name = "probability_difficulty")
	private int probabilityDifficulty;

	@Column(name = "percent_difficulty")
	private int percentDifficulty;

	@OneToOne(mappedBy = "conceptDifficultyModel")
	private Difficulty difficulty;

	public ConceptDifficultyModel(int computationalRulesDifficulty, int measurementDifficulty,
			int probabilityDifficulty, int percentDifficulty) {
		this.computationalRulesDifficulty = computationalRulesDifficulty;
		this.measurementDifficulty = measurementDifficulty;
		this.probabilityDifficulty = probabilityDifficulty;
		this.percentDifficulty = percentDifficulty;
	}

	public ConceptDifficultyModel() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getComputationalRulesDifficulty() {
		return computationalRulesDifficulty;
	}

	public void setComputationalRulesDifficulty(int computationalRulesDifficulty) {
		this.computationalRulesDifficulty = computationalRulesDifficulty;
	}

	public int getMeasurementDifficulty() {
		return measurementDifficulty;
	}

	public void setMeasurementDifficulty(int measurementDifficulty) {
		this.measurementDifficulty = measurementDifficulty;
	}

	public int getProbabilityDifficulty() {
		return probabilityDifficulty;
	}

	public void setProbabilityDifficulty(int probabilityDifficulty) {
		this.probabilityDifficulty = probabilityDifficulty;
	}

	public int getPercentDifficulty() {
		return percentDifficulty;
	}

	public void setPercentDifficulty(int percentDifficulty) {
		this.percentDifficulty = percentDifficulty;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

}
