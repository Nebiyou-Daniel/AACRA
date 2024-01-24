package com.aacra.record;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.text.similarity.LevenshteinDistance;

@WebServlet("/pages/searchRecords")
public class SearchRecordsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get search parameters from the request
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String dob = request.getParameter("dob");
        String crimeType = request.getParameter("crimeType");

        // Perform the search logic
        ArrayList<Record> searchResults = performSearch(fname, lname, dob, crimeType);
        

        // Check if exact match not found, show similar results
        if (searchResults.isEmpty()) {
//            ArrayList<Record> similarResults = findSimilarResults(name, dob, crimeType);
//            request.setAttribute("similarResults", similarResults);
        	
        } else {
            request.setAttribute("searchResults", searchResults);        	
        }

        // Forward to the search results JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/SearchResults.jsp");
        dispatcher.forward(request, response);
    }

    private ArrayList<Record> performSearch(String fname,String lname, String dob, String crimeType) {
        // Implement your search logic here based on the provided parameters
        // This could involve querying a database or another data source
        // Return a ArrayList of matching records
        // ...

        // For the purpose of this example, let's return an empty ArrayList
        return null;
    }

//    private ArrayList<Record> findSimilarResults(String name, String dob, String crimeType) {
//        // Implement partial matching and fuzzy matching logic here
//        // For example, use Levenshtein distance for fuzzy matching
//
//        ArrayList<Record> allRecords = getAllRecords(); // Implement this method to retrieve all records
//
//        ArrayList<Record> similarResults = new ArrayList<>();
//
//        for (Record record : allRecords) {
//            // Implement your matching criteria based on name, dob, and crimeType
//            // For simplicity, let's use Levenshtein distance for name matching
//            int nameDistance = LevenshteinDistance.getDefaultInstance().apply(record.getName(), name);
//            
//            if (nameDistance < 3) { // Adjust the threshold based on your requirements
//                similarResults.add(record);
//            }
//            // Add similar matching logic for dob and crimeType
//        }
//
//        return similarResults;
//    }
}

