package book.address.system;

import java.util.Scanner;

public class AddressBookMain {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program!");
		String option;
		Scanner scanner = new Scanner(System.in);
		
		AddressBook book1 = new AddressBook();
		while (true) {
			book1.addPerson();
			System.out.println("Do you wan't to add one more Person Contacts: [Yes|No]: ");
			option = scanner.next();
			
			if (option.equalsIgnoreCase("no"))
				break;
		}
		
		System.out.println(book1);
	}
}
