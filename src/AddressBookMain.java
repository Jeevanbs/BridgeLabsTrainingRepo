import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AddressBookMain {
	static public Map<String, AddressBookVo> addressBk = new HashMap<String, AddressBookVo>();

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Boolean loopController = true;
		while(loopController) {
			Scanner stdin = new Scanner(System.in);
			System.out.println("Enter your choice");
			System.out.println("1. Create New Contact 2. Edit existing contact 3. view all contact");
			int choice = stdin.nextInt();
			switch (choice) {
			case 1:
				createNewContact(stdin);
				break;

			case 2:
				editExistingContact(stdin);
				break;
				
			case 3:
				viewAddressBook();
				break;
			
			case 4:
				deleteContact(stdin);
				break;
			
			case 5:
				createMultipleContact(stdin);
				break;

			default:
				loopController = false;
				break;
			}
		}
	}

	
	private static void createMultipleContact(Scanner stdin) {
		System.out.println("Enter Number of contacts to be created");
		int count = stdin.nextInt();
		IntStream.range(0, count).forEach(contact -> {
			createNewContact(stdin);
		});
	}


	private static void deleteContact(Scanner stdin) {
		System.out.println("Enter First Name to delete the record");
		String firstName = stdin.next();
		if(addressBk.containsKey(firstName)) {
			addressBk.remove(firstName);
		}else {
			System.out.println("Firstname doesn't exist - " + firstName);
		}
		
	}

	private static void viewAddressBook() {
		addressBk.forEach((firstName, vo )-> System.out.println( firstName + " --- " + vo));
	}

	private static void editExistingContact(Scanner stdin) {
		System.out.println("Enter First Name ");
		String firstName = stdin.next();
		if(addressBk.containsKey(firstName)) {
			AddressBookVo vo = addressBk.get(firstName);
			System.out.println("Enter the choice of info to update : 1.LastName 2.Address 3.City 4.State 5.pincode 6.Phone Number 7.Email ");
			int choice = stdin.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the LastName to be updated");
				String lastName = stdin.next();
				vo.setLastName(lastName);
				break;
			case 2:
				System.out.println("Enter the Address to be updated");
				String address = stdin.next();
				vo.setAddress(address); 
				break;
			case 3:
				System.out.println("Enter the City to be updated");
				String city = stdin.next();
				vo.setCity(city);
				break;	
			case 4:
				System.out.println("Enter the State to be updated");
				String state = stdin.next();
				vo.setState(state);
				break;
			case 5:
				System.out.println("Enter the pincode to be updated");
				Long pincode = stdin.nextLong();
				vo.setPincode(pincode);
				break;
			case 6:
				System.out.println("Enter the Phone Number to be updated");
				Long phoneNo = stdin.nextLong();
				vo.setPhoneNumber(phoneNo);
				break;
			case 7:
				System.out.println("Enter the LastName to be Email");
				String email = stdin.next();
				vo.setEmail(email);
				break;
			default:
				break;
			}

		}else {
			System.out.println("Contact Doesn't exists");
		}
	}

	private static void createNewContact(Scanner stdin) {
		AddressBookVo vo = new AddressBookVo();
		System.out.println("Enter First Name ");
		String firstName = stdin.next();
		vo.setFirstName(firstName);
		System.out.println("Enter Last Name ");
		String lastName = stdin.next();
		vo.setLastName(lastName);
		System.out.println("Enter Address ");
		String address = stdin.next();
		vo.setAddress(address);
		System.out.println("Enter City ");
		String city = stdin.next();
		vo.setCity(city);
		System.out.println("Enter State ");
		String state = stdin.next();
		vo.setState(state);
		System.out.println("Enter pincode ");
		Long pincode = stdin.nextLong();
		vo.setPincode(pincode);
		System.out.println("Enter Phone Number ");
		Long phoneNo = stdin.nextLong();
		vo.setPhoneNumber(phoneNo);
		System.out.println("Enter Email ");
		String email = stdin.next();
		vo.setEmail(email);
		addressBk.put(firstName, vo);
		System.out.println("Contact Created with name : " + vo);
	}

}
