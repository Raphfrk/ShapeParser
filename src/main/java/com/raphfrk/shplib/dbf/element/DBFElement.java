package com.raphfrk.shplib.dbf.element;

import com.raphfrk.shplib.dbf.DBFField;

public class DBFElement {
	
	private DBFField field;
	
	protected DBFElement(DBFField field) {
		this.field = field;
	}
	
	public String toString() {
		return field.toString();
	}
}
