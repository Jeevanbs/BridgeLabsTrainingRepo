import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddressBookMain {
	static public List<String> addressBookListNames = new LinkedList<String>();
	static public Map<String, Map<String, AddressBookVo>> addressBookMap = new HashMap<String, Map<String,AddressBookVo>>();

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		Map<String, AddressBookVo> addressBook = new HashMap<String, AddressBookVo>();
		addressBookMap.put("addressBook1",addressBook);
		addressBookListNames.add("addressBook1");

		Boolean loopController = true;
		while(loopController) {
			Scanner stdin = new Scanner(System.in);
			System.out.println("Enter your choice");
			System.out.println("1. Create New Contact 2. Edit existing contact 3. view all contact 4. Delete A contact 5. Create Multiple contacts"
					+ "6. Create New AddressBook");
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

			case 6:
				createNewAddressBook(stdin);
				break;

			case 7:
				searchAddrBkBasedOnCityOrState(stdin);
				break;

			default:
				loopController = false;
				break;
			}
		}
	}


	private static void searchAddrBkBasedOnCityOrState(Scanner stdin) {
		System.out.println("Enter City to be searched");
		String city = stdin.next();
		System.out.println("Enter State to be searched");
		String state = stdin.next();
		//Map<String, AddressBookVo> filteredMap = new HashMap<String, AddressBookVo>();
		
		addressBookListNames.forEach(addressBookName -> {
			Map<String, AddressBookVo>  addressBook = addressBookMap.get(addressBookName);
			Map<String, AddressBookVo> filteredMap = addressBook.entrySet().stream()
					.filter(a -> a.getValue().getCity().equals(city) || a.getValue().getState().equals(state))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			filteredMap.forEach((firstName, vo)-> System.out.println(firstName + " " + vo));
		});
	}


	private static void createNewAddressBook(Scanner stdin) {
		System.out.println("Enter AddressBook Name to be created");
		String addrBkName = stdin.next();
		if(addressBookListNames.contains(addrBkName)) {
			System.out.println("AddressBook already exists " + addrBkName);
		}else {
			addressBookListNames.add(addrBkName);
			System.out.println("Address Book Created - " + addrBkName);
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
		System.out.println("Select AddressBook in which contact needs to be edited " + addressBookListNames);
		AtomicInteger count = new AtomicInteger(1);
		addressBookListNames.forEach(addrBkList -> System.out.println(count.getAndIncrement() + " -- " + addrBkList));
		int selct = stdin.nextInt();
		String addressBkName = addressBookListNames.get(selct);
		Map<String, AddressBookVo>  addressBook = addressBookMap.get(addressBkName);

		System.out.println("Enter First Name to delete the record");
		String firstName = stdin.next();
		if(addressBook.containsKey(firstName)) {
			addressBook.remove(firstName);
			addressBookMap.put(addressBkName, addressBook);
		}else {
			System.out.println("Firstname doesn't exist - " + firstName);
		}

	}

	private static void viewAddressBook() {
		AtomicInteger count = new AtomicInteger(1);
		addressBookListNames.forEach(addressBookName -> {
			Map<String, AddressBookVo>  addressBook = addressBookMap.get(addressBookName);
			addressBook.forEach((firstName, vo )-> System.out.println( count.getAndIncrement() + " - " + firstName + " --- " + vo));
		});
	}

	private static void editExistingContact(Scanner stdin) {
		System.out.println("Select AddressBook in which contact needs to be edited " + addressBookListNames);
		AtomicInteger count = new AtomicInteger(1);
		addressBookListNames.forEach(addrBkList -> System.out.println(count.getAndIncrement() + " -- " + addrBkList));
		int selct = stdin.nextInt();
		String addressBkName = addressBookListNames.get(selct);
		Map<String, AddressBookVo>  addressBook = addressBookMap.get(addressBkName);
		System.out.println("Enter First Name ");
		String firstName = stdin.next();

		if(addressBook.containsKey(firstName)) {
			AddressBookVo vo = addressBook.get(firstName);
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
			addressBook.put(firstName, vo);
			addressBookMap.put(addressBkName, addressBook);
		}else {
			System.out.println("Contact Doesn't exists");
		}
	}

	private static void createNewContact(Scanner stdin) {
		System.out.println("Select AddressBook to which contact needs to be added " + addressBookListNames);
		AtomicInteger count = new AtomicInteger(1);
		addressBookListNames.forEach(addrBkList -> System.out.println(count.getAndIncrement() + " -- " + addrBkList));
		int selct = stdin.nextInt();
		String addressBkName = addressBookListNames.get(selct);
		Map<String, AddressBookVo>  addrBk = addressBookMap.get(addressBkName);

		AddressBookVo vo = new AddressBookVo();
		System.out.println("Enter First Name ");
		String firstName = stdin.next();
		if(!addrBk.containsKey(firstName)) {
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
			addrBk.put(firstName, vo);
			addressBookMap.put(addressBkName, addrBk);
			System.out.println("Contact Created with name : " + vo);
		}else {
			System.out.println("FirstName Already Exists Cannot Duplicate + " + firstName);
		}
	}

}
