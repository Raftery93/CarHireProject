package ie.gmit.sw.ds.Controllers;

import java.io.Console;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import ie.gmit.sw.ds.RMI.InterfaceRMI;
import ie.gmit.sw.ds.RMI.ReturnedCarHire;

public class BookingController {
	
	InterfaceRMI bookingSevice;
	
	public BookingController(){
		try {
			this.bookingSevice = (InterfaceRMI) Naming.lookup("rmi://127.0.0.1:1099/carBookingService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createBooking(ReturnedCarHire booking){
		//String query = "Insert INTO bookings VALUES(" + booking.getBookingId() + "," + booking.getVehicleId() + ","
		//		+ booking.getCustomerId() + ",\"" + booking.getStartDate() + "\",\"" + booking.getEndDate() + "\");";
		
		String query = "Insert into carbookings values(123, 456, 789, '11-12-18', '12-12-18');";
		
		try {
			bookingSevice.createCarHire(query);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBooking(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter booking id to delete:\n");
		int bookingId = sc.nextInt();
		String query = "Delete from carbookings where booking_id = " + bookingId + ";";
		
		try {
			bookingSevice.deleteCarHire(query);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}