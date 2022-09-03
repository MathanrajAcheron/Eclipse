package com.training.services;

public class MobileImpl extends MobileServices {

	public MobileImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] showAll() {
		String[] allPhones= {"jio","vivo","mi"};
		return allPhones;
	}

}
