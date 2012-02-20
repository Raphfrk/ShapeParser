package com.raphfrk.shplib.dbf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.raphfrk.shplib.dbf.element.DBFElement;
import com.raphfrk.shplib.util.ExtendedDataInputStream;

public class DBFHeader {

	private final byte version;
	private final long records;
	private final int year;
	private final int month;
	private final int day;
	private final byte flags;
	private final int headerLength;
	private final int recordLength;
	private final List<DBFField> fields;
	private final int fieldCount;
	
	public DBFHeader(ExtendedDataInputStream in) throws IOException {
		version = in.readByte();
		if (version != 3) {
			throw new DBFException("Header version must be 3");
		}
		year = 1900 + (in.readByte() & 0xFF);
		month = in.readByte() & 0xFF;
		day = in.readByte() & 0xFF;
		records = in.readIntLE() & 0xFFFFFFFFL;
		headerLength = in.readShortLE() & 0xFFFF;
		recordLength = in.readShortLE() & 0xFFFF;
		for (int i = 0; i < 16; i++) {
			in.readByte();
		}
		flags = in.readByte();
		in.readByte(); // mark
		if (in.readShort() != 0) {
			throw new DBFException("Reserved at position 30, 31 not 0");
		}
		ArrayList<DBFField> fields = new ArrayList<DBFField>();
		while (true) {
			DBFField next = new DBFField(this, in);
			if (next.isTerminator()) {
				break;
			} else {
				fields.add(next);
			}
		}
		this.fields = Collections.unmodifiableList(fields);
		fieldCount = this.fields.size();
	}
	
	/**
	 * Gets the version byte
	 * 
	 * @return the version byte
	 */
	public byte getVersion() {
		return version;
	}
	
	/**
	 * Gets the number of records 
	 * 
	 * @return the number of records
	 */
	public long getRecords() {
		return records;
	}
	
	/**
	 * Get record length in bytes
	 * 
	 * @return the record length
	 */
	public int getRecordLength() {
		return recordLength;
	}
	
	/**
	 * Get header length in bytes
	 * 
	 * @return the header length
	 */
	public int getHeaderLength() {
		return headerLength;
	}
	
	/**
	 * Gets the year of the last update
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/** 
	 * Gets the day of the month of the last update
	 * 
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Gets the month of the last update
	 * 
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Gets flags
	 * 
	 * @return flags
	 */
	public byte getFlags() {
		return flags;
	}
	
	/**
	 * Get number of fields
	 */
	public List<DBFField> getFields() {
		return fields;
	}
	
	/**
	 * Read a DBFRecord from a stream.  A new record instance is created if record is null or the wrong length
	 * 
	 * @param in the input stream
	 * @param record a temporary record
	 * @return the record
	 */
	public DBFRecord readDBFRecord(ExtendedDataInputStream in, DBFRecord record) throws IOException {
		if (record == null || record.getFieldCount() != fieldCount) {
			record = new DBFRecord(fieldCount);
		}
		byte deleteFlag = in.readByte();
		DBFElement[] elementArray = record.getElementArray();
		for (int i = 0; i < fieldCount; i++) {
			DBFElement e = fields.get(i).readElement(in);
			elementArray[i] = e;
		}
		return record;
	}
	
}
