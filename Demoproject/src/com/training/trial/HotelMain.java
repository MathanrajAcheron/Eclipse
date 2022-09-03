package com.training.trial;

import java.util.List;
import java.util.Scanner;



public class HotelMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hotel Management...");
		Scanner sc=new Scanner(System.in);
		System.out.println("1.SouthIndian\t2.Chinese\t3.NorthIndian\n");
		String selectTheTypeOfFood=sc.next();
		HotelService hotelService=new HotelServiceImpl();
		List<String> FoodIdems=hotelService.showItems(selectTheTypeOfFood);
		for(String fooditem:FoodIdems) {
			System.out.println(fooditem);
		}
		

	}

}
