package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginUserApplication {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		String username;
		String name;
		String password;
		String[] userString = new String[4];
		UserService userService = new UserService();
		int attemptsRemaining = 5;
		boolean validatedUser = false;
		
		
		User Persons[] = new User[4];
		
		
		username = getUsername();
		password = getPassword();
		
		userString = readData();
		
		for(int i=0; i<userString.length; i++ ) {
			String[] userArray = userString[i].split(",");
				Persons[i] = userService.createUser(userArray[0], userArray[1], userArray[2]);
		}
		validatedUser = checkUser(Persons, username, password);
		while (attemptsRemaining >1 && validatedUser == false) {
			
			if (validatedUser == true) {
				name=getName(Persons, username);
				System.out.println("Welcome, " + name);

			} else if(attemptsRemaining >0){
				System.out.println("Invalid username/password. Please try again.");
				username = getUsername();
				password = getPassword();
				attemptsRemaining--;
				validatedUser = checkUser(Persons, username, password);
				}
			}

		if (attemptsRemaining == 1 && validatedUser==false) {

			System.out.println("Too many failed login attempts, you are now locked out.");
		}


	

		scan.close();
	}
	
	private static String getName(User Persons[], String username) {
		String name="";
		for(int k=0; k<Persons.length; k++) {
			if (Persons[k].getUsername().equalsIgnoreCase(username)) {
				name= Persons[k].getName();
				}
		}
		return name;
	}
	
	
	private static boolean checkUser(User Persons[], String username, String password) {
		boolean validatedUser = false;
		for (int j=0; j<Persons.length; j++) {
			if (Persons[j].getUsername().equalsIgnoreCase(username)){
				if (Persons[j].getPassword().equals(password)) {
					System.out.println("Welcome:" + Persons[j].getName());
					return true;	
				} else {
					return false;
				}
				
			} else {
				validatedUser = false;
						}
		}
		return validatedUser;
	}
	private static String getUsername() {
		String username;
		System.out.println("Please enter your username:");
		username =scan.nextLine();
		return username;
	}
	private static String getPassword() {
		String password;
		System.out.println("Please enter your password:");
		password =scan.nextLine();		
		return password;
	}
	
	private static String[] readData() {
		String[] userString = new String[4];
		BufferedReader fileReader = null;
		
		try {
		
			fileReader = new BufferedReader(new FileReader("data.txt"));
			
			String line;
			int counter = 0;
			while ((line = fileReader.readLine()) != null) {
				userString[counter] = line;
			
				counter++;
				}
			} catch (FileNotFoundException e) {
			System.out.println("Oops, file not found");
			e.printStackTrace();
		} catch (IOException e) {
				System.out.println("Oops, there was an I/O exception.");
				e.printStackTrace();
			} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		
		return userString;
	}
	
	


}
