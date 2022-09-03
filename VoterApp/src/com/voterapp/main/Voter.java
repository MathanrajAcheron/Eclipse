package com.voterapp.main;

import java.util.Scanner;

import com.voterapp.exception.LocalityNotFoundException;
import com.voterapp.exception.NoVoterIDException;
import com.voterapp.exception.NotEligibleException;
import com.voterapp.exception.UnderAgeException;
import com.voterapp.service.ElectionBooth;

public class Voter {

	public static void main(String[] args) throws LocalityNotFoundException,NotEligibleException,NoVoterIDException,UnderAgeException {
		Scanner sc=new Scanner(System.in);
		ElectionBooth booth=new ElectionBooth();
		System.out.println("Welcome to VoterApp Application");
		System.out.println("Connecting.....");
		try{
			System.out.println("Type Your Age As per in Aadhaar Card:");
			int age=sc.nextInt();
			System.out.println("Enter Your Locality As per in Aadhaar Card:");
			sc.nextLine();
			String locality=sc.nextLine();
			System.out.println("Enter Your VoterID Number As per in Voter Card:");
			int vid=sc.nextInt();
			boolean eligible=booth.checkEligibility(age, locality, vid);
			if(eligible) {
				System.out.println("Your are Eligible to Vote");
			}
			
		
		} 
		catch(UnderAgeException e) { System.out.println(e.getMessage()); }
		// | LocalityNotFoundException| NoVoterIDException|NotEligibleException
		catch(LocalityNotFoundException e) { System.out.println(e.getMessage()); }
		catch(NoVoterIDException e) { System.out.println(e.getMessage()); }
		catch(NotEligibleException e) { System.out.println(e.getMessage()); }
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			System.out.println("Thank you for Visiting our Application");
		}
		sc.close();

	}

}
