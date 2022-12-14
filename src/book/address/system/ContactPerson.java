package book.address.system;

import org.json.simple.JSONObject;

public class ContactPerson implements Comparable<ContactPerson> {
	private String firstName, lastName, email, address, city, state;
	private int zip;

	private long phonNum;

	ContactPerson(String firstName, String lastName, String email, String address, String city, String state, int zip,
			long phonNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phonNum = phonNum;
	}

	public String getCity() {
		return city;
	}


	public String getState() {
		return state;
	}

	public Integer getZip() {
		return zip;
	}

	String getName() {
		return firstName + lastName;
	}
	
	public String toString() {
		return "Person Name: " + firstName + " " + lastName + "\nPerson Address: " + address + ", " + city + ", "
				+ state + "\nPerson Email: " + email + "\nPerson Postal Code: " + zip + "\nPerson Phone number: "
				+ phonNum + "\n\n";
	}	

	public boolean equals(ContactPerson person) {
		String name1 = getName();
		String name2 = person.firstName + person.lastName;
		if (name1.equals(name2)) {
			return true;
		}
		return false;
	}
	
	public int compareTo(ContactPerson person) {
		if (getName().compareTo(person.getName()) > 0)
			return 1;
		else if (getName().compareTo(person.getName()) < 0)
			return -1;
		else
			return 0;		
	}
	
	String[] getContactStrings() {
		return new String[] {firstName, lastName, email, address, city, state, zip + "", phonNum + ""};
	}
	
	JSONObject getContactJSON() {
		JSONObject jsonPerson = new JSONObject();
		jsonPerson.put("firstName", firstName);
		jsonPerson.put("lastName", lastName);
		jsonPerson.put("email", email);
		jsonPerson.put("address", address);
		jsonPerson.put("city", city);
		jsonPerson.put("state", state);
		jsonPerson.put("zip", zip);
		jsonPerson.put("phonNum", phonNum);
		
		JSONObject jsonPersonObject = new JSONObject();
		jsonPersonObject.put("person", jsonPerson);
		return jsonPersonObject;
	}
}
