package com.learningplatform.learningplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of a product.
 *
 */
@Entity
@Table(name = "product")
public class Product extends Model {

	private int uid;
	private String name;
	private int cost;

	public Product(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	public Product() {
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@NotNull
	@Column(name = "id_product", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "cost")
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
