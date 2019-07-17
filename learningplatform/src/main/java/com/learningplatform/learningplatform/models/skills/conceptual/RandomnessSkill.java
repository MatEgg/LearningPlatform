package com.learningplatform.learningplatform.models.skills.conceptual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learningplatform.learningplatform.models.Model;

@Entity
@Table(name = "randomness_skill")
public class RandomnessSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_randomness_skill", unique = true)
	private int uid;

	@Column(name = "calculate_probabilities_skill")
	private int calculateProbabilitiesSkill;

	public RandomnessSkill() {
		calculateProbabilitiesSkill = 1;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCalculateProbabilitiesSkill() {
		return calculateProbabilitiesSkill;
	}

	public void setCalculateProbabilitiesSkill(int calculateProbabilitiesSkill) {
		this.calculateProbabilitiesSkill = calculateProbabilitiesSkill;
	}

}
