package com.raphfrk.shplib.dbf;

import com.raphfrk.shplib.dbf.element.DBFElement;

public class DBFRecord {
	
	private final DBFElement[] elements;
	
	public DBFRecord(int fields) {
		this.elements = new DBFElement[fields];
	}
	
	/**
	 * Gets the number of fields in the record
	 * 
	 * @return
	 */
	public int getFieldCount() {
		return elements.length;
	}
	
	/**
	 * Gets the field array
	 * 
	 * @return the field array
	 */
	public DBFElement[] getElementArray() {
		return elements;
	}
	

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (DBFElement e : elements) {
			if (!first) {
				sb.append(",");
			} else {
				first = false;
			}
			sb.append(e);
		}
		return sb.toString();
	}
	
}
