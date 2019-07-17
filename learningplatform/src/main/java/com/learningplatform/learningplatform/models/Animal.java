package com.learningplatform.learningplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of an animal.
 *
 */
@Entity
@Table(name = "animal")
public class Animal extends Model {

	public enum PossibleAttr {
		NAME, FOOD_REQ;
	}

	private int uid;
	private String name;
	private float foodReq;

	public Animal(String name, float foodReq) {
		this.name = name;
		this.foodReq = foodReq;
	}

	public Animal() {
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "food_Req")
	public float getFoodReq() {
		return foodReq;
	}

	public void setFoodReq(float foodReq) {
		this.foodReq = foodReq;
	}

	@Id
	@NotNull
	@Column(name = "id_Animal", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
