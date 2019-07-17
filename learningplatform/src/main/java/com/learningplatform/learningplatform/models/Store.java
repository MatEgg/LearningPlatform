package com.learningplatform.learningplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of a Store.
 *
 */
@Entity
@Table(name = "store")
public class Store extends Model {

	private int uid;
	// normal name of the element
	private String name;
	// name of the element when it is in plural
	private String namePlural;
	// name of the element when it is at the start
	private String nameStart;
	// name of the element when it is at the start (variation)
	private String nameStartStart;
	// name of the element when it is in the middle
	private String nameMiddle;
	private int area;
	private int length;
	private int width;
	private int height;
	private int animalCount;

	public Store(String name, String namePlural, String nameStart, String nameStartStart, String nameMiddle, int area,
			int length, int width, int height, int animalCount) {
		this.name = name;
		this.namePlural = namePlural;
		this.nameStart = nameStart;
		this.nameStartStart = nameStartStart;
		this.nameMiddle = nameMiddle;
		this.area = area;
		this.length = length;
		this.width = width;
		this.height = height;
		this.animalCount = animalCount;
	}

	public Store() {
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_plural")
	public String getNamePlural() {
		return namePlural;
	}

	public void setNamePlural(String namePlural) {
		this.namePlural = namePlural;
	}

	@Column(name = "name_start")
	public String getNameStart() {
		return nameStart;
	}

	public void setNameStart(String nameStart) {
		this.nameStart = nameStart;
	}

	@Column(name = "area")
	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	@Column(name = "length")
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Column(name = "width")
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Column(name = "animalCount")
	public int getAnimalCount() {
		return animalCount;
	}

	public void setAnimalCount(int animalCount) {
		this.animalCount = animalCount;
	}

	@Id
	@NotNull
	@Column(name = "id_store", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "height")
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Column(name = "name_start_start")
	public String getNameStartStart() {
		return nameStartStart;
	}

	public void setNameStartStart(String nameStartStart) {
		this.nameStartStart = nameStartStart;
	}

	@Column(name = "name_middle")
	public String getNameMiddle() {
		return nameMiddle;
	}

	public void setNameMiddle(String nameMiddle) {
		this.nameMiddle = nameMiddle;
	}
}
