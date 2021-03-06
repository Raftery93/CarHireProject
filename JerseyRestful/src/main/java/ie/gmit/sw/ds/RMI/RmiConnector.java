package ie.gmit.sw.ds.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ds.Models.Booking;

public class RmiConnector {
	
	InterfaceRMI bookingService;
	
	public RmiConnector(){

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
	
	//========== GET BY ID =================
	public Booking getBookingById(int id) {
		List<Booking> bookings = new ArrayList<Booking>();
		Booking resultBooking = null;

		try {
			bookings = bookingService.readCarHire();
		} catch (RemoteException e) {
			System.out.println("error accessing data from remote object");
			e.printStackTrace();
		}

		for (Booking b : bookings) {
			if (b.getBookingId() == id) {
				resultBooking = b;
			}

		}
		
		if(resultBooking != null) {
			return resultBooking;
		}
		else {
			return null;
		}
		
	}
	
	public void deleteBooking(int id) {
		String query = "DELETE FROM bookings WHERE booking_id =" + id + ";";
		
		try {
			bookingService.deleteCarHire(query);
		} catch (RemoteException e) {
			System.out.println("error deleting booking in Booking controller");
			e.printStackTrace();
		}
		
	}
	
	public void updateBooking(Booking booking) {

		String query = "UPDATE bookings SET vehicle_id =" + booking.getVehicleId() + ", " + "customer_id ="
				+ booking.getCustomerId() + ", " + "start_date =\"" + booking.getStartDate() + "\", " + "end_date =\""
				+ booking.getEndDate() + "\" WHERE booking_id=" + booking.getBookingId() + ";";

		try {
			bookingService.updateCarHire(query);
		} catch (RemoteException e) {
			System.out.println("error updating booking in Booking controller");
			e.printStackTrace();
		}

	}
}
