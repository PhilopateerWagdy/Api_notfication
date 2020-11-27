import java.util.ArrayList;

public class User {

	private static final User User = null;
	private String name;
	private String phoneNumber;
	private int userID;
	private String email;
	private String password;
	public ArrayList<Notification> notificationList=new ArrayList<>();
	private Templete templete;
	private NotificationManger manger = new CRUD();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTemplete(Templete templete) {
		this.templete = templete;
	}

	public void showItems() {

		for(int i = 0; i < templete.getItem().size(); i++ )
		{
			System.out.println(templete.getItemID().get(i)+"\t"+ templete.getItem().get(i)+"\t"+templete.getPrice().get(i));
		}

	}

	public void bookingItem(int itemId,Type type) {

		templete.callCreate(this, itemId,type);
	}

	public void callRead(int NID) {
		for (int i = 0; i < notificationList.size(); i++) {
			if (notificationList.get(i).getNotificationID()==NID) {
				System.out.println(manger.read(notificationList.get(i)));
				return;
			}
		}
		System.out.println("Wrong ID");
	}

	public void callDelete(int NID) {

		manger.delete(notificationList,NID);

	}

	public void showNotificationIDs() {

		for(int i = 0; i < notificationList.size(); i++)
		{
			System.out.println(notificationList.get(i).getNotificationID()+"\t"+notificationList.get(i).getContent().getType());
		}
		System.out.println("\n");
	}

}