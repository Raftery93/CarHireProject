package ie.gmit.sw.ds.RMI;

import java.rmi.*;
import java.util.List;

import ie.gmit.sw.ds.Models.Booking;


public interface InterfaceRMI extends Remote{
	
	public void createCarHire(String q) throws RemoteException; // to create a booking
	
	public List<Booking> readCarHire() throws RemoteException; // to list all the bookings
	
	public void updateCarHire(String q) throws RemoteException; // to update an existing booking
	
	public void deleteCarHire(String q) throws RemoteException; // to delete an existing booking

}
