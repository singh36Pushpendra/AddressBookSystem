package book.address.system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class AddressBookMain {

	public static void main(String[] args) {
		String addrBookName = "", stateName, cityName;
		System.out.println("Welcome to Address Book Program!");
		Scanner scanner = new Scanner(System.in);
		AddressBook addrBook;
		HashMap<String, AddressBook> addrBooks = new HashMap<String, AddressBook>();

		char superChoice = ' ', choice, viewChoice, countChoice, sortChoice;

		Comparator<ContactPerson> cityComparator = (prsn1, prs2) -> {
			if (prsn1.getCity().compareTo(prs2.getCity()) > 0)
				return 1;
			else if (prsn1.getCity().compareTo(prs2.getCity()) < 0)
				return -1;
			else
				return 0;
		};

		Comparator<ContactPerson> stateComparator = (prsn1, prs2) -> {
			if (prsn1.getState().compareTo(prs2.getState()) > 0)
				return 1;
			else if (prsn1.getState().compareTo(prs2.getState()) < 0)
				return -1;
			else
				return 0;
		};

		Comparator<ContactPerson> zipComparator = (prsn1, prs2) -> {
			if (prsn1.getZip().compareTo(prs2.getZip()) > 0)
				return 1;
			else if (prsn1.getZip().compareTo(prs2.getZip()) < 0)
				return -1;
			else
				return 0;
		};

		while (true) {

			if (superChoice == 'x')
				break;

			System.out.println("Enter '1' to add new Address Book!");
			System.out.println("Enter '2' to view existing Address Book");
			System.out.println("Enter 'x' to Exit Program!");
			System.out.println("Enter your choice:");
			superChoice = scanner.next().charAt(0);

			switch (superChoice) {
			case '1':
				scanner.nextLine();
				System.out.print("Enter New Address Book Name: ");
				addrBookName = scanner.nextLine();
				addrBooks.put(addrBookName, new AddressBook());
				choice = ' ';

				while (true) {

					if (choice == 'x')
						break;

					System.out.println("Press '1' to add new Contact!");
					System.out.println("Press '2' to edit existing Contact!");
					System.out.println("Press '3' to view Contacts in list!");
					System.out.println("Press '4' to delete Contact!");
					System.out.println("Press '5' to view Persons in State or City!");
					System.out.println("Press '6' to count Persons by city or state!");
					System.out.println("Press '7' to view sorted Persons by name!");
					System.out.println("Press '8' to view sorted Persons either by city, state or zip!");
					System.out.println("Press 'x' to exit current Address Book!");
					System.out.print("Enter your choice: ");
					choice = scanner.next().charAt(0);

					addrBook = addrBooks.get(addrBookName);
					System.out.println();
					switch (choice) {
					case '1':
						addrBook.addPerson();
						break;
					case '2':
						addrBook.updatePerson();
						break;
					case '3':
						System.out.println("\n-------------------------------------------------------");
						System.out.println("Name of Address Book: " + addrBookName + "\n");
						System.out.print(addrBook);
						System.out.println("-------------------------------------------------------");
						break;
					case '4':
						addrBook.deletePerson();
						break;
					case '5':
						scanner.nextLine();
						System.out.println("Enter '1' to view by State Names!");
						System.out.println("Enter '2' to view by City Names!");
						System.out.println("Enter your choice: ");
						viewChoice = scanner.next().charAt(0);

						System.out.println("\n-------------------------------------------------------");

						switch (viewChoice) {
						case '1':
							scanner.nextLine();
							System.out.println("Enter State Name: ");
							stateName = scanner.nextLine();
							addrBook.viewPersons(stateName);

							break;
						case '2':
							scanner.nextLine();
							System.out.println("Enter State Name: ");
							stateName = scanner.nextLine();

							System.out.println("Enter City Name: ");
							cityName = scanner.next();

							addrBook.viewPersons(stateName, cityName);
							break;

						default:
							System.out.println("Invalid: View Choice!");
						}

						System.out.println("-------------------------------------------------------");
						break;
					case '6':
						scanner.nextLine();
						System.out.println("Enter '1' to count by State Names!");
						System.out.println("Enter '2' to count by City Names!");
						System.out.println("Enter your choice: ");
						countChoice = scanner.next().charAt(0);

						System.out.println("\n-------------------------------------------------------");

						switch (countChoice) {
						case '1':
							scanner.nextLine();
							System.out.println("Enter State Name: ");
							stateName = scanner.nextLine();
							long countByState = addrBook.getCountByState(stateName);
							System.out.println(
									"Total no of Persons from state: " + stateName + "" + " = " + countByState);
							break;
						case '2':
							System.out.println("Enter City Name: ");
							cityName = scanner.next();
							long countByCity = addrBook.getCountByCity(cityName);
							System.out.println("Total no of Persons from city: " + cityName + "" + " = " + countByCity);
							break;
						default:
							System.out.println("Invalid: Count Choice!");
						}
						break;
					case '7':
						List<ContactPerson> sortedPersons = addrBook.sortPersons();
						System.out.println(sortedPersons);
						break;
					case '8':
						System.out.println("Press '1' to sort by city names!");
						System.out.println("Press '2' to sort by state names!");
						System.out.println("Press '3' to sort by zip names!");
						System.out.println("Enter your choice:");
						sortChoice = scanner.next().charAt(0);

						ArrayList<ContactPerson> persons = addrBook.getPersons();

						List<ContactPerson> sortedPrsns = null;
						switch (sortChoice) {
						case '1':
							sortedPrsns = persons.stream().sorted(cityComparator).collect(Collectors.toList());
							break;
						case '2':
							sortedPrsns = persons.stream().sorted(stateComparator).collect(Collectors.toList());
							break;
						case '3':
							sortedPrsns = persons.stream().sorted(zipComparator).collect(Collectors.toList());
							break;
						default:
							System.out.println("Invalid: Sort choice!");
						}
						System.out.println(sortedPrsns);
						break;
					case 'x':
						System.out.println("Exiting Current Address Book!\n");
						continue;
					default:
						System.out.println("Invalid Input: Please choose correct option!");
					}
					System.out.println();

				}
				break;
			case '2':
				scanner.nextLine();
				System.out.println("Enter Name of Address Book to view:");
				addrBookName = scanner.nextLine();
				addrBook = addrBooks.get(addrBookName);

				System.out.println("Enter '1' to view by State Names!");
				System.out.println("Enter '2' to view by City Names!");
				System.out.println("Enter your choice: ");
				viewChoice = scanner.next().charAt(0);

				System.out.println("\n-------------------------------------------------------");
				System.out.println("Name of Address Book: " + addrBookName + "\n");
				switch (viewChoice) {
				case '1':
					scanner.nextLine();
					System.out.println("Enter State Name: ");
					stateName = scanner.nextLine();
					addrBook.viewAddrBook(stateName);
					break;
				case '2':
					scanner.nextLine();
					System.out.println("Enter State Name: ");
					stateName = scanner.nextLine();

					System.out.println("Enter City Name: ");
					cityName = scanner.next();

					addrBook.viewAddrBook(stateName, cityName);
					break;
				default:
					System.out.println("Invalid: View Choice!");
				}

				System.out.println("-------------------------------------------------------");
				break;
			case 'x':
				System.out.println("Exiting Program!");
				continue;
			default:
				System.out.println("Invalid: Please enter correct choice!");
			}
		}

		Path path = Paths.get("C:/Users/micro/Downloads/address-book-data.txt");
		try {
			Files.deleteIfExists(path);
			Files.write(path,
					addrBooks.keySet().stream().map(key -> addrBooks.get(key).toString()).collect(Collectors.toList()),
					StandardOpenOption.CREATE);

			List<String> readAllLines = Files.readAllLines(path);
			readAllLines.stream().forEach(line -> System.out.println(line));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Writing CSV

		FileWriter fileWriter = null;

		String csvPath = "C:/Users/micro/Downloads/address-book-csv.csv";

		try {
			fileWriter = new FileWriter(csvPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CSVWriter writer = new CSVWriter(fileWriter);
		List<String[]> csvLines = new ArrayList<String[]>();

		addrBooks.keySet().stream().forEach(bookName -> addrBooks.get(bookName).getPersons()
				.stream().forEach(person -> csvLines.add(person.getContactStrings())));

		
		writer.writeAll(csvLines);

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Reading CSV

		FileReader fileReader = null;
		try {
			fileReader = new FileReader(csvPath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		CSVReader reader = new CSVReaderBuilder(fileReader).build();

		List<String[]> linesOfData = null;

		try {
			linesOfData = reader.readAll();
		} catch (IOException | CsvException e) {

			e.printStackTrace();
		}

		System.out.println("\nReading data from csv file:");
		linesOfData.stream().forEach(csvs -> {
			for (String value : csvs)
				System.out.print(value + "\t");
			System.out.println();
		});

		try {
			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		scanner.close();
	}
}
