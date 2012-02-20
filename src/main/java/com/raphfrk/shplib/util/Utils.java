package com.raphfrk.shplib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.raphfrk.shplib.ShapeException;

public class Utils {
	
	public static ZipEntry getZipEntry(ZipFile zipFile, String extension) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (entry.getName().endsWith(extension)) {
				return entry;
			}
		}
		return null;
	}

	public static InputStream getZipInputStream(ZipFile zipFile, String extension) throws IOException {
		ZipEntry entry = getZipEntry(zipFile, extension);
		if (entry == null) {
			throw new ShapeException("No " + extension + " file in zip file, " + zipFile.getName());
		} else {
			return zipFile.getInputStream(entry);
		}
	}
	
	public static FileInputStream getFileInputStream(File directory, String extension) throws IOException {
		File[] files = directory.listFiles();
		
		for (File f : files) {
			if (f.getName().endsWith(extension)) {
				return new FileInputStream(f);
			}
		}
		throw new IOException("No " + extension + " file in directory, " + directory);
	}

}
