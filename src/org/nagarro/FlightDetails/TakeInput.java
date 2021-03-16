package org.nagarro.FlightDetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sun.xml.internal.ws.message.stream.StreamAttachment; 

class TakeInput {

	public static void main(String[] args) {
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
		try {
			
			System.out.println("Enter departure Location");
			String departureLocation = reader.readLine();
			System.out.println("Enter arrival location");
			String arrivingLocation = reader.readLine();
			System.out.println("Enter flight date");
			String flightdate = reader.readLine();
			
			System.out.println("Enter Class E or B");
			String flightClass = reader.readLine();
			System.out.println("Enter output prefernece by which you want to sort date 1).BY fare 2.By Both fare and Duration");
			String outputpreference = reader.readLine();
			
			
			FlightDetails.setflightClass(flightClass);
			FindAllFlightDetails.setArrivingLocation(arrivingLocation);
			FindAllFlightDetails.setDepartureLocation(departureLocation);
			FindAllFlightDetails.setDate(flightdate);
			FindAllFlightDetails.setFlightClass(flightClass);
			
			File file = new File("C:\\Users\\shrishtigupta\\Downloads\\Advanced java Material\\Assignment Links\\Assignment Links");
			String[] fileNames = file.list();
			
			ExecutorService ex = Executors.newFixedThreadPool(3);
			
			Future<ArrayList<FlightDetails>>[] results = new Future[fileNames.length];
			
			
			for(int i =0;i<fileNames.length;i++) {
				FindAllFlightDetails obj1 = new FindAllFlightDetails(fileNames[i]);
				results[i]= ex.submit(obj1);
				
			}
			ArrayList<FlightDetails> flightFinalList = new ArrayList<>();
			
		
			for(Future<ArrayList<FlightDetails>> res : results)
				res.get().forEach(l -> flightFinalList.add(l));
			if(flightFinalList.size()!=0) {
				if(outputpreference.equalsIgnoreCase("fare")) {
				Collections.sort(flightFinalList,(f1,f2)->{
						return Float.compare(f1.getFare(),f2.getFare());
				})	;
				System.out.println("Sorted By fare");
				System.out.println("FGHT_NO  "+"DEP_LOC  "+"ARR_LOC  "+"FARE  "+"DURATION   "+"TIME");
				System.out.println("--------------------------------------------------------------");
				flightFinalList.forEach(l -> l.tostring());
				}
				else {
					
					System.out.println("\n"+"Sorted By  duration");
					System.out.println("FGHT_NO  "+"DEP_LOC  "+"ARR_LOC  "+"FARE  "+"DURATION   "+"TIME");
					System.out.println("--------------------------------------------------------------");
					flightFinalList.forEach(l -> l.tostring());
				}
			}
			else {
				System.out.println("NO FLIGHTS AVAILABLE ON " + flightdate+" FROM "+ departureLocation+" TO "+arrivingLocation);
				System.out.println("SORRY FOR INCONVINIENCE");
			}
			
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}	String[] a = new String[5];

		
}	
	


