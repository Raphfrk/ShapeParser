package com.raphfrk.shplib.dbf;

import java.io.IOException;
import java.io.InputStream;

import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class DBFInputStream extends ExtendedDataInputStream {
	
	private DBFHeader header;
	
	public DBFInputStream(InputStream in) throws IOException {
		super(in);
		header = new DBFHeader(this);
	}
	
	/**
	 * Reads a DBFRecord from this stream
	 * 
	 * @return the record
	 * @throws IOException
	 */
	public DBFRecord readRecord() throws IOException {
		return header.readDBFRecord(this, null);
	}
	
}
