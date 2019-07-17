package com.learningplatform.learningplatform.models;

import java.util.Set;

import com.learningplatform.learningplatform.models.skills.conceptual.ConceptualSkill;
import com.learningplatform.learningplatform.models.skills.numerical.NumericalSkill;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Model of a user.
 *
 */
@Entity
@Table(name = "user")
public class User extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_user", unique = true)
	private int uid;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToOne
	@JoinColumn(name = "id_settings")
	private Settings settings;

	@OneToOne
	@JoinColumn(name = "id_numerical_skill")
	private NumericalSkill numericalSkill;

	@OneToOne
	@JoinColumn(name = "id_conceptual_skill")
	private ConceptualSkill conceptualSkill;

	public User(String name, String password, Settings settings, NumericalSkill numericalSkill,
			ConceptualSkill conceptualSkill) {
		this.name = name;
		this.password = password;
		this.settings = settings;
		this.numericalSkill = numericalSkill;
		this.conceptualSkill = conceptualSkill;
	}

	public User() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public NumericalSkill getNumericalSkill() {
		return numericalSkill;
	}

	public void setNumericalSkill(NumericalSkill numericalSkill) {
		this.numericalSkill = numericalSkill;
	}

	public ConceptualSkill getConceptualSkill() {
		return conceptualSkill;
	}

	public void setConceptualSkill(ConceptualSkill conceptualSkill) {
		this.conceptualSkill = conceptualSkill;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
