package com.training.services;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Create Abstraction for Mobiles.....");
		MobileServices mobileService = new MobileImpl();
		
		String[] show=mobileService.showAll();
		for(String showMobile:show) {
			System.out.println(showMobile);
		}
		
		

	}

}
