package com.voterapp.service;

import com.voterapp.exception.*;

public class ElectionBooth {
	
	boolean checkAge(int age) throws UnderAgeException{
		boolean flag=true;
		if(age<18) {
			throw new UnderAgeException("Age is less than 18....");
		}
		return flag;
		
	}
	
	boolean checkLocality(String locality) throws LocalityNotFoundException{
		String localalities[]= {"AK nagar","Banana nagar","Candle nagar","Deer nagar","MK nagar"};
		
		boolean flag=false;
		for(String nlocal:localalities) {
			if(nlocal.equalsIgnoreCase(locality)) {
				flag=true;
				break;
			}
		}
		if(!flag) {
			throw new LocalityNotFoundException("Locality not Found.....");
		}
		return flag;
	}
	boolean checkVoterID(int vid) throws NoVoterIDException{
		boolean flag=false;
		if(vid>=1000&&vid<=9000) {
			flag=true;
			
		}else {
			
			throw new NoVoterIDException("VoterID is not found.....");
		}
		return flag;
		
	}
	public boolean checkEligibility(int age,String locality,int vid) throws NotEligibleException,LocalityNotFoundException,NoVoterIDException, UnderAgeException{
		boolean flag=false;
		try{
			boolean localage=checkAge(age);
		boolean locallocality=checkLocality(locality);
		boolean localvoterid=checkVoterID(vid);
		if(localage&&locallocality&&localvoterid) {
			flag=true;///locality is not working
		}
		/*
		 * if(!localage) { throw new UnderAgeException("Age is less than 18...."); }
		 * if(!locallocality) { throw new
		 * LocalityNotFoundException("Locality not Found.....");//it is not working }
		 * if(!localvoterid) { throw new
		 * NoVoterIDException("VoterID is not found....."); }
		 */
//		if(!localage||!locallocality||!localvoterid){
//			
//		}}
		}
		catch(Exception e) {
			throw e;
		}
		return flag;
		
	}
}
