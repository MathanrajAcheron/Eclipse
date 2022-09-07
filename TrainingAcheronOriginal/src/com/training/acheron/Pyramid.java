package com.training.acheron;

import java.util.Scanner;

public class Pyramid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pyramidCreation;
		Scanner sc = new Scanner(System.in);
		pyramidCreation = sc.nextInt();
		for (int i = 1; i <= pyramidCreation; i++) {
			for (int j = pyramidCreation-i; j > 0; j--) {
				System.out.print(" ");
			}
			for (int k = 1; k <= i; k++) {
				System.out.print(i+" ");
			}
			System.out.println(" ");

		}

	}

}
