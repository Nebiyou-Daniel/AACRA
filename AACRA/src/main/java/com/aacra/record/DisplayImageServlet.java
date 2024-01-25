package com.aacra.record;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.aacra.record.model.BookingRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/displayImage")
public class DisplayImageServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Retrieve the InputStream from the User object in the request
		
		String mugshot = request.getParameter("mugshot");
//		BookingRecord bi = (BookingRecord) request.getAttribute("bookingRecord");
        InputStream photoInputStream = convertStringToInputStream(mugshot);
        
        response.setContentType("image/jpeg");
        copyInputStreamToResponse(photoInputStream, response);

	}
    private InputStream convertStringToInputStream(String inputString) {
        byte[] bytes = inputString.getBytes(StandardCharsets.UTF_8);
        return new ByteArrayInputStream(bytes);
    }

    private void copyInputStreamToResponse(InputStream inputStream, HttpServletResponse response) throws IOException {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } finally {
            // Close the InputStream
            inputStream.close();
        }
    }
}
