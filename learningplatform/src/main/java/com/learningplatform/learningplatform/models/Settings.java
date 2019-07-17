package com.learningplatform.learningplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.aspectj.weaver.bcel.AtAjAttributes;

import com.learningplatform.learningplatform.models.difficulty.Difficulty;

/**
 * Model of possible settings.
 *
 */
@Entity
@Table(name = "settings")
public class Settings extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_settings", unique = true)
	private int uid;

	@OneToOne
	@JoinColumn(name = "id_difficulty")
	private Difficulty difficulty;

	@Column(name = "decimal_enabled")
	private int decimalEnabled;

	@Column(name = "measurement_enabled")
	private int measurementEnabled;

	@Column(name = "percentage_enabled")
	private int percentageEnabled;

	@Column(name = "sensibility")
	private int sensibility;

	@Column(name = "automatic_enabled")
	private int automaticEnabled;

	@OneToOne(mappedBy = "settings")
	private User user;

	public Settings(int decimalEnabled, Difficulty difficulty, int automaticEnabled) {
		this.decimalEnabled = decimalEnabled;
		this.difficulty = difficulty;
		this.automaticEnabled = automaticEnabled;
		this.sensibility = 3;
	}

	public Settings() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDecimalEnabled() {
		return decimalEnabled;
	}

	public void setDecimalEnabled(int decimalEnabled) {
		this.decimalEnabled = decimalEnabled;
	}

	public int getAutomaticEnabled() {
		return automaticEnabled;
	}

	public void setAutomaticEnabled(int automaticEnabled) {
		this.automaticEnabled = automaticEnabled;
	}

	public int getSensibility() {
		return sensibility;
	}

	public void setSensibility(int sensibility) {
		this.sensibility = sensibility;
	}

	public int getMeasurementEnabled() {
		return measurementEnabled;
	}

	public void setMeasurementEnabled(int measurementEnabled) {
		this.measurementEnabled = measurementEnabled;
	}

	public int getPercentageEnabled() {
		return percentageEnabled;
	}

	public void setPercentageEnabled(int percentageEnabled) {
		this.percentageEnabled = percentageEnabled;
	}

}
