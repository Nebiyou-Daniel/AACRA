package com.aacra.record.model;

import java.io.InputStream;

public class BookingRecord {
	private int arrestRecordId;
	private InputStream mugshot;
	private String bookingNumber;
	
	public int getArrestRecordId() {
		return arrestRecordId;
	}
	public void setArrestRecordId(int arrestRecordId) {
		this.arrestRecordId = arrestRecordId;
	}
	public InputStream getMugshot() {
		return mugshot;
	}
	public void setMugshot(InputStream mugshot) {
		this.mugshot = mugshot;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
}
