import java.util.ArrayList;

public class User {

	private String name;
	private String phoneNumber;
	private int userID;
	private String email;
	private String password;
	private ArrayList<Notification> notificationList;
	private Templete templete;
	private NotificationManger manger;

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
		// TODO - implement User.showItems
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param itemId
	 */
	public void bookingItem(int itemId) {
		// TODO - implement User.bookingItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public void rqstChngPW(String password) {
		// TODO - implement User.rqstChngPW
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NID
	 */
	public void callRead(int NID) {
		// TODO - implement User.callRead
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NID
	 */
	public void callDelete(int NID) {
		// TODO - implement User.callDelete
		throw new UnsupportedOperationException();
	}

	public void showNotificationIDs() {
		// TODO - implement User.showNotificationIDs
		throw new UnsupportedOperationException();
	}

}