package ie.gmit.sw.ApiConsumer;

import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.Marshalling.*;
import ie.gmit.sw.Models.Booking;
import ie.gmit.sw.Models.Bookings;

public class RestAccess extends MarshalBooking {

	private String base = "http://localhost:8080/JerseyRestful/webapi/myresource"; 
	private Client client = ClientBuilder.newClient();
	private WebTarget target = client.target(base);
	private String xmlResponse;
	private String send;
	
	Response response; 

	public List<Booking> getBookings() { 

		
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(xmlResponse);
		
		Bookings bookings = getBookingsFromXML(xmlResponse);
		
		List<Booking> bookingList;
		
		bookingList = bookings.getBooking();
		System.out.println(bookingList.size());

		return bookingList;

	}
	
	public Booking getBookingById(int id) {
		target = client.target(base + "/" + id);
		xmlResponse = target.request(MediaType.APPLICATION_XML).get(String.class);
		Booking newBooking = getBookingFromXML(xmlResponse); 
		return newBooking;
		
	}
	
	public void updateBooking(Booking booking) {
		target = client.target(base + "/" + booking.getBookingId());
		send = getBookingAsXML(booking);
		response = target.request().put(Entity.xml(send)); 
		System.out.println(response); 
		
	}
	
	public void createBooking(Booking booking) { 
		target = client.target(base);
		
		send = getBookingAsXML(booking);
		
		response = target.request().post(Entity.xml(send));
		
		System.out.println(response);
	}
	
	public void deleteBooking(int id) {
		target = client.target(base + "/" + id);
		
		response = target.request().delete();
		
		System.out.println(response);
		
	}

}
