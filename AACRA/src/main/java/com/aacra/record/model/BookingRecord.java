package com.aacra.record.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class BookingRecord {
	private int arrestRecordId;
	private int criminalId;
	
	private InputStream mugshot;
	private OutputStream mugshotPhoto;
	private String bookingNumber;
	
	public int getCriminalId() {
		return criminalId;
	}
	public void setCriminalId(int criminalId) {
		this.criminalId = criminalId;
	}
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
    public OutputStream getMugshotPhoto() {
		return mugshotPhoto;
	}
	public void setMugshotPhoto(OutputStream mugshotPhoto) {
		this.mugshotPhoto = mugshotPhoto;
	}
	
	
	public static InputStream blobToInputStream(Blob blob) {
        try {
            if (blob != null) {
                return blob.getBinaryStream();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void copyInputStreamToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            // Close the streams
            inputStream.close();
            outputStream.close();
        }
    }
}
