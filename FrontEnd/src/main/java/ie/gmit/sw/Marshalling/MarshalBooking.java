package ie.gmit.sw.Marshalling;

import java.io.*;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;

import ie.gmit.sw.Models.Booking;

public class MarshalBooking {

	public MarshalBooking(){
		//null contructor
	}
	
	protected String getBookingAsXML(Booking booking) {
		// Marshal the Booking into XML
		StringWriter sw = new StringWriter();
		Marshaller m;

		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.ds.Models");
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(booking, sw);
		} catch (JAXBException e) {
			System.out.println("Error marshalling booking");
			e.printStackTrace();
		}

		return sw.toString();
	}// end getBookingAsXML
	
	protected Booking getBookingFromXML(String input) {
		// Unmarshal the Booking from XML
		StringReader sr1 = new StringReader(input);
		Unmarshaller um1;
		Booking bookingFromXml = null;

		try {
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.ds.Models");
			um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<Booking> bookingElement1 = um1.unmarshal(source1, Booking.class);
			bookingFromXml = (Booking) bookingElement1.getValue();
		} catch (JAXBException e) {
			System.out.println("Problem unmarshalling booking object");
			e.printStackTrace();
		}

		return bookingFromXml;
	}
}
