package com.learningplatform.learningplatform.models.skills.numerical;

import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "numerical_skill_types")
public class NumericalSkillTypes extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_numerical_skill_types", unique = true)
	private int uid;

	@Column(name = "decimal_handling")
	private int decimalHandling;

	@Column(name = "big_number_handling")
	private int bigNumberHandling;

	@Column(name = "numerical_type_handling")
	private int numericalTypeHandling;

	public NumericalSkillTypes() {
		decimalHandling = 1;
		bigNumberHandling = 1;
		numericalTypeHandling = 1;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDecimalHandling() {
		return decimalHandling;
	}

	public void setDecimalHandling(int decimalHandling) {
		this.decimalHandling = decimalHandling;
	}

	public int getBigNumberHandling() {
		return bigNumberHandling;
	}

	public void setBigNumberHandling(int bigBumberHandling) {
		this.bigNumberHandling = bigBumberHandling;
	}

	public int getNumericalTypeHandling() {
		return numericalTypeHandling;
	}

	public void setNumericalTypeHandling(int numericalTypeHandling) {
		this.numericalTypeHandling = numericalTypeHandling;
	}

}
