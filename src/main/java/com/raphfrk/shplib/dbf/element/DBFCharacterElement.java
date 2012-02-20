package com.raphfrk.shplib.dbf.element;

import java.io.IOException;

import com.raphfrk.shplib.dbf.DBFException;
import com.raphfrk.shplib.dbf.DBFField;
import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class DBFCharacterElement extends DBFElement {

	private final String value;
	
	public DBFCharacterElement(DBFField field, ExtendedDataInputStream in, int position, int length) throws IOException {
		super(field);
		in.skip(position);
		value = in.readASCII(length - position);
		
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return super.toString() + ":'" + value + "'";
	}

}
