import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Templete templete=new Templete();
	private static  ArrayList<User> users=new ArrayList<>();
	private static int userID = 0;

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public static int signIn(String email, String password) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(email)&&users.get(i).getPassword().equals(password)) {
				users.get(userID-1).setTemplete(templete);
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @param password
	 */
	public static void signUp(String name, String phoneNumber, String email, String password,Type type) {
		User temp =new User();
		temp.setName(name);
		temp.setPhoneNumber(phoneNumber);
		temp.setEmail(email);
		temp.setPassword(password);
		temp.setUserID(userID++);
		users.add(temp);
		users.get(userID-1).setTemplete(templete);
		int activationMsg =-1;
		templete.callCreate(users.get(userID-1),activationMsg,type);
	}

	/**
	 * 
	 * @param sendingType
	 */
	public static int forgetPW(String sendingType,Type type,String newPW) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(sendingType)||users.get(i).getPhoneNumber().equals(sendingType)){
				users.get(i).setPassword(newPW);
				int forgetPWMsg = -2;
				templete.callCreate(users.get(i),forgetPWMsg,type);
				return i;
			}
		}
		return -1;
	}

	public static Type selectType(){
		while(true) {
			Type type;
			System.out.println("Enter type : \n1-sms\t2-mail");
			Scanner scanner=new Scanner(System.in);
			String input=scanner.nextLine();
			if (input.startsWith("1")){
				type= Type.sms;
				return type;
			}
			else if(input.startsWith("2")){
				type= Type.mail;
				return type;
			}
			else {
				System.out.println("Wrong input!!!");
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input =new String();
		int index= -1;
		while (true){
			System.out.println("1- Sign in");
			System.out.println("2- Sign up");
			System.out.println("3- Forget password");
			input=scanner.nextLine();
			if (input.startsWith("1")){
				System.out.print("Enter email : ");
				String email=scanner.nextLine();
				System.out.print("Enter Password : ");
				String password=scanner.nextLine();
				index =signIn(email,password);
			}
			else if (input.startsWith("2")){
				Type type ;
				System.out.print("Enter email : ");
				String email;
				email=scanner.nextLine();
				System.out.print("Enter name : ");
				String name;
				name=scanner.nextLine();
				System.out.print("Enter Password : ");
				String password=scanner.nextLine();
				System.out.print("Enter Phone number : ");
				String phoneNumber=scanner.nextLine();
				type = selectType();
				signUp(name,phoneNumber,email,password,type);
				index=userID-1;
			}
			else if(input.startsWith("3")){
				Type type;
				System.out.print("Please enter your Email or Phone Number : ");
				String sendType = scanner.nextLine();
				System.out.println("Please enter your new Password");
				String newPW = scanner.nextLine();

				type = selectType();
				index=forgetPW(sendType,type,newPW);
			}
			if (index==-1){
				System.out.println("Wrong Input");
				continue;
			}
			while (index!=-1){
				String choice1=new String();
				int choice=0;
				System.out.println("1- Show Items");
				System.out.println("2- Book Item");
				System.out.println("3- Change Item");
				System.out.println("4- Add Item");
				System.out.println("5- Show notification ids");
				System.out.println("6- Read notification");
				System.out.println("7- Delete notification");
				System.out.println("8- Sign out");

				choice1=scanner.nextLine();
				switch(choice1.charAt(0)) {
					case '1':{
						users.get(index).showItems();
						break;
					}
					case '2':{
						System.out.println("Enter item Id");
						choice1= scanner.nextLine();
						choice =Integer.parseInt(choice1);
						Type type=selectType();
						users.get(index).bookingItem(choice,type);
						break;
					}
					case '3' :{
						System.out.println("Enter item Id");
						choice1= scanner.nextLine();
						choice =Integer.parseInt(choice1);

						templete.callUpdate(users.get(index),users.get(index).notificationList.get(users.get(index).notificationList.size()-1).getNotificationID(),choice);
						break;
					}
					case '4':{
						System.out.println("Enter item name : ");
						input =scanner.nextLine();
						System.out.println("Enter item price : ");
						choice1= scanner.nextLine();
						int price = Integer.parseInt(choice1);
						templete.addItem(input,price);
						users.get(index).setTemplete(templete);
						break;
					}
					case '5':{
						users.get(index).showNotificationIDs();
						break;
					}
					case '6':{
						System.out.println("Enter Notification ID : ");
						choice1= scanner.nextLine();
						choice =Integer.parseInt(choice1);
						users.get(index).callRead(choice);
						break;
					}
					case '7':{
						System.out.println("Enter Notification ID : ");
						choice1= scanner.nextLine();
						choice =Integer.parseInt(choice1);
						users.get(index).callDelete(choice);
						break;
					}
					case '8':{
						index=-1;
						break;
					}
					default:{
						System.out.println("Wrong Input!!!");
						break;
					}
				}
			}
		}
	}
}