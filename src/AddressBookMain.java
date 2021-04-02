import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
	static public Map<String, AddressBookVo> addressBk = new HashMap<String, AddressBookVo>();
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		
		Boolean loopController = true;
		while(loopController) {
			Scanner stdin = new Scanner(System.in);
			System.out.println("Enter your choice");
			int choice = stdin.nextInt();
			switch (choice) {
			case 1:
				createNewContact(stdin);
				

			default:
				loopController = false;
				break;
			}
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
