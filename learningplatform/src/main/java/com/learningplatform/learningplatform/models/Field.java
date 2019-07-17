package com.learningplatform.learningplatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model of a field.
 *
 */
@Entity
@Table(name = "field")
public class Field extends Model {

	private int uid;
	private String name;
	private String namePlural;
	private String nameStart;
	private float area;
	private float length;
	private float width;
	private float height;
	private int used;

	public Field(String name, String namePlural, String nameStart, float area, float length, float width,
			float height, int used) {
		this.name = name;
		this.namePlural = namePlural;
		this.nameStart = nameStart;
		this.area = area;
		this.length = length;
		this.width = width;
		this.height = height;
		this.used = used;
	}

	public Field() {
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
	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	@Column(name = "length")
	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	@Column(name = "width")
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	@Column(name = "used")
	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	@Id
	@NotNull
	@Column(name = "id_field", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "height")
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
