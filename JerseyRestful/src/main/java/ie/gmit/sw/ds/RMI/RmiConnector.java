package ie.gmit.sw.ds.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import ie.gmit.sw.ds.Models.Booking;

public class RmiConnector {
	
	InterfaceRMI bookingService;
	
	public RmiConnector(){
		System.out.println("In RmiConnector before naming lookup");

		try {
			this.bookingService = (InterfaceRMI) Naming.lookup("rmi://127.0.0.1:1099/carbooking");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// ============== READ ====================
	public List<Booking> readData() throws Exception {
		return bookingService.readCarHire();
	}
	
	//=============== CREATE ==================
	public void createBooking(Booking booking) {
		String query = "Insert INTO bookings VALUES(" + booking.getBookingId() + "," + booking.getVehicleId() + ","
				+ booking.getCustomerId() + ",\"" + booking.getStartDate() + "\",\"" + booking.getEndDate() + "\");";


			try {
				bookingService.createCarHire(query);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
