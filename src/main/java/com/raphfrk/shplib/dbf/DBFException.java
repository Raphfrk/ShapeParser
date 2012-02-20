package com.raphfrk.shplib.dbf;

import java.io.IOException;

public class DBFException extends IOException {

	private static final long serialVersionUID = 1L;

	public DBFException(String message) {
		super(message);
	}
	
	public DBFException(String message, Throwable t) {
		super(message, t);
	}
	
}
