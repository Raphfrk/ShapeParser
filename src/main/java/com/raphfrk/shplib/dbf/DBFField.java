package com.raphfrk.shplib.dbf;

import java.io.IOException;

import com.raphfrk.shplib.dbf.element.DBFCharacterElement;
import com.raphfrk.shplib.dbf.element.DBFElement;
import com.raphfrk.shplib.dbf.element.DBFNumberElement;
import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class DBFField {
	
	private final DBFHeader header;
	private final boolean terminator;
	private final String name;
	private final char type;
	private final int position;
	private final int length;
	private final int decimals;
	private final byte flags;
	private final int next;
	private final int step;
	
	public DBFField(DBFHeader header, ExtendedDataInputStream in) throws IOException {
		this.header = header;
		char c = (char)(in.readByte() & 0xFF);
		terminator = c == 0x0D;
		if (terminator) {
			name = null;
			type = 0;
			position = 0;
			length = 0;
			decimals = 0;
			flags = 0;
			next = 0;
			step = 0;
			return;
		} else {
			name = c + in.readASCII(10);
			type = (char)(in.readByte() & 0xFF);
			position = in.readIntLE();
			length = in.readByte() & 0xFF;
			decimals = in.readByte() & 0xFF;
			flags = in.readByte();
			next = in.readIntLE();
			step = in.readByte() & 0xFF;
			in.readLong(); // reserved
		}
	}
	
	public DBFElement readElement(ExtendedDataInputStream in) throws IOException {
		switch(type) {
		case 'N': return new DBFNumberElement(this, in, position, length);
		case 'C': return new DBFCharacterElement(this, in, position, length);
		default: throw new DBFException("Unknown element type: " + type);
		}
	}
	
	/**
	 * Gets if the field is a terminator field
	 * 
	 * @return
	 */
	public boolean isTerminator() {
		return terminator;
	}
	
	/**
	 * Gets the field name
	 * 
	 * @return the field name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the fields type character
	 * 
	 * @return the field type
	 */
	public char getType() {
		return type;
	}
	
	public DBFHeader getHeader() {
		return header;
	}
	
	public String toString() {
		return getName() + "(" + getType() + ")";
	}
	
}
