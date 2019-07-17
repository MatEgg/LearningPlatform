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
@Table(name = "number_skill")
public class NumberSkill extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_number_skill", unique = true)
	private int uid;

	@Column(name = "computational_rules_skill")
	private int computationalRulesSkill;

	public NumberSkill() {
		computationalRulesSkill = 1;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getComputationalRulesSkill() {
		return computationalRulesSkill;
	}

	public void setComputationalRulesSkill(int computationalRulesSkill) {
		this.computationalRulesSkill = computationalRulesSkill;
	}
}
