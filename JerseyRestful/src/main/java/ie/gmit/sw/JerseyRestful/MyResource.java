package ie.gmit.sw.JerseyRestful;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import ie.gmit.sw.Marshalling.MarshalBooking;
import ie.gmit.sw.ds.Models.Booking;
import ie.gmit.sw.ds.RMI.InterfaceRMI;;

/**
 * Root resource (exposed at "myresource" path)
 */
@XmlRootElement
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

}
