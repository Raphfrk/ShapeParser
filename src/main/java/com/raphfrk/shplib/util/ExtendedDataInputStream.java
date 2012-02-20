package com.raphfrk.shplib.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExtendedDataInputStream extends DataInputStream {
	
	public ExtendedDataInputStream(InputStream in) {
		super(in);
	}
	
	public short readShortLE() throws IOException {
		return (short) (((readByte() & 0xFF) << 0) | ((readByte() & 0xFF) << 8));
	}
	
	public int readIntLE() throws IOException {
		return 
				((readByte() & 0xFF) << 0) | 
				((readByte() & 0xFF) << 8) | 
				((readByte() & 0xFF) << 16) | 
				((readByte() & 0xFF) << 24);
	}
	
	public long readLongLE() throws IOException {
		return 
			((readByte() & 0xFFL) << 0) | 
			((readByte() & 0xFFL) << 8) | 
			((readByte() & 0xFFL) << 16) | 
			((readByte() & 0xFFL) << 24) |
			((readByte() & 0xFFL) << 32) | 
			((readByte() & 0xFFL) << 40) | 
			((readByte() & 0xFFL) << 48) | 
			((readByte() & 0xFFL) << 56);
	}
	
	public String readASCII(int length) throws IOException {
		char[] chars = new char[length];
		for (int i = 0; i < length; i++) {
			chars[i] = (char)(readByte() & 0xFF);
		}
		return new String(chars);
	}

}
