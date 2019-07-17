package com.learningplatform.learningplatform.types;

public class Construction {

	private int manpower;
	private String constructionMaterial;
	private int countPerArea;
	private float cost;

	public Construction(int manpower, String constructionMaterial, int countPerArea, float cost) {
		this.manpower = manpower;
		this.constructionMaterial = constructionMaterial;
		this.countPerArea = countPerArea;
		this.cost = cost;
	}

	public int getManpower() {
		return manpower;
	}

	public void setManpower(int manpower) {
		this.manpower = manpower;
	}

	public String getConstructionMaterial() {
		return constructionMaterial;
	}

	public void setConstructionMaterial(String constructionMaterial) {
		this.constructionMaterial = constructionMaterial;
	}

	public int getCountPerArea() {
		return countPerArea;
	}

	public void setCountPerArea(int countPerArea) {
		this.countPerArea = countPerArea;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
