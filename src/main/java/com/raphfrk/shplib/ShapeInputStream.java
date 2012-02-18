package com.raphfrk.shplib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

public class ShapeInputStream {
    
	private final ZipFile shpZipFile;
	private final ZipFile dbfZipFile;
	private final InputStream shpInputStreamRaw;
	private final InputStream dbfInputStreamRaw;
	private final SHPInputStream shpInputStream;
	private final DBFInputStream dbfInputStream;
	
	/**
	 * Constructs a ShapeInputStream.  The file parameter can be a directory or a zip file.<br>
	 * <br>
	 * The .shp and .dbf files are parsed to extract the Shapes.
	 * 
	 * @param file the zip file or directory
	 * @throws IOException
	 */
	public ShapeInputStream(File file) throws IOException {
		if (!file.exists()) {
			throw new IOException("File does not exist, " + file);
		} else if (!file.isDirectory()) {
			shpZipFile = new ZipFile(file);
			dbfZipFile = new ZipFile(file);
			shpInputStreamRaw = Utils.getZipInputStream(shpZipFile, ".shp");
			dbfInputStreamRaw = Utils.getZipInputStream(shpZipFile, ".dbf");			
		} else {
			shpZipFile = null;
			dbfZipFile = null;
			shpInputStreamRaw = Utils.getFileInputStream(file, ".shp");
			dbfInputStreamRaw = Utils.getFileInputStream(file, ".dbf");		
		}
		shpInputStream = new SHPInputStream(shpInputStreamRaw);
		dbfInputStream = new DBFInputStream(dbfInputStreamRaw);
	}
	
	/**
	 * Closes the ShapeInputStream
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		IOException exception = null;
		try {
			shpInputStreamRaw.close();
		} catch (IOException e) {
			exception = e;
		}
		try {
			dbfInputStreamRaw.close();
		} catch (IOException e) {
			exception = e;
		}
		if (shpZipFile != null) {
			try {
				shpZipFile.close();
			} catch (IOException e) {
				exception = e;
			}
		}
		if (dbfZipFile != null) {
			try {
				dbfZipFile.close();
			} catch (IOException e) {
				exception = e;
			}
		}
		if (exception != null) {
			throw new IOException("Unable to close ShapeInputStream", exception);
		}
	}
	
	
}
