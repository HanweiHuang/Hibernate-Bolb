package com.harvey.Entity;

import java.sql.Blob;
import java.sql.Clob;

/**
 * Student Entity
 * @author Harvey
 * @date Sep 1, 2016
 * @time 12:23:08 PM
 */

public class Student {
	//id
	private int id;
	//student name
	private String name;
	//student image
	private Blob image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
}
