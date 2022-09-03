package com.training.trial;

import java.util.ArrayList;
import java.util.*;

public class HotelServiceImpl extends HotelService {

	@Override
	public List<String> showItems(String cuisine) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		if (cuisine.equalsIgnoreCase("S")) {
			result.add("idle");
			result.add("dosa");
		} else if (cuisine.equalsIgnoreCase("C")) {
			result.add("Pizza");
			result.add("noodles");
		} else if (cuisine.equalsIgnoreCase("N")) {
			result = Arrays.asList("Chappathi", "Alu purata");

		} else {
			result = Arrays.asList("Cakes", "Hot chips");
		}

		return result;
	}

}
