package com.learningplatform.learningplatform.settings;

import com.learningplatform.learningplatform.guiObjects.DifficultyGui;

public class SettingsGui {

	private int uid;
	private DifficultyGui difficultyGui;
	private int decimalEnabled;
	private int measurementEnabled;
	private int percentageEnabled;
	private int automaticEnabled;
	private int sensibility;

	public SettingsGui(int uid, DifficultyGui difficultyGui, int decimalEnabled, int measurementEnabled,
			int percentageEnabled, int automaticEnabled, int sensibility) {
		this.uid = uid;
		this.difficultyGui = difficultyGui;
		this.decimalEnabled = decimalEnabled;
		this.automaticEnabled = automaticEnabled;
		this.measurementEnabled = measurementEnabled;
		this.percentageEnabled = percentageEnabled;
		this.sensibility = sensibility;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public DifficultyGui getDifficultyGui() {
		return difficultyGui;
	}

	public void setDifficultyGui(DifficultyGui difficultyGui) {
		this.difficultyGui = difficultyGui;
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
