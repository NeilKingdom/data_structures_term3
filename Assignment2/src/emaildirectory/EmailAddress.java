package emaildirectory;
import java.util.Scanner;
/***********************************************************************************
Class:  EmailAddress
Purpose:  This class will model the data and actions needed for an email address data type
@author :   Anu Thomas
@version : 1.0
Course:   CST8130 - Data Structures     	          
*************************************************************************************/

public class EmailAddress {
	
	/** String - hold the value of a valid email address */
	private String emailAddress;
	
	/** no-arg constructor - set empty string field */
	public EmailAddress() {
		emailAddress = "";
	}
	
	/** parameterized constructor - sets object to String parameter emailAddress if valid  */
	public EmailAddress(String email){
		if(email.contains("@") && email.contains(".") && email.length()>7)
			emailAddress = email;
	}
	
	/**
	 * This method returns the data of the emailAddress field
	 * @return emailAddress email
	 */
	public String toString(){
		return emailAddress;
	}
	
	/**
	 * This method reads in valid email address from Scanner
	 * @param input Scanner object
	 */
	public void readAddress(Scanner input) {
		
		System.out.println("Enter valid email address: ");
		emailAddress = input.next();
		
		while(!emailAddress.contains("@") || !emailAddress.contains(".") || emailAddress.length() < 7){
			System.out.println("Enter valid email address.... it should contain @ and . and should be at least 7 characters long");
			emailAddress = input.next();
		}
	}
}
