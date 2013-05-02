package com.smiechmateusz.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="foobaz")
public class Foo implements Serializable
{
	private int x, y;
	String description;
	int id;
	
	@Column(name = "x")
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Column(name = "y")
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
