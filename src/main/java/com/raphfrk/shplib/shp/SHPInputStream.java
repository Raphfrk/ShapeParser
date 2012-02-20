package com.raphfrk.shplib.shp;

import java.io.InputStream;

import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class SHPInputStream extends ExtendedDataInputStream {
		
		public SHPInputStream(InputStream in) {
			super(in);
		}
}
