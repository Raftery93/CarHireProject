<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ie.gmit.sw.ApiConsumer.Controller"%>
<%@page import="ie.gmit.sw.Models.Booking"%>
<%@page import="ie.gmit.sw.Models.Customer"%>
<%@page import="ie.gmit.sw.Models.Vehicle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking</title>
</head>
<body>

	<%
		Controller controller = new Controller();
		int bookingId = Integer.parseInt(request.getParameter("booking_id"));

		Booking booking = new Booking();


		booking.setBookingId(bookingId);
		booking = controller.getBookingId(bookingId);
	%>


<h1>Booking Details:</h1>
<p>Booking ID:
<%=booking.getBookingId()
%>
</p>
<p>Vehicle ID:
<%=booking.getVehicleId()
%>
<p>Customer ID:
<%=booking.getCustomerId()
%>
<p>Start Date:
<%=booking.getStartDate()
%>
<p>End Date:
<%=booking.getEndDate()
%>

</body>
</html>