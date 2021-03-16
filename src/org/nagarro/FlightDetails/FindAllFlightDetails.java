package org.nagarro.FlightDetails;

import java.io.BufferedReader;
import java.util.concurrent.Callable;
import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FindAllFlightDetails implements Callable<ArrayList<FlightDetails>> {
	
	private String fileName;
	private static  String arrivingLocation;
	private static String departureLocation;
	private static String date;
	private static String flightClass;
	final String seatAvailable = "Y";
	
	
	FindAllFlightDetails(String fileName){
		this.fileName = fileName;
	}
	public static void setArrivingLocation(String location) {
		arrivingLocation = location;
	}
	
	public static void setFlightClass(String Class) {
		flightClass = Class;
	}
	
	public static void setDepartureLocation(String location) {
		departureLocation = location;
	}
	
	public static void setDate(String date) {
		FindAllFlightDetails.date = date;
	}
	
	public ArrayList<FlightDetails> findFlights() throws IOException, ParseException {
		String input = null;
		ArrayList<FlightDetails> flightList = new ArrayList<>();
		
		int count =0;
		
	
		try(BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\shrishtigupta\\Downloads\\Advanced java Material\\Assignment Links\\Assignment Links"+"\\"+fileName))){
			while((input =reader.readLine())!=null) {
				if(count!=0) {
				String[] flightDetails = input.split("\\|");
				String flightNo = flightDetails[0];
				
				String departure = flightDetails[1];
				
				String arriving = flightDetails[2];
				String flightdate = flightDetails[3];
				
				String flightTime = flightDetails[4];
				String duration = flightDetails[5];
				float fare = Float.parseFloat(flightDetails[6]);
				SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
				
				if(arriving.equalsIgnoreCase(arrivingLocation) && departure.equalsIgnoreCase(departureLocation) && (d.parse(flightdate).compareTo(d.parse(date))>=0)) {
					
					if(flightDetails[8].contains(flightClass) && flightDetails[7].equalsIgnoreCase(seatAvailable)) {
						
						
						FlightDetails flight = new FlightDetails(flightNo,departure,arriving,duration,fare,flightTime);
						flight.setFareClass();
						flightList.add(flight);
					}
				}
			}
				count=count+1;
		}
			
		}
		return flightList;
		
	}
	public ArrayList<FlightDetails> call() throws IOException,ParseException{
		return findFlights();
	}

}
