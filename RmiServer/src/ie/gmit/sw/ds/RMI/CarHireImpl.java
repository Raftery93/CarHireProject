package ie.gmit.sw.ds.RMI;

import java.rmi.RemoteException;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.rmi.server.*;
import ie.gmit.sw.ds.Models.Booking;

public class CarHireImpl extends UnicastRemoteObject implements InterfaceRMI{

	private static final long serialVersionUID = 1L;
	// connect to the database
	DataSource mysqlDS;
	Statement stmt;

	protected CarHireImpl() throws RemoteException, SQLException {
		super();

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carbooking?useSSL=false",
				"root", ""); // connect to the database
		stmt = conn.createStatement(); // create the statement
	}

	// Override implement methods
	public void createCarHire(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // just pass the query to the database
		} catch (SQLException e) {
			System.out.println("error processing sql query");
			e.printStackTrace();
		}
	}

	
	public List<Booking> readCarHire() throws RemoteException {
		String strSelect = "select * from bookings";
		ResultSet rset = null;
		//ArrayList<ResultSet> resultSetSerialized = new ArrayList<ResultSet>();
		Booking booking = new Booking();
		//Vehicle vehicle = new Vehicle();
		List<Booking> bookings = new ArrayList<Booking>();

		try {
			rset = stmt.executeQuery(strSelect); // generate the result set
		} catch (SQLException e) {
			System.out.println("sql error");
			System.out.println(e);
			System.out.println(rset.toString());
		}

		try {
			while (rset.next()) {
				booking.setBookingId(rset.getInt("booking_id"));
				booking.setVehicleId(rset.getInt("vehicle_id"));
				booking.setCustomerId(rset.getInt("customer_id"));
				booking.setStartDate(rset.getString("start_date"));
				booking.setEndDate(rset.getString("end_date"));
								
				bookings.add(booking);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bookings;
	}

	public void updateCarHire(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCarHire(String query) throws RemoteException {
		try {
			stmt.executeUpdate(query); // pass the query to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
