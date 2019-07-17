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
@Table(name = "measurement_skill")
public class MeasurementSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_measurement_skill", unique = true)
	private int uid;

	@Column(name = "area_calculation_skill")
	private int areaCalculationSkill;

	@Column(name = "geometrical_calculation_skill")
	private int geometricalCalculationSkill;

	public MeasurementSkill() {
		areaCalculationSkill = 1;
		geometricalCalculationSkill = 1;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAreaCalculationSkill() {
		return areaCalculationSkill;
	}

	public void setAreaCalculationSkill(int areaCalculationSkill) {
		this.areaCalculationSkill = areaCalculationSkill;
	}

	public int getGeometricalCalculationSkill() {
		return geometricalCalculationSkill;
	}

	public void setGeometricalCalculationSkill(int geometricalCalculationSkill) {
		this.geometricalCalculationSkill = geometricalCalculationSkill;
	}
}
