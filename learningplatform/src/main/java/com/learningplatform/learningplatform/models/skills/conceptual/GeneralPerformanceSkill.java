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
@Table(name = "general_performance_skill")
public class GeneralPerformanceSkill extends Model {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_general_performance_skill", unique = true)
	private int uid;
	
	@Column(name = "steps_needed_skill")
	private int stepsNeededSkill;
	
	@Column(name = "solving_carry_tasks_skill")
	private int solvingCarryTasksSkill;

	public GeneralPerformanceSkill() {
		stepsNeededSkill = 1;
		solvingCarryTasksSkill = 1;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getStepsNeededSkill() {
		return stepsNeededSkill;
	}

	public void setStepsNeededSkill(int stepsNeededSkill) {
		this.stepsNeededSkill = stepsNeededSkill;
	}

	public int getSolvingCarryTasksSkill() {
		return solvingCarryTasksSkill;
	}

	public void setSolvingCarryTasksSkill(int solvingCarryTasksSkill) {
		this.solvingCarryTasksSkill = solvingCarryTasksSkill;
	}
}
