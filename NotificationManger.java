import java.util.ArrayList;

public interface NotificationManger {

	/**
	 * 
	 * @param data
	 */
	Notification create(String[] data,Content content);

	/**
	 * 
	 * @param notification
	 */
	String read(Notification notification);

	/**
	 * 
	 * @param notification
	 * @param data
	 */
	Notification update(Notification notification, String[] data);

	/**
	 * 
	 * @param notfications
	 * @param ID
	 */
	void delete(ArrayList<Notification> notfications, int ID);

}