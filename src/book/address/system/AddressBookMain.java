package book.address.system;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program!");
		Scanner scanner = new Scanner(System.in);
		AddressBook book1 = new AddressBook();

		char choice = ' ';
		while (true) {

			if (choice == 'x')
				break;
			
			System.out.println("Press '1' to add new Contact!");
			System.out.println("Press '2' to edit existing Contact!");
			System.out.println("Press '3' to view Contacts in list!");
			System.out.println("Press '4' to delete Contact!");
			System.out.println("Press 'x' to exit program!");
			System.out.print("Enter your choice: ");
			choice = scanner.next().charAt(0);

			System.out.println();
			switch(choice) {
			case '1':
				book1.addPerson();
				break;
			case '2':
				book1.updatePerson();
				break;
			case '3':
				System.out.print(book1);
				break;
			case '4':
				book1.deletePerson();
				break;
			case 'x':
				System.out.println("Exiting Program!");
				continue;
			default:
				System.out.println("Invalid Input: Please choose correct option!");
			}
			System.out.println();

		}

		scanner.close();
	}
}
