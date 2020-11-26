public class User {

	private string name;
	private string phoneNumber;
	private int userID;
	private string email;
	private string password;
	private ArrayList<Notification> notificationList;
	private Templete templete;
	private NotificationManger manger;

	public string getName() {
		return this.name;
	}

	public void setName(string name) {
		this.name = name;
	}

	public string getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(string phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public string getEmail() {
		return this.email;
	}

	public void setEmail(string email) {
		this.email = email;
	}

	public string getPassword() {
		return this.password;
	}

	public void setPassword(string password) {
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
	public void rqstChngPW(string password) {
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

}