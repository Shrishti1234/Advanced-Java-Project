package org.nagarro.FlightDetails;

public class FlightDetails{
	
	private String flightNumber;
	private String departureLocation;
	private String arrivalLocation;
	private String duration;
	private float fare;
	private static String flightClass ;
	private String flightTime;
	static final String buisnessClass = "B";
	
	
	// Constructor
	FlightDetails(String flightNumber,String departure,String arrival,String duration,float fare,String flightTime){
		this.flightNumber = flightNumber;
		this.departureLocation = departure;
		this.arrivalLocation = arrival;
		this.fare = fare ;
		this.duration = duration;
		this.flightTime = flightTime;
		
	}
	public static void setflightClass(String Class ) {
		flightClass = Class;
	}
	
	public String  getFlightNumber() {
		return this.flightNumber;
	}
	
	public String  getdepartureLocation() {
		return this.departureLocation;
	}
	
	public String  getarrivalLocation() {
		return this.arrivalLocation;
	}
	
	
	
	
	
	public String  getDuration() {
		return this.duration;
	}
	
	public float getFare() {
		
		return this.fare;
 }
	public void setFareClass() {
		
		if(flightClass.equalsIgnoreCase(buisnessClass)) 
			 this.fare = (this.fare*140)/100;
		
	}
	
	
	public void tostring() {
		System.out.println(flightNumber+"\t"+departureLocation+"\t"+arrivalLocation+"\t"+fare+"\t"+duration+"\t"+flightTime);
	}
	
	
	}
