package com.learningplatform.learningplatform.models.skills.conceptual;


import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "solving_task_skill")
public class SolvingTaskSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_solving_task_skill", unique = true)
	private int uid;

	@Column(name = "choose_correct_formulas_skill")
	private int chooseCorrectFormulasSkill;

	@Column(name = "choose_correct_information_skill")
	private int chooseCorrectInformationSkill;

	@Column(name = "insert_correct_values_skill")
	private int insertCorrectValuesSkill;

	public SolvingTaskSkill() {
		chooseCorrectFormulasSkill = 1;
		chooseCorrectInformationSkill = 1;
		insertCorrectValuesSkill = 1;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getChooseCorrectFormulasSkill() {
		return chooseCorrectFormulasSkill;
	}

	public void setChooseCorrectFormulasSkill(int chooseCorrectFormulasSkill) {
		this.chooseCorrectFormulasSkill = chooseCorrectFormulasSkill;
	}

	public int getChooseCorrectInformationSkill() {
		return chooseCorrectInformationSkill;
	}

	public void setChooseCorrectInformationSkill(int chooseCorrectInformationSkill) {
		this.chooseCorrectInformationSkill = chooseCorrectInformationSkill;
	}

	public int getInsertCorrectValuesSkill() {
		return insertCorrectValuesSkill;
	}

	public void setInsertCorrectValuesSkill(int insertCorrectValuesSkill) {
		this.insertCorrectValuesSkill = insertCorrectValuesSkill;
	}
}
