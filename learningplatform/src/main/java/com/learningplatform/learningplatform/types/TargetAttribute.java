package com.learningplatform.learningplatform.types;


public class TargetAttribute {

	private int id;
	private int elementId;
	private boolean alreadyUpdated = false;

	public TargetAttribute(int id, int elementId, boolean alreadyUpdated) {
		this.id = id;
		this.elementId = elementId;
		this.alreadyUpdated = alreadyUpdated;
	}

	public TargetAttribute() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public boolean isAlreadyUpdated() {
		return alreadyUpdated;
	}

	public void setAlreadyUpdated(boolean alreadyUpdated) {
		this.alreadyUpdated = alreadyUpdated;
	}

}
