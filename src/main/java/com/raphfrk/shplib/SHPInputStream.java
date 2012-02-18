package com.raphfrk.shplib;

import java.io.DataInputStream;
import java.io.InputStream;

public class SHPInputStream extends DataInputStream {
		
		public SHPInputStream(InputStream in) {
			super(in);
		}
}
