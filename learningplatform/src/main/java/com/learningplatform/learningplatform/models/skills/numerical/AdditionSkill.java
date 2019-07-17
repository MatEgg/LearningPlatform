package com.learningplatform.learningplatform.models.skills.numerical;

import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addition_skill")
public class AdditionSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_addition_skill", unique = true)
	private int uid;

	@Column(name = "overall_skill")
	private int overallSkill;

	@OneToOne
	@JoinColumn(name = "id_numerical_skill_types_addition")
	private NumericalSkillTypes numericalSkillTypes;

	@OneToOne(mappedBy = "additionSkill")
	private NumericalSkill numericalSkill;

	public AdditionSkill(NumericalSkillTypes numericalSkillTypes) {
		this.numericalSkillTypes = numericalSkillTypes;
		overallSkill = 1;
	}

	public AdditionSkill() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "overall_skill")
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
