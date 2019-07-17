package com.learningplatform.learningplatform.models.skills.numerical;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learningplatform.learningplatform.models.Model;

@Entity
@Table(name = "multiplication_skill")
public class MultiplicationSkill extends Model {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_multiplication_skill", unique = true)
	private int uid;

	@Column(name = "overall_skill")
	private int overallSkill;

	@OneToOne
	@JoinColumn(name = "id_numerical_skill_types_multiplication")
	private NumericalSkillTypes numericalSkillTypes;

	@OneToOne(mappedBy = "multiplicationSkill")
	private NumericalSkill numericalSkill;

	public MultiplicationSkill(NumericalSkillTypes numericalSkillTypes) {
		this.numericalSkillTypes = numericalSkillTypes;
		overallSkill = 1;
	}

	public MultiplicationSkill() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOverallSkill() {
		return overallSkill;
	}

	public void setOverallSkill(int overallSkill) {
		this.overallSkill = overallSkill;
	}

	public NumericalSkill getNumericalSkill() {
		return numericalSkill;
	}

	public void setNumericalSkill(NumericalSkill numericalSkill) {
		this.numericalSkill = numericalSkill;
	}

	public NumericalSkillTypes getNumericalSkillTypes() {
		return numericalSkillTypes;
	}

	public void setNumericalSkillTypes(NumericalSkillTypes numericalSkillTypes) {
		this.numericalSkillTypes = numericalSkillTypes;
	}

}
