package book.address.system;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

	private Scanner scanner = new Scanner(System.in);
	private String fname, lname, mail, address, city, state;
	private int zip, i;
	private long phone;
	private ArrayList<ContactPerson> persons = new ArrayList<>();
	private static int count;
	private boolean checkEquality;

	private void inputNames() {
		System.out.println("Enter first name: ");
		fname = scanner.next();
		System.out.println("Enter last name: ");
		lname = scanner.next();
	}

	private void inputContacts() {
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
	}

	void addPerson() {
		count++;
		System.out.println("Enter Person " + count + " Details:");
		inputNames();
		inputContacts();
		persons.add(new ContactPerson(fname, lname, mail, address, city, state, zip, phone));

	}

	private boolean areNamesEqual() {
		for (i = 0; i < persons.size(); i++)
			if (persons.get(i).getName().equals(fname + lname)) {
				System.out.println("Match found at position " + i);
				return true;
			}
		return false;
	}

	void updatePerson() {
		inputNames();
		checkEquality = areNamesEqual();
		if (checkEquality) {
			inputContacts();
			persons.set(i, new ContactPerson(fname, lname, mail, address, city, state, zip, phone));
			System.out.println("Person " + (i + 1) + " Contact updated successfully!");
		}
		else
			System.out.println("No match available!");
	}

	void deletePerson() {
		inputNames();
		checkEquality = areNamesEqual();
		if (checkEquality) {
			persons.remove(i);
			System.out.println("Person " + (i + 1) + " Contact removed successfully!");
		}
		else
			System.out.println("No match available!");
	}

	public String toString() {
		String personsData = "";
		for (i = 0; i < count; i++) {
			personsData += "Person " + (i + 1) + " Details:\n";
			personsData += persons.get(i);
		}
		return personsData;
	}
}
