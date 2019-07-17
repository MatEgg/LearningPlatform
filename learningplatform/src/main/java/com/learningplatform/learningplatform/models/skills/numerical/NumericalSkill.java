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
@Table(name = "numerical_skill")
public class NumericalSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_numerical_skill", unique = true)
	private int uid;

	@OneToOne
	@JoinColumn(name = "id_addition_skill")
	private AdditionSkill additionSkill;

	@OneToOne
	@JoinColumn(name = "id_subtraction_skill")
	private SubtractionSkill subtractionSkill;

	@OneToOne
	@JoinColumn(name = "id_multiplication_skill")
	private MultiplicationSkill multiplicationSkill;

	@OneToOne
	@JoinColumn(name = "id_division_skill")
	private DivisionSkill divisionSkill;

	public NumericalSkill(AdditionSkill additionSkill, SubtractionSkill subtractionSkill,
			MultiplicationSkill multiplicationSkill, DivisionSkill divisionSkill) {
		this.additionSkill = additionSkill;
		this.subtractionSkill = subtractionSkill;
		this.multiplicationSkill = multiplicationSkill;
		this.divisionSkill = divisionSkill;
	}

	public NumericalSkill() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public AdditionSkill getAdditionSkill() {
		return additionSkill;
	}

	public void setAdditionSkill(AdditionSkill additionSkill) {
		this.additionSkill = additionSkill;
	}

	public SubtractionSkill getSubtractionSkill() {
		return subtractionSkill;
	}

	public void setSubtractionSkill(SubtractionSkill subtractionSkill) {
		this.subtractionSkill = subtractionSkill;
	}

	public MultiplicationSkill getMultiplicationSkill() {
		return multiplicationSkill;
	}

	public void setMultiplicationSkill(MultiplicationSkill multiplicationSkill) {
		this.multiplicationSkill = multiplicationSkill;
	}

	public DivisionSkill getDivisionSkill() {
		return divisionSkill;
	}

	public void setDivisionSkill(DivisionSkill divisionSkill) {
		this.divisionSkill = divisionSkill;
	}
}
