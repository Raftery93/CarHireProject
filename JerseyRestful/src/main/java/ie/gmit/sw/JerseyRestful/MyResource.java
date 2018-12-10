package ie.gmit.sw.JerseyRestful;

import java.net.MalformedURLException;
import java.rmi.Naming;
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
	public Response create(String input) {
    	
    	
    	Booking booking = getBookingFromXML(input);
    	
		Customer customer = null;
		Vehicle vehicle = null;

		// construct the returnedBooking object
		booking.getBookingId();
		customer.getCustomerId();
		vehicle.getId();
		booking.getEndDate();
		booking.getStartDate();

		RmiConnector connector = new RmiConnector();
		connector.createBooking(booking);
		//ifRmi.createCarHire(booking);

		return Response.ok().build();
	}
    /*
    @GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response getById(@PathParam("value") String value) {
		Booking booking = new Booking();
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		int id = Integer.parseInt(value); // convert the value to an int
		ReturnedBooking returnedBooking = controller.getBookingById(id);
		if (returnedBooking != null) {
			System.out.println(returnedBooking.toString());
			// construct the booking response object
			booking.setBookingId(returnedBooking.getBookingId());
			customer.setCustomerId(returnedBooking.getCustomerId());
			vehicle.setId(returnedBooking.getVehicleId());
			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setStartDate(returnedBooking.getStartDate());
			booking.setEndDate(returnedBooking.getEndDate());
			booking.setBookingNumber(null);

			String msg = getBookingAsXML(booking);

			System.out.println(msg);

			return Response.status(200).entity(msg).build();
		} else {
			return Response.status(404).entity("Resource doesn't exist").build();
		}

	}
	*/

}
