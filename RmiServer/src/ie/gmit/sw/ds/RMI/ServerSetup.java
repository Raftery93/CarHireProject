package ie.gmit.sw.ds.RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class ServerSetup {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		InterfaceRMI carBookingService = new CarHireImpl();

		// Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "carBookingService"
		Naming.rebind("carbooking", carBookingService);

		// Print a message to standard
		System.out.println("Server ready.");
		
		//BookingController bc = new BookingController();
		//bc.createBooking(null);
		//bc.deleteBooking();
	}

}
