package com.learningplatform.learningplatform.models.skills.conceptual;

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
@Table(name = "conceptual_skill")
public class ConceptualSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_conceptual_skill", unique = true)
	private int uid;

	@OneToOne
	@JoinColumn(name = "id_number_skill")
	private NumberSkill numberSkill;

	@OneToOne
	@JoinColumn(name = "id_measurement_skill")
	private MeasurementSkill measurementSkill;

	@OneToOne
	@JoinColumn(name = "id_randomness_skill")
	private RandomnessSkill randomnessSkill;

	@OneToOne
	@JoinColumn(name = "id_solving_task_skill")
	private SolvingTaskSkill solvingTaskSkill;

	@OneToOne
	@JoinColumn(name = "id_general_performance_skill")
	private GeneralPerformanceSkill generalPerformanceSkill;

	public ConceptualSkill(NumberSkill numberSkill, MeasurementSkill measurementSkill, RandomnessSkill randomnessSkill,
			SolvingTaskSkill solvingTaskSkill, GeneralPerformanceSkill generalPerformanceSkill) {
		this.numberSkill = numberSkill;
		this.measurementSkill = measurementSkill;
		this.randomnessSkill = randomnessSkill;
		this.solvingTaskSkill = solvingTaskSkill;
		this.generalPerformanceSkill = generalPerformanceSkill;
	}

	public ConceptualSkill() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public NumberSkill getNumberSkill() {
		return numberSkill;
	}

	public void setNumberSkill(NumberSkill numberSkill) {
		this.numberSkill = numberSkill;
	}

	public MeasurementSkill getMeasurementSkill() {
		return measurementSkill;
	}

	public void setMeasurementSkill(MeasurementSkill measurementSkill) {
		this.measurementSkill = measurementSkill;
	}

	public RandomnessSkill getRandomnessSkill() {
		return randomnessSkill;
	}

	public void setRandomnessSkill(RandomnessSkill randomnessSkill) {
		this.randomnessSkill = randomnessSkill;
	}

	public SolvingTaskSkill getSolvingTaskSkill() {
		return solvingTaskSkill;
	}

	public void setSolvingTaskSkill(SolvingTaskSkill solvingTaskSkill) {
		this.solvingTaskSkill = solvingTaskSkill;
	}

	public GeneralPerformanceSkill getGeneralPerformanceSkill() {
		return generalPerformanceSkill;
	}

	public void setGeneralPerformanceSkill(GeneralPerformanceSkill generalPerformanceSkill) {
		this.generalPerformanceSkill = generalPerformanceSkill;
	}
}
