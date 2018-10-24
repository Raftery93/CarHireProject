package ie.gmit.sw.ds.RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiRunner {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		InterfaceRMI bookingService = new CarHireImpl();

		LocateRegistry.createRegistry(1099); // Start the RMI registry on port
												// 1099

		Naming.rebind("bookingService", bookingService); // Bind our remote
															// object to the
															// registry with the
															// human-readable
															// name
															// "bookingService"

		System.out.println("Server ready."); // Print a message to standard
	}

}
