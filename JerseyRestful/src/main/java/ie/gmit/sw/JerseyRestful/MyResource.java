package ie.gmit.sw.JerseyRestful;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import ie.gmit.sw.Marshalling.MarshalBooking;
import ie.gmit.sw.ds.Models.Booking;
import ie.gmit.sw.ds.Models.Customer;
import ie.gmit.sw.ds.Models.Vehicle;
import ie.gmit.sw.ds.RMI.InterfaceRMI;
import ie.gmit.sw.ds.RMI.RmiConnector;;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource extends MarshalBooking{
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Booking> getIt() throws MalformedURLException, RemoteException, Exception  {
    	
    	InterfaceRMI ifRmi;
    	
    	ifRmi = (InterfaceRMI) Naming.lookup("rmi://127.0.0.1:1099/carbooking");
    	List<Booking> allBookings = ifRmi.readCarHire();
    	
    	for (Booking bmc : allBookings) {
			System.out.println(bmc.toString());
			
		}
    	//System.out.println(ifRmi.readCarHire().toString());
        return allBookings;
    }
    
    
    @POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response create(String input) throws RemoteException, MalformedURLException, NotBoundException {
    	
    	RmiConnector connector = new RmiConnector();
    	
    	Booking booking = getBookingFromXML(input);
    	
  
		System.out.println(booking.toString()+"============================");
		// construct the returnedBooking object
		booking.setBookingId(booking.getBookingId());
		booking.setCustomerId(booking.getCustomerId());
		booking.setVehicleId(booking.getVehicleId());
		booking.setEndDate(booking.getEndDate());
		booking.setStartDate(booking.getStartDate());

		connector.createBooking(booking);

		return Response.ok().build();
	}
    
    @GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response getById(@PathParam("value") String value) {
		Booking booking = new Booking();
		RmiConnector connector = new RmiConnector();
		int id = Integer.parseInt(value); // convert the value to an int
		booking = connector.getBookingById(id);
		if (booking != null) {
			System.out.println(booking.toString());
			// construct the booking response object
			booking.setBookingId(booking.getBookingId());
			booking.getCustomerId();
			booking.getVehicleId();
			booking.setCustomerId(id);
			booking.setVehicleId(booking.getVehicleId());
			booking.setStartDate(booking.getStartDate());
			booking.setEndDate(booking.getEndDate());
			booking.setBookingNumber(null);

			String msg = getBookingAsXML(booking);

			System.out.println(msg);

			return Response.status(200).entity(msg).build();
		} else {
			return Response.status(404).entity("Resource doesn't exist").build();
		}

	}
    
    //================ DELETE ==============================
    @DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response delete(@PathParam("id") final String id, String input) {

    	RmiConnector connector = new RmiConnector();
    	
		int value = Integer.parseInt(id); // convert the value to an int

		Booking returnedBooking = connector.getBookingById(value);

		if (returnedBooking != null) {
			connector.deleteBooking(value);
		}

		return Response.ok().build();
	}
    
    //=================== UPDATE ============================
    @PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response Update(@PathParam("id") final String id, String input) {
    	RmiConnector connector = new RmiConnector();
		Booking booking = getBookingFromXML(input);
		int value = Integer.parseInt(id); // convert the value to an int
		Booking myBooking = connector.getBookingById(value);
		
		
		myBooking.setBookingId(booking.getBookingId());
		
		myBooking.setCustomerId(booking.getCustomerId());
		myBooking.setVehicleId(booking.getVehicleId());
		myBooking.setStartDate(booking.getStartDate());
		myBooking.setEndDate(booking.getEndDate());

		connector.updateBooking(myBooking);

		return Response.ok().build();

	}

	

}
