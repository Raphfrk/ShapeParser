package com.raphfrk.shplib.dbf.element;

import java.io.IOException;

import com.raphfrk.shplib.dbf.DBFException;
import com.raphfrk.shplib.dbf.DBFField;
import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class DBFNumberElement extends DBFElement {

	private final long value;
	
	public DBFNumberElement(DBFField field, ExtendedDataInputStream in, int position, int length) throws IOException {
		super(field);
		in.skip(position);
		String numericString = in.readASCII(length - position);
		try {
			value = Long.parseLong(numericString.trim());
		} catch (NumberFormatException e) {
			throw new DBFException("Unable to parse " + numericString, e);
		}
	}
	
	public long getValue() {
		return value;
	}
	
	public String toString() {
		return super.toString() + ":" + value;
	}

}
