package com.psa.flight_reservation_app.utilities;

import java.io.FileNotFoundException;


import java.io.FileOutputStream;

import java.util.Date;

import org.springframework.jdbc.core.metadata.GenericTableMetaDataProvider;
import org.springframework.stereotype.Component;

//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.List;
//import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.psa.flight_reservation_app.entity.Reservation;

@Component
public class PDFgenerator {
		
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);

	    
	    public void generateItineray(Reservation reservation, String filePath) {
	    	Document document = new Document();
	    	try {
	    		PdfWriter.getInstance(document, new FileOutputStream(filePath));
	    		document.open();
	    		document.add(generateTable( reservation));
	    		document.close();
	    	}catch(FileNotFoundException | DocumentException e) {
	    		System.out.println(e);
	    	}catch(Exception e) {
	    		System.out.println(e);
	    	}
	    	
	    	
	    }
	  
	    
	    private PdfPTable generateTable(Reservation reservation) throws Exception {
	    	PdfPTable table = new PdfPTable(2);
	    	PdfPCell cell;
	    	
	    	cell = new PdfPCell(new Phrase("Flight Itinerary"));
	    	cell.setColspan(2);
	    	table.addCell(cell);
	    	
	    	
	    	cell = new PdfPCell(new Phrase("Flight Details"));
	    	cell.setColspan(2);
	    	table.addCell(cell);
	    	
	    	table.addCell("Departure City");
	    	table.addCell(reservation.getFlight().getDepartureCity());
	    	
	    	
	    	table.addCell("Arrival City");
	    	table.addCell(reservation.getFlight().getArrivalCity());
	    	
	    	table.addCell("Flight Number");
	    	table.addCell(reservation.getFlight().getFlightNumber());

	    	table.addCell("Operating Airline");
	    	table.addCell(reservation.getFlight().getOperatingAirlines());
	   
	    
	    	table.addCell("Departure Date");
	    	table.addCell(reservation.getFlight().getDateOfDeparture().toString());
	    	
	    	table.addCell("Departure Time");
	    	table.addCell(reservation.getFlight().getEsimatedDepartureTime().toString());
	    	

	    	cell= new PdfPCell(new Phrase("Passenger Details"));
	    	cell.setColspan(2);
	    	table.addCell(cell);
	    	
	    	
	    	table.addCell("First Name");
	    	table.addCell(reservation.getPassenger().getFirstName());
	    	
	    	
	    	table.addCell("Last Name");
	    	table.addCell(reservation.getPassenger().getLastName());
	    	
	    	table.addCell("Email");
	    	table.addCell(reservation.getPassenger().getEmail());

	    	
	    	table.addCell("Mobile Number");
	    	table.addCell(reservation.getPassenger().getPhone());
	    
	    	 
		       // document.close();
	    	 return table;
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*
	  
	  
	  // This is the first apporach it is also working find it is created a pdf file named reservation0
	  // because of the lenthar code i am coping sir's code another one.
	  
	    public void generatedPDF(String filePath, String name, String emailId, String phone, String operatingAirlines, Date departureDate, String departureCity, String arrivalCity) {
	        try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream(filePath));
	            document.open();
	            addMetaData(document);
	            
	            addTitleAndTable(document,name, emailId, phone, operatingAirlines, departureDate,departureCity ,arrivalCity);
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 
	    private static void addMetaData(Document document) {
	        document.addTitle("My first PDF");
	        document.addSubject("Using iText");
	        document.addKeywords("Java, PDF, iText");
	        document.addAuthor("Lars Vogel");
	        document.addCreator("Lars Vogel");
	    }

	    private static void addTitleAndTable(Document document,String name,String emailId, String phone, String operatingArilines, Date departureDate, String departureCity, String arrivalCity )
	            throws DocumentException {
	        Paragraph preface = new Paragraph();
	        preface.add(new Paragraph("Flight Booking Details", catFont));

	        preface.add(new Paragraph(
	                "Report generated by: " + "Flight Reservation Company" + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                smallBold));
//	
	        document.add(preface);
	        document.add(Chunk.NEWLINE);
	        document.add(Chunk.NEWLINE);
	        
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        
	        PdfPCell cl = new PdfPCell(new Phrase("Passenger Detail"));
	        cl.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cl.setColspan(2);
	        table.addCell(cl);
	        
	        table.addCell("Passenger name");
	        table.addCell(name);
	        table.addCell("Email Id");
	        table.addCell(emailId);
	        table.addCell("Phone Number");
	        table.addCell(phone);
	        
	        document.add(table);
	        //bgjg  ggh yky
	    
	        PdfPTable table1 = new PdfPTable(2);
	        table1.setWidthPercentage(100); 
	        
	        PdfPCell c2 = new PdfPCell(new Phrase("Flight Detail"));
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c2.setColspan(2);
	        table1.addCell(c2);
	        
	        table1.addCell("Operating Airlines");
	        table1.addCell(operatingArilines);
	        table.addCell("Departure Date");
	        table.addCell(departureDate.toString());
	        table.addCell("Departure City");
	        table.addCell(departureCity);
	       
	        table.addCell("Arrival City");
	        table.addCell(arrivalCity);
	        
	        document.add(table1);
	        document.close();
	             
	    }

	    */
}

