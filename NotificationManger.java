public interface NotificationManger {

	/**
	 * 
	 * @param data
	 */
	Notification create(string[] data);

	/**
	 * 
	 * @param notification
	 */
	string read(Notification notification);

	/**
	 * 
	 * @param notification
	 * @param data
	 */
	Notification update(Notification notification, string[] data);

	/**
	 * 
	 * @param notfications
	 * @param ID
	 */
	void delete(ArrayList<Notification> notfications, int ID);

}