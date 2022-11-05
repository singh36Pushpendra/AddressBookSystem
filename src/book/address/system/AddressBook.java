package book.address.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddressBook {

	private Scanner scanner = new Scanner(System.in);
	private String fname, lname, mail, address, city, state;
	private int zip, i;
	private long phone;
	private ArrayList<ContactPerson> persons;
	private int count;
	private boolean checkEquality;
	private HashMap<String, ContactPerson> statePersonMap, cityPersonMap;

	public AddressBook() {

		persons = new ArrayList<>();
		statePersonMap = new HashMap<String, ContactPerson>();
		cityPersonMap = new HashMap<String, ContactPerson>();
	}

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
		System.out.println("Enter Person " + (count + 1) + " Details:");
		inputNames();
		inputContacts();
		ContactPerson person = new ContactPerson(fname, lname, mail, address, city, state, zip, phone);

		List<ContactPerson> duplicate = persons.stream().filter(prsn -> prsn.equals(person))
				.collect(Collectors.toList());

		if (duplicate.toString().equals("[]")) {
			count++;
			persons.add(person);
			statePersonMap.put(state, person);
			cityPersonMap.put(city, person);
		} else
			System.out.println("Duplicate Name: Can't add person details!");

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
			statePersonMap.remove(state);
			cityPersonMap.remove(city);

			inputContacts();
			ContactPerson person = new ContactPerson(fname, lname, mail, address, city, state, zip, phone);
			persons.set(i, person);

			statePersonMap.put(state, person);
			statePersonMap.put(city, person);
			System.out.println("Person " + (i + 1) + " Contact updated successfully!");
		} else
			System.out.println("No match available!");
	}

	void deletePerson() {
		inputNames();
		checkEquality = areNamesEqual();
		if (checkEquality) {
			count--;
			persons.remove(i);
			statePersonMap.remove(state);
			cityPersonMap.remove(city);

			System.out.println("Person " + (i + 1) + " Contact removed successfully!");
		} else
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

	void viewAddrBook(String... stateCity) {
		String state = stateCity[0];

		Predicate<ContactPerson> predicatePerson;

		System.out.println("\n-------------------------------------------------------");

		try {
			System.out.println("State Name: " + stateCity[0]);
			System.out.println("City Name: " + stateCity[1]);
			String city = stateCity[1];

			predicatePerson = person -> person.getState().equals(state) && person.getCity().equals(city);

		} catch (ArrayIndexOutOfBoundsException aioobe) {
			predicatePerson = person -> person.getState().equals(state);

		}

		persons.stream().filter(predicatePerson).forEach(prsn -> System.out.print("\n" + prsn + "\n"));
	}

	void viewPersons(String... stateCity) {
		String state = stateCity[0];

		System.out.println("\n-------------------------------------------------------");
		Predicate<String> predicate;
		try {
			System.out.println("State Name: " + stateCity[0]);
			System.out.println("City Name: " + stateCity[1]);
			String city = stateCity[1];

			predicate = personCity -> personCity.equals(city);

			cityPersonMap.keySet().stream().filter(predicate)
					.forEach(prsnKey -> System.out.print("\n" + cityPersonMap.get(prsnKey) + "\n"));

		} catch (ArrayIndexOutOfBoundsException aioobe) {
			
			predicate = personState -> personState.equals(state);
			statePersonMap.keySet().stream().filter(predicate)
					.forEach(prsnKey -> System.out.print("\n" + statePersonMap.get(prsnKey) + "\n"));
			
		}
	}
}
