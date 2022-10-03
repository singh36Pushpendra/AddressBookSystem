package book.address.system;

import java.util.Scanner;

public class AddressBook {

	private Scanner scanner = new Scanner(System.in);
	private String fname, lname, mail, address, city, state;
	private int zip;
	private long phone;
	private ContactPerson[] persons = new ContactPerson[2];
	private static int count;
	private ContactPerson[] copyPersons;
	
	void addPerson() {
		System.out.println("Enter Person " + (count + 1) + " Details:");
        System.out.println("Enter first name: ");
        fname = scanner.next();
        System.out.println("Enter last name: ");
        lname = scanner.next();
        System.out.println("Enter email: ");
        mail = scanner.next();
        scanner.nextLine();
        System.out.println("Enter address: ");
        address = scanner.nextLine();
        System.out.println("Enter city: ");
        city = scanner.next();
        scanner.nextLine();
        System.out.println("Enter state: ");
        state = scanner.nextLine();
        System.out.println("Enter postal(zip) code: ");
        zip = scanner.nextInt();
        System.out.println("Enter phone number: ");
        phone = scanner.nextLong();
        
        persons[count] = new ContactPerson(fname, lname, mail, address, city, state, zip, phone);
        
        if (count == persons.length - 1) {
        	copyPersons = new ContactPerson[persons.length + 4];
        	
        	for (int i = 0; i < persons.length; i++)
        		copyPersons[i] = persons[i];
        	
        	persons = copyPersons;
        	copyPersons = null;
        }
        count++;
	}
	
	public String toString() {
		String personsData = "";
		for (int i = 0; i < count; i++) {
			personsData += "Person " + (i + 1) + " Details:\n";
			personsData += persons[i];
		}
		return personsData;
	}
}
