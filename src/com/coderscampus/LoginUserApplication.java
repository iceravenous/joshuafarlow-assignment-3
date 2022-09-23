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
		while (attemptsRemaining >= 1 && validatedUser == false ) {
			attemptsRemaining--;
			validatedUser = checkUser(Persons, username, password);
			if (validatedUser == false) {
				
				System.out.println("Invalid login, please try again.");
				username = getUsername();
				password = getPassword();
			}
		}
		if (validatedUser == false) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
		

	
/* TODO:
 *  1-create POJO <<<DONE>>>>
 *  
 *  2-load .CSV into array of strings <<<<DONE>>>>
 *  2b - convert array to objects using split <<<<<DONE>>>>>
 *  3-Collect username and password via scanner  <<<<DONE>>>>
 *  4 - match username (case insensitive) to .CSV information
 *  5 - match password (case sensitive) to .CSV information
 *  6. Display of there is no match, or if there is a match. Also lock user out after 5th invalid attempt. 
 * 
 */

		scan.close();
	}
	
	private static boolean checkUser(User Persons[], String username, String password) {
		boolean validatedUser = true;
		for (int j=0; j<Persons.length; j++) {
			if (Persons[j].getUsername().equalsIgnoreCase(username)){
				validatedUser = true;
				if (Persons[j].getPassword().equals(password)) {
					System.out.println("Welcome:" + Persons[j].getName());
					return validatedUser;	
				} else {
					validatedUser = false;
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
				//System.out.println(userString[counter]);
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
