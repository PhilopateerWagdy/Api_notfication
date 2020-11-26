public class Templete {

	private ArrayList item;
	private ArrayList itemID;
	private ArrayList price;
	private NotificationManger notificationManger;
	private static int ID = 0;

	public ArrayList getItem() {
		return this.item;
	}

	public ArrayList getItemID() {
		return this.itemID;
	}

	public ArrayList getPrice() {
		return this.price;
	}

	public NotificationManger getNotificationManger() {
		return this.notificationManger;
	}

	public void setNotificationManger(NotificationManger notificationManger) {
		this.notificationManger = notificationManger;
	}

	/**
	 * 
	 * @param itemName
	 * @param price
	 */
	public void addItem(string itemName, int price) {
		// TODO - implement Templete.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param itemID
	 */
	public void callCreate(User user, int itemID) {
		// TODO - implement Templete.callCreate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param notificationId
	 * @param itemID
	 */
	public void callUpdate(User user, int notificationId, int itemID) {
		// TODO - implement Templete.callUpdate
		throw new UnsupportedOperationException();
	}

}